package com.bjxst.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginServlet extends HttpServlet {   
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		
			//设置响应编码格式
			resp.setContentType("text/html;charset=utf-8");
			//设置请求编码格式
			req.setCharacterEncoding("utf-8");
			//获取请求信息
			
			
			//当登录账户或密码出错时，重定向到本Servet,需要使用到的错误提示信息，利用请求转发进行信息的传递
			String str=(String) req.getAttribute("str");
				
				//处理请求
				//响应处理结果
				resp.getWriter().write("<html>");
				
				resp.getWriter().write("<head>");
				resp.getWriter().write("</head>");
				
				resp.getWriter().write("<body>");
				resp.getWriter().write("<form action='log' method='get'>");
				resp.getWriter().write("账号<input type='text' name='uname' value=''>");
				resp.getWriter().write("<br>");
				resp.getWriter().write("密码<input type='text' name='pwd' value=''>");
				resp.getWriter().write("<br>");
				resp.getWriter().write("<input type='submit' value='登录'>");
				resp.getWriter().write("<form>");
				if(str!=null){
					resp.getWriter().write("<br>");
				resp.getWriter().write("<font color='red'>"+str+"</font>");
				}
				
				
				resp.getWriter().write("</body>");
				
				resp.getWriter().write("</html>");

			
			
			
	}	
}

