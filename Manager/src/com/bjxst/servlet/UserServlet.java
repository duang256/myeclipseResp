package com.bjxst.servlet;

import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Dao.Impl.LoginDaoImpl;
import pojo.User;
import service.UserService;
import service.Impl.ServiceImpl;

/**
 * 
 * Servlet�ض���·���ܽ᣺
 * ���·�����ӵ�ǰ�����·��������Դ��·��
 * ���·�����servlet�ı����а���Ŀ¼��������ض��������Դʧ��
 * ����·������һ��/��ʾ��������Ŀ¼
 *  /������Ŀ����Դ·��
 *
 */

//������ύ�����ݣ���֤�ɹ�����ת��mainҳ��
public class UserServlet extends HttpServlet {
	Logger logger=Logger.getLogger(UserServlet.class);
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String oper=req.getParameter("oper");
		if("login".equals(oper)){
			//���õ�¼����
			checkUserLogin(req, resp);
		}else if("reg".equals(oper)){
			//����ע�Ṧ��
			userRge(req,resp);
		}else if("out".equals(oper)){
			userOut(req, resp);
		}else if("pwd".equals(oper)){
			//�����޸�
			userChangePwd(req,resp);
		}else if("show".equals(oper)){
			//�����޸�
			userShow(req,resp);
		}else{
			//oper������
			logger.debug("û���ҵ���Ӧ�Ĳ�������"+oper);
		}
	
	}
	
	
	
	
	private void userRge(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//��ȡ������Ϣ
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		String sex=req.getParameter("sex");
		int age=req.getParameter("age")!=""?Integer.parseInt(req.getParameter("age")):0;
		String birth=req.getParameter("birth");
		User user=new User();
		user.setUname(uname);
		user.setBirth(birth);
		user.setSex(sex);
		user.setPwd(pwd);
		user.setAge(age);
		
		//����ҵ�����д���
		UserService us=new ServiceImpl();
		int index=us.userRegService(user);
		if(index>0){
			HttpSession session=req.getSession();
			session.setAttribute("reg", "true");
			resp.sendRedirect("/mg/login.jsp");
		}
		
	}




	private void userShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//��ʾ�����û���Ϣ
		UserService us=new ServiceImpl();
		List <User> list=us.userShowService();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/user/showUser.jsp").forward(req, resp);
		
	}	
	
	private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String newPwd=req.getParameter("newPwd");
		//��session�л�ȡ�û���Ϣ
		User user=(User) req.getSession().getAttribute("user");
		//��������
		int uid=user.getUid();
		UserService us=new ServiceImpl();
		int index=us.userChangePwdService(uid,newPwd);
		if(index>0){
			HttpSession hs=req.getSession();
			hs.setAttribute("pwd", "true");
			//�ض��򵽵�¼ҳ��
			resp.sendRedirect("/mg/login.jsp");
		}
	}
	
	
	
	
	private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//��ȡsession����
		HttpSession hs=req.getSession();
		//ǿ������session
		hs.invalidate();
		//�ض��򵽵�¼ҳ��
		resp.sendRedirect("/mg/login.jsp");
	}
	
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		//����������Ϣ
			UserService us=new ServiceImpl();
			User u=us.checkUserLoginService(uname, pwd);
		//�ض���
			if(u!=null){
				//��ȡsession����
				HttpSession hsHttpSession=req.getSession();
				//���û����ݴ洢��session��
				hsHttpSession.setAttribute("user", u);
				resp.sendRedirect("/mg/main/main.jsp");
			}else{
				//��ӱ�ʶ����request��
				req.setAttribute("flag", 0);
				//����ת�� ��login.jsp��Ӧ�������������req��������ת��
				//Servlet����ת���У�/��ʾ��Ŀ��Ŀ¼       �ض����б�ʾ��������Ŀ¼
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
	}
}
