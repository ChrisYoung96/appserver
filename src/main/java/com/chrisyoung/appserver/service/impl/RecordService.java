package com.chrisyoung.appserver.service.impl;

import com.chrisyoung.appserver.domain.Record;
import com.chrisyoung.appserver.service.IRecordService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-16 09:59
 * @description: 交易记录服务类
 **/

@Service
public class RecordService implements IRecordService {
    @Override
    public boolean addNewRecord(Record record) {
        return false;
    }

    @Override
    public List<Record> showAllRecord(String bId) {
        return null;
    }

    @Override
    public boolean modifyRecord(Record record) {
        return false;
    }

    @Override
    public boolean deleteRecord(String rId) {
        return false;
    }

    @Override
    public List<Record> showRecordAtTimeRange(String bId, Timestamp sTime, Timestamp eTime) {
        return null;
    }
}
