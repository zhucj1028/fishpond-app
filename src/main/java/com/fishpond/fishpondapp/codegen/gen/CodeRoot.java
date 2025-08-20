package com.fishpond.fishpondapp.codegen.gen;

import com.fishpond.fishpondapp.codegen.core.ColumnInfo;
import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.util.List;


@Data
@Builder
public class CodeRoot {
    /**类描述*/
    private String comment;
    /**作者*/
    private String author;
    /**版本*/
    private String version;
    /**表名称*/
    private String tableName;
    /**ID字段*/
    private ColumnInfo idColumn;
    /**其他字段*/
    private List<ColumnInfo> columns;
    /**实体类简称*/
    private String entityClassName;
    /**实体类变量名*/
    private String entityClassVarName;
    /**实体类字符串必填字段默认使用NotBlank注解，true则使用NotNull*/
    private boolean entityPreferNotNull;
    /**基础包名（controller包上级）*/
    private String basePackage;
    /**模块目录*/
    private File moduleDir;
    private int year;
    private String lineSeparator;
    private String tab;
    /**控制器Mapping名*/
    private String controllerMapping;
    /**控制器方法中加入deleted字段设置*/
    private boolean controllerContainsDeleted;
    /**控制器方法加入权限注解，此处设置服务名作为权限前缀，如：upms、danger等*/
    private String preAuthorize;

    /**业务接口导入包*/
    private String serviceImportPackages;
    /**业务接口注解*/
    private String serviceAnnotations;
}
