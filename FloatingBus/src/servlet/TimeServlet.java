package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//������������ʽ
				req.setCharacterEncoding("utf-8");
				//������Ӧ�����ʽ
				resp.setContentType("text/xml;charset=utf-8");
				//��ȡ������Ϣ
				
				 Date date = new Date(); // this object contains the current date value 
		   		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd��    HH:mm:ss");  
		      	 resp.getWriter().write(formatter.format(new Date()));

	}
}
