package com.chrisyoung.appserver.dao;

import com.chrisyoung.appserver.domain.UserDiy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-12-06 11:33
 * @description: 对用户自定义收支类型表的操作
 **/
@Mapper
@Repository
public interface UserDiyDao {
    @Insert("insert into user_diy (u_id,d_type,d_kind) values(#{uId},#{dType},#{dKind})")
    int addNewKind(UserDiy newkind);

    @Delete("delete from user_diy where d_id=#{dId}")
    int deleteKind(@Param("dId") long dId);

    @Select("select * from user_diy where u_id=#{uId}")
    @Results({
            @Result(property = "dId", column = "d_id"),
            @Result(property = "uId", column = "u_id"),
            @Result(property = "dType", column = "d_type"),
            @Result(property = "dKind", column = "d_kind")
    })
    ArrayList<UserDiy> findAllDiyKind(@Param("uId") String uId);

}
