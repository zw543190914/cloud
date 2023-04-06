package com.zw.cloud.tools.controller.file;

import com.zw.cloud.common.utils.file.ZipUtils;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zip")
public class ZipController {

    @GetMapping
    //http://localhost:9040/zip?inputFile=F:/蚂蚁课堂4期/4期/Download/normal&outputFile=F:/蚂蚁课堂4期/4期/Download/normal.zip&password=password
    public void file(@RequestParam String inputFile,@RequestParam String outputFile,@RequestParam String password) throws ZipException {
        ZipUtils.zipFileWithPwd(inputFile, outputFile, password);
    }
}
