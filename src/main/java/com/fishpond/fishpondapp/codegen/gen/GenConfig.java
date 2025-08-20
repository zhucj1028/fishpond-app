/*
 * 文件名：GenConfig.java
 * Copyright © 2011-2023 重庆若谷信息技术有限公司
 */

package com.fishpond.fishpondapp.codegen.gen;

import lombok.Builder;
import lombok.Data;

/**
 * @author zuojunxin
 * @since 2023.0.0
 */
@Data
@Builder
public class GenConfig {
    /**模块*/
    private String moduleName;
    /**包（长度只能为2）*/
    private String[] packages;
    /**作者*/
    private String author;
    /**实体类简称*/
    private String entityClassName;
    /**实体类字符串必填字段默认使用NotBlank注解，true则使用NotNull*/
    private boolean entityPreferNotNull;
    /**控制器方法中加入deleted字段设置*/
    private boolean controllerContainsDeleted;
    /**控制器方法加入权限注解，此处设置服务名作为权限前缀，如：upms、danger等*/
    private String preAuthorizeServiceName;

    /**业务接口导入包*/
    private String serviceImportPackages;
    /**业务接口注解*/
    private String serviceAnnotations;
}
