package com.bjxst.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjxst.service.UserService;
import com.bjxst.serviceImpl.UserServiceImpl;
import com.bjxst.vo.User;
import com.google.gson.Gson;

public class UserServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//������������ʽ
		req.setCharacterEncoding("utf-8");
		//������Ӧ�����ʽ
		//resp.setContentType("text/html;charset=utf-8");
		resp.setContentType("text/xml;charset=utf-8");
		//��ȡ������Ϣ
		String name=req.getParameter("name");
		System.out.println("�û�����"+name);
		//����������Ϣ
			//��ȡҵ������
			UserService us=new UserServiceImpl();
			//�������ݿⷽ��
			User user=us.getUserInfoService(name);
			//���Դӵ��ô� �� DaoImpl�Ƿ���bug
			if(user!=null){
				System.out.println(user.toString());
			}
		//��Ӧ������
			//resp.getWriter().write(new Gson().toJson(user));
			resp.getWriter().write("<user><name>"+user.getName()+"</name><price>"+user.getPrice()+"</price><loc>"+user.getLoc()+"</loc></user>");
		
		
	}
}
