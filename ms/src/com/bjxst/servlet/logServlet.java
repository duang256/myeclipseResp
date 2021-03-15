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
		//������������ʽ
		req.setCharacterEncoding("utf-8");
		//������Ӧ�����ʽ
		resp.setContentType("text/html;charset=utf-8");
		//��ȡ������Ϣ
		String uname=req.getParameter("uname");
//		uname=new String(uname.getBytes("iso8859-1"),"utf-8");
		String pwd=req.getParameter("pwd");
		System.out.println(uname+":"+pwd);
		//����������Ϣ
		
		
		LoginService ls=new LoginServiceImpl();
		User u=(User) ls.checkLoginService(uname,pwd);
		System.out.println(u);
		//��Ӧ������
		if(u!=null){
			//resp.getWriter().write("��¼�ɹ�");
//			req.setAttribute("uname", uname);
//			req.getRequestDispatcher("main").forward(req, resp);
			
			//����uname��֤��Ϣ
			uname=URLEncoder.encode(uname,"UTF-8");
			Cookie c=new Cookie("uname", uname);//��ȡCoolie
			c.setMaxAge(3*24*3600);//�������ݱ���ʱ��
			resp.addCookie(c);
			
			//�����û�����
			HttpSession hs=req.getSession();
			hs.setAttribute("user", u);
			
			//���ü�����
			ServletContext sc=this.getServletContext();
			if(sc.getAttribute("times")!=null){
				int times=(int) sc.getAttribute("times");
				sc.setAttribute("times", times+1);
			}else{
				sc.setAttribute("times", 1);
			}
			
			resp.sendRedirect("/ms/main");
			
		}else{
//			resp.getWriter().write("��¼ʧ��");
			//����ת��(��һ�����������������������req��resp)
			req.setAttribute("str", "�˺Ż�������������µ�½");
			req.getRequestDispatcher("login").forward(req, resp);
		}
		
	}
	}
	
