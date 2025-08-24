package com.fishpond.fishpondapp.codegen.gen;

import cn.hutool.core.date.DateUtil;
import com.fishpond.fishpondapp.codegen.core.CodeUtil;
import com.fishpond.fishpondapp.codegen.core.TableInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Paths;
import java.util.*;


@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class CodeGenerator {
    private final CodeRoot root;

    public static CodeGenerator create(GenConfig config, TableInfo table) {
        String basePackage = "com.fishpond." + config.getPackages()[0] + "." + config.getPackages()[1];
        String entityClassName = StringUtils.isBlank(config.getEntityClassName()) ? CodeUtil.camelCaseTable(table.getName()) : config.getEntityClassName();
        String controllerMapping = CodeUtil.controllerMappingName(entityClassName);
        String preAuthorize = CodeUtil.controllerPreAuthorize(config.getPreAuthorizeServiceName(), entityClassName);
        return new CodeGenerator(
                CodeRoot.builder()
                        .comment(table.getComment())
                        .author(config.getAuthor())
                        .version(DateUtil.format(new Date(), "yyyy-MM-dd"))
                        .tableName(table.getName())
                        .idColumn(table.getIdColumn())
                        .columns(table.getColumns())
                        .entityClassName(entityClassName)
                        .entityClassVarName(entityClassName.substring(0, 1).toLowerCase() + entityClassName.substring(1))
                        .entityPreferNotNull(config.isEntityPreferNotNull())
                        .basePackage(basePackage)
                        .moduleDir(CodeUtil.findModuleDir(config.getModuleName()))
                        .year(Calendar.getInstance().get(Calendar.YEAR))
                        .lineSeparator(System.lineSeparator())
                        .tab("\t")
                        .controllerMapping(controllerMapping)
                        .controllerContainsDeleted(config.isControllerContainsDeleted())
                        .preAuthorize(preAuthorize)
                        .serviceImportPackages(config.getServiceImportPackages())
                        .serviceAnnotations(config.getServiceAnnotations())
                        .build()
        )//
//        .generatePackages()//
        ;
    }

    public CodeGenerator generateController() {
        String content = CodeUtil.renderTemplate("template/controller.java.vm", root, new HashMap<>());
        CodeUtil.writeJavaFile(root.getModuleDir(), root.getBasePackage(), "controller",
                root.getEntityClassName() + "Controller", content);
        System.out.println("生成Controller类成功");
        return this;
    }

    public CodeGenerator generateEntity() {
        String content = CodeUtil.renderTemplate("template/entity.java.vm", root, new HashMap<>());
        CodeUtil.writeJavaFile(root.getModuleDir(), root.getBasePackage(), "entity",
                root.getEntityClassName(), content);
        System.out.println("生成Entity类成功");
        return this;
    }

    public CodeGenerator generateMapper() {
        String content = CodeUtil.renderTemplate("template/mapper.java.vm", root, new HashMap<>());
        CodeUtil.writeJavaFile(root.getModuleDir(), root.getBasePackage(), "mapper",
                root.getEntityClassName() + "Mapper", content);
        System.out.println("生成Mapper类成功");
        return this;
    }

    public CodeGenerator generateMapperXml(String... xmlBasePath) {
        String content = CodeUtil.renderTemplate("template/mapper.xml.vm", root, new HashMap<>());
        StringBuilder subDirs = new StringBuilder("mapper");
        for (String path : xmlBasePath) {
            subDirs.append(String.format("/%s", path));
        }
        CodeUtil.writeResourceFile(root.getModuleDir(), subDirs.toString(), root.getEntityClassName() + ".xml", content);
        System.out.println("生成MapperXml成功");
        return this;
    }

    public CodeGenerator generateService() {
        String content = CodeUtil.renderTemplate("template/service.java.vm", root, new HashMap<>());
        CodeUtil.writeJavaFile(root.getModuleDir(), root.getBasePackage(), "service",
                root.getEntityClassName() + "Service", content);

        String content2 = CodeUtil.renderTemplate("template/service.impl.java.vm", root, new HashMap<>());
        CodeUtil.writeJavaFile(root.getModuleDir(), root.getBasePackage(), "service.impl",
                root.getEntityClassName() + "ServiceImpl", content2);

        System.out.println("生成Service类成功");
        return this;
    }

    public CodeGenerator generatePackages() {
//        List<String> pkgs = Arrays.asList("controller", "service.impl", "mapper", "entity", "dto", "bo", "enums", "constant", "job", "msg", "handler", "builder");
        List<String> pkgs = Arrays.asList("controller", "service.impl", "mapper", "entity", "dto");

        for (String pkg : pkgs) {
            List<String> dirs = new ArrayList<>();
            dirs.add("src");
            dirs.add("main");
            dirs.add("java");
            dirs.addAll(Arrays.asList(root.getBasePackage().split("\\.")));
            dirs.addAll(Arrays.asList(pkg.split("\\.")));

            Paths.get(root.getModuleDir().getAbsolutePath(), dirs.toArray(new String[0])).toFile().mkdirs();
        }
        return this;
    }
}
