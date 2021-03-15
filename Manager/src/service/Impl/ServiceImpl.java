package service.Impl;

import java.util.List;

import org.apache.log4j.Logger;

import pojo.User;
import service.UserService;
import Dao.LoginDao;
import Dao.Impl.LoginDaoImpl;

public class ServiceImpl implements UserService{
	LoginDao ld=new LoginDaoImpl();
//声明日志对象
	Logger logger=Logger.getLogger(ServiceImpl.class);

	@Override
	public User checkUserLoginService(String uname, String pwd) {
		logger.debug(uname+"发起登录请求");
		User user=ld.checkUserLoginDao(uname, pwd);
		if(user!=null){
			logger.debug(uname+"登陆成功");
		}else{
			logger.debug(uname+"登陆失败");
		}
		return user;
	}

	//修改用户密码
	@Override
	public int userChangePwdService(int uid, String newPwd) {
		logger.debug(uid+":发起密码修改");
		int index=ld.userChangePwdDao(uid,newPwd);
		if(index>0){
			logger.debug(uid+":密码修改成功");
		}else{
			logger.debug(uid+":密码修改失败");
		}
		return index;
	}

	//获取所有用户信息
	@Override
	public List<User> userShowService() {
		// TODO Auto-generated method stub
		List<User> list=ld.usershowDao();
		if(list!=null){
			logger.debug("显示所有用户信息"+list);
		}else{
			logger.debug("查看用户信息失败");
		}
		return list;
	}

	@Override
	public int userRegService(User user) {
		int index=ld.userRegDao(user);
		if(index>0){
			logger.debug("注册成功");
		}else{
			logger.debug("注册失败");
		}
		return index;
	}
	
}
