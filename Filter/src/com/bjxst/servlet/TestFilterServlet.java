package com.bjxst.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestFilterServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		//设置请求编码格式
		//设置响应编码格式
		//获取请求信息
		//处理请求信息
		System.out.println("过滤器测试");
		//响应处理结果
		
		
	}
}
