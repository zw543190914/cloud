package com.zw.cloud.tools.test;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        HashSet<Integer> set1 = Sets.newHashSet(1, 2, 3);
        HashSet<Integer> set2 = Sets.newHashSet( 2, 3,4);
        Set<Integer> difference = Sets.difference(set1, set2);
        System.out.println(difference.toString());
        System.out.println(Sets.intersection(set1, set2).toString());
    }
}
