package com.fishpond.fishpondapp.codegen.core;

import com.fishpond.fishpondapp.codegen.gen.CodeRoot;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.util.*;


public class CodeUtil {
    public static String renderTemplate(String template, CodeRoot root, Map<String, Object> extra) {
        // 设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        // 数据
        Map<String, Object> map = new HashMap<>(extra);
        map.put("root", root);

        VelocityContext context = new VelocityContext(map);
        //渲染模板
        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate(template, "UTF-8");
        tpl.merge(context, sw);
        return sw.toString();
    }

    public static void writeJavaFile(File moduleDir, String basePackage, String subPackages, String fileName, String content) {
        List<String> dirs = new ArrayList<>();
        dirs.add("src");
        dirs.add("main");
        dirs.add("java");
        dirs.addAll(Arrays.asList(basePackage.split("\\.")));
        dirs.addAll(Arrays.asList(subPackages.split("\\.")));

        File dir = Paths.get(moduleDir.getAbsolutePath(), dirs.toArray(new String[0])).toFile();
        dir.mkdirs();

        File file = new File(dir, fileName + ".java");

        FileUtil.write(file, content);
    }

    public static void writeResourceFile(File moduleDir, String subDirs, String fileName, String content) {
        List<String> dirs = new ArrayList<>();
        dirs.add("src");
        dirs.add("main");
        dirs.add("resources");
        dirs.addAll(Arrays.asList(subDirs.split("\\.")));

        File dir = Paths.get(moduleDir.getAbsolutePath(), dirs.toArray(new String[0])).toFile();
        dir.mkdirs();

        File file = new File(dir, fileName);

        FileUtil.write(file, content);
    }

    public static File findModuleDir(String moduleName) {
        String res = "/" + CodeUtil.class.getName().replace('.', '/') + ".class";
        String thisModuleDir = CodeUtil.class.getResource(res).getFile().replace("/target/classes" + res, "");
        File workspaceDir = new File(thisModuleDir).getParentFile().getParentFile();
        File[] modules = new File[1];
        findDir(workspaceDir, moduleName, modules);
        Objects.requireNonNull(modules[0], "未找到模块：" + moduleName);
        return modules[0];
    }

    private static void findDir(File root, String name, File[] finded) {
        File[] children = root.listFiles();
        if (children != null) {
            for (File child : children) {
                if (child.isDirectory()) {
                    if (child.getName().equals(name)) {
                        finded[0] = child;
                        return;
                    }
                    findDir(child, name, finded);
                }
            }
        }
    }

    public static String camelCaseTable(String tableName) {
        if (tableName.startsWith("t_")) {
            tableName = tableName.substring(2);
        }
        return camelCase_(tableName);
    }

    public static String camelCaseColumn(String columnName) {
        if (columnName.contains("_")) {
            String str = camelCase_(columnName);
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return columnName;
    }

    private static String camelCase_(String str) {
        StringBuilder sb = new StringBuilder();
        for (String s : str.split("_")) {
            sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        }
        return sb.toString();
    }


    public static String controllerMappingName(String entityClassName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < entityClassName.length(); i++) {
            char c = entityClassName.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("/");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String controllerPreAuthorize(String serviceName, String entityClassName) {
        if (StringUtils.isNotBlank(serviceName)) {
            StringBuilder sb = new StringBuilder();
            sb.append(serviceName);
            for (int i = 0; i < entityClassName.length(); i++) {
                char c = entityClassName.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append(":");
                    sb.append(Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return null;
    }
}
