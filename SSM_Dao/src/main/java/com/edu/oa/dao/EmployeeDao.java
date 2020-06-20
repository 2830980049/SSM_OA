package com.edu.oa.dao;

import com.edu.oa.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/20 13:57
 */
@Repository("EmployeeDao")
public interface EmployeeDao {
    void insert(Employee department);
    void update(Employee department);
    void delete(String sn);
    Employee select(String sn);
    List<Employee> selectAll();
}
