package com.edu.oa.controller;

import com.edu.oa.entity.Employee;
import com.edu.oa.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/20 15:41
 */
@Controller("GlobalController")
public class GlobalController {
    @Autowired
    private GlobalService globalService;
    @RequestMapping("/to_login")
    public String toLogin(){
        System.out.println("login");
        return "login";
    }
    //多个参数需要加上@RequestParam 注解
    @RequestMapping("/login")
    public String login(HttpSession session, @RequestParam String sn, @RequestParam String password){
        Employee employee = globalService.login(sn, password);
        if(employee == null)
            return "redirect:to_login";
        session.setAttribute("employee", employee);
        return "redirect:self";
    }

    @RequestMapping("/self")
    public String self(){
        return "self";
    }

    @RequestMapping("/quit")
    public String login(HttpSession session){
        session.setAttribute("employee", null);
        return "redirect:to_login";
    }

    @RequestMapping("/to_change_password")
    public String toChangePassword(){
        return "change_password";
    }

    @RequestMapping("/change_password")
    public String changePassword(HttpSession session, @RequestParam String old, @RequestParam String new1, @RequestParam String new2){
        Employee employee = (Employee)session.getAttribute("employee");
        if(employee.getPassword().equals(old)){
            if (new1.equals(new2)){
                employee.setPassword(new1);
                globalService.changePassword(employee);
                return "redirect:self";
            }
        }
        return "redirect:to_change_password";
    }
}
