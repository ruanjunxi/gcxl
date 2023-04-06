package com.example.gcxl.mapper;

import com.example.gcxl.domain.Logontolog;
import com.example.gcxl.domain.PurchasingContract;
import com.example.gcxl.domain.User;
import org.apache.ibatis.annotations.*;

import javax.sound.midi.Soundbank;
import java.util.Date;
import java.util.List;

/**
 * @author Eason
 * @ClassName:Usermapper
 * @data:2022/4/12 19:20
 */

public interface UserMapper {

    @Select("select * from User")
    List<User> findAll();

    @Select("select * from User where account = #{username}")
    User findByName(@Param("username") String name);

    @Select("select password from user where account = #{username}")
    String findPswByName(@Param("username") String UserName);

    @Insert("insert into user(account,password) value (#{account},#{password})")
    boolean save(User user);

    @Update("update user set password =#{newPassword} where account=#{account}")
    boolean updatePassword(String newPassword, String account);

    @Select("select COALESCE(count(*),0) from user")
    int getCountUser();

    @Insert("insert into logontolog value (#{account},#{ip},now())")
    boolean insertLogonLog(Logontolog logontolog);

    @Select("<script>" +
            "select * FROM logontolog " +
            "<where>" +
            "1=1 "+
            "<if test=\"account !=null  and account!=''\">and account like '%${account}%'</if>" +
            "<if test=\" startDate !=null and startDate!='' and endDate !=null and endDate !='' \">and time between #{startDate} and #{endDate}</if>" +
            "</where>" +
            "</script>"
    )
    List<Logontolog> selectByCondition(String account,String startDate,String endDate);

    @Select("select * FROM logontolog")
    List<Logontolog> selectAllLogon();
}
