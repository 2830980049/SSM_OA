package com.edu.oa.controller;

import com.edu.oa.entity.Department;
import com.edu.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/20 12:50
 */
@Controller("DepartmentController")
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        //JSP 使用SpringMVC时使用
        map.put("list", departmentService.getAll());
        //因为spring-web里面配置了前后缀
        return "department_list";
    }

    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("department", new Department());
        //因为spring-web里面配置了前后缀
        return "department_add";
    }

    @RequestMapping("/add")
    public String Add(Department department){
        departmentService.add(department);
        //因为spring-web里面配置了前后缀
        return "redirect:list";
    }

    @RequestMapping(value = "/to_update", params = "sn")
    public String toUpdate(String sn, Map<String,Object> map){
        //JSP 使用SpringMVC时使用
        map.put("department", departmentService.get(sn));
        //因为spring-web里面配置了前后缀
        return "department_update";
    }

    @RequestMapping("/update")
    public String Update(Department department){
        departmentService.edit(department);
        //因为spring-web里面配置了前后缀
        return "redirect:list";
    }

    @RequestMapping(value = "/remove", params = "sn")
    public String remove(String sn){
        departmentService.remove(sn);
        //因为spring-web里面配置了前后缀
        return "redirect:list";
    }
}
