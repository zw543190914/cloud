package com.zw.cloud.tools.mtop.dto;

import java.io.Serializable;
import java.util.List;

public class MtopParamDTO implements Serializable {
    private String uri;
    private List args;
    private String workId;
    private String workName;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List getArgs() {
        return args;
    }

    public void setArgs(List args) {
        this.args = args;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }
}
