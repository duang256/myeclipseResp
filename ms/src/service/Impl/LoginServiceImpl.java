package service.Impl;

import pojo.User;
import Dao.LoginDao;
import Dao.Impl.LoginDaoImpl;
import service.LoginService;

public class LoginServiceImpl implements LoginService{
	LoginDao ld= new LoginDaoImpl();

	@Override
	public User checkLoginService(String uname, String pwd) {
		return (User) ld.checkLoginDao(uname, pwd);
	}

	@Override
	public User checkUnameService(String uname) {
		return ld.checkUnameDao(uname);
	}
	
}
