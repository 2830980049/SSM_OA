package com.edu.oa.dto;

import com.edu.oa.entity.ClaimVoucher;
import com.edu.oa.entity.ClaimVoucherItem;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/21 18:15
 */
public class ClaimVoucherInfo {
    private ClaimVoucher claimVoucher;
    private List<ClaimVoucherItem> items;

    @Override
    public String toString() {
        return "ClaimVoucherInfo{" +
                "claimVoucher=" + claimVoucher +
                ", items=" + items +
                '}';
    }

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public List<ClaimVoucherItem> getItems() {
        return items;
    }

    public void setItems(List<ClaimVoucherItem> items) {
        this.items = items;
    }
}
