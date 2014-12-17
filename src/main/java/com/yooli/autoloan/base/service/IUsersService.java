package com.yooli.autoloan.base.service;

import java.util.Map;

import com.yooli.autoloan.base.domain.UserManagement;


public interface IUsersService {

	Boolean checkUsersIdExists(String userId);

	UserManagement loginUsers(String userId,String password);

	Map<String, Object> saveUsers(UserManagement users);

	void queryUser();

}
