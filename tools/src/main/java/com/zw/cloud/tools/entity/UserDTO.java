package com.zw.cloud.tools.entity;

import com.zw.cloud.tools.entity.poem.Poem;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDTO extends User implements Serializable {
    private Poem poem;
}
