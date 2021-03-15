package com.bjxst.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bjxst.dao.UserDao;
import com.bjxst.vo.User;

public class UserDaoImpl implements UserDao{

	@Override
	public User getUserInfoDao(String name) {
		        Connection conn=null;
		        PreparedStatement ps=null;
		        ResultSet rs=null;
		        String driver="com.mysql.jdbc.Driver";
		        String url="jdbc:mysql://localhost:3306/login";
		        String username="root";
		        String password="bjxst";
		        User user=null;
		        try {
		            Class.forName(driver);//包名+类名加载驱动
		            conn= DriverManager.getConnection(url,username,password);
		            String  sql="select * from user where name=?";
		            ps=conn.prepareStatement(sql);// 这个类是conn连接数据库相关的//    编译并发送SQL命令给数据库
		            ps.setString(1,name);
		            
		            //执行SQL命令
		            //如果没有执行成功返回-1，如果成功返回更改的数据条数
		            rs=ps.executeQuery();
		            while(rs.next()){
		            	user=new User();
		            	user.setUid(rs.getInt("uid"));
		            	user.setPrice(rs.getString("price"));
		            	user.setName(rs.getString("name"));
		            	user.setLoc(rs.getString("loc"));
		            }
		            //关闭资源

		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {
		        	try {
		        		if(rs!=null){
						rs.close();
		        		}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            try {
		            	if(ps!=null){
		                ps.close();
		            	}
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		            try {
		            	if(conn!=null){
		                conn.close();
		            	}
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		return user;
	}

}
