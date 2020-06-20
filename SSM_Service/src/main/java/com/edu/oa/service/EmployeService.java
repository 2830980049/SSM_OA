package com.edu.oa.service;

import com.edu.oa.entity.Employee;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/20 14:17
 */
public interface EmployeService {
    void add(Employee employee);
    void edit(Employee employee);
    void remove(String sn);
    Employee get(String sn);
    List<Employee> getAll();
}
