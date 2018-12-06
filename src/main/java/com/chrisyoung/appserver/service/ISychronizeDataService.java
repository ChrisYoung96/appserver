package com.chrisyoung.appserver.service;

import com.chrisyoung.appserver.domain.*;
import com.chrisyoung.appserver.dto.SychroNizeBillAndRecords;
import com.chrisyoung.appserver.dto.SychronizeDataItem;
import com.chrisyoung.appserver.dto.SychronizeDataModel;
import io.swagger.annotations.Api;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-12 20:14
 * @description: 数据同步服务接口
 **/

public interface ISychronizeDataService {
    boolean sychronizeUserAuthsC2S(Queue<SychronizeDataItem<UserAuths>> datas);

    boolean sychronizeRecordsC2S(Queue<SychronizeDataItem<Record>> datas);

    boolean sychronizeBillsC2S(Queue<SychronizeDataItem<Bill>> datas);

    boolean sychronizeUserDiyKindC2S(Queue<SychronizeDataItem<UserDiy>> datas);

    LinkedList<SychronizeDataItem<Record>> sychronizeRecordsS2C(String bId);

    LinkedList<SychronizeDataItem<Bill>> sychronizeBillsS2C(String uId);

    LinkedList<SychronizeDataItem<UserDiy>> sychronizeUserDiyKindS2C(String uId);

    LinkedList<SychronizeDataModel> loadData4FirstTime(String uId);

    boolean sychronizeDataC2S(LinkedList<SychronizeDataModel> datas);

    AppUser sychronizeUserInfoS2C(String uId);


}
