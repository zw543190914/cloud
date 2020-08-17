package com.zw.cloud.tools.utils;

import com.zw.cloud.db.entity.User;
import org.stringtemplate.v4.ST;

import java.util.Random;

public class StringTemplete {

    public static void main(String[] args) {
        String template = "<user.name> <user.age>";
        ST st = new ST(template);
        User user = new User();
        user.setName("zw");
        user.setAge((byte)22);
        st.add("user", user);
        System.out.println(st.render());

        Random random = new Random();

        for (int i = 0; i <10 ;i++){
            System.out.println(random.nextInt(35) + "  " + random.nextInt(35) + "  " +
                    random.nextInt(35) + "  " + random.nextInt(35) + "  " +
                    random.nextInt(35) + "  " + random.nextInt(12) + "  " +
                    random.nextInt(12));
        }


    }

}
