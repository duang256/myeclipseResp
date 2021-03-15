package com.bjxst.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyListener implements HttpSessionListener,ServletContextListener,HttpSessionAttributeListener{

	//session被创建时人数自增
	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		System.out.println("session创建成功");
		ServletContext sc=hse.getSession().getServletContext();
		int count=(int)sc.getAttribute("count");
		sc.setAttribute("count", ++count);
	}
	//session对象销毁，人数自减
	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		ServletContext sc=hse.getSession().getServletContext();
		int count=(int)sc.getAttribute("count");
		sc.setAttribute("count", --count);
		
	}

	
	//application 对象销毁
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		
	}
	//application对象初始化
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc=sce.getServletContext();
		sc.setAttribute("count", 0);
	}
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println("添加session");
		
	}
	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
