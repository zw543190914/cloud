package com.zw.cloud.tools.controller;

import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.tools.modle.vo.MyPutRet;
import com.zw.cloud.tools.service.impl.FileServiceImpl;
import com.zw.cloud.tools.utils.CustomerExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.Future;

@RestController
@RequestMapping("/tools/file")
public class FileController {

    @Autowired
    private FileServiceImpl fileService;


    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/upload")
    //http://localhost:9040/tools/file/upload
    public WebResult upload(String id, MultipartFile file) throws Exception{
        return WebResult.success().withData(fileService.upload(id, file));
    }

    @PostMapping("/continueUpload")
    //http://localhost:9040/tools/file/continueUpload
    public WebResult continueUpload(String id, MultipartFile file)throws Exception{
        return WebResult.success().withData(fileService.continueUpload(id, file));

    }

    @GetMapping("/download")
    //http://localhost:9040/tools/file/download?fileName=0.jpg
    public WebResult download(String fileName)throws Exception{
        return WebResult.success().withData(fileService.download(fileName));

    }

    @GetMapping("/delete")
    //http://localhost:9040/tools/file/delete?key=null_1593952612702用户数据 (1).xlsx
    public void delete(String key)throws Exception{
        fileService.delete(key);
    }

}
