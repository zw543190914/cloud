package com.zw.cloud.activiti.service.impl;

import com.zw.cloud.activiti.service.api.IActivitiDeployService;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

@Service
public class ActivitiDeployServiceImpl implements IActivitiDeployService {

    @Autowired
    private RepositoryService repositoryService;


    private Logger logger = LoggerFactory.getLogger(ActivitiDeployServiceImpl.class);


    //流程定义和部署
    @Override
    public String defineProcess(String filePath, String deployName) throws Exception {
        // 获得一个部署构建器对象，用于加载流程定义文件（test01.bpmn test01.png）完成流程定义的部署
        DeploymentBuilder builder = repositoryService.createDeployment();
        //   加载流程定义文件
        builder.addClasspathResource("process/"+filePath).name(deployName);
        //    部署流程定义
        Deployment deploy = builder.deploy();
        return deploy.getName();
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

    //16.10.   流程定义查询
    @Override
    public List<ProcessDefinition> queryDefined(String deployName, Integer pageNo, Integer pageSize) throws Exception {
        if (StringUtils.isBlank(deployName)){
            deployName = "";
        }
        //根据流程部署名称 --->部署id----->流程定义
        List<Deployment> deploymentList = repositoryService.createDeploymentQuery()
                .deploymentNameLike(deployName + "%").list();
        Set<String> deployIds = deploymentList.parallelStream().map(Deployment::getId).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(deployIds)){
            return null;
        }
        //分页
        int firstResult = (pageNo - 1) * pageSize;
        return repositoryService.createProcessDefinitionQuery()
                .deploymentIds(deployIds).orderByProcessDefinitionVersion().desc().listPage(firstResult, pageSize);
        // 重新封装实体类返回
        /*List<ProcessDefinitionVO> processDefinitionVOList = new ArrayList<>();
        processDefinitions.forEach(processDefinition -> {
            ProcessDefinitionVO processDefinitionVO = new ProcessDefinitionVO();
            BeanUtils.copyProperties(processDefinition,processDefinitionVO );
            processDefinitionVOList.add(processDefinitionVO);
        });
        return processDefinitionVOList;*/
    }

    //16.11.   查询流程图—中文乱码
    @Override
    public void queryImage(String deployId, HttpServletResponse response) throws Exception {
        /*Deployment deployment = repositoryService.createDeploymentQuery()
                .deploymentId(deployId).singleResult();*/
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployId).singleResult();
        String imageName = processDefinition.getDiagramResourceName();
        try {
            InputStream in = repositoryService.getResourceAsStream(deployId, imageName);
            BufferedImage bufferedImage = ImageIO.read(in);
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage,"JPEG" ,outputStream );
            in.close();
            outputStream.close();
        } catch (IOException e) {
            logger.error("[queryImage] error is {}",e );
        }
    }
}
