package com.zxp.java.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

/**
 * Created by zxp on 2017/12/26.
 * desc:
 */
public class FileUtil extends org.apache.commons.io.FileUtils {
    public static final String encoding = "UTF-8";

    /* 写文件
     * 1.这里只列出3种方式全参数形式，api提供部分参数的方法重载
     * 2.最后一个布尔参数都是是否是追加模式
     * 3.如果目标文件不存在，FileUtils会自动创建
     * */
    public static void write(File file, CharSequence data) throws IOException {
        write(file, data,encoding,true);
    }
    public static void writeLines(File file,  Collection<?> lines) throws IOException {
        writeLines(file, lines,encoding,true);
    }
    public static void writeStringToFile(File file, String data) throws IOException {
        writeStringToFile(file, data,encoding,true);
    }


    /* 读文件
     * */
    public static String readFileToString(File file) throws IOException {
        return readFileToString(file, encoding);
    }
    public static List<String> readLines(File file) throws IOException {
        return readLines(file, encoding);
    }

    /**
     * 返回byte[]
     * @param base64
     * @return
     * @throws Exception
     */
    public static byte[] base64ToByte(String base64){
        if(StringUtils.isNotBlank(base64)){
            // 解密
            return Base64.decodeBase64(base64);
        }
        return null;
    }
    /**
     * 把base64 转换为文件
     * @param base64
     * @param file 保存的文件路径
     * @param fileName
     * @throws Exception
     */
    public static void base64ToFile(String base64, File file) throws IOException {
        if(StringUtils.isNotBlank(base64)){
            // 解密
            byte[] b = Base64.decodeBase64(base64);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            if(!file.exists()){
                file.mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();
        }
    }

    public static void base64ToFile(String base64, String fileName) throws IOException {
        base64ToFile(base64, new File(fileName));
    }

    public static void main(String[] args) {
    }


}
