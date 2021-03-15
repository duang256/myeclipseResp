package cn.wit.realm;

import java.beans.PropertyVetoException;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import cn.wit.users.Users;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserRealm extends AuthorizingRealm{
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "uesrRealm";
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken taken) throws AuthenticationException {
			String username=(String) taken.getPrincipal();
			Users users=null;
			Connection conn=null;
	        PreparedStatement ps=null;
	        ResultSet rs=null;
	        ComboPooledDataSource cpds=null;
	        try {
	          //c3p0��ȡ���ݿ�����conn
	    		cpds= new ComboPooledDataSource();
	    		cpds.setDriverClass("com.mysql.jdbc.Driver");
	    		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/login");
	    		cpds.setUser("root");
	    		cpds.setPassword("wityy");
	    		conn = cpds.getConnection();
	            
	            String sql="select *from users where username=?";
	            ps= conn.prepareStatement(sql);
	            ps.setObject(1,username);
	            rs=ps.executeQuery();
	            while(rs.next()){
	            	users=new Users();
	            	users.setId(rs.getInt("id"));
	            	users.setUsername(rs.getString("username"));
	            	users.setPassword(rs.getString("password"));
	            	users.setPassword_salt(rs.getString("password_salt"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
	            try {
	            	if(rs!=null){
	            		rs.close();
	            	}
	            } catch (SQLException e) {
	                e.printStackTrace();
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
		
	      username=users.getUsername();
	      String password=users.getPassword();
	      String salt=users.getPassword_salt();
	      System.out.println(username);
	      System.out.println(password);
	      System.out.println(salt);
	      //�����ݿ����õ��������Ǿ������ܵģ����Ҽ����Σ����������ݿ��в������������ԣ���Ҫ���ܲ���Ҫ�õ����룬��Ҫ�����ݿ��õ���
	      //����֮�⣬��Ҫ����realm ��֪shiro��Ҫ��md5�ķ�ʽ���н���
	      SimpleAuthenticationInfo info=new 
	    		  SimpleAuthenticationInfo(username,password,ByteSource.Util.bytes(salt),getName());
		
		
		
		return info;
	}

	
}
