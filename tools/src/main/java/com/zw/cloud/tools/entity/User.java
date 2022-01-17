package com.zw.cloud.tools.entity;

import com.zw.cloud.tools.bindcheckgroup.InsertCheckGroup;
import com.zw.cloud.tools.bindcheckgroup.UpdateCheckGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class User extends Base implements Serializable {
    @NotNull(message = "id is null",groups = {UpdateCheckGroup.class})
    private Long id;

    @NotBlank(message = "name is empty",groups = {InsertCheckGroup.class,UpdateCheckGroup.class})
    private String name;

    private Byte age;

    private Date bir;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private String description;

}

class Base {
    private Long baseId;
}