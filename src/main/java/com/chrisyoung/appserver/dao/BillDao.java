package com.chrisyoung.appserver.dao;

import com.chrisyoung.appserver.domain.Bill;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-06 11:28
 * @description: 账单接口
 **/
@Mapper
@Repository
public interface BillDao {

    //添加新的账本
    @Insert("insert into bill values (#{bId},#{uId},#{bName},#{bDate},#{bDesc}) ")
    int addBill(Bill bill);

    //查找某用户所有账本信息
    @Select("select * from bill where u_id=#{uId}")
    @Results({
            @Result(property = "bId",column = "b_id"),
            @Result(property = "bName",column = "b_name"),
            @Result(property = "bDate",column = "b_date"),
            @Result(property = "bDesc",column = "b_desc")
    })
    List<Bill> findAllBills(@Param("uId") String uid);

    //根据账本编号查找账本
    @Select("select * from bill where b_id=#{bId}")
    @Results({
            @Result(property = "bId",column = "b_id"),
            @Result(property = "bName",column = "b_name"),
            @Result(property = "bDate",column = "b_date"),
            @Result(property = "bDesc",column = "b_desc")
    })
    Bill findBillById(@Param("bId") String bId);


    //更新账本信息
    @Update("update bill set b_name=#{bName},b_desc=#{bDesc} where b_id=#{bId}")
    int updateBill(Bill bill);

    //删除账本信息
    @Delete("delete from bill where b_id=#{bId}")
    int deleteBill(@Param("bId") String bId);
}
