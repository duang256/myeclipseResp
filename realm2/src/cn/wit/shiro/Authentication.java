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
 * ����û���֤����
 * @author Administrator
 *
 */
public class Authentication {
	public static void main(String[] args) {
		//�õ�SecurityManager�������ŵ���������
		Factory<SecurityManager>factory=new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		//�õ�subject�ӿ�
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken taken=new UsernamePasswordToken("zhangsan","123");
		try {
			if(taken!=null){
				subject.login(taken);
			}
			if(subject.isAuthenticated()){
				System.out.println("��¼�ɹ�");
			}
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			System.out.println("�˺Ż��������");
		}catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			System.out.println("�˺Ż��������");
		}
		
		
	}
}