package cn.wit.realm;

import java.util.List;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;




import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.wit.pojo.Users;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class UsersRealm  extends AuthorizingRealm {
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "usersRealm";
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		//授权主要的工作是将用户拥有的权限返回给shiro
		
		String username=principals.getPrimaryPrincipal().toString();
		Set <String> permitt=new HashSet<String>();
		Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ComboPooledDataSource cpds=null;
        Users users=new Users();
        try {
          //c3p0获取数据库连接conn
    		cpds= new ComboPooledDataSource();
    		cpds.setDriverClass("com.mysql.jdbc.Driver");
    		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/login");
    		cpds.setUser("root");
    		cpds.setPassword("wityy");
    		conn = cpds.getConnection();
            
            String sql="select e.*,re.rid from element e join role_element re on e.id=re.eid  where rid =(select u.rid from users u where username=?)";
            ps= conn.prepareStatement(sql);
            ps.setObject(1,username);
            rs=ps.executeQuery();
            List<String>elements=new ArrayList<String>();
            
            while(rs.next()){
            	elements.add(rs.getString("eleno"));
            }
            users.setElement(elements);
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
	
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		
		
			
		for (String element : users.getElement()) {
			System.out.println(element);
			info.addStringPermission(element);
		}
		
		return info;
		
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//认证要做的主要工作就是通过用户名将用户信息从数据库中取出来，然后让shiro去帮助比对
		String username=(String) token.getPrincipal();
		String pwd="";
	
		Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ComboPooledDataSource cpds=null;
        try {
          //c3p0获取数据库连接conn
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
            	Users users=new Users();
            	users.setId(rs.getInt("id"));
            	users.setUsername(rs.getString("username"));
            	users.setPassword(rs.getString("password"));
            	pwd=users.getPassword();
            	username=users.getUsername();
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
	
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(username,pwd,getName());
		return info;
	}

}
