package com.example.gcxl.controller;

import com.example.gcxl.controller.ex.*;
import com.example.gcxl.domain.*;
import com.example.gcxl.service.UserService;
import com.example.gcxl.service.ex.InsertException;
import com.example.gcxl.util.IpUtil;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author Eason
 * @ClassName:UserController
 * @data:2022/4/12 19:29
 */
@Slf4j
@RestController
@Api("用户信息接口")
@RequestMapping("users")
@Transactional(rollbackFor = Exception.class)
//@CrossOrigin(origins = "http://172.17.169.18:8080", maxAge = 3600,allowCredentials = "true")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public ResultData<User> login(@ApiParam("用户类") @RequestBody User user, HttpServletRequest req, HttpSession session) {
        if (user == null) {
            throw new NullPointerException("传入的数据存在空值");
        }
        String ipAddr = IpUtil.getIpAddr(req);
        log.info("登录时的sessionID   " + session.getId());
        log.info(ipAddr);
        log.info(user.toString());
        User login = userService.login(user, req);
        session.setAttribute("username", login.getAccount());

        userService.insertLogonLog(new Logontolog(user.getAccount(), ipAddr));
        return new ResultData<>(ReturnCode.RC100, login);
    }

    @ApiOperation("注册")
    @PostMapping("/regist")
    public ResultData<Void> regist(@ApiParam("用户类") @RequestBody User user, HttpSession session) {
        log.info("注册时的id    " + session.getId());
        if (user == null) {
            throw new NullPointerException("传入的数据存在空值");
        }
        userService.register(user);
        return new ResultData<>(ReturnCode.RC100);
    }

    @ApiOperation("修改密码")
    @GetMapping("/updatepw")
    public ResultData<Void> changePassword(@ApiParam("旧密码") @RequestParam(value = "oldPassword", required = false) String oldPassword,
                                           @ApiParam("新密码") @RequestParam(value = "newPassword", required = false) String newPassword,
                                           HttpSession session) {
        if (oldPassword == null || newPassword == null) {
            throw new NullPointerException("传入的数据存在空值");
        }
//        System.out.println(oldPassword);
//        System.out.println(newPassword);
//        System.out.println("============================");
//        System.out.println(getUsernameFromSession(session));
        log.info(getUsernameFromSession(session));
        userService.changePassword(oldPassword, newPassword, getUsernameFromSession(session));
        return new ResultData<>(ReturnCode.RC100);
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public ResultData<Void> logout(HttpSession session) {
        session.removeAttribute("username");
        return new ResultData<>(ReturnCode.RC100);
    }

    @ApiOperation("获取用户总数")
    @GetMapping("/getCountUser")
    public ResultData<Integer> getCountUser() {
        int countUser = userService.getCountUser();
        return new ResultData<>(ReturnCode.RC100, countUser);
    }

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ApiOperation("获取验证码")
    @GetMapping("/kaptcha")
    public void getKaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置响应头
//        System.out.println("==================================");
//        System.out.println(request.getSession().getId());
        log.info("验证码时的sessionId     " + request.getSession().getId());
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String text = defaultKaptcha.createText();
        HttpSession session = request.getSession();
        //将验证码存入session
        session.setAttribute(KAPTCHA_SESSION_KEY, text);
        log.info(session.getAttribute(KAPTCHA_SESSION_KEY).toString());
        //创建验证码图片
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image, "jpg", os);
        IOUtils.closeQuietly(os);
    }

    @ApiOperation("获取登录日志")
    @GetMapping("/selectByCondition")
    public ResultData<PageInfo<Logontolog>> selectByCondition(@ApiParam("账号") @RequestParam(value = "account", required = false) String account,
                                                              @ApiParam("开始日期") @RequestParam(value = "startDate", required = false) String startDate,
                                                              @ApiParam("截止日期") @RequestParam(value = "endDate", required = false) String endDate,
                                                              @ApiParam("页数") @RequestParam(value = "pageNum", required = false) Integer pageNum,
                                                              @ApiParam("页面大小") @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNum == null && pageSize == null) {
            throw new NullPointerException("存在空");
        }
        log.info(startDate+"        "+endDate);
        if ((startDate==null&&endDate!=null)||(startDate!=null&&endDate==null)){
            throw new TimeNullException("kong");
        }
        PageInfo<Logontolog> logontologPageInfo = userService.selectByCondition(account, startDate, endDate, pageNum, pageSize);
        return new ResultData(ReturnCode.RC100, logontologPageInfo);
    }

    /*设置文件上传的最大值*/
    public static final int MAXSIZE = 10 * 1024 * 1024;
    /*限制上传的类型*/
    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("application/pdf");
    }

    @ApiOperation("上传文件")
    @PostMapping ("/insertFiles")
    public ResultData<Void> insertFiles(  @ApiParam("文件列表")@RequestParam MultipartFile[] files,
                                          @ApiParam("合同或发票ID")@RequestParam(required = false) String typeID,
                                          @ApiParam("是采购还是销售的合同或发票")@RequestParam(required = false)String type,
                                          HttpSession session) {
        if (files.length == 0) {
            throw new FileEmptyException("文件列表为空");
        }
        for (MultipartFile file : files) {
            if (file.getSize() > MAXSIZE) {
                throw new FileSizeException("文件超出限制");
            }
            String contentType = file.getContentType();
            if (!AVATAR_TYPE.contains(contentType)) {
                throw new FileTypeException("文件类型不支持");
            }
            String parent = session.getServletContext().getRealPath("upload");
            File dir = new File(parent);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String originalFilename = file.getOriginalFilename();
            String filename = UUID.randomUUID().toString().toUpperCase(Locale.ROOT) + originalFilename;
            File dest = new File(dir, filename);//空文件
            try {
                file.transferTo(dest);
            }catch (FileStateException e){
                throw new FileStateException("文件状态异常");
            }catch (IOException e) {
                throw new FileUploadIOException("文件读写异常");
            }
            String filePath ="/upload"+filename;
            userService.insertFile(new AttachmentFile(originalFilename,typeID,filePath));
        }
        if ("采购合同".equals(type)){
            userService.updateNumberAttachments(files.length,typeID);
        }else if("采购发票".equals(type)){
            userService.updateInvoiceNumberAttachments(files.length,typeID);
        }else if ("销售合同".equals(type)){

        }else if ("销售发票".equals(type)){

        }
        return new ResultData<>(ReturnCode.RC100);
    }

    @ApiOperation("通过typeID得到附件")
    @GetMapping("/getFilesByTypeID")
    public ResultData<List<AttachmentFile>> getFilesByTypeID(@ApiParam("合同或发票ID")@RequestParam(required = false) String typeID){
        List<AttachmentFile> filesByTypeID = userService.getFilesByTypeID(typeID);
        return new ResultData<>(ReturnCode.RC100,filesByTypeID);
    }
    @ApiOperation("通过id删除附件")
    @GetMapping("/deleteFileByID")
    public ResultData<Void> deleteFileByID(@ApiParam("附件id")@RequestParam(required = false) Integer id){
        userService.deleteFile(id);
        return new ResultData<>(ReturnCode.RC100);
    }

    @ApiOperation("得到所有登录日志")
    @GetMapping("/selectAllLogon")
    public ResultData<PageInfo<Logontolog>> selectAllLogon(@ApiParam("页数") @RequestParam(value = "pageNum", required = false) Integer pageNum,
                                                           @ApiParam("页面大小") @RequestParam(value = "pageSize", required = false) Integer pageSize){
        PageInfo<Logontolog> logontologPageInfo = userService.selectAllLogon(pageNum, pageSize);
        return new ResultData<>(ReturnCode.RC100,logontologPageInfo);
    }
}
