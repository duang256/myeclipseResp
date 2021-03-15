package com.bjxst.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;

/**
 *过滤器的使用
 *	作用：对服务器接收的请求资源和给浏览器的资源进行管理
 *		保护servlet
 * 	使用：创建一个实现了filter接口的普通java类
 * 		复写接口的方法
 * 		init,doFilter,destory
 * 		在web.xml中配置过滤器
 *
 */
public class MyFilter implements Filter{

	//服务器关闭时调用
	@Override
	public void destroy() {
		System.out.println("我被销毁");
		
	}

	/*
	   	拦截请求方法   在servlet之前过滤，如果过滤通过的，则流转，
		servlet处理完毕，回到本方法，对响应给浏览器的数据进行处理
		‘/*’拦截所有请求
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("我被执行");
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		//判断session
		HttpSession hsHttpSession=((HttpServletRequest) req).getSession();
		if(hsHttpSession.getAttribute("user")==null){
			((HttpServletResponse)resp).sendRedirect("/a/login.jsp");
		}else{
			//放行
			chain.doFilter(req, resp);
		}
		
		//过滤响应数据
		System.out.println("被执行2");
	}

	//服务器启动时由服务器调用执行，初始化数据
	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("我被初始化了");
		
	}

}
