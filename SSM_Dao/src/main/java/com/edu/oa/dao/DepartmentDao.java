package com.edu.oa.dao;

import com.edu.oa.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/20 11:57
 */
@Repository("DepartmentDao")
public interface DepartmentDao {
    void insert(Department department);
    void update(Department department);
    void delete(String sn);
    Department select(String sn);
    List<Department> selectAll();
}
