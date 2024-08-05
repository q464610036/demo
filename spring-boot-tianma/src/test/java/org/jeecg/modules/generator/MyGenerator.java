package org.jeecg.modules.generator;

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
    void test() {
        Object o = null;

    }

    @Test
    void contextLoads() {
        /**
         * oracle账号密码：system admin
         * mysql账号密码：admin admin
         */
        FastAutoGenerator.create("jdbc:oracle:thin:@//localhost:1521/orcl.168.175.1", "system", "admin")
                .globalConfig(builder -> {
                    builder
//                            .author("chenmengfei") // 设置作者
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
                    builder.parent("org.jeecg.modules")
                            // 设置父包模块名
                            .moduleName("equipment")
                            .serviceImpl("service.impl")
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir")+"/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder
//                            .addInclude("ct_tmos_equipment_template")
//                            .addInclude("ct_tmos_equipment_sub_unit")
//                            .addInclude("ct_tmos_equipment_item")
//                            .addInclude("ct_tmos_equipment_order")
//                            .addInclude("ct_tmos_equipment_order_item")
//                            .addInclude("ct_tmos_equipment_order_node")
//                            .addInclude("ct_tmos_equipment_incomplete")
//                            .addInclude("ct_tmos_equipment_temp_upkeep")
//                            .addInclude("ct_tmos_equipment_upkeep_in")
//                            .addInclude("ct_tmos_equipment_plan")

                            .addInclude("ct_tmos_equipment_borrow")
                            // 设置过滤表前缀
                            .addTablePrefix("CT_TMOS_");
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
