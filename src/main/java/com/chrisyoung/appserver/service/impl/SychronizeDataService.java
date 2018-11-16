package com.chrisyoung.appserver.service.impl;

import com.chrisyoung.appserver.domain.AppUser;
import com.chrisyoung.appserver.domain.Bill;
import com.chrisyoung.appserver.domain.Record;
import com.chrisyoung.appserver.domain.UserAuths;
import com.chrisyoung.appserver.dto.SychronizeDataItem;
import com.chrisyoung.appserver.dto.SychronizeDataModel;
import com.chrisyoung.appserver.service.ISychronizeDataService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-16 09:59
 * @description: 数据同步服务类
 **/

@Service
public class SychronizeDataService implements ISychronizeDataService {
    @Override
    public boolean sychronizeUserAuthsC2S(Queue<SychronizeDataItem<UserAuths>> datas) {
        return false;
    }

    @Override
    public boolean sychronizeRecordsC2S(Queue<SychronizeDataItem<Record>> datas) {
        return false;
    }

    @Override
    public boolean sychronizeBillsC2S(Queue<SychronizeDataItem<Bill>> datas) {
        return false;
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
    public AppUser loadUserInfo4FirstTime(String uId) {
        return null;
    }
}
