package com.skypro.employee.record;

import org.apache.commons.lang3.StringUtils;

import java.net.BindException;

public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private int department;
    private int salary;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws BindException {
        if (StringUtils.isBlank(firstName) || !StringUtils.isAlpha(firstName) || StringUtils.isNumeric(firstName)) {
            throw new BindException();
        } else {
            this.firstName = StringUtils.capitalize(firstName);
        }

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws BindException {
        if (StringUtils.isBlank(lastName) || !StringUtils.isAlpha(lastName) || StringUtils.isNumeric(lastName)) {
            throw new BindException();
        } else {
            this.lastName = StringUtils.capitalize(lastName);
        }
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;

    }
}
