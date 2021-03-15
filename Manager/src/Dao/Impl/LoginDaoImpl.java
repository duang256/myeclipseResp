package Dao.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.LoginDao;
import pojo.User;


public class LoginDaoImpl implements LoginDao{

	@Override
	public User checkUserLoginDao(String uname, String pwd) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User u=null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//�������Ӷ���
			conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","wityy");
			//�����sql����ܹ��鵽����ôu�ͻ���ڣ�����鲻����u��Ϊnull,logServlet����u�Ƿ�Ϊnull
			String sql="select * from t_user where uname=? and pwd=?"; 
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, pwd);
			rs=ps.executeQuery();
			while(rs.next()){
				u=new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
				u.setAge(rs.getInt("age"));
				u.setBirth(rs.getString("birth"));
				u.setSex(rs.getString("sex"));
				//System.out.println(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return u;
	}

	
	//�޸��û�����
	@Override
	public int userChangePwdDao(int uid, String newPwd) {
		
		int index=-1;
		Connection conn=null;
		PreparedStatement ps=null;
		User u=null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//�������Ӷ���
			conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","wityy");
			//�����sql����ܹ��鵽����ôu�ͻ���ڣ�����鲻����u��Ϊnull,logServlet����u�Ƿ�Ϊnull
			String sql="update t_user set pwd=? where uid=?"; 
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, newPwd);
			ps.setInt(2, uid);
			index=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return index;
	}


	//�鿴�����û���Ϣ
	@Override
	public List<User> usershowDao() {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<User>list=new ArrayList<>();
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//�������Ӷ���
			conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","wityy");
			//�����sql����ܹ��鵽����ôu�ͻ���ڣ�����鲻����u��Ϊnull,logServlet����u�Ƿ�Ϊnull
			String sql="select * from t_user "; 
			ps=(PreparedStatement) conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				User u=new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
				u.setAge(rs.getInt("age"));
				u.setBirth(rs.getString("birth"));
				u.setSex(rs.getString("sex"));
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}


	@Override
	public int userRegDao(User user) {
		int index=-1;
		Connection conn=null;
		PreparedStatement ps=null;
		User u=null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//�������Ӷ���
			conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","wityy");
			//�����sql����ܹ��鵽����ôu�ͻ���ڣ�����鲻����u��Ϊnull,logServlet����u�Ƿ�Ϊnull
			String sql="insert into t_user value(default,?,?,?,?,?)"; 
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, user.getUname());
			ps.setString(2, user.getPwd());
			ps.setInt(3, user.getAge());
			ps.setString(4, user.getSex());
			String birth=user.getBirth();
			String[]bs=null;
			if(birth!=""){
				bs=birth.split("/");
			}
			StringBuffer sb=new StringBuffer();
			sb.append(bs[2]);
			sb.append("/");
			sb.append(bs[0]);
			sb.append("/");
			sb.append(bs[1]);
			ps.setString(5, sb.toString());
			index=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return index;
	}
	}

