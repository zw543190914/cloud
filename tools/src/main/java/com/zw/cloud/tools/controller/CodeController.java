package com.zw.cloud.tools.controller;

import com.zw.cloud.tools.entity.Code;
import com.zw.cloud.tools.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Code)表控制层
 *
 * @author makejava
 * @since 2021-08-29 19:37:37
 */
@RestController
@RequestMapping("code")
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class CodeController {
    /**
     * 服务对象
     */
    private final CodeService codeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    //http://localhost:9040/code/selectOne?id=8
    public Code selectOne(Integer id) {
        return this.codeService.queryById(id);
    }

}