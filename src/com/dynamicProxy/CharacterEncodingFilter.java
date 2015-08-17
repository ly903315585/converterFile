package com.dynamicProxy;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

/**
* @ClassName: CharacterEncodingFilter
* @Description: 解决中文乱码的字符过滤器
* @author:
* @date:
*
*/ 
public class CharacterEncodingFilter implements Filter{

    @Override
    public void destroy() {
        
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        
        //解决以Post方式提交的中文乱码问题
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取获取HttpServletRequest对象的代理对象
        ServletRequest requestProxy = getHttpServletRequestProxy(request);
        /**
         * 传入代理对象requestProxy给doFilter方法，
         * 这样用户在使用request对象时实际上使用的是HttpServletRequest对象的代理对象requestProxy
        */
        chain.doFilter(requestProxy, response);
    }
    
    /**
     * @Method: getHttpServletRequestProxy
     * @Description: 获取HttpServletRequest对象的代理对象
     * @Anthor:孤傲苍狼
     *
     * @param request
     * @return HttpServletRequest对象的代理对象
     */ 
    private ServletRequest getHttpServletRequestProxy(final HttpServletRequest request){
        ServletRequest proxy = (ServletRequest) Proxy.newProxyInstance(
                CharacterEncodingFilter.class.getClassLoader(),
                request.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //如果请求方式是get并且调用的是getParameter方法
                        if(request.getMethod().equalsIgnoreCase("get") &&
                                method.getName().equals("getParameter")){
                            //调用getParameter方法获取参数的值
                            String value = (String) method.invoke(request, args);
                            if(value == null){
                                return null;
                            }
                            //解决以get方式提交的中文乱码问题
                            return new String(value.getBytes("iso8859-1"),"UTF-8");
                        }else{
                            //直接调用相应的方法进行处理
                            return method.invoke(request, args);
                        }
                    }
                });
        //返回HttpServletRequest对象的代理对象
        return proxy;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }
    
}
