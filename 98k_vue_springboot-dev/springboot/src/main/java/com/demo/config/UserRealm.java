package com.demo.config;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserRepository userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //用户名，密码  -数据库取
        UsernamePasswordToken userToken=(UsernamePasswordToken) authenticationToken;
        System.out.println(userToken.getUsername());
        //连接真实数据库
        User user=  userService.findByName(userToken.getUsername());
        if(user==null){
            //没有
            return null;
        }
        //密码认证，shiro做

        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
