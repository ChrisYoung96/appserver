package com.chrisyoung.appserver.dao;

import com.chrisyoung.appserver.domain.UserAuths;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-06 11:30
 * @description: 用户登陆方式操作接口
 **/
@Repository
@Mapper
public interface UserAuthsDao {
    //添加用户验证条目
    @Insert("insert into user_auths(u_id,identity_type,identify,credential,refreshtoken,role) values(#{uId},#{identityType},#{identify},#{credential},#{refreshtoken},#{role})")
    int addAuth(UserAuths auth);

    //查找验证条目（如：根据手机和密码查找）
    @Select("select * from user_auths where identify=#{identify} and credential=#{credential}")
    @Results({
            @Result(property = "uId",column = "u_id"),
            @Result(property = "identityType",column = "identity_type"),
            @Result(property = "identify",column = "identify"),
            @Result(property = "credential",column = "credential"),
            @Result(property = "refreshtoken",column = "refreshtoken"),
            @Result(property = "role",column = "role")
    })
    UserAuths findAuth(@Param("identify") String identify, @Param("credential") String credential);

    //显示所有用户的所有验证条目
    @Select("select * from user_auths")
    @Results({
            @Result(property = "uaId",column = "ua_id"),
            @Result(property = "uId",column = "u_id"),
            @Result(property = "identityType",column = "identity_type"),
            @Result(property = "identify",column = "identify"),
            @Result(property = "credential",column = "credential"),
            @Result(property = "refreshtoken",column = "refreshtoken"),
            @Result(property = "role",column = "role")
    })
    List<UserAuths> findAllUserAuths();


    //显示某用户的所有验证条目
    @Select("select * from user_auths where u_id=#{uId} and identify=#{identify}")
    @Results({
            @Result(property = "uaId",column = "ua_id"),
            @Result(property = "uId",column = "u_id"),
            @Result(property = "identityType",column = "identity_type"),
            @Result(property = "identify",column = "identify"),
            @Result(property = "credential",column = "credential"),
            @Result(property = "refreshtoken",column = "refreshtoken"),
            @Result(property = "role",column = "role")
    })
    UserAuths findAuthByuId(@Param("uId") String uId,@Param("identify") String identify);


    //修改用户的某一验证条目
    @Update("update user_auths set credential=#{credential} where u_id=#{uId} and identify=#{identify}")
    int updateAuth(@Param("uId") String uId,@Param("identify") String identify,@Param("credential") String credential);
}
