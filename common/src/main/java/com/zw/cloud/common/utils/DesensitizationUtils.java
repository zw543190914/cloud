package com.zw.cloud.common.utils;

import cn.hutool.core.util.DesensitizedUtil;

public class DesensitizationUtils {

    public static void main(String[] args) {
        String phone="13723231234";
        System.out.println(DesensitizedUtil.mobilePhone(phone)); //输出：137****1234

        String bankCard="6217000130008255666";
        System.out.println(DesensitizedUtil.bankCard(bankCard)); //输出：6217 **** **** *** 5666

        String idCardNum="411021199901102321";
        //只显示前4位和后2位
        System.out.println(DesensitizedUtil.idCardNum(idCardNum,4,2)); //输出：4110************21

        String password="www.jd.com_35711";
        System.out.println(DesensitizedUtil.password(password)); //输出：****************
    }
}
