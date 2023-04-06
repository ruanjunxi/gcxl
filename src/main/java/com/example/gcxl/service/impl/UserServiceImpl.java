package com.example.gcxl.service.impl;

import com.example.gcxl.domain.*;
import com.example.gcxl.mapper.UserMapper;
import com.example.gcxl.service.UserService;
import com.example.gcxl.service.ex.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author Eason
 * @ClassName:UserServiceImpl
 * @data:2022/4/12 19:27
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Autowired
    private UserMapper usermapper;
    private final String SALT = "hshshshhsh";

    //    @Override
//    public ResultData login(User user) {
//        User user1 = usermapper.findByName(user.getAccount());
//        if(user1!=null){
//            String pw = usermapper.findPswByName(user.getAccount());
//            if (pw.equals(user.getPassword())){
//                return ResultData.success(user.getAccount()+"登录成功，欢迎");
//            }else {
//                return ResultData.fail("登录失败，密码错误") ;
//            }
//        }else {
//            return ResultData.fail("登录失败，账户不存在");
//        }
//    }
//    @Override
//    public ResultData login(User user, String code, HttpServletRequest req) {
//        User user1 = usermapper.findByName(user.getAccount());
//        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        System.out.println(token);
//        System.out.println(code);
//
//        if (token != null && token.equalsIgnoreCase(code)) {
//            if (user1 != null) {
//                String pw = usermapper.findPswByName(user.getAccount());
//                if (pw.equals(user.getPassword())) {
//                    req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
//                    return ResultData.success(user.getAccount() + "登录成功，欢迎");
//                } else {
//                    return ResultData.fail("登录失败，密码错误");
//                }
//            } else {
//                return ResultData.fail("登录失败，账户不存在");
//            }
//        } else {
//            return ResultData.fail("登录失败，验证码不正确");
//        }
//    }


    @Override
    public User login(User user, HttpServletRequest req) {
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        User result = usermapper.findByName(user.getAccount());
        if (token == null || !token.equalsIgnoreCase(user.getCode())) {
            throw new VerificationCodeErrorException("验证码错误");
        }
        if (result == null) {
            throw new UserNotFoundException("用户不存在");
        }
        String newMd5pw = getMD5Password(user.getPassword(), SALT);
        if (!newMd5pw.equals(result.getPassword())) {
            throw new PasswordNotMatchException("用户密码错误");
        }
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        result.setPassword(null);
        return result;
    }

    @Override
    public void register(User user) {
        User result = usermapper.findByName(user.getAccount());
        if (result != null) {
            throw new UsernameDuplicatedException("用户名已经被占用");
        }
        String oldpw = user.getPassword();
        String password = getMD5Password(oldpw, SALT);
        user.setPassword(password);
        boolean flag = usermapper.save(user);
        if (flag == false) {
            throw new InsertException("未知错误");
        }
    }

    @Override
    public List<User> getall() {
        return usermapper.findAll();
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String account) {
        User result = usermapper.findByName(account);
        System.out.println(result);
        String oldMd5Password = getMD5Password(oldPassword, SALT);
        System.out.println(oldMd5Password);
        if (!result.getPassword().equals(oldMd5Password)) {
            throw new PasswordNotMatchException("原密码错误");
        }
        String newMD5Password = getMD5Password(newPassword, SALT);
        boolean b = usermapper.updatePassword(newMD5Password, account);
        if (!b) {
            throw new UpdateException("更新数据产生异常");
        }
    }

    @Override
    public int getCountUser() {
        int countUser = usermapper.getCountUser();
        return countUser;
    }


    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes(StandardCharsets.UTF_8)).toUpperCase(Locale.ROOT);
        }
        return password;
    }

    @Override
    public void insertLogonLog(Logontolog logontolog) {
        boolean b = usermapper.insertLogonLog(logontolog);
        if (!b) {
            throw new InsertException("插入过程出现错误");
        }
    }

    @Override
    public PageInfo<Logontolog> selectByCondition(String account, String startDate, String endDate, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Logontolog> logontologs = usermapper.selectByCondition(account, startDate, endDate);
        PageInfo<Logontolog> purchasingContractPageInfo = new PageInfo<>(logontologs);
        return purchasingContractPageInfo;
    }

    @Override
    public PageInfo<Logontolog> selectAllLogon(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Logontolog> logontologs = usermapper.selectAllLogon();
        PageInfo<Logontolog> purchasingContractPageInfo = new PageInfo<>(logontologs);
        return purchasingContractPageInfo;
    }
}
