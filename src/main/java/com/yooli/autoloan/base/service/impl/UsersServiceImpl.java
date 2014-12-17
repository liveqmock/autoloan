package com.yooli.autoloan.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yooli.autoloan.base.dao.IUsersDao;
import com.yooli.autoloan.base.domain.UserManagement;
import com.yooli.autoloan.base.service.IUsersService;


@Component
public class UsersServiceImpl implements IUsersService {
	@Resource
	private IUsersDao usersDao;

	@Override
	public Boolean checkUsersIdExists(String userId) {
		UserManagement user = new UserManagement();
		user = usersDao.findUsersByUserId(userId);
		if(null == user) {
			return true;
		} 
		return false;
	}
	@Override
public void queryUser(){
	 List<UserManagement> list=this.usersDao.queryUsers(new HashMap<String, Object>());
	 System.out.println(list);

}


	@Override
	public UserManagement loginUsers(String userId,String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		UserManagement user = new UserManagement();
		map.put("userId", userId);
		map.put("password", password);
		user = usersDao.findUsersByLogin(map);
//		user = this.checkUsersIdExists(userId);
//		map = this.checkUsers(user);
//		if(map.get("code").equals(obj))
		return user;
	}

	@Override
	public Map<String, Object> saveUsers(UserManagement users) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", users.getUserId());
//		List<UserManagement> userList = usersDao.queryUsersByConditions(map);
		Integer usersCount = usersDao.queryUsersCountByConditions(map);
		System.out.println(usersCount);
		if(usersCount > 0) {
			map.put("code", "300");
			map.put("msg", "注册失败，该用户名已存在！");
			return map;
		}
		Long usersId = usersDao.saveUsers(users);
		System.out.println("该注册用户id为："+usersId+"，用户名为："+users.getUserId());
		map.put("code", "200");
		map.put("msg", "注册成功，您的用户名为："+users.getUserId()+"，密码为："+users.getPassword()+"！");
		return map;
	}


}
