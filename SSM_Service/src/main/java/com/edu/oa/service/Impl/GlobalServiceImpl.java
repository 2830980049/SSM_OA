package com.edu.oa.service.Impl;

import com.edu.oa.dao.EmployeeDao;
import com.edu.oa.entity.Employee;
import com.edu.oa.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/20 15:39
 */
@Service("GlobalServiceImpl")
public class GlobalServiceImpl implements GlobalService {
    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public Employee login(String sn, String password) {
        Employee employee = employeeDao.select(sn);
        if (employee != null && employee.getPassword().equals(password))
            return employee;
        return null;
    }

    @Override
    public void changePassword(Employee employee) {
        employeeDao.update(employee);
    }
}
