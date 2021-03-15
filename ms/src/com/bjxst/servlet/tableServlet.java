package com.bjxst.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.User;

public class tableServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		
		
		HttpSession hs=req.getSession();
//		System.out.println(hs.getId());
		User u=(User) hs.getAttribute("user");
		if(u==null){
			resp.sendRedirect("ck");
		}else{
			resp.getWriter().write("<html>");
		resp.getWriter().write("<head>");
		resp.getWriter().write("</head>");
		
		resp.getWriter().write("<body>");
		
		resp.getWriter().write("<table border=1px>");
		resp.getWriter().write("<tr>");
		resp.getWriter().write("<td>”√ªß√˚</td>");
		resp.getWriter().write("<td>√‹¬Î</td>");
		
		resp.getWriter().write("</tr>");
		
		resp.getWriter().write("<tr>");
		resp.getWriter().write("<td>"+u.getUname()+"</td>");
		resp.getWriter().write("<td>"+u.getPwd()+"</td>");
		resp.getWriter().write("</tr>");
		
		resp.getWriter().write("</table>");
		
		resp.getWriter().write("</body>");
		resp.getWriter().write("<html>");
		}
		
		
	}
}


