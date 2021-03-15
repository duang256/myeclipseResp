package com.bjxst.serviceImpl;

import com.bjxst.dao.UserDao;
import com.bjxst.daoImpl.UserDaoImpl;
import com.bjxst.service.UserService;
import com.bjxst.vo.User;

public class UserServiceImpl implements UserService{


	@Override
	public User getUserInfoService(String name) {
		UserDao ud=new UserDaoImpl();
		return ud.getUserInfoDao(name);
	}

}
