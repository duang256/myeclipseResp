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
 * Servlet重定向路径总结：
 * 相对路径：从当前请求的路径查找资源的路径
 * 相对路径如果servlet的别名中包含目录，会造成重定向查找资源失败
 * 绝对路径：第一个/表示服务器根目录
 *  /虚拟项目名资源路径
 *
 */

//处理表单提交的数据，验证成功后跳转到main页面
public class UserServlet extends HttpServlet {
	Logger logger=Logger.getLogger(UserServlet.class);
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String oper=req.getParameter("oper");
		if("login".equals(oper)){
			//调用登录功能
			checkUserLogin(req, resp);
		}else if("reg".equals(oper)){
			//调用注册功能
			userRge(req,resp);
		}else if("out".equals(oper)){
			userOut(req, resp);
		}else if("pwd".equals(oper)){
			//密码修改
			userChangePwd(req,resp);
		}else if("show".equals(oper)){
			//密码修改
			userShow(req,resp);
		}else{
			//oper不存在
			logger.debug("没有找到对应的操作符："+oper);
		}
	
	}
	
	
	
	
	private void userRge(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//获取请求信息
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
		
		//调用业务层进行处理
		UserService us=new ServiceImpl();
		int index=us.userRegService(user);
		if(index>0){
			HttpSession session=req.getSession();
			session.setAttribute("reg", "true");
			resp.sendRedirect("/mg/login.jsp");
		}
		
	}




	private void userShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//显示所有用户信息
		UserService us=new ServiceImpl();
		List <User> list=us.userShowService();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/user/showUser.jsp").forward(req, resp);
		
	}	
	
	private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String newPwd=req.getParameter("newPwd");
		//从session中获取用户信息
		User user=(User) req.getSession().getAttribute("user");
		//处理请求
		int uid=user.getUid();
		UserService us=new ServiceImpl();
		int index=us.userChangePwdService(uid,newPwd);
		if(index>0){
			HttpSession hs=req.getSession();
			hs.setAttribute("pwd", "true");
			//重定向到登录页面
			resp.sendRedirect("/mg/login.jsp");
		}
	}
	
	
	
	
	private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//获取session对象
		HttpSession hs=req.getSession();
		//强制销毁session
		hs.invalidate();
		//重定向到登录页面
		resp.sendRedirect("/mg/login.jsp");
	}
	
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		//处理请求信息
			UserService us=new ServiceImpl();
			User u=us.checkUserLoginService(uname, pwd);
		//重定向
			if(u!=null){
				//获取session对象
				HttpSession hsHttpSession=req.getSession();
				//将用户数据存储到session中
				hsHttpSession.setAttribute("user", u);
				resp.sendRedirect("/mg/main/main.jsp");
			}else{
				//添加标识符到request中
				req.setAttribute("flag", 0);
				//请求转发 让login.jsp响应浏览器，所以是req进行请求转发
				//Servlet请求转发中，/表示项目根目录       重定向中表示服务器根目录
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
	}
}
