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
		
		
		
			//������Ӧ�����ʽ
			resp.setContentType("text/html;charset=utf-8");
			//������������ʽ
			req.setCharacterEncoding("utf-8");
			//��ȡ������Ϣ
			
			
			//����¼�˻����������ʱ���ض��򵽱�Servet,��Ҫʹ�õ��Ĵ�����ʾ��Ϣ����������ת��������Ϣ�Ĵ���
			String str=(String) req.getAttribute("str");
				
				//��������
				//��Ӧ������
				resp.getWriter().write("<html>");
				
				resp.getWriter().write("<head>");
				resp.getWriter().write("</head>");
				
				resp.getWriter().write("<body>");
				resp.getWriter().write("<form action='log' method='get'>");
				resp.getWriter().write("�˺�<input type='text' name='uname' value=''>");
				resp.getWriter().write("<br>");
				resp.getWriter().write("����<input type='text' name='pwd' value=''>");
				resp.getWriter().write("<br>");
				resp.getWriter().write("<input type='submit' value='��¼'>");
				resp.getWriter().write("<form>");
				if(str!=null){
					resp.getWriter().write("<br>");
				resp.getWriter().write("<font color='red'>"+str+"</font>");
				}
				
				
				resp.getWriter().write("</body>");
				
				resp.getWriter().write("</html>");

			
			
			
	}	
}

