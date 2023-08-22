package com.zw.cloud.tools.test;

public class DocTest {
    public static void main(String[] args) {
        for (int i = 1; i < 5; i++) {
            String s = "/**\n" +
                    "     * 含水量" + i + "#设定值\n" +
                    "     */\n" +
                    "    @Column(name = \"waterRatio" + i + "\")\n" +
                    "    private BigDecimal waterRatio" + i + ";\n" +
                    "    /**\n" +
                    "     * 含水量"+ i +"#实际值\n" +
                    "     */\n" +
                    "    @Column(name = \"waterRatioAct"+ i +"\")\n" +
                    "    private BigDecimal waterRatioAct"+ i +";";
            System.out.println(s);
        }

    }
}
