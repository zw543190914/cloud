package com.zw.cloud.tools.controller;

import com.zw.cloud.tools.entity.Tc;
import com.zw.cloud.tools.service.TcService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Tc)表控制层
 *
 * @author makejava
 * @since 2021-08-29 21:10:20
 */
@RestController
@RequestMapping("tc")
public class TcController {
    /**
     * 服务对象
     */
    @Resource
    private TcService tcService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Tc selectOne(Integer id) {
        return this.tcService.queryById(id);
    }

}