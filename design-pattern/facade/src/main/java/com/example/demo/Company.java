package com.example.demo;

import java.util.zip.Inflater;

/**
 * @Author: zhoujing
 * @Date: 2025/2/8 13:16
 * @Description:
 */
public class Company {

    private String id;
    private String name;
    private String bankAccount;

    private String taxCode;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }
}
