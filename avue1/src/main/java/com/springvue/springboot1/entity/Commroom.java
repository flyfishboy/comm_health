package com.springvue.springboot1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Commroom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String time;
    private String contenta;
    private String contentb;
    private String accounta;
    private String accountb;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContenta() {
        return contenta;
    }

    public void setContenta(String contenta) {
        this.contenta = contenta;
    }

    public String getContentb() {
        return contentb;
    }

    public void setContentb(String contentb) {
        this.contentb = contentb;
    }

    public String getAccounta() {
        return accounta;
    }

    public void setAccounta(String accounta) {
        this.accounta = accounta;
    }

    public String getAccountb() {
        return accountb;
    }

    public void setAccountb(String accountb) {
        this.accountb = accountb;
    }

    @Override
    public String toString() {
        return "Commroom{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", contenta='" + contenta + '\'' +
                ", contentb='" + contentb + '\'' +
                ", accounta='" + accounta + '\'' +
                ", accountb='" + accountb + '\'' +
                '}';
    }
}
