package com.chrisyoung.appserver.dao;

import com.chrisyoung.appserver.domain.AppUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-06 11:27
 * @description: 用户操作接口
 **/
@Mapper
@Repository
public interface AppUserDao {
    @Insert("insert into app_user values(#{uId},#{uName},#{uSex},#{uBirthday},#{uPhone},#{uMail},#{uPhoto})")
    int addUser(AppUser user);

    @Select("select * from app_user where u_id=#{uId}")
    @Results({
            @Result(property ="uId",column = "u_id"),
            @Result(property = "uName",column = "u_name"),
            @Result(property = "uSex",column = "u_sex"),
            @Result(property = "uBirthday",column = "u_birthday"),
            @Result(property = "uPhone",column = "u_phone"),
            @Result(property = "uMail",column = "u_mail"),
            @Result(property = "uPhoto",column = "u_photo")
    })
    AppUser findUserById(@Param("uId") String uId);

    @Update("update app_user set u_name=#{uName},u_sex=#{uSex},u_birthday=#{uBirthday},u_phone=#{uPhone},u_mail=#{uMail},u_photo=#{uPhoto} where u_id=#{uId}")
    int updateUserInfo(AppUser user);

    @Update("update app_user set u_photo=#{photoPath} where u_id=#{uId}")
    int updateUserPhoto(@Param("uId") String uId,@Param("photoPath") String photoPath);

    @Delete("delete from app_user where u_id=#{uId}")
    int deleteUser(@Param("uId") String uId);

}
