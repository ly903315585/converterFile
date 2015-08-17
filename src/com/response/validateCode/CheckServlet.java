package com.response.validateCode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 服务器端接收到验证码后的处理
 * 
 * @author liuyong
 *
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
    private static final long serialVersionUID = -5513765846134070401L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientCheckCode = req.getParameter("validateCode");// 接收客户端浏览器提交上来的验证码
        String serverCheckCode = (String) req.getSession().getAttribute("checkCode");// 从服务器端的session中取出验证码
        if (clientCheckCode.equals(serverCheckCode)) {
            // 将客户端验证码和服务器端验证比较，如果相等，则表示验证通过
            System.out.println("验证码验证通过！");
        } else {
            System.out.println("验证码验证失败！");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}
