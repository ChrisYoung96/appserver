package com.chrisyoung.appserver.service.impl;

import com.chrisyoung.appserver.domain.Bill;
import com.chrisyoung.appserver.service.IBillService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-16 09:58
 * @description: 账本服务类
 **/

@Service
public class BillService implements IBillService {
    @Override
    public boolean addBill(Bill bill) {
        return false;
    }

    @Override
    public boolean modifyBill(Bill bill) {
        return false;
    }

    @Override
    public boolean deleteBill(String billId) {
        return false;
    }

    @Override
    public List<Bill> showAllBills(String uId) {
        return null;
    }
}
