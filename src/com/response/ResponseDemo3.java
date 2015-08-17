package com.response;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation 生成随机图片
 */
@WebServlet("/ResponseDemo3")
public class ResponseDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("refresh", "5");//设置refresh响应头控制浏览器每隔5秒钟刷新一次
		//1.在内存中创建一张图片
		BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
		//2.得到图片
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.WHITE);//设置图片的背景色
		g.fillRect(0, 0, 100, 30);//填充背景色
		//3.向图片上写数据
		g.setColor(Color.BLUE);//设置图片上字体的颜色
		g.setFont(new Font(null,Font.BOLD,20));
		g.drawString(makeNum(), 0, 30);
		//4.设置响应头控制浏览器浏览器以图片的方式打开
		response.setContentType("image/jpeg");//等同于response.setHeader("Content-Type", "image/jpeg");
		//5.设置响应头控制浏览器不缓存图片数据
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Prama", "no-cache");
		//6.将图片写给浏览器
		ImageIO.write(image, "jpg", response.getOutputStream());
	}
	
	/**
     * 生成随机数字
     * @return
     */
	private String makeNum(){
	    Random random = new Random();
	    String num = random.nextInt(99999999)+"";
	    StringBuffer sb = new StringBuffer();
	    for(int i=0;i<8-num.length();i++){
	        sb.append("0");
	    }
	    num = sb.toString() + num;
	    return num;
	}
	
}
