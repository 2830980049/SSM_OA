package com.edu.oa.controller;

import com.edu.oa.entity.Department;
import com.edu.oa.entity.Employee;
import com.edu.oa.global.Contant;
import com.edu.oa.service.DepartmentService;
import com.edu.oa.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/20 14:22
 */
@Controller("EmployeeController")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeService employeService;

    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        //JSP 使用SpringMVC时使用
        map.put("list", employeService.getAll());
        //因为spring-web里面配置了前后缀
        return "employee_list";
    }

    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("employee", new Employee());
        map.put("dlist", departmentService.getAll());
        map.put("plist", Contant.getPosts());
        //因为spring-web里面配置了前后缀
        return "employee_add";
    }

    @RequestMapping("/add")
    public String Add(Employee employee){
        employeService.add(employee);
        //因为spring-web里面配置了前后缀
        return "redirect:list";
    }

    @RequestMapping(value = "/to_update", params = "sn")
    public String toUpdate(String sn, Map<String,Object> map){
        //JSP 使用SpringMVC时使用
        map.put("employee", employeService.get(sn));
        map.put("dlist", departmentService.getAll());
        map.put("plist", Contant.getPosts());
        //因为spring-web里面配置了前后缀
        return "employee_update";
    }

    @RequestMapping("/update")
    public String Update(Employee employee){
        employeService.edit(employee);
        //因为spring-web里面配置了前后缀
        return "redirect:list";
    }

    @RequestMapping(value = "/remove", params = "sn")
    public String remove(String sn){
        employeService.remove(sn);
        //因为spring-web里面配置了前后缀
        return "redirect:list";
    }
}
