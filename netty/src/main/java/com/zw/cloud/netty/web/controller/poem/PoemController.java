package com.zw.cloud.netty.web.controller.poem;


import com.zw.cloud.netty.web.entity.poem.Poem;
import com.zw.cloud.netty.web.service.api.poem.IPoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zw
 * @since 2022-01-17
 */
@RestController
@RequestMapping("/poem")
public class PoemController {

    @Autowired
    private IPoemService poemService;

    @PostMapping
    public void insert(@RequestBody Poem poem) {
        poemService.saveOrUpdate(poem);
    }
}

