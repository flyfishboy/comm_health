package com.springvue.springboot1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Atob implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String accounta;
    private String accountb;
    private String namea;
    private String nameb;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNamea() {
        return namea;
    }

    public void setNamea(String namea) {
        this.namea = namea;
    }

    public String getNameb() {
        return nameb;
    }

    public void setNameb(String nameb) {
        this.nameb = nameb;
    }

    @Override
    public String toString() {
        return "Atob{" +
                "id=" + id +
                ", accounta='" + accounta + '\'' +
                ", accountb='" + accountb + '\'' +
                ", namea='" + namea + '\'' +
                ", nameb='" + nameb + '\'' +
                '}';
    }
}
