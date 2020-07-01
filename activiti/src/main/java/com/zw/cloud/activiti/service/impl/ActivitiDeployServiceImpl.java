package com.zw.cloud.activiti.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.cloud.activiti.dao.ActReDeploymentMapper;
import com.zw.cloud.activiti.entity.ActReDeploymentExample;
import com.zw.cloud.activiti.service.api.IActivitiDeployService;
import com.zw.cloud.common.utils.WebResult;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.zip.ZipInputStream;

@Service
public class ActivitiDeployServiceImpl implements IActivitiDeployService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private ActReDeploymentMapper actReDeploymentMapper;


    private Logger logger = LoggerFactory.getLogger(ActivitiDeployServiceImpl.class);


    //流程定义和部署
    @Override
    public WebResult deploy(String deployName){

        RepositoryService repositoryService = processEngine.getRepositoryService();
        //创建一个部署对象
        Deployment deployment = repositoryService
                .createDeployment().name(deployName)
                .key(deployName)
                .addClasspathResource("processes/" +deployName+ ".bpmn")
                .deploy();
        return WebResult.success().withData(deployment.getKey());
    }

    @Override
    public boolean defineProcessByZip(MultipartFile multipartFile, String deployName) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            repositoryService.createDeployment().name(deployName).addZipInputStream(zipInputStream).deploy();
            zipInputStream.close();
            inputStream.close();
            return true;
        } catch (IOException e) {
            logger.error("[defineProcessByZip] error is {}",e);
            return false;
        }
    }

    @Override
    public void deleteDeploy(String deployId) throws Exception {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // true 为级联删除，--部署表和流程定义表
        processEngine.getRepositoryService().deleteDeployment(deployId,true );
    }


    @Override
    public Object queryDefined(String key, Integer pageNo, Integer pageSize) throws Exception {
        ActReDeploymentExample example = new ActReDeploymentExample();
        example.setOrderByClause("ID_ desc");
        if (StringUtils.isNoneBlank(key)){
            example.createCriteria().andKeyEqualTo(key);
            return actReDeploymentMapper.selectByExample(example);
        }
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(actReDeploymentMapper.selectByExample(example));
    }

    //  查询流程图—中文乱码
    @Override
    public void queryImage(String deployId, HttpServletResponse response) throws Exception {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployId).singleResult();
        String imageName = processDefinition.getResourceName();
        try {
            InputStream inputstream = repositoryService.getResourceAsStream(deployId, imageName);
            /*BufferedImage bufferedImage = ImageIO.read(in);
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage,"JPEG" ,outputStream );
            in.close();
            outputStream.close();*/
            String fileName = "image" + Instant.now().getEpochSecond()+".png";
            FileUtils.copyInputStreamToFile(inputstream, new File("src/main/resources/processes/"+fileName));


        } catch (IOException e) {
            logger.error("[queryImage] error is {}",e );
        }
    }
}
