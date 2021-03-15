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
		//创建SecurityManager工厂 读取配置文件
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
		//通过SecurityManager工厂获取SecurityManager的实例
		SecurityManager securityManager=factory.getInstance();
		//将SecurityManager对象设置到运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		//通过SecurityUtils获取主体subject
		Subject subject=SecurityUtils.getSubject();
		//假如用户名 zhangsan和1111,这里测试的数据是假设登录时用户输入的数据
		//而ini文件中的数据相当于数据库中的数据
		UsernamePasswordToken token=new UsernamePasswordToken("zhangsan","123");
		try {
			//用户身份验证
			subject.login(token);
			//通过subject判断用户是否通过验证,isAuthenticated表示用户是否被验证过
			if(subject.isAuthenticated()){
				System.out.println("登录成功");
			}
		} catch (UnknownAccountException e) {
			// TODO Auto-generated catch block
			System.out.println("用户名或密码不正确");
		}catch (IncorrectCredentialsException e) {
			System.out.println("用户名或密码不正确");
		}
		
	}
}
