package Dao;

import pojo.User;

public interface LoginDao {
	User checkLoginDao(String uname,String pwd);
	User checkUnameDao(String uname);
}

