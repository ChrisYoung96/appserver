package com.chrisyoung.appserver.service;

import com.chrisyoung.appserver.domain.Record;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-06 11:37
 * @description: 记录服务接口
 **/

public interface IRecordService {
    boolean addNewRecord(Record record);

    List<Record> showAllRecord(String bId);

    boolean modifyRecord(Record record);

    boolean deleteRecord(String rId);

    List<Record> showRecordAtTimeRange(String bId, Timestamp sTime, Timestamp eTime);


}
