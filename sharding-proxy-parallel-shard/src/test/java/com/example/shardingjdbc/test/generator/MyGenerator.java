package com.example.shardingjdbc.test.generator;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Types;
import java.util.Collections;


@SpringBootTest
public class MyGenerator {

    @Test
    void contextLoads() {
        //最好不要在分片库去生成代码，找一个包含所有表的库来生成，不然会有各种问题。
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/test", "root", "admin")
                .globalConfig(builder -> {
                    builder.author("chenmengfei") // 设置作者
                            // 开启 swagger 模式 默认不开启
                            //.enableSwagger()
                            .outputDir(System.getProperty("user.dir")+"/src/main/java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    // 设置父包名
                    builder.parent("com.example.shardingjdbc.module")
                            // 设置父包模块名
                            .moduleName("order")
                            .serviceImpl("service.impl")
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir")+"/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude("t_order")
                    // 设置过滤表前缀
                    .addTablePrefix("t_", "c_");
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}