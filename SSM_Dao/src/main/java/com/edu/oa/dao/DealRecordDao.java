package com.edu.oa.dao;

import com.edu.oa.entity.DealRecord;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/21 13:42
 */
public interface DealRecordDao {
    void insert(DealRecord dealRecord);
    List<DealRecord> selectByClaimVoucher(int cvid);
}
