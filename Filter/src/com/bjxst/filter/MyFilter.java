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
 *��������ʹ��
 *	���ã��Է��������յ�������Դ�͸����������Դ���й���
 *		����servlet
 * 	ʹ�ã�����һ��ʵ����filter�ӿڵ���ͨjava��
 * 		��д�ӿڵķ���
 * 		init,doFilter,destory
 * 		��web.xml�����ù�����
 *
 */
public class MyFilter implements Filter{

	//�������ر�ʱ����
	@Override
	public void destroy() {
		System.out.println("�ұ�����");
		
	}

	/*
	   	�������󷽷�   ��servlet֮ǰ���ˣ��������ͨ���ģ�����ת��
		servlet������ϣ��ص�������������Ӧ������������ݽ��д���
		��/*��������������
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("�ұ�ִ��");
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		//�ж�session
		HttpSession hsHttpSession=((HttpServletRequest) req).getSession();
		if(hsHttpSession.getAttribute("user")==null){
			((HttpServletResponse)resp).sendRedirect("/a/login.jsp");
		}else{
			//����
			chain.doFilter(req, resp);
		}
		
		//������Ӧ����
		System.out.println("��ִ��2");
	}

	//����������ʱ�ɷ���������ִ�У���ʼ������
	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("�ұ���ʼ����");
		
	}

}
