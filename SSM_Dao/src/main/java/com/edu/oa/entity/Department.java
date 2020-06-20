package com.edu.oa.entity;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/20 11:53
 */
public class Department {
    private String sn;
    private String name;
    private String address;

    public Department() {
    }

    @Override
    public String toString() {
        return "Department{" +
                "sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Department(String sn, String name, String address) {
        this.sn = sn;
        this.name = name;
        this.address = address;
    }
}
