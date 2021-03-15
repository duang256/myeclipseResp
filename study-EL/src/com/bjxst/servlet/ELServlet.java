package com.bjxst.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjxst.pojo.User;

public class ELServlet extends HttpServlet {
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	resp.setContentType("text/html;charset=utf-8");
	String uname=req.getParameter("uname");
	String pwd=req.getParameter("pwd");
	System.out.println(uname+":"+pwd);
	req.setAttribute("weather", "�����Ǹ�����");
	ArrayList<User> users=new ArrayList<>();
	User user=new User("����",20,"��");
	User user2=new User("����",20,"��");
	User user3=new User("л�",20,"��");
	User user4=new User("��׿",20,"��");
	users.add(user);
	users.add(user2);
	users.add(user3);
	users.add(user4);
	req.setAttribute("users", users);
	
	Map<String, String> map=new HashMap<>();
	map.put("a", "����");
	map.put("b", "�Ϻ�");
	map.put("c", "����");
	map.put("d", "����");
	req.setAttribute("map", map);
	
	Map<String, User> us=new HashMap<>();
	User u1=new User("������",20,"Ů");
	User u2=new User("������",20,"Ů");
	User u3=new User("������",20,"Ů");
	User u4=new User("������",20,"Ů");
	us.put("u1", u1);
	us.put("u2", u2);
	us.put("u3", u3);
	us.put("u4", u4);
	req.setAttribute("us", us);
	req.setAttribute("a", new User());
	
	req.getRequestDispatcher("el.jsp").forward(req, resp);
}
}
