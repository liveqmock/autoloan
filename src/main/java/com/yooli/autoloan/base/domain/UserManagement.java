package com.yooli.autoloan.base.domain;

/**
 * 用户信息
 * @author Mike.Lin
 * @createDate 2014年10月30日 上午10:58:07
 * @version 2014年10月30日
 */
public class UserManagement {
	/*管理员用户角色标识*/
	public static final int ADMIN = 0;
	/*加盟用户角色标识*/
	public static final int JOIN_CUSTOMER = 2;
	/*租赁用户角色标识*/
	public static final int LEASE_CUSTOMER = 3;
	/*重置密码标识*/
	public static final String PWD = "111111";
	
	/* 签名账号,采用账号生成器生成 */
	private Long account = AccountGenerator.generate();
	
	/*用户签名是否可用*/
//	private Boolean enabled = true;
	
	
	/* 签名标识 */
	private Long id;
	/* 签名中包含的用户名（用户名,不可重复后台控制不在数据库中控制） */
	private String userId;
	/* 签名中包含的用户密码（登录密码） */
	private String password;
	/* 昵称 */
	private String nickName;
	/* 账号状态 */
	private String status;
	/* 账号权限 */
	private String authority;
	/* 用户类型,加盟用户/租赁用户 */
	private String userType;
	/* 营业执照,针对加盟用户 */
	private String businessLicence;
	/* 年龄 */
	private String age;
	/* 个人姓名/公司名 */
	private String name;
	/* 个人电话/公司负责人电话 */
	private String telephone;
	/* 邮箱 */
	private String email;
	/* 城市 */
	private String city;
	/* 腾讯qq */
	private String qq;
	/* 新浪微博 */
	private String xinLang;
	/* 公司主要经营产品 */
	private String products;
	/* 公司注册资本 */
	private String registeredCapital;
	/* 个人意向购买设备 */
	private String intent;
	/* 注册时间 */
	private String registrationTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getBusinessLicence() {
		return businessLicence;
	}
	public void setBusinessLicence(String businessLicence) {
		this.businessLicence = businessLicence;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getXinLang() {
		return xinLang;
	}
	public void setXinLang(String xinLang) {
		this.xinLang = xinLang;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	public String getRegisteredCapital() {
		return registeredCapital;
	}
	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public String getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}

}
