package com.chrisyoung.appserver.service;

import com.chrisyoung.appserver.domain.Bill;

import java.util.List;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-06 11:36
 * @description: 账本服务接口
 **/

public interface IBillService {
    boolean addBill(Bill bill);

    boolean modifyBill(Bill bill);

    boolean deleteBill(String billId);

    List<Bill> showAllBills(String uId);


}
