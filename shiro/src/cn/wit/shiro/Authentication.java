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
 * ����û���֤����
 * @author Administrator
 *
 */
public class Authentication {
	public static void main(String[] args) {
		//����SecurityManager���� ��ȡ�����ļ�
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
		//ͨ��SecurityManager������ȡSecurityManager��ʵ��
		SecurityManager securityManager=factory.getInstance();
		//��SecurityManager�������õ����л�����
		SecurityUtils.setSecurityManager(securityManager);
		//ͨ��SecurityUtils��ȡ����subject
		Subject subject=SecurityUtils.getSubject();
		//�����û��� zhangsan��1111,������Ե������Ǽ����¼ʱ�û����������
		//��ini�ļ��е������൱�����ݿ��е�����
		UsernamePasswordToken token=new UsernamePasswordToken("zhangsan","123");
		try {
			//�û������֤
			subject.login(token);
			//ͨ��subject�ж��û��Ƿ�ͨ����֤,isAuthenticated��ʾ�û��Ƿ���֤��
			if(subject.isAuthenticated()){
				System.out.println("��¼�ɹ�");
			}
		} catch (UnknownAccountException e) {
			// TODO Auto-generated catch block
			System.out.println("�û��������벻��ȷ");
		}catch (IncorrectCredentialsException e) {
			System.out.println("�û��������벻��ȷ");
		}
		
	}
}
