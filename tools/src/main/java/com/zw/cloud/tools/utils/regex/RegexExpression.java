package com.zw.cloud.tools.utils.regex;

import java.util.regex.Pattern;

public class RegexExpression {
    private static Pattern NUMBER_PATTERN = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");

    public static void main(String[] args) {

        System.out.println(NUMBER_PATTERN.matcher("001").matches());
        System.out.println(NUMBER_PATTERN.matcher("100").matches());
        System.out.println(NUMBER_PATTERN.matcher("010").matches());
        System.out.println(NUMBER_PATTERN.matcher("-010").matches());

    }
}
