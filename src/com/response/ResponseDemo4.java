package com.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation 通过response实现请求重定向
 */
@WebServlet("/ResponseDemo4")
public class ResponseDemo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ResponseDemo4() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    /**
         * 1.调用sendRedirect方法实现请求重定向,
         * sendRedirect方法内部调用了
         * response.setHeader("Location", "/JavaWeb_HttpServletResponse_Study_20140615/index.jsp");
         * response.setStatus(HttpServletResponse.SC_FOUND);//设置302状态码，等同于response.setStatus(302);
         */
	    response.sendRedirect(this.getServletContext().getRealPath("/ndex.jsp"));
	    //2.使用response设置302状态码和设置location响应头实现重定向实现请求重定向
        //response.setHeader("Location", "/index.jsp");
        //response.setStatus(HttpServletResponse.SC_FOUND);//设置302状态码，等同于response.setStatus(302);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
