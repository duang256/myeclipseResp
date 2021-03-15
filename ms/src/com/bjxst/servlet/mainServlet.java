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
		//������������ʽ
		req.setCharacterEncoding("utf-8");
		//������Ӧ�����ʽ
		resp.setContentType("text/html;charset=utf-8");
		
		
		//ͨ��Session�õ�user���Ӷ���֤���õ�uname
		HttpSession hs=req.getSession();
		//System.out.println(hs.getId());
		User u=(User) hs.getAttribute("user");
		
		//  �õ���¼����
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
				resp.getWriter().write("<b>"+u.getUname()+",��ӭ����</b>");
				resp.getWriter().write("<br>");
				resp.getWriter().write("<br>");
				resp.getWriter().write("<br>");
				resp.getWriter().write("<form  action='ts'  method='post'>");
				resp.getWriter().write("<input  value='�ҵ��û�����' type='submit'>");
				resp.getWriter().write("</form>");
				resp.getWriter().write("<b>"+"��ҳ���ʴ���:"+times+"</b>");
				
				resp.getWriter().write("</body>");
				resp.getWriter().write("<html>");
			}
		}else{
			resp.sendRedirect("login");
		}
		
		
		
		
		
	}
}
