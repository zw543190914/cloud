package com.zw.cloud.common.utils.file;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.util.Objects;

@Slf4j
public class ZipUtils {

    public static void main(String[] args) throws ZipException {
        zipFileWithPwd("F:\\蚂蚁课堂4期\\4期\\Download\\mv1","F:\\蚂蚁课堂4期\\4期\\Download\\mv1.zip","password");
        System.out.println("====================================");

//        zipFileWithPwd("F:\\相册\\Camera","F:\\相册\\Camera.zip","password");
        System.out.println("====================================");

    }

    /**
     * 不加密的压缩
     *
     * @param inputFile  D:\\test  要打包的文件夹
     * @param outputFile D:\test.zip  生成的压缩包的名字
     */
    public static void zipFile(String inputFile, String outputFile) throws ZipException {
        // 生成的压缩文件
        ZipFile zipFile = new ZipFile(outputFile);
        ZipParameters parameters = new ZipParameters();
        // 压缩方式
        parameters.setCompressionMethod(CompressionMethod.DEFLATE);
        // 压缩级别
        parameters.setCompressionLevel(CompressionLevel.NORMAL);
        // 要打包的文件夹
        File currentFile = new File(inputFile);
        File[] fs = currentFile.listFiles();
        if (Objects.isNull(fs) || ArrayUtils.isEmpty(fs)) {
            return;
        }
        // 遍历test文件夹下所有的文件、文件夹
        int index = 1;
        for (File f : fs) {
            if (f.isDirectory()) {
                zipFile.addFolder(f, parameters);
                log.info("zipFile isDirectory num is {}",index);
            } else {
                zipFile.addFile(f, parameters);
                log.info("zipFile addFile num is {}",index);
            }
            index ++;
        }

    }


    /**
     * 压缩加密
     *
     * @param inputFile  D:\\test  要打包的文件夹
     * @param outputFile D:\test1.zip 生成的压缩包的名字
     */
    public static void zipFileWithPwd(String inputFile, String outputFile, String password) throws ZipException {
        log.info("zipFileWithPwd inputFile {},outputFile is {}",inputFile,outputFile);
        // 生成的压缩文件
        ZipFile zipFile = new ZipFile(outputFile, password.toCharArray());
        ZipParameters parameters = new ZipParameters();
        // 压缩方式
        parameters.setCompressionMethod(CompressionMethod.DEFLATE);
        // 压缩级别
        parameters.setCompressionLevel(CompressionLevel.NORMAL);
        //设置加密文件
        parameters.setEncryptFiles(true);
        //设置加密方式（必须要有加密算法） //设置AES加密方式
        parameters.setEncryptionMethod(EncryptionMethod.AES);
        parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        // 要打包的文件夹
        File currentFile = new File(inputFile);
        File[] fs = currentFile.listFiles();
        if (Objects.isNull(fs) || ArrayUtils.isEmpty(fs)) {
            return;
        }
        int index = 1;
        for (File f : fs) {
            if (f.isDirectory()) {
                zipFile.addFolder(f, parameters);
                log.info("zipFileWithPwd isDirectory num is {}",index);
            } else {
                zipFile.addFile(f, parameters);
                log.info("zipFileWithPwd addFile num is {}",index);
            }
            index ++;
        }
        log.info("zipFileWithPwd inputFile {},outputFile is {},end",inputFile,outputFile);

    }

    /**
     * 解压缩
     *
     * @param inputFile  D:\test1.zip  要解压缩的压缩包
     * @param outputFile D:\\test1  解压后的文件名
     */
    public static void unzip(String inputFile, String outputFile,String password) {
        try {
            ZipFile zipFile = new ZipFile(inputFile);
            // 如果解压需要密码
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password.toCharArray());
            }
            zipFile.extractAll(outputFile);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    /**
     * zip文件下删除文件
     *
     * @param inputFile  D:\test1.zip 要删除的压缩包
     * @param deleteFile logger/error.2021-08-20.log  删除压缩包下路径的文件
     */
    public static void deleteFile(String inputFile, String deleteFile) {
        try {
            ZipFile zipFile = new ZipFile(inputFile);
            zipFile.removeFile(deleteFile);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}
