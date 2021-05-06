package com.demo.config;

import com.demo.filter.CORSAuthenticationFilter;
import com.demo.manger.MySessionManager;
import com.demo.utils.MyExceptionHandler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    private static Logger log = LoggerFactory.getLogger(ShiroConfig.class);
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        //设置安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro内置过滤器
        /**
         * anon:无需认证即可访问
         * authc:必须认证了才能访问
         * user:必须拥有 记住我 功能才能用
         * perms:拥有对某个资源的权限才能玩
         * role: 拥有某个角色权限才能访问
         *
         *
         * 这里Map设置拦截路径，只有login和logout是不被拦截的，
         * 其余所有请求都会丢给login
         * */
        Map<String,String> filterMap=new LinkedHashMap<>();
        filterMap.put("/static/ulogin","anon");
        filterMap.put("/user/insert","anon");
        filterMap.put("/file/download","anon");
        filterMap.put("/file/upload","anon");
        filterMap.put("/picture/**","anon");
        /*filterMap.put("/static/logout","logout");
        filterMap.put("/user/**","anon");
        filterMap.put("/file/**","anon");
        filterMap.put("/dloadfile/**","anon");
        filterMap.put("/mes/**","anon");
        filterMap.put("/fileuntil/**","anon");*/
        filterMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //设置登录请求
        shiroFilterFactoryBean.setLoginUrl("/static/login");
        //自定义拦截器
        Map<String, Filter> customFilterMap = new LinkedHashMap<>();
        customFilterMap.put("corsAuthenticationFilter", new CORSAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(customFilterMap);


        return shiroFilterFactoryBean;
    }


    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") com.demo.config.UserRealm userRealm, SessionManager sessionManager){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联
        securityManager.setRealm(userRealm);
        //自定义的shiro session 缓存管理器
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }
    /**
     * 自定义的 shiro session 缓存管理器，用于跨域等情况下使用 token 进行验证，不依赖于sessionId
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        System.out.println("******sessionManager()");
        return new MySessionManager();
    }
    public CORSAuthenticationFilter corsAuthenticationFilter(){
        return new CORSAuthenticationFilter();
    }

    @Bean
    public com.demo.config.UserRealm userRealm() {
        return new com.demo.config.UserRealm();
    }
    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param getDefaultWebSecurityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager getDefaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager( getDefaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 注册全局异常处理
     * @return
     */
    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new MyExceptionHandler();
    }








}
