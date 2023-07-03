package com.zw.cloud.tools.controller.location;

import com.zw.cloud.common.entity.vo.LocationVO;
import com.zw.cloud.common.gaode.LocationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @GetMapping(value = {"/queryLocation","/queryLocation/{city}"})
    //http://localhost:9040/location/queryLocation
    public List<LocationVO> queryLocation(@PathVariable(required = false) String city) {
        return LocationUtils.queryLocation(city);
    }
}
