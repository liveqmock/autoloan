package com.yooli.autoloan.base.dao;

import java.util.List;
import java.util.Map;

import com.yooli.autoloan.base.domain.UserManagement;


/**
 * 用户Dao
 * @author Mike.Lin
 * @createDate 2014年10月30日 下午2:50:58
 * @version 2014年10月30日
 */
public interface IUsersDao {
	Integer queryUsersCountByConditions(Map<String, Object> map);
	List<UserManagement> queryUsersByConditions(Map<String, Object> map);
	
	UserManagement findUsersByUserId(String UserId);
	UserManagement getUserById(Long id);

	Long saveUsers(UserManagement users);
	
	/**
	 * 获取与传入用户类似的用户，既用户相同但ID不同
	 * @param user
	 * @return
	 */
	UserManagement getAnalogousUser(UserManagement user);
	/**
	 * 用户密码修改
	 * @param id
	 * @param pwd
	 */
	Integer updateUserPwd(Long id, String pwd);
	
	/**
	 * 获取用户id 
	 */
	Long getId(String username);
	UserManagement findUsersByLogin(Map<String, Object> map);
	List<UserManagement> queryUsers(Map<String, Object> map);

	
	/**
	 * 根据登录id删除用户信息
	 * @param loginId		登录ID
	 * @param flag			用户类型
	 * @return
	 */
	 
	/*Integer deleteUser(String loginId,Integer flag);*/
}
