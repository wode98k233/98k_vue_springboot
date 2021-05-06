package com.demo.controller;

import com.demo.utils.ResultJson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 作者:Liu
 * 登录拦截，前端需要把username 和password 发送给/static/ulogin端口，然后返回该端口的信息给前端，
 * 前端才能从login界面访问到其他路由并且后端才会发送数据给前端
 *
 * */

@RestController
@RequestMapping("/static")
public class MyController {

    @RequestMapping("/login")
    public String toLogin(){ return "no"; }

    @RequestMapping("/ulogin")
    public ResultJson login(String username, String password, HttpServletRequest request){
        //获取当前用户
        Subject subject= SecurityUtils.getSubject();
        //封装用户登录数据
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        SecurityUtils.getSubject().getSession().setTimeout(1000*60*60*3);
        Serializable tokenId = subject.getSession().getId();
        try {
            subject.login(token);//执行登录方法,如果没异常，🆗了
            return new ResultJson(200, token, "登录认证成功",  tokenId);
        }catch(UnknownAccountException e){
            return new ResultJson(10003, "error", "用户名错误",  null);
        }catch(IncorrectCredentialsException e){
            return new ResultJson(10003, "error", "密码错误", null);
        }

    }
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout";
    }






}
