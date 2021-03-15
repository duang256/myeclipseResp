package cn.wit.shiro;

import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;

/**
 * 完成用户认证功能
 * @author Administrator
 *
 */
public class Authentication {
	public static void main(String[] args) {
		//拿到SecurityManager并将它放到环境当中
		Factory<SecurityManager>factory=new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		//拿到subject接口
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken taken=new UsernamePasswordToken("zhangsan","123");
		try {
			subject.login(taken);
			if(subject.isAuthenticated()){
				System.out.println("登录成功");
			}
		} catch (UnknownAccountException e) {
			System.out.println("账号或密码错误");
		}catch (IncorrectCredentialsException e) {
			System.out.println("账号或密码错误");
		}
		
		
	}
}
