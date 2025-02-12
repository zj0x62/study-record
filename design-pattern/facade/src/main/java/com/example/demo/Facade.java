package com.example.demo;

/**
 * @Author: zhoujing
 * @Date: 2025/2/8 13:20
 * @Description:
 */
public class Facade {

    private AdminOfIndustry admin;
    private Bank bank;
    private Taxation taxation;

    public Facade() {
        this.admin = new AdminOfIndustry();
        this.bank = new Bank();
        this.taxation = new Taxation();
    }

    public Company openCompany(String name) {
        Company company = admin.register(name);
        String account = bank.openAccount(company.getId());
        company.setBankAccount(account);
        String taxCode = taxation.applyTaxCode(company.getId());
        company.setTaxCode(taxCode);
        return company;
    }
}
