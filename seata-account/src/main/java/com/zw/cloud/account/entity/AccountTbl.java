package com.zw.cloud.account.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AccountTbl implements Serializable {
    private Integer id;

    private String userId;

    private Integer money;

    private static final long serialVersionUID = 1L;


}