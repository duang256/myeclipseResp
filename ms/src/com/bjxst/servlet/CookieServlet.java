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
import service.LoginService;
import service.Impl.LoginServiceImpl;

public class CookieServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//��Ӧ��������
		resp.setContentType("text/html;charset=utf-8");
		//�����������
		req.setCharacterEncoding("utf-8"); 
		
		//��ȡCookie����
			//���cookie���ݴ��ڲ���У��ɹ��������mainServlet������ص���¼ҳ��

		Cookie[]cks=req.getCookies();
		if(cks!=null){
			String uname="";
			for(Cookie c:cks){
				if(c.getName().equals("uname")){
					uname=c.getValue();
				}
			}
			if("".equals(uname)){
				req.getRequestDispatcher("login").forward(req, resp);
				return;
			}else{
				LoginService ls=new LoginServiceImpl();
				User u=ls.checkUnameService(uname);
				if(u!=null){
					ServletContext sc=this.getServletContext();
					if(sc.getAttribute("times")==null){
						resp.sendRedirect("login");
					}else{
					int times=(int) sc.getAttribute("times");
					sc.setAttribute("times", times+1);
							
					
					HttpSession hs=req.getSession();
					//System.out.println(hs.getId());
					hs.setAttribute("user", u);
					req.getRequestDispatcher("main").forward(req, resp);
					}
				}else{
					req.getRequestDispatcher("login").forward(req, resp);
					return;
				}
			}
			
		}else{
			req.getRequestDispatcher("login").forward(req, resp);
			return;
		}
		
	}
}

