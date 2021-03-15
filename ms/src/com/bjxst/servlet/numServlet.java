package com.bjxst.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class numServlet extends HttpServlet {
	
	
	
	@Override
	public void init() throws ServletException {
		BufferedReader br=null;
		FileReader fr=null;
		try {
			fr=new FileReader(new File("/nums/nums.txt"));
			br=new BufferedReader(fr);
			String times=br.readLine();
			ServletContext sc=this.getServletContext();
			sc.setAttribute("times", times);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(fr!=null){
					fr.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public void destroy() {
		String times=(String) this.getServletContext().getAttribute("times");
		BufferedWriter bd=null;
		FileWriter fw=null;
		
		try {
			fw=new FileWriter(new File("/nums/nums.txt"));
			bd=new BufferedWriter(fw);
			bd.write(times);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bd.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
