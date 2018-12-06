package com.chrisyoung.appserver.service.impl;

import com.chrisyoung.appserver.constant.OptCode;
import com.chrisyoung.appserver.dao.*;
import com.chrisyoung.appserver.domain.*;
import com.chrisyoung.appserver.dto.SychronizeDataItem;
import com.chrisyoung.appserver.dto.SychronizeDataModel;
import com.chrisyoung.appserver.service.ISychronizeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-16 09:59
 * @description: 数据同步服务类
 **/

@Service
public class SychronizeDataService implements ISychronizeDataService {

    private final AppUserDao appUserDao;
    private final UserAuthsDao userAuthsDao;
    private final BillDao billDao;
    private final RecordDao recordDao;
    private final UserDiyDao userDiyDao;

    @Autowired
    public SychronizeDataService(AppUserDao appUserDao, UserAuthsDao userAuthsDao, BillDao billDao, RecordDao recordDao, UserDiyDao userDiyDao) {
        this.appUserDao = appUserDao;
        this.userAuthsDao = userAuthsDao;
        this.billDao = billDao;
        this.recordDao = recordDao;
        this.userDiyDao = userDiyDao;
    }

    @Override
    public boolean sychronizeUserAuthsC2S(Queue<SychronizeDataItem<UserAuths>> datas) {
        return false;
    }

    @Override
    public boolean sychronizeRecordsC2S(Queue<SychronizeDataItem<Record>> datas) {
        int result=0;
        if (datas.isEmpty()) {
            return false;
        } else {
            while (!datas.isEmpty()) {
                SychronizeDataItem<Record> item = datas.poll();
                if (item != null) {
                    switch (item.getOptCode()) {
                        case OptCode.INSERT:
                            result=recordDao.addRecord(item.getData());
                            break;
                        case OptCode.UPDATE:
                            result=recordDao.updateRecord(item.getData());
                            break;
                        case OptCode.DELETE:
                            result=recordDao.deleteRecord(item.getData().getRId());
                            break;
                    }
                }
                if(result==0){
                    return false;
                }
            }
        }
        return datas.isEmpty();
    }

    @Override
    public boolean sychronizeBillsC2S(Queue<SychronizeDataItem<Bill>> datas) {
        int result=0;
        if(datas.isEmpty()){
            return false;
        }else {
            while(!datas.isEmpty()){
                SychronizeDataItem<Bill> item=datas.poll();
                if (item != null) {
                    switch (item.getOptCode()) {
                        case OptCode.INSERT:
                            result=billDao.addBill(item.getData());
                            break;
                        case OptCode.UPDATE:
                            result= billDao.updateBill(item.getData());
                            break;
                        case OptCode.DELETE:
                            result= billDao.deleteBill(item.getData().getBId());
                            break;
                    }
                }
                if(result==0){
                    return false;
                }

            }
        }
        return datas.isEmpty();
    }

    @Override
    public boolean sychronizeUserDiyKindC2S(Queue<SychronizeDataItem<UserDiy>> datas) {
        int result=0;
        if(datas.isEmpty()){
            return false;
        }else {
            while(!datas.isEmpty()){
                SychronizeDataItem<UserDiy> item=datas.poll();
                if (item != null) {
                    switch (item.getOptCode()){
                        case OptCode.INSERT:
                            result=userDiyDao.addNewKind(item.getData());
                            break;
                        case OptCode.DELETE:
                            result=userDiyDao.deleteKind((int)item.getData().getDId());
                            break;
                    }
                }
            }

            if(result==0){
                return false;
            }
        }
        return datas.isEmpty();
    }

    @Override
    public LinkedList<SychronizeDataItem<Record>> sychronizeRecordsS2C(String bId) {
        LinkedList<SychronizeDataItem<Record>> allrecords=new LinkedList<>();
        List<Record> records=recordDao.findAllRecord(bId);
        if(!records.isEmpty()){
            for (Record r:records
                 ) {
                SychronizeDataItem<Record> record=new SychronizeDataItem<>();
                record.setData(r);
                record.setOptCode(OptCode.INSERT);
                allrecords.addLast(record);
            }
        }
        return allrecords;
    }

    @Override
    public LinkedList<SychronizeDataItem<Bill>> sychronizeBillsS2C(String uId) {
        LinkedList<SychronizeDataItem<Bill>> allBills=new LinkedList<>();
        List<Bill> bills=billDao.findAllBills(uId);
        if(!bills.isEmpty()){
            for(Bill b:bills){
                SychronizeDataItem<Bill> bill=new SychronizeDataItem<>();
                bill.setOptCode(OptCode.INSERT);
                bill.setData(b);
                allBills.addLast(bill);
            }
        }
        return allBills;
    }

    @Override
    public LinkedList<SychronizeDataItem<UserDiy>> sychronizeUserDiyKindS2C(String uId) {
        LinkedList<SychronizeDataItem<UserDiy>> allDiyKinds=new LinkedList<>();
        List<UserDiy> userDiys=userDiyDao.findAllDiyKind(uId);
        if(!userDiys.isEmpty()){
            for(UserDiy d:userDiys){
                SychronizeDataItem<UserDiy> diyKind=new SychronizeDataItem<>();
                diyKind.setData(d);
                diyKind.setOptCode(OptCode.INSERT);
                allDiyKinds.addLast(diyKind);
            }
        }
        return allDiyKinds;
    }

    @Override
    public LinkedList<SychronizeDataModel> loadData4FirstTime(String uId) {
        return null;
    }

    @Override
    public boolean sychronizeDataC2S(LinkedList<SychronizeDataModel> datas) {
        return false;
    }

    @Override
    public AppUser sychronizeUserInfoS2C(String uId) {
        return appUserDao.findUserById(uId);
    }
}
