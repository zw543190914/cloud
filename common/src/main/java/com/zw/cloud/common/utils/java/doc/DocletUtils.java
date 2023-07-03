package com.zw.cloud.common.utils.java.doc;

import com.alibaba.fastjson2.JSON;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.RootDoc;
import com.sun.tools.javadoc.Main;
import com.zw.cloud.common.entity.vo.FieldVO;

import java.util.ArrayList;
import java.util.List;

public class DocletUtils extends Doclet {

    /**
     * 会自动注入
     */
    private static RootDoc rootDoc;

    /**
     * 会自动调用这个方法
     *
     * @param root root
     * @return true
     */
    public static boolean start(RootDoc root) {
        rootDoc = root;
        return true;
    }

    public static List<FieldVO> execute(String beanFilePath) {
        Main.execute(new String[]{"-doclet", DocletUtils.class.getName(), beanFilePath});

        ClassDoc[] classes = rootDoc.classes();

        if (classes == null || classes.length == 0) {
            return null;
        }
        ClassDoc classDoc = classes[0];
        // 获取属性名称和注释
        FieldDoc[] fields = classDoc.fields(false);

        List<FieldVO> fieldVOList = new ArrayList<>(fields.length);

        for (FieldDoc field : fields) {
            fieldVOList.add(new FieldVO(field.name(), field.type().typeName(), field.commentText()));
        }
        return fieldVOList;
    }

    public static void main(String[] args) {
        String beanFilePath = "D:\\zhijing\\project\\cloud\\common\\src\\main\\java\\com\\zw\\cloud\\common\\entity\\dto\\FeishuMsgDTO.java";
        List<FieldVO> fieldVOList = DocletUtils.execute(beanFilePath);
        // [{"describe":"时间戳","fieldName":"timestamp","fieldType":"Integer"},{"describe":"签名","fieldName":"sign","fieldType":"String"},{"describe":"消息类型","fieldName":"msg_type","fieldType":"String"},{"describe":"消息内容","fieldName":"content","fieldType":"FeishuMsgDTO.MsgContent"}]
        System.out.println(JSON.toJSONString(fieldVOList));
    }

}
