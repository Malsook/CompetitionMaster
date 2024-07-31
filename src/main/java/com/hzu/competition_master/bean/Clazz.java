package com.hzu.competition_master.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;

@Repository
public class Clazz {
    private int id;
    @JsonProperty("clazzName")
    private String clazzName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    @Override
    public String toString() {
        return "clazz{" +
                "id=" + id +
                ", clazzName='" + clazzName + '\'' +
                '}';
    }
}
