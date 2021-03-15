package service;

import java.util.List;

import pojo.User;

public interface UserService {
	 User checkUserLoginService(String uname,String pwd);
	 int userChangePwdService(int uid,String pwd);
	 List<User> userShowService();
	int userRegService(User user);
}
