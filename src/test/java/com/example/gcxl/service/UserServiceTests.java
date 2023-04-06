package com.example.gcxl.service;

import com.example.gcxl.domain.User;
import com.example.gcxl.service.ex.SeviceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eason
 * @ClassName:UserServiceTests
 * @data:2022/4/16 14:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;
    @Test
    public void register(){
        try {
            User user = new User();
            user.setPassword("123");
            user.setAccount("1234");
            userService.register(user);
            System.out.println("注册成功");
        }catch (SeviceException e){
            System.out.println("注册失败"+e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }
    @Test
    public void changePassword(){
        try {
            userService.changePassword("1234","1234","eason");
            System.out.println("修改成功");
        }catch (SeviceException e){
            System.out.println(e.getMessage());
        }
    }

}
