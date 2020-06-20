package com.edu.oa.service;

import com.edu.oa.entity.Department;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/20 12:40
 */
public interface DepartmentService {
    void add(Department department);
    void edit(Department department);
    void remove(String sn);
    Department get(String sn);
    List<Department> getAll();
}
