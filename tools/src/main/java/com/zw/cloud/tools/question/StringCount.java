package com.zw.cloud.tools.question;

import java.util.Scanner;

public class StringCount {
    public static void main(String[] args) {
        /**
         * 统计字符串中字母、空格、数字和其它字符的个数
         */
        int abcCounter = 0;
        int spaceCounter = 0;
        int numCounter = 0;
        int otherCounter = 0;

        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        char[] ch = str.toCharArray();

        for (int i = 0; i < ch.length; i++) {
            if (Character.isLetter(ch[i])) {
                abcCounter++;
            }else if (Character.isDigit(ch[i])) {
                numCounter++;
            }else if (Character.isSpaceChar(ch[i])) {
                spaceCounter++;
            }else{
                otherCounter++;
            }
        }

        System.out.println("abcCounter:" + abcCounter);
        System.out.println("spaceCounter:" + spaceCounter);
        System.out.println("numCounter:" + numCounter);
        System.out.println("otherCounter:" + otherCounter);
    }
}
