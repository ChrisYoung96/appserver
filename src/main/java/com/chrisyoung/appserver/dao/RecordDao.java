package com.chrisyoung.appserver.dao;

import com.chrisyoung.appserver.domain.Record;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-06 11:29
 * @description: 记录操作接口
 **/
@Mapper
@Repository
public interface RecordDao {
    @Insert("insert into record values(#{rId},#{bId},#{rType},#{rKind},#{rMoney},#{rWay},#{rTime},#{rDesc},#{rVersion})")
    int addRecord(Record record);

    @Update("update record set r_type=#{rType},r_kind=#{rKind},r_money=#{rMoney},r_way=#{rWay},r_time=#{rTime},r_desc=#{rDesc},r_version=#{rVersion} where r_id=#{rId}")
    int updateRecord(Record record);

    @Select("select * from record where b_id=#{bId} order by r_time")
    @Results({
            @Result(property = "rId",column = "r_id"),
            @Result(property = "bId",column = "b_id"),
            @Result(property = "rType",column = "r_type"),
            @Result(property = "rKind",column = "r_kind"),
            @Result(property = "rMoney",column = "r_money"),
            @Result(property = "rWay",column = "r_way"),
            @Result(property = "rTime",column = "r_time"),
            @Result(property = "rDesc",column = "r_desc"),
            @Result(property = "rVersion",column = "r_version")
    })
    List<Record> findAllRecord(@Param("bId") String bId);

    @Select("select * from record where r_id=#{rId}")
    @Results({
            @Result(property = "rId",column = "r_id"),
            @Result(property = "bId",column = "b_id"),
            @Result(property = "rType",column = "r_type"),
            @Result(property = "rKind",column = "r_kind"),
            @Result(property = "rMoney",column = "r_money"),
            @Result(property = "rWay",column = "r_way"),
            @Result(property = "rTime",column = "r_time"),
            @Result(property = "rDesc",column = "r_desc"),
            @Result(property = "rVersion",column = "r_version")
    })
    Record findRecordById(@Param("rId") String rid);

    @Select("select * from record where b_id=#{bId} and r_time between #{sTime} and #{eTime} order by r_time")
    @Results({
            @Result(property = "rId",column = "r_id"),
            @Result(property = "bId",column = "b_id"),
            @Result(property = "rType",column = "r_type"),
            @Result(property = "rKind",column = "r_kind"),
            @Result(property = "rMoney",column = "r_money"),
            @Result(property = "rWay",column = "r_way"),
            @Result(property = "rTime",column = "r_time"),
            @Result(property = "rDesc",column = "r_desc"),
            @Result(property = "rVersion",column = "r_version")
    })
    List<Record> findRecordsBetweenTime(@Param("bId") String bId, @Param("sTime")Time sTime, @Param("eTime") Time eTime);

    @Delete("delete from record where r_id=#{rId}")
    int deleteRecord(@Param("rId") String rId);

    @Select("select r_version from record where r_id=#{rId}")
    int findRecordVersion(String rId);
}
