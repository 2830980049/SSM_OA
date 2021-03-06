package com.edu.oa.controller;

import com.edu.oa.dto.ClaimVoucherInfo;
import com.edu.oa.entity.ClaimVoucher;
import com.edu.oa.entity.DealRecord;
import com.edu.oa.entity.Employee;
import com.edu.oa.global.Contant;
import com.edu.oa.service.ClaimVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/21 18:16
 */
@Controller("ClaimVoucherController")
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {
    @Autowired
    private ClaimVoucherService claimVoucherService;

    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("items", Contant.getItems());
        map.put("info",new ClaimVoucherInfo());
        return "claim_voucher_add";
    }

    @RequestMapping("/add")
    public String add(HttpSession session, ClaimVoucherInfo info){
        Employee employee = (Employee)session.getAttribute("employee");
        System.out.println(employee);
        info.getClaimVoucher().setCreate_sn(employee.getSn());
        System.out.println(info);
        claimVoucherService.save(info.getClaimVoucher(), info.getItems());
        return "redirect:deal";
    }

    @RequestMapping("/detail")
    public String detail(Map<String,Object> map,int id){
        map.put("claimVoucher", claimVoucherService.get(id));
        map.put("items", claimVoucherService.getItems(id));
        System.out.println("id "+id);
        System.out.println("claimVoucher "+claimVoucherService.get(id));
        map.put("records", claimVoucherService.getRecords(id));
        return "claim_voucher_detail";
    }

    @RequestMapping("/self")
    public String self(Map<String,Object> map, HttpSession session){
        Employee employee = (Employee)session.getAttribute("employee");
        map.put("list", claimVoucherService.getForSelf(employee.getSn()));
        return "claim_voucher_self";
    }

    @RequestMapping("/deal")
    public String deal(Map<String,Object> map, HttpSession session){
        Employee employee = (Employee)session.getAttribute("employee");
        map.put("list", claimVoucherService.getForDeal(employee.getSn()));
        return "claim_voucher_deal";
    }

    @RequestMapping("/to_update")
    public String toUpdate(Map<String,Object> map, int id){
        map.put("items", Contant.getItems());
        ClaimVoucherInfo info = new ClaimVoucherInfo();
        info.setClaimVoucher(claimVoucherService.get(id));
        info.setItems(claimVoucherService.getItems(id));
        map.put("info", info);
        return "claim_voucher_update";
    }

    @RequestMapping("/update")
    public String update(ClaimVoucherInfo info, HttpSession session){
        Employee employee = (Employee)session.getAttribute("employee");
        info.getClaimVoucher().setCreate_sn(employee.getSn());
        claimVoucherService.update(info.getClaimVoucher(), info.getItems());
        return "redirect:deal";
    }

    @RequestMapping("/submit")
    public String submit(int id){
        claimVoucherService.submit(id);
        return "redirect:deal";
    }

    @RequestMapping("/to_check")
    public String toCheck(Map<String,Object> map, int id){
        map.put("claimVoucher", claimVoucherService.get(id));
        map.put("items", claimVoucherService.getItems(id));
        map.put("records", claimVoucherService.getRecords(id));
        DealRecord dealRecord = new DealRecord();
        dealRecord.setClaim_voucher_id(id);
        map.put("record", dealRecord);
        return "claim_voucher_check";
    }

    @RequestMapping("/check")
    public String check(DealRecord dealRecord, HttpSession session){
        Employee employee = (Employee)session.getAttribute("employee");
        dealRecord.setDeal_sn(employee.getSn());
        claimVoucherService.deal(dealRecord);
        return "redirect:deal";
    }

}
