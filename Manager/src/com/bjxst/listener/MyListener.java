package com.bjxst.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyListener implements HttpSessionListener,ServletContextListener,HttpSessionAttributeListener{

	//session������ʱ��������
	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		System.out.println("session�����ɹ�");
		ServletContext sc=hse.getSession().getServletContext();
		int count=(int)sc.getAttribute("count");
		sc.setAttribute("count", ++count);
	}
	//session�������٣������Լ�
	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		ServletContext sc=hse.getSession().getServletContext();
		int count=(int)sc.getAttribute("count");
		sc.setAttribute("count", --count);
		
	}

	
	//application ��������
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		
	}
	//application�����ʼ��
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc=sce.getServletContext();
		sc.setAttribute("count", 0);
	}
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println("���session");
		
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
