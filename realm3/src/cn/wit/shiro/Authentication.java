package cn.wit.shiro;


import org.apache.shiro.SecurityUtils;



import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
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
		//相当于配置realm
		Factory<SecurityManager>factory=new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		//拿到subject接口
		Subject subject = SecurityUtils.getSubject();
		//模拟拿到用户登录信息
		UsernamePasswordToken taken=new UsernamePasswordToken("zhangsan","123");
		try {
			if(taken!=null){
				subject.login(taken);
			}
			if(subject.isAuthenticated()){
				System.out.println("登录成功");
			}
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			System.out.println("账号或密码错误");
		}catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			System.out.println("账号或密码错误");
		}
		System.out.println(subject.isPermitted("flower:select"));
		System.out.println(subject.isPermitted("flower:add"));
		System.out.println(subject.isPermitted("flower:update"));
		System.out.println(subject.isPermitted("flower:delete"));
		
		
	}
}
