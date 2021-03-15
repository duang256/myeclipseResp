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
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		//resp.setContentType("text/html;charset=utf-8");
		resp.setContentType("text/xml;charset=utf-8");
		//获取请求信息
		String name=req.getParameter("name");
		System.out.println("用户名："+name);
		//处理请求信息
			//获取业务层对象
			UserService us=new UserServiceImpl();
			//调用数据库方法
			User user=us.getUserInfoService(name);
			//测试从调用处 到 DaoImpl是否有bug
			if(user!=null){
				System.out.println(user.toString());
			}
		//响应处理结果
			//resp.getWriter().write(new Gson().toJson(user));
			resp.getWriter().write("<user><name>"+user.getName()+"</name><price>"+user.getPrice()+"</price><loc>"+user.getLoc()+"</loc></user>");
		
		
	}
}
