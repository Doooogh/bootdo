package com.bootdo.system.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.MenuService;
import com.bootdo.system.service.UserService;
import io.swagger.models.auth.In;
import net.sf.ehcache.search.parser.MAggregate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MenuService menuService;
    @Autowired
    FileService fileService;
    @Autowired
    BootdoConfig bootdoConfig;

    @Autowired
    private UserService userService;



    @Log("请求访问主页")
    @GetMapping({"/index"})
    String index(Model model,String destination) {

        if(destination.equals("2")){
            List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
            model.addAttribute("menus", menus);
            model.addAttribute("name", getUser().getName());
            FileDO fileDO = fileService.get(getUser().getPicId());
            if (fileDO != null && fileDO.getUrl() != null) {
                if (fileService.isExist(fileDO.getUrl())) {
                    model.addAttribute("picUrl", fileDO.getUrl());
                } else {
                    model.addAttribute("picUrl", "/img/photo_s.jpg");
                }
            } else {
                model.addAttribute("picUrl", "/img/photo_s.jpg");
            }
            model.addAttribute("username", getUser().getUsername());
            return "index_v1";
        }else if(destination.equals("1")){
            return "/front/front/index";
        }
        return "/front/front/index";
    }

    @GetMapping(value = {"/login","","/"})
    String login(Model model,String username,String password) {
        model.addAttribute("codeValidate",bootdoConfig.getCodeValidate());
        if(org.apache.commons.lang3.StringUtils.isNotBlank(username)&& org.apache.commons.lang3.StringUtils.isNotBlank(password)){
            model.addAttribute("username",username);
            model.addAttribute("password",password);
        }
        return "login";
    }
    @GetMapping(value = {"/register"})
    String register(Model model) {
        return "register";
    }

    @Log("登录")
    @PostMapping("/login")
    @ResponseBody
    R ajaxLogin(String username, String password,String verify,HttpServletRequest request) {

        try {
            if(bootdoConfig.getCodeValidate()){
                //从session中获取随机数
                String random = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
                if (StringUtils.isBlank(verify)) {
                    return R.error("请输入验证码");
                }
                if (random.equals(verify)) {
                } else {
                    return R.error("请输入正确的验证码");
                }
            }
        } catch (Exception e) {
            logger.error("验证码校验失败", e);
            return R.error("验证码校验失败");
        }
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return R.ok();
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }

    @PostMapping("/register")
    @ResponseBody
    R ajaxRegister(UserDO user){
        try{
            String password=user.getPassword();
           int r= userService.registerUser(user);
           if(r>0){
               Map<String,Object> map=new HashMap<>();
               map.put("username",user.getUsername());
               map.put("password",password);
               return R.ok(map);
           }
        }catch (Exception e){
            e.printStackTrace();
            return R.error("注册失败，请稍后重试!");
        }
        return R.error("注册失败，请稍后重试!");
    }
    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/exitUsername")
    @ResponseBody
    boolean exitUsername(String username){
        Map<String,Object> map=new HashMap<>();
        map.put("username",username);
        try{
                return !userService.exit(map);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/main")
    String main() {
        return "main";
    }

    /**
     * 生成验证码
     */
    @GetMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            logger.error("获取验证码失败>>>> ", e);
        }
    }

}
