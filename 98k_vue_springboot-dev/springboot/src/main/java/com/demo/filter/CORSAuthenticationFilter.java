package com.demo.filter;
import com.alibaba.fastjson.JSON;
import com.demo.utils.ResultJson;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author Liu
 * @Date 2021/4/6 14:28
 */
public class CORSAuthenticationFilter extends FormAuthenticationFilter {
    public CORSAuthenticationFilter() {
        super();
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(request instanceof HttpServletRequest){
            System.out.println(((HttpServletRequest) request).getMethod().toUpperCase());
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")){
                System.out.println("OPTIONS请求");
                return true;
            }
            else if (((HttpServletRequest) request).getMethod().toUpperCase().equals("GET")){
                System.out.println("GET");
                return true;
            }else if (((HttpServletRequest) request).getMethod().toUpperCase().equals("POST")){
                System.out.println("POST");
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=utf-8");
        PrintWriter writer = res.getWriter();
        Map map = new HashMap();
        map.put("code", new ResultJson(100003, "error", "请先登录系统！", null));
        map.put("msg", new ResultJson(100003, "error", "请先登录系统！", null));
        writer.write(JSON.toJSONString(map));
        writer.close();
        return false;
    }
}
