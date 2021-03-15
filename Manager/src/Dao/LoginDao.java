package Dao;

import java.util.List;

import pojo.User;

public interface LoginDao {
	User checkUserLoginDao(String uname,String pwd);

	int userChangePwdDao(int uid, String newPwd);

	List<User> usershowDao();

	int userRegDao(User user);

	
}
