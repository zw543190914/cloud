package com.zw.cloud.tools.service.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import com.zw.cloud.tools.controller.FileController;
import com.zw.cloud.tools.modle.vo.MyPutRet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Paths;

@Service
public class FileServiceImpl {
    private static final String accessKey = "5oNKbLcrzy3lOpOKwSNqbUjxR-CmpoL8cmD7mZ7A";
    private static final String secretKey = "jvoq9Bm6K7DjHMfkRm5TwX4wYjsJHpBga1W4zGco";
    private static final String bucket = "zw-cloud";
    private static final String domainOfBucket = "qczu563pc.bkt.clouddn.com";

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    public MyPutRet upload(String id, MultipartFile file) throws Exception{
        String key = id + "_" +System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                //解析上传成功的结果
                Response response = uploadManager.put(file.getBytes(), key, upToken);
                return response.jsonToObject(MyPutRet.class);
            } catch (QiniuException ex) {
                Response response = ex.response;
                logger.error("[FileController][upload]QiniuException error is {}",response.toString());
                throw ex;
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
            throw ex;
        }
    }

    public MyPutRet continueUpload(String id, MultipartFile file)throws Exception{
        Configuration cfg = new Configuration(Region.region2());
        String key = id + "_" +System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        String localTempDir = Paths.get(System.getProperty("java.io.tmpdir"), bucket).toString();
        logger.info("[FileController][continueUpload]localTempDir is {}",localTempDir);
        try {
            //设置断点续传文件进度保存目录
            FileRecorder fileRecorder = new FileRecorder(localTempDir);
            UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
            try {
                //Response response = uploadManager.put(localFilePath, key, upToken);
                Response response = uploadManager.put(file.getBytes(), key, upToken);
                return response.jsonToObject(MyPutRet.class);
            } catch (QiniuException ex) {
                Response response = ex.response;
                logger.error("[FileController][continueUpload]QiniuException error is {}",response.bodyString());
                throw ex;
            }
        } catch (IOException ex) {
            logger.error("[FileController][continueUpload]IOException error is {}",ex);
            throw ex;
        }
    }

    public String download(String fileName)throws Exception{
        String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        Auth auth = Auth.create(accessKey, secretKey);
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        logger.info("[FileController][download]finalUrl is {}",finalUrl);
        return finalUrl;
    }

    public void delete(String key)throws Exception{
        Configuration cfg = new Configuration(Region.region2());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

}
