package com.edu.oa.service.Impl;

import com.edu.oa.dao.ClaimVoucherDao;
import com.edu.oa.dao.ClaimVoucherItemDao;
import com.edu.oa.dao.DealRecordDao;
import com.edu.oa.dao.EmployeeDao;
import com.edu.oa.entity.ClaimVoucher;
import com.edu.oa.entity.ClaimVoucherItem;
import com.edu.oa.entity.DealRecord;
import com.edu.oa.entity.Employee;
import com.edu.oa.global.Contant;
import com.edu.oa.service.ClaimVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/21 18:04
 */
@Service("ClaimVoucherServiceImpl")
public class ClaimVoucherServiceImpl implements ClaimVoucherService {
    @Autowired
    private ClaimVoucherDao claimVoucherDao;
    @Autowired
    private ClaimVoucherItemDao claimVoucherItemDao;
    @Autowired
    private DealRecordDao dealRecordDao;
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setCreate_time(new Date());
        claimVoucher.setNext_deal_sn(claimVoucher.getCreate_sn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucherDao.insert(claimVoucher);
        for (ClaimVoucherItem item:items){
            item.setClaim_voucher_id(claimVoucher.getId());
            System.out.println("item "+item);
            claimVoucherItemDao.insert(item);
        }
        System.out.println("claimVoucher "+claimVoucher);
    }

    @Override
    public ClaimVoucher get(int id) {
        return claimVoucherDao.select(id);
    }

    @Override
    public List<ClaimVoucherItem> getItems(int cvid) {
        return claimVoucherItemDao.selectByClaimVoucher(cvid);
    }

    @Override
    public List<DealRecord> getRecords(int cvid) {
        return dealRecordDao.selectByClaimVoucher(cvid);
    }

    @Override
    public List<ClaimVoucher> getForSelf(String sn) {
        return claimVoucherDao.selectByCreateSn(sn);
    }

    @Override
    public List<ClaimVoucher> getForDeal(String sn) {
        return claimVoucherDao.selectByNextDealSn(sn);
    }

    @Override
    public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setNext_deal_sn(claimVoucher.getCreate_sn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucherDao.update(claimVoucher);

        List<ClaimVoucherItem> olds = claimVoucherItemDao.selectByClaimVoucher(claimVoucher.getId());
        for (ClaimVoucherItem old:olds){
            boolean flag = false;
            for (ClaimVoucherItem item:items){
                if(item.getId() == old.getId()){
                    flag = true;
                    break;
                }
            }
            if(!flag)
                claimVoucherItemDao.delete(old.getId());
        }
        for (ClaimVoucherItem item:items){
            item.setClaim_voucher_id(claimVoucher.getId());
            if(item.getId() != null && item.getId() > 0)
                claimVoucherItemDao.update(item);
            else
                claimVoucherItemDao.insert(item);
        }
    }

    @Override
    public void submit(int id) {
        ClaimVoucher claimVoucher = claimVoucherDao.select(id);
        Employee employee = employeeDao.select(claimVoucher.getCreate_sn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_SUBMIT);
        claimVoucher.setNext_deal_sn(employeeDao.selectByDepartmentAndPost(employee.getDepartment_sn(), Contant.POST_FM).get(0).getSn());
        claimVoucherDao.update(claimVoucher);

        DealRecord dealRecord = new DealRecord();
        dealRecord.setDeal_way(Contant.DEAL_SUBMIT);
        dealRecord.setDeal_sn(employee.getSn());
        dealRecord.setClaim_voucher_id(id);
        dealRecord.setDeal_result(Contant.CLAIMVOUCHER_SUBMIT);
        dealRecord.setDeal_time(new Date());
        dealRecord.setComment("无");
        dealRecordDao.insert(dealRecord);
    }

    @Override
    public void deal(DealRecord dealRecord) {
        ClaimVoucher claimVoucher = claimVoucherDao.select(dealRecord.getClaim_voucher_id());
        Employee employee = employeeDao.select(dealRecord.getDeal_sn());
        if (dealRecord.getDeal_way().equals(Contant.DEAL_PASS)){
            if (claimVoucher.getTotal_amount() <= Contant.LIMIT_CHECK || employee.getPost().equals(Contant.POST_GM)) {
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                claimVoucher.setNext_deal_sn(employeeDao.selectByDepartmentAndPost(null, Contant.POST_CASHIER).get(0).getSn());

                dealRecord.setDeal_time(new Date());
                dealRecord.setDeal_result(Contant.CLAIMVOUCHER_APPROVED);
            }
            else{
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_RECHECK);
                claimVoucher.setNext_deal_sn(employeeDao.selectByDepartmentAndPost(null, Contant.POST_GM).get(0).getSn());

                dealRecord.setDeal_time(new Date());
                dealRecord.setDeal_result(Contant.CLAIMVOUCHER_RECHECK);
            }
        }
        else if(dealRecord.getDeal_way().equals(Contant.DEAL_BACK)){
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_BACK);
            claimVoucher.setNext_deal_sn(claimVoucher.getCreate_sn());

            dealRecord.setDeal_time(new Date());
            dealRecord.setDeal_result(Contant.CLAIMVOUCHER_BACK);
        }
        else if(dealRecord.getDeal_way().equals(Contant.DEAL_REJECT)){
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_TERMINATED);
            claimVoucher.setNext_deal_sn(null);

            dealRecord.setDeal_time(new Date());
            dealRecord.setDeal_result(Contant.CLAIMVOUCHER_TERMINATED);
        }
        else if(dealRecord.getDeal_way().equals(Contant.DEAL_PAID)){
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_PAID);
            claimVoucher.setNext_deal_sn(null);

            dealRecord.setDeal_time(new Date());
            dealRecord.setDeal_result(Contant.CLAIMVOUCHER_PAID);
        }
        claimVoucherDao.update(claimVoucher);
        dealRecordDao.insert(dealRecord);
    }
}
