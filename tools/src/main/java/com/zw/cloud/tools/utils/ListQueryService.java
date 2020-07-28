package com.zw.cloud.tools.utils;

import com.zw.cloud.db.entity.User;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ListQueryService {

    public static List<User> listSearch(String name, List<User> list){
        //Pattern pattern = Pattern.compile(name);
        Pattern pattern = Pattern.compile(name,Pattern.CASE_INSENSITIVE);
        return list.stream().map(user -> {
            Matcher matcher = pattern.matcher(user.getName());
            // matcher.find()即可以进行模糊匹配，lookAt右模糊
            if (matcher.matches()) {
                return user;
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
