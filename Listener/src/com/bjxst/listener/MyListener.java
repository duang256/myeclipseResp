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
		System.out.println("request������");
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("request������");
	}

	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		System.out.println("Session������");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		System.out.println("Session������");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context������");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("context������");
		
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.out.println("ServletContext�������:"+scae.getName()+","+scae.getValue());
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.out.println("ServletContextɾ������"+scae.getName()+","+scae.getValue());
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.out.println("ServletContext��������"+scae.getName()+","+scae.getValue());
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		System.out.println("Session�������:"+hsbe.getName()+","+hsbe.getValue());
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent hsbe) {
		System.out.println("Sessionɾ������"+hsbe.getName()+","+hsbe.getValue());
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent hsbe) {
		System.out.println("Session��������"+hsbe.getName()+","+hsbe.getValue());
		
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("Request������ݣ�"+srae.getName()+","+srae.getValue());
		
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("Requestɾ������"+srae.getName()+","+srae.getValue());
		
		
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("Request��������"+srae.getName()+","+srae.getValue());
		
	}

}
