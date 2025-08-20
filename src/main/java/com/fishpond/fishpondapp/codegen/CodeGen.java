package com.fishpond.fishpondapp.codegen;

import com.fishpond.fishpondapp.codegen.core.DataSourceConfig;
import com.fishpond.fishpondapp.codegen.core.TableLoader;
import com.fishpond.fishpondapp.codegen.gen.CodeGenerator;
import com.fishpond.fishpondapp.codegen.gen.GenConfig;

/**
 * @Author: Zhucj
 * @Description: TODO
 * @Date: 2025/8/20 14:57
 */
public class CodeGen {
    static DataSourceConfig dataSourceConfig = DataSourceConfig.builder()
            .url("jdbc:mysql://120.25.174.173:3309")
            .driver("com.mysql.cj.jdbc.Driver")
            .username("root")
            .password("Zhu19971028")
            .excludeColumns(new String[]{"create_time", "update_time"})
            .databaseName("fishpond_app")
            .tableName("admin_user")
            .build();

    static GenConfig genConfig = GenConfig.builder()
            .moduleName("fishpond-app")
            .packages(new String[]{"fishpondapp.business","admin"})//自动补全为：com.ruogu.cloud.[packages1].[packages2]
            .author("zhucj")
            .entityClassName("adminUser")// 未设置实体类名时，通过表名生成类名
            .controllerContainsDeleted(true)
            .build();

    public static void main(String[] args) {
        CodeGenerator.create(genConfig, TableLoader.load(dataSourceConfig))
                .generateEntity()
                .generateMapper()
//                .generateMapperXml(genConfig.getPackages()[0],genConfig.getPackages()[1])
                .generateService()
                .generateController()
                .generatePackages()
        ;
    }
}
