package com.zw.cloud.tools.pattern.strateg;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pattern/strategy")
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StrategyPatternTest {

    private final List<TravelInterface> travelInterfaceList = Lists.newCopyOnWriteArrayList();

    private final Map<String,TravelInterface> map = Maps.newConcurrentMap();

    public StrategyPatternTest(List<TravelInterface> travelInterfaceList){
        travelInterfaceList.forEach(travelInterface -> {
            map.put(travelInterface.getType(),travelInterface);
        });
    }

    @GetMapping
    //http://localhost:9040/pattern/strategy?type=air
    public String test(@RequestParam String type) {
        System.out.println(JSON.toJSONString(map));
        return map.get(type).travel();
    }
}
