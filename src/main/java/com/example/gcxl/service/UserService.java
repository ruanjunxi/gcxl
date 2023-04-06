package com.example.gcxl.service;

import com.example.gcxl.domain.Logontolog;
import com.example.gcxl.domain.PurchasingContract;
import com.example.gcxl.domain.ResultData;
import com.example.gcxl.domain.User;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService extends BaseService{
    User login(User user, HttpServletRequest req);
    void register(User user);
    List<User> getall();
    void changePassword(String oldPassword,String newPassword,String account);
    int getCountUser();
    void insertLogonLog(Logontolog logontolog);
    PageInfo<Logontolog> selectByCondition(String account, String startDate, String endDate, int pageNum, int pageSize);
    PageInfo<Logontolog> selectAllLogon(int pageNum, int pageSize);
}
