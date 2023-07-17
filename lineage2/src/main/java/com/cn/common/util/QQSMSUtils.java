package com.cn.common.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class QQSMSUtils {
    // 发送邮件的服务器地址，QQ服务器
    private static final String host = "smtp.qq.com";
    // 发件人邮箱
    private static final String fromEmail = "464610036@qq.com";
    // 邮箱授权码
    private static final String password = "zwnvslefcyqxbjec";
    //发件人用户名
    private static final String username = "464610036@qq.com";

    /**
     * 发送
     *
     * 1次发送不能超过10封(否则就只能发送十几条就挂掉了)
     * 1分钟内不能发送超过40封
     * 1天内不能发送超过100封
     *
     * @param toEmails
     * @param title
     * @param content
     * @throws Exception
     * @throws AddressException
     */
    public static void send(List<String> toEmails, String title, String content) throws Exception, AddressException {
        InternetAddress[] toEmailList = new InternetAddress[toEmails.size()];
        for (int i = 0; i < toEmails.size(); i++) {
            String toEmail = toEmails.get(i);
            toEmailList[i] = new InternetAddress(toEmail);
        }
        // 使用QQ邮箱时配置
        Properties prop = new Properties();
        // 设置QQ邮件服务器
        prop.setProperty("mail.host", "smtp.qq.com");
        // 邮件发送协议
        prop.setProperty("mail.transport.protocol", "smtp");
        // 需要验证用户名和密码
        prop.setProperty("mail.smtp.auth", "true");
        // 关于QQ邮箱，还要设置SSL加密，其他邮箱不需要
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        // 创建定义整个邮件程序所需的环境信息的 Session 对象，QQ才有，其他邮箱就不用了
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱用户名，授权码
                return new PasswordAuthentication(username, password);
            }
        });
        // 开启 Session 的 debug 模式，这样就可以查看程序发送 Email 的运行状态
        session.setDebug(true);
        // 通过 session 得到 transport 对象
        Transport ts = session.getTransport();
        // 使用邮箱的用户名和授权码连上邮箱服务器
        ts.connect(host, username, password);
        // 创建邮件，写邮件
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress(fromEmail));
        // 指明邮件的收件人
        message.setRecipients(Message.RecipientType.TO, toEmailList);
        // 邮件主题
        message.setSubject(title);
        // 邮件内容
        message.setContent(content, "text/html;charset=utf-8");
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        System.out.println("发送成功");
        // 释放资源
        ts.close();
    }
}
