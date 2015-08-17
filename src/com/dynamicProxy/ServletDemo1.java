package com.dynamicProxy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ServletDemo1 extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 接收参数
        String username = request.getParameter("username");
        // 获取请求方式
        String method = request.getMethod();
        // 获取输出流
        PrintWriter out = response.getWriter();
        out.write("请求的方式：" + method);
        out.write("<br/>");
        out.write("接收到的参数：" + username);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
