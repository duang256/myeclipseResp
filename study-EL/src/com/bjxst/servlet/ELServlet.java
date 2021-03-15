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
	req.setAttribute("weather", "今天是个晴天");
	ArrayList<User> users=new ArrayList<>();
	User user=new User("颜悦",20,"男");
	User user2=new User("刘川",20,"男");
	User user3=new User("谢宇凡",20,"男");
	User user4=new User("陈卓",20,"男");
	users.add(user);
	users.add(user2);
	users.add(user3);
	users.add(user4);
	req.setAttribute("users", users);
	
	Map<String, String> map=new HashMap<>();
	map.put("a", "北京");
	map.put("b", "上海");
	map.put("c", "广州");
	map.put("d", "深圳");
	req.setAttribute("map", map);
	
	Map<String, User> us=new HashMap<>();
	User u1=new User("黄予曦",20,"女");
	User u2=new User("黄予曦",20,"女");
	User u3=new User("黄予曦",20,"女");
	User u4=new User("黄予曦",20,"女");
	us.put("u1", u1);
	us.put("u2", u2);
	us.put("u3", u3);
	us.put("u4", u4);
	req.setAttribute("us", us);
	req.setAttribute("a", new User());
	
	req.getRequestDispatcher("el.jsp").forward(req, resp);
}
}
