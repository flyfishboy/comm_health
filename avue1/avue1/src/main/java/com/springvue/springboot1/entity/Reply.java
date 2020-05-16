package com.springvue.springboot1.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Reply implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String text;
    private String time2;
    private String name;
    private Integer rid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2y(String time2) {
        this.time2 = time2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", name='" + name + '\'' +
                ", rid=" + rid +
                '}';
    }
}
