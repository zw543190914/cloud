package com.zw.cloud.netty.web.controller.poem;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zw.cloud.netty.web.entity.poem.Poem;
import com.zw.cloud.netty.web.service.api.poem.IPoemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@Slf4j
public class PoemController {

    @Autowired
    private IPoemService poemService;

    @PostMapping("/insert")
    public void insert(@RequestBody Poem poem) {
        poemService.saveOrUpdate(poem);
    }

    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<Poem> poemList) {
        poemService.saveBatch(poemList);
    }

    @PostMapping("/updateBatchById")
    public void updateBatchById(@RequestBody List<Poem> poemList) {
        poemService.updateBatchById(poemList);
    }

    @PostMapping("/saveOrUpdateBatch")
    public void saveOrUpdateBatch(@RequestBody List<Poem> poemList) {

        poemService.saveOrUpdateBatch(poemList);
        log.info("[PoemController][saveOrUpdateBatch]poemList is {}", JSON.toJSONString(poemList));
    }

    @GetMapping("/queryByTitleOrContent")
    //http://localhost:18092/poem/queryByTitleOrContent?title=
    public List<Poem> queryByTitleOrContent(@RequestParam String title) {
        return poemService.queryByTitleOrContent(title);
    }

    @GetMapping("/testLock")
    //http://localhost:18092/poem/testLock?id=1&title=11
    public Poem queryByTitleOrContent(@RequestParam Long id,@RequestParam String title) throws Exception{
        return poemService.testLock(id,title);
    }

}

