package com.edu.oa.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/20 11:51
 */
public class DealRecord {
    private Integer id;
    private Integer claim_voucher_id;   //报销单编号
    private String deal_sn; //处理人编号
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date deal_time;
    private String deal_way;    //处理方式
    private String deal_result; //
    private String comment;
    private Employee dealer;

    public DealRecord(Integer id, Integer claim_voucher_id, String deal_sn, Date deal_time, String deal_way, String deal_result, String comment, Employee dealer) {
        this.id = id;
        this.claim_voucher_id = claim_voucher_id;
        this.deal_sn = deal_sn;
        this.deal_time = deal_time;
        this.deal_way = deal_way;
        this.deal_result = deal_result;
        this.comment = comment;
        this.dealer = dealer;
    }

    public DealRecord() {
    }

    public Employee getDealer() {
        return dealer;
    }

    @Override
    public String toString() {
        return "DealRecord{" +
                "id=" + id +
                ", claim_voucher_id=" + claim_voucher_id +
                ", deal_sn='" + deal_sn + '\'' +
                ", deal_time=" + deal_time +
                ", deal_way='" + deal_way + '\'' +
                ", deal_result='" + deal_result + '\'' +
                ", comment='" + comment + '\'' +
                ", dealer=" + dealer +
                '}';
    }

    public void setDealer(Employee dealer) {
        this.dealer = dealer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClaim_voucher_id() {
        return claim_voucher_id;
    }

    public void setClaim_voucher_id(Integer claim_voucher_id) {
        this.claim_voucher_id = claim_voucher_id;
    }

    public String getDeal_sn() {
        return deal_sn;
    }

    public void setDeal_sn(String deal_sn) {
        this.deal_sn = deal_sn;
    }

    public Date getDeal_time() {
        return deal_time;
    }

    public void setDeal_time(Date deal_time) {
        this.deal_time = deal_time;
    }

    public String getDeal_way() {
        return deal_way;
    }

    public void setDeal_way(String deal_way) {
        this.deal_way = deal_way;
    }

    public String getDeal_result() {
        return deal_result;
    }

    public void setDeal_result(String deal_result) {
        this.deal_result = deal_result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
