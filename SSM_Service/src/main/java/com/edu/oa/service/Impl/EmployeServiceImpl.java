package com.edu.oa.service.Impl;

import com.edu.oa.dao.EmployeeDao;
import com.edu.oa.entity.Employee;
import com.edu.oa.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/20 14:17
 */
@Service("EmployeServiceImpl")
public class EmployeServiceImpl implements EmployeService {
    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public void add(Employee employee) {
        employee.setPassword("123456");
        employeeDao.insert(employee);
    }

    @Override
    public void edit(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void remove(String sn) {
        employeeDao.delete(sn);
    }

    @Override
    public Employee get(String sn) {
        return employeeDao.select(sn);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDao.selectAll();
    }
}
