package com.dynamicProxy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GzipFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {
        
    }

    /**
    * @ClassName: GzipFilter
    * @Description: 压缩过滤器，将web应用中的文本都经过压缩后再输出到浏览器
    * @author:  
    * @date: 
    *
    */ 
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) resp;
        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final PrintWriter pw = new PrintWriter(new OutputStreamWriter(bout,"UTF-8"));
        
        chain.doFilter(request, getHttpServletResponseProxy(response, bout, pw));
        pw.close();
        //拿到目标资源的输出
        byte result[] = bout.toByteArray();
        System.out.println("原始大小：" + result.length);
        ByteArrayOutputStream bout2 = new ByteArrayOutputStream();
        GZIPOutputStream gout = new GZIPOutputStream(bout2);
        gout.write(result);
        gout.close();
        
        //拿到目标资源输出的压缩数据
        byte gzip[] = bout2.toByteArray();
        System.out.println("压缩大小：" + gzip.length);
        
        response.setHeader("content-encoding", "gzip");
        response.setContentLength(gzip.length);
        response.getOutputStream().write(gzip);
    }
    
    /**
     * @Method: getHttpServletResponseProxy
     * @Description: 获取HttpServletResponse对象的代理对象
     * @Anthor: 
     * @param response
     * @param bout
     * @param pw
     * @return HttpServletResponse对象的代理对象
     */ 
    private ServletResponse getHttpServletResponseProxy(
            final HttpServletResponse response,
            final ByteArrayOutputStream bout, 
            final PrintWriter pw) {
        return (ServletResponse) Proxy.newProxyInstance(GzipFilter.class.getClassLoader(), 
                response.getClass().getInterfaces(), 
                new InvocationHandler(){
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if(method.getName().equals("getWriter")){
                            return pw; 
                        }else if(method.getName().equals("getOutputStream")){
                            return new MyServletOutputStream(bout);
                        }else{
                            return method.invoke(response, args);
                        }
                    }
                });
    }
    
    class MyServletOutputStream extends ServletOutputStream{
        private ByteArrayOutputStream  bout = null;
        public MyServletOutputStream(ByteArrayOutputStream  bout){
            this.bout = bout;
        }
        
        @Override
        public void write(int b) throws IOException {
            bout.write(b);
        }
    }
    
}
