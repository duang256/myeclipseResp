package com.bjxst.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.User;
import service.LoginService;
import service.Impl.LoginServiceImpl;

public class logServlet extends HttpServlet {   // log
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		//获取请求信息
		String uname=req.getParameter("uname");
//		uname=new String(uname.getBytes("iso8859-1"),"utf-8");
		String pwd=req.getParameter("pwd");
		System.out.println(uname+":"+pwd);
		//处理请求信息
		
		
		LoginService ls=new LoginServiceImpl();
		User u=(User) ls.checkLoginService(uname,pwd);
		System.out.println(u);
		//响应处理结果
		if(u!=null){
			//resp.getWriter().write("登录成功");
//			req.setAttribute("uname", uname);
//			req.getRequestDispatcher("main").forward(req, resp);
			
			//保留uname验证信息
			uname=URLEncoder.encode(uname,"UTF-8");
			Cookie c=new Cookie("uname", uname);//获取Coolie
			c.setMaxAge(3*24*3600);//设置数据保留时间
			resp.addCookie(c);
			
			//保留用户数据
			HttpSession hs=req.getSession();
			hs.setAttribute("user", u);
			
			//设置计数器
			ServletContext sc=this.getServletContext();
			if(sc.getAttribute("times")!=null){
				int times=(int) sc.getAttribute("times");
				sc.setAttribute("times", times+1);
			}else{
				sc.setAttribute("times", 1);
			}
			
			resp.sendRedirect("/ms/main");
			
		}else{
//			resp.getWriter().write("登录失败");
			//请求转发(第一个参数填别名，后面两个填req，resp)
			req.setAttribute("str", "账号或密码错误，请重新登陆");
			req.getRequestDispatcher("login").forward(req, resp);
		}
		
	}
	}
	
