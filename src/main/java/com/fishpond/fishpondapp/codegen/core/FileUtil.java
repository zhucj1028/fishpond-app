package com.fishpond.fishpondapp.codegen.core;

import lombok.SneakyThrows;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

public class FileUtil {

    /**
     * 获取临时文件夹对象
     */
    public static File tempDir() {
        return new File(System.getProperty("java.io.tmpdir"));
    }

    /**
     * 获取 user.dir 文件夹对象
     */
    public static File userDir() {
        return new File(System.getProperty("user.dir"));
    }

    /**
     * 创建临时文件夹对象
     *
     * @param prefix 临时文件夹前缀
     * @param attrs  文件夹属性
     */
    @SneakyThrows
    public static File createTempDir(String prefix, FileAttribute<?>... attrs) {
        return Files.createTempDirectory(prefix, attrs).toFile();
    }

    /**
     * 创建多级文件夹
     */
    public static void mkdirs(File dir) {
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 创建多级文件夹，路径为：first/more1/more2/more3/[...]
     *
     * @return 已创建的文件夹对象
     */
    public static File mkdirs(String first, String... more) {
        File dir = Paths.get(first, more).toFile();
        mkdirs(dir);
        return dir;
    }

    /**
     * 递归删除文件
     *
     * @param file 需删除的文件夹或文件
     */
    public static void delete(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                for (File f : listFiles(file)) {
                    delete(f);
                }
            }
            file.delete();
        }
    }

    /**
     * 递归删除文件夹下的所有子文件（文件夹和文件）
     *
     * @param dir 删除该文件夹下的所有子文件
     */
    public static void deleteChilden(File dir) {
        for (File f : listFiles(dir)) {
            delete(f);
        }
    }

    /**
     * 复制文件
     */
    @SneakyThrows
    public static void copy(File from, File to) {
        mkdirs(to.getParentFile());
        writeAndClose(new FileInputStream(from), new FileOutputStream(to));
    }

    /**
     * 移动文件
     */
    public static void move(File from, File to) {
        copy(from, to);
        from.delete();
    }

    /**
     * 获取文件夹的子文件夹及文件对象数组（第一级）
     *
     * @return 无子文件夹或子文件时，返回长度为0的数组
     */
    public static File[] listFiles(File dir) {
        File[] fs = null;
        if (null != dir && dir.isDirectory()) {
            fs = dir.listFiles();
        }
        return null == fs ? new File[0] : fs;
    }

    /**
     * 将字符串写入文件，替换原有内容
     */
    @SneakyThrows
    public static void write(File file, String data) {
        mkdirs(file.getParentFile());
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            out.write(data);
        }
    }

    /**
     * 将字节数组写入文件，替换原有内容
     */
    @SneakyThrows
    public static void write(File file, byte[] data) {
        mkdirs(file.getParentFile());
        try (OutputStream out = new FileOutputStream(file)) {
            out.write(data);
        }
    }

    /**
     * 将文件内容写入输出流，不关闭输出流
     */
    @SneakyThrows
    public static void write(File file, OutputStream os) {
        try (InputStream fis = new FileInputStream(file)) {
            write(fis, os);
        }
    }

    /**
     * 流输入、输出。不关闭输入、输出流。
     */
    @SneakyThrows
    public static void write(InputStream inputStream, OutputStream outputStream) {
        int r;
        byte[] bytes = new byte[8192];
        while ((r = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, r);
        }
    }

    /**
     * 流输入》输出。自动关闭输入、输出流。
     */
    @SneakyThrows
    public static void writeAndClose(InputStream inputStream, OutputStream outputStream) {
        try (InputStream in = inputStream; OutputStream out = outputStream) {
            write(in, out);
        }
    }

    /**
     * 读取文件内容为字符串
     */
    @SneakyThrows
    public static String read(File file) {
        try (InputStream in = new FileInputStream(file)) {
            return IOUtil.readString(in);
        }
    }

    /**
     * 读取文件内容为字节数组
     */
    @SneakyThrows
    public static byte[] readAsBytes(File file) {
        try (InputStream in = new FileInputStream(file)) {
            return IOUtil.readBytes(in);
        }
    }

    public static void downloadNetFile(String path,String localPath){
        try {
            File file = new File(localPath);
            if (file.exists()){
                return;
            }
            mkdirs(file.getParentFile());
            URL url = new URL(path);
            try (InputStream inStream = url.openStream()) {
                try (FileOutputStream fileOutputStream = new FileOutputStream(localPath)){
                    int r = 0;
                    byte[] buf = new byte[2048];
                    while ((r = inStream.read(buf)) != -1) {
                        fileOutputStream.write(buf, 0, r);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String splitSuffix(File file){
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
    }

    public static String fileType(File file){
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

}
