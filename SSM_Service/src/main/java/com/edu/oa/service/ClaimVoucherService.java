package com.edu.oa.service;

import com.edu.oa.entity.ClaimVoucher;
import com.edu.oa.entity.ClaimVoucherItem;
import com.edu.oa.entity.DealRecord;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/21 17:22
 */
public interface ClaimVoucherService {
    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);
    ClaimVoucher get(int id);
    List<ClaimVoucherItem> getItems(int cvid);
    List<DealRecord> getRecords(int cvid);

    List<ClaimVoucher> getForSelf(String sn);
    List<ClaimVoucher> getForDeal(String sn);

    void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    void submit(int id);
    void deal(DealRecord dealRecord);
}
