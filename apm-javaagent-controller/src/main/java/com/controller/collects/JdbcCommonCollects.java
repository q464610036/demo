package com.controller.collects;

import com.controller.AbstractCollects;
import com.controller.AgentLoader;
import com.controller.Collect;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * jdbc 数据采集
 * Created by tommy on 17/7/16.
 */
public class JdbcCommonCollects extends AbstractCollects implements Collect {
    public static final JdbcCommonCollects INSTANCE = new JdbcCommonCollects();

    private final static String[] connection_agent_methods = new String[]{"prepareStatement"};
    private final static String[] prepared_statement_methods = new String[]{"execute", "executeUpdate", "executeQuery"};

    private static final String beginSrc;
    private static final String endSrc;
    private static final String errorSrc;

    static {
        // connect
        beginSrc = "com.controller.collects.JdbcCommonCollects inst=com.controller.collects.JdbcCommonCollects.INSTANCE;";
        errorSrc = "inst.error(null,e);";
        endSrc = "result=inst.proxyConnection((java.sql.Connection)result);";
    }

    @Override
    public void sendStatistics(Statistics stat) {

    }

    @Override
    public boolean isTarget(String className, ClassLoader loader, CtClass ctclass) {
        //连接从driver开始，mysql的driver继承的NonRegisteringDriver，所以监听NonRegisteringDriver，重新他的connection方法
        if (className.equals("com.mysql.jdbc.NonRegisteringDriver")) {
            return true;
        }
        return false;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, byte[] classfileBuffer, CtClass ctclass) throws Exception {
        AgentLoader byteLoade = new AgentLoader(className, loader, ctclass);
        //重写他的connect方法，返回值是Connect，就需要对目标对象Connect进行动态代理
        CtMethod connectMethod = ctclass.getMethod("connect", "(Ljava/lang/String;Ljava/util/Properties;)Ljava/sql/Connection;");
//      connectMethod.getMethodInfo().getDescriptor();
        AgentLoader.MethodSrcBuild build = new AgentLoader.MethodSrcBuild();
        build.setBeginSrc(beginSrc);
        build.setErrorSrc(errorSrc);
        build.setEndSrc(endSrc);
        byteLoade.updateMethod(connectMethod, build);
        return byteLoade.toBytecote();
    }

    public Connection proxyConnection(final Connection connection) {
        Object c = Proxy.newProxyInstance(JdbcCommonCollects.class.getClassLoader()
                , new Class[]{Connection.class}, new ConnectionHandler(connection));
        return (Connection) c;
    }

    public PreparedStatement proxyPreparedStatement(final PreparedStatement statement, JdbcStatistics jdbcStat) {
        Object c = Proxy.newProxyInstance(JdbcCommonCollects.class.getClassLoader()
                , new Class[]{PreparedStatement.class}, new PreparedStatementHandler(statement, jdbcStat));
        return (PreparedStatement) c;
    }

    @Override
    public void end(Statistics stat) {
        JdbcStatistics jdbcStat= (JdbcStatistics) stat;
        if (jdbcStat.jdbcUrl != null) {
            jdbcStat.databaseName = getDbName(jdbcStat.jdbcUrl);
        }
        super.end(stat);
    }

    /**
     * connection 代理处理
     */
    public class ConnectionHandler implements InvocationHandler {
        //代理的目标对象
        private final Connection connection;

        private ConnectionHandler(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            boolean isTargetMethod = false;
            System.out.println(method.getName());
            for (String agentm : connection_agent_methods) {
                if (agentm.equals(method.getName())) {
                    isTargetMethod = true;
                }
            }
            Object result = null;
            JdbcStatistics jdbcStat = null;
            try {
                if (isTargetMethod) { // 获取PreparedStatement 开始统计
                    jdbcStat = new JdbcStatistics(JdbcCommonCollects.this.begin(null, null));
                    jdbcStat.jdbcUrl = connection.getMetaData().getURL();
                    jdbcStat.sql = (String) args[0];
                }
                result = method.invoke(connection, args);
                //代理 PreparedStatement
                if (isTargetMethod && result instanceof PreparedStatement) {
                    PreparedStatement ps = (PreparedStatement) result;
                    result = proxyPreparedStatement(ps, jdbcStat);
                }
            } catch (Throwable e) {
                e.printStackTrace();
                JdbcCommonCollects.this.error(jdbcStat, e);
                JdbcCommonCollects.this.end(jdbcStat);
                throw e;
            }
            return result;
        }
    }

    /**
     * PreparedStatement 代理处理
     */
    public class PreparedStatementHandler implements InvocationHandler {
        private final PreparedStatement statement;
        private final JdbcStatistics jdbcStat;

        public PreparedStatementHandler(PreparedStatement statement, JdbcStatistics jdbcStat) {
            this.statement = statement;
            this.jdbcStat = jdbcStat;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            boolean isTargetMethod = false;
            for (String agentm : prepared_statement_methods) {
                if (agentm.equals(method.getName())) {
                    isTargetMethod = true;
                }
            }
            Object result = null;
            try {
                result = method.invoke(statement, args);
            } catch (Throwable e) {
                if (isTargetMethod) {
                    JdbcCommonCollects.this.error(jdbcStat, e);
                }
                throw e;
            } finally {
                if (isTargetMethod) {
                    JdbcCommonCollects.this.end(jdbcStat);
                }
            }
            return result;
        }
    }

    public static class JdbcStatistics extends Statistics {
        // jdbc url
        public String jdbcUrl;
        // sql 语句
        public String sql;
        // 数据库名称
        public String databaseName;


        public JdbcStatistics() {

        }
        public JdbcStatistics(Statistics stat) {
            super(stat);
        }
    }

    private static String getDbName(String url) {
        int index = url.indexOf("?"); //$NON-NLS-1$
        if (index != -1) {
            String paramString = url.substring(index + 1, url.length());
            url = url.substring(0, index);
        }
        String dbName = url.substring(url.lastIndexOf("/") + 1);
        return dbName;
    }
}
