package com.zw.cloud.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class FileUtils {

    public static void main(String[] args) throws IOException {
        String fileUrl = "https://img.pconline.com.cn/images/upload/upc/tx/itbbs/1308/16/c8/24543026_1376637095791_mthumb.jpg";
        downLoadImage(fileUrl,"D:/test/","hello.jpg");
    }

    public static void copyFile(File srcFile, File dstFile) throws IOException {
        if (srcFile == null || !srcFile.exists()) {
            return;
        }
        if (dstFile == null || !dstFile.exists()) {
            return;
        }

        FileInputStream fileIns = null;
        FileOutputStream fileOuts = null;
        FileChannel source = null;
        FileChannel destination = null;

        try {
            fileIns = new FileInputStream(srcFile);
            fileOuts = new FileOutputStream(dstFile);
            source = fileIns.getChannel();
            destination = fileOuts.getChannel();
            destination.transferFrom(source, 0, source.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileIns != null) {
                fileIns.close();
            }
            if (fileOuts != null) {
                fileOuts.close();
            }
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    /**
     *
     * @param uri 网络图片地址
     * @param filePath 下载目录
     * @param fileName 下载文件名
     */
    public static void downLoadImage(String uri, String filePath,String fileName) {
        ReadableByteChannel readableByteChannel = null;
        FileChannel fileChannel = null;
        File file;
        URL url;
        FileOutputStream fileOutputStream = null;
        try {
            url = new URL(uri);
            //首先从 URL stream 中创建一个 ReadableByteChannel 来读取网络文件
            readableByteChannel = Channels.newChannel(url.openStream());
            String path = filePath + fileName;
            file = new File(path);
            if (!file.getParentFile().exists() && !file.getParentFile().isDirectory()) {
                file.getParentFile().mkdirs();
            }
            //通过 ReadableByteChannel 读取到的字节会流动到一个 FileChannel 中，然后再关联一个本地文件进行下载操作
            fileOutputStream = new FileOutputStream(file);
            fileChannel = fileOutputStream.getChannel();
            //最后用 transferFrom()方法就可以把 ReadableByteChannel 获取到的字节写入本地文件
            fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != readableByteChannel) {
                    readableByteChannel.close();
                }
                if (null != fileChannel) {
                    fileChannel.close();
                }
                if (null != fileOutputStream) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
