package com.zw.cloud.tools.event.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BaseEvent implements Serializable {

    private String businessId;

    private String userId;
}
