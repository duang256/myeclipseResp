package Dao.Impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.LoginDao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import pojo.User;

public class LoginDaoImpl implements LoginDao{

	public User checkLoginDao(String uname, String pwd) {
		//声明Jdbc对象
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User u=null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//创建连接对象
			conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","wityy");
			//如果能sql语句能够查到，那么u就会存在，如果查不到，u就为null,logServlet会检测u是否为null
			String sql="select * from t_user where uname=? and pwd=?"; 
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, pwd);
			rs=ps.executeQuery();
			while(rs.next()){
				u=new User();
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
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

	public User checkUnameDao(String uname) {
		//声明Jdbc对象
		
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				User u=null;
				try {
					//加载驱动
					Class.forName("com.mysql.jdbc.Driver");
					//创建连接对象
					conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","wityy");
					String sql="select * from t_user where uname=?"; 
					ps=(PreparedStatement) conn.prepareStatement(sql);
					ps.setString(1, uname);
					rs=ps.executeQuery();
					while(rs.next()){
						u=new User();
						u.setUname(rs.getString("uname"));
						u.setPwd(rs.getString("pwd"));
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

}
