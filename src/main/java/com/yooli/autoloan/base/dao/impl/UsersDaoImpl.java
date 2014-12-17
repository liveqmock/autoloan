package com.yooli.autoloan.base.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yooli.autoloan.base.dao.IUsersDao;
import com.yooli.autoloan.base.domain.UserManagement;
import com.yooli.autoloan.common.dao.ibatis.IBatisBaseDao;


@Component
public class UsersDaoImpl extends IBatisBaseDao implements
		IUsersDao {

	@Override
	public Integer queryUsersCountByConditions(
			Map<String, Object> map) {
		return (Integer) this.getSqlMapClientTemplate().queryForPaginatedCount("users.queryUsersCountByConditions", map);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<UserManagement> queryUsersByConditions(Map<String, Object> map) {
		return this.getSqlMapClientTemplate().queryForPaginatedList("users.queryUsersByConditions", map);
	}
	@Override
	public List<UserManagement> queryUsers(Map<String, Object> map) {
		return this.getSqlMapClientTemplate().queryForList("users.findUsers",map);
	}
	
	@Override
	public UserManagement findUsersByUserId(String UserId) {
		return (UserManagement) this.getSqlMapClientTemplate().queryForObject("users.findUserByUserId", UserId);
	}

	@Override
	public UserManagement getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long saveUsers(UserManagement users) {
		return (Long) this.getSqlMapClientTemplate().insert("users.addUsers", users);
	}

	@Override
	public UserManagement getAnalogousUser(UserManagement user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateUserPwd(Long id, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getId(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserManagement findUsersByLogin(Map<String, Object> map) {
		return (UserManagement) this.getSqlMapClientTemplate().queryForObject("users.findUserByLogin", map);
	}


}
