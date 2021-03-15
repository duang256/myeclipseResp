package service.Impl;

import java.util.List;

import org.apache.log4j.Logger;

import pojo.User;
import service.UserService;
import Dao.LoginDao;
import Dao.Impl.LoginDaoImpl;

public class ServiceImpl implements UserService{
	LoginDao ld=new LoginDaoImpl();
//������־����
	Logger logger=Logger.getLogger(ServiceImpl.class);

	@Override
	public User checkUserLoginService(String uname, String pwd) {
		logger.debug(uname+"�����¼����");
		User user=ld.checkUserLoginDao(uname, pwd);
		if(user!=null){
			logger.debug(uname+"��½�ɹ�");
		}else{
			logger.debug(uname+"��½ʧ��");
		}
		return user;
	}

	//�޸��û�����
	@Override
	public int userChangePwdService(int uid, String newPwd) {
		logger.debug(uid+":���������޸�");
		int index=ld.userChangePwdDao(uid,newPwd);
		if(index>0){
			logger.debug(uid+":�����޸ĳɹ�");
		}else{
			logger.debug(uid+":�����޸�ʧ��");
		}
		return index;
	}

	//��ȡ�����û���Ϣ
	@Override
	public List<User> userShowService() {
		// TODO Auto-generated method stub
		List<User> list=ld.usershowDao();
		if(list!=null){
			logger.debug("��ʾ�����û���Ϣ"+list);
		}else{
			logger.debug("�鿴�û���Ϣʧ��");
		}
		return list;
	}

	@Override
	public int userRegService(User user) {
		int index=ld.userRegDao(user);
		if(index>0){
			logger.debug("ע��ɹ�");
		}else{
			logger.debug("ע��ʧ��");
		}
		return index;
	}
	
}
