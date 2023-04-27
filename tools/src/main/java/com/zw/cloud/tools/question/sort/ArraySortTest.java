package com.zw.cloud.tools.question.sort;

import com.alibaba.fastjson2.JSON;

import java.util.Objects;

public class ArraySortTest {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 5, 4, 7, 6};
        sort1(arr);
    }

    static void sort1(int[] arr) {
        if (Objects.isNull(arr) || arr.length < 1) {
            System.out.println(JSON.toJSONString(arr));
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i+1]) {
                int temp = arr[i+1];
                arr[i+1] = arr[i];
                arr[i] = temp;
            }
        }
        System.out.println(JSON.toJSONString(arr));
    }
}
