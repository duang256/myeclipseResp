package com.bjxst.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.User;

public class mainServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		
		
		//通过Session得到user，从而验证并得到uname
		HttpSession hs=req.getSession();
		//System.out.println(hs.getId());
		User u=(User) hs.getAttribute("user");
		
		//  得到登录次数
		ServletContext sc=this.getServletContext();
		int times=(int) sc.getAttribute("times");
		
		

		
		if(u!=null){
			
			String uname=null;
			Cookie[]cks=req.getCookies();
			if(cks!=null){
				for(Cookie c:cks){
					if(c.getName().equals("uname")){
						uname=c.getValue();
					}
				}
			}
			if(uname!=null){
				resp.getWriter().write("<html>");
				resp.getWriter().write("<head>");
				resp.getWriter().write("</head>");
				
				resp.getWriter().write("<body>");
				resp.getWriter().write("<b>"+u.getUname()+",欢迎回来</b>");
				resp.getWriter().write("<br>");
				resp.getWriter().write("<br>");
				resp.getWriter().write("<br>");
				resp.getWriter().write("<form  action='ts'  method='post'>");
				resp.getWriter().write("<input  value='我的用户数据' type='submit'>");
				resp.getWriter().write("</form>");
				resp.getWriter().write("<b>"+"网页访问次数:"+times+"</b>");
				
				resp.getWriter().write("</body>");
				resp.getWriter().write("<html>");
			}
		}else{
			resp.sendRedirect("login");
		}
		
		
		
		
		
	}
}
