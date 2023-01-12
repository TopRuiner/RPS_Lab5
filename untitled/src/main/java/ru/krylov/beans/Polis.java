package ru.krylov.beans;

import java.sql.Date;

public class Polis {
    private Integer id;
    private String company;
    private Date endDate;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return ("Id - " + getId() + " | " + "Company -  " + getCompany() + " | " + "End Date " + getEndDate());
    }
}
