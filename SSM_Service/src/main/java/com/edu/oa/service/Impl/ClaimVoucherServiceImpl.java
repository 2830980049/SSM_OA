package com.edu.oa.service.Impl;

import com.edu.oa.dao.ClaimVoucherDao;
import com.edu.oa.dao.ClaimVoucherItemDao;
import com.edu.oa.dao.DealRecordDao;
import com.edu.oa.entity.ClaimVoucher;
import com.edu.oa.entity.ClaimVoucherItem;
import com.edu.oa.entity.DealRecord;
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
}
