package com.zw.cloud.tools.pattern.strateg;

import org.springframework.stereotype.Service;

@Service
public class AirTravelStrategy implements TravelInterface {

    @Override
    public String getType() {
        return "air";
    }

    @Override
    public String travel() {
        return "air";
    }
}
