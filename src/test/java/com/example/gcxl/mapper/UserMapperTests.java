package com.example.gcxl.mapper;

import com.example.gcxl.domain.PurchasingContract;
import com.example.gcxl.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

/**
 * @author Eason
 * @ClassName:UserMapperTests
 * @data:2022/4/15 20:13
 */
//表示当前的类是一个测试类，不会随同项目一起打包
@SpringBootTest
//表示启动这个单元测试类，单元测试类是不能够运行的
@RunWith(SpringRunner.class)
public class UserMapperTests {
    //idea有检测的功能，接口是不能直接创建bean的，动态代理技术来解决

    @Autowired
    private UserMapper userMapper;
    @Test
    public void save(){
        /**
         * 单元测试方法可以单独独立运行，不用启动整个项目
         * 1、必须被@test注解修饰
         * 2、返回类型必须是void
         * 3、方法的参数列表不指定任何类型
         * 4、方法的访问修饰符必须是public
         */
        User user = new User();
        user.setAccount("aa");
        user.setPassword("22");
        boolean rows=userMapper.save(user);
        System.out.println(rows);
    }
    @Test
    public void findByName(){
        User user =userMapper.findByName("001");
        System.out.println(user);
    }
    @Test
    public void updatePassword(){
        boolean flag=userMapper.updatePassword("123","eason");
        System.out.println(flag);
    }

    @Test
    public void getCountUser(){
        int countUser = userMapper.getCountUser();
        System.out.println(countUser);
    }

   @Test
   public void test(){
       int arrInt[] = {-2135563002,-2147396491,-2147482097,-2147443117,-2034172319};
       System.out.println(randomString(arrInt[0]) + " " + randomString(-147909649)+
               randomString(arrInt[1]) + " " +
               randomString(arrInt[2]) + " " +
               randomString(arrInt[3]) + " " +
               randomString(arrInt[4]));
   }
        public static String randomString(int i) {
            Random ran = new Random(i);
            StringBuilder sb = new StringBuilder();
            while (true) {
                int k = ran.nextInt(27);
                if (k == 0)
                    break;

                sb.append((char) ('`' + k));
            }
            return sb.toString();
        }

}
