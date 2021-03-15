package com.bjxst.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyListener implements ServletRequestListener,HttpSessionListener,ServletContextListener,ServletRequestAttributeListener,HttpSessionAttributeListener,ServletContextAttributeListener{

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("request被销毁");
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("request被创建");
	}

	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		System.out.println("Session被创建");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		System.out.println("Session被销毁");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context被销毁");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("context被创建");
		
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.out.println("ServletContext添加数据:"+scae.getName()+","+scae.getValue());
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.out.println("ServletContext删除数据"+scae.getName()+","+scae.getValue());
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.out.println("ServletContext更改数据"+scae.getName()+","+scae.getValue());
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		System.out.println("Session添加数据:"+hsbe.getName()+","+hsbe.getValue());
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent hsbe) {
		System.out.println("Session删除数据"+hsbe.getName()+","+hsbe.getValue());
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent hsbe) {
		System.out.println("Session更改数据"+hsbe.getName()+","+hsbe.getValue());
		
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("Request添加数据："+srae.getName()+","+srae.getValue());
		
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("Request删除数据"+srae.getName()+","+srae.getValue());
		
		
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("Request更改数据"+srae.getName()+","+srae.getValue());
		
	}

}
