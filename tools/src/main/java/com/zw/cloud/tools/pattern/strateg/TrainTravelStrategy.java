package com.zw.cloud.tools.pattern.strateg;

import org.springframework.stereotype.Service;

@Service
public class TrainTravelStrategy implements TravelInterface {

    @Override
    public String getType() {
        return "train";
    }

    @Override
    public String travel() {
        return "train";
    }
}
