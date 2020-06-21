package com.edu.oa.service;

import com.edu.oa.entity.Employee;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/20 15:38
 */
public interface GlobalService {
    Employee login(String sn, String password);
    void changePassword(Employee employee);
}
