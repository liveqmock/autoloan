package com.yooli.autoloan.base.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yooli.autoloan.base.service.IUsersService;

//import com.yooli.autoloan.base.domain.UserManagement;
//import com.yooli.autoloan.base.service.IUsersService;


@Controller
@RequestMapping
public class UsersController {
	@Resource
	private IUsersService usersService;
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/modules/test/helloWord");
		return mav;
		
	}
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, HttpSession session,String j_username,String j_password, String j_usertype) {
		
		ModelAndView mav =new ModelAndView();
		usersService.queryUser();
		return mav;
	}

		
	//	@RequestMapping(value = "/login")
//	public ModelAndView login(HttpServletRequest request, HttpSession session,String j_username,String j_password, String j_usertype) {
//		Map<String, Object> map = new HashMap<String, Object>(); 
//		ModelAndView mav = new ModelAndView();
//		UserManagement loginuser = new UserManagement();
//		Boolean isExists = usersService.checkUsersIdExists(j_username);
//		if(isExists) {
//			// 假
//			mav.addObject("code", "300");
//			mav.addObject("msg", "用户名不存在！");
//		    mav.setViewName("/modules/security/login");
//			return mav;
//		} 
//		System.out.println(loginuser.getUserId()+"-"+loginuser.getPassword());
//		loginuser = usersService.loginUsers(j_username, j_password);
//		if(null == loginuser) {
//			// 假
//			mav.addObject("code", "300");
//			mav.addObject("msg", "用户名或密码错误！");
//		    mav.setViewName("/modules/security/login");
//			return mav;
//		}
//		//		UserManagement loginuser = new UserManagement();
////		loginuser.setUserId(j_username);
////		loginuser.setPassword(j_password);
////		loginuser.setUserType(j_usertype);
////		map = usersService.loginUsers(j_username, j_password, j_usertype);
////		if("200".equals(map.get("code").toString())) {
////			
////		} else {
////			
////		}
////		"登陆失败!请确认输入的用户名密码正确!" 
//		//session.setAttribute("userId", users.getUserId());
//		
////		Enumeration<String> e = session.getAttributeNames();
////        while (e.hasMoreElements()) {
////            String s = e.nextElement();
////            System.out.println(s + " == " + session.getAttribute(s));
////        }
////		mav.addObject("session", users.getUserId());
////	    mav.setViewName("/modules/security/login");
////		mav.addObject("loginuser", loginuser);
//		mav.addObject("code", "200");
//		mav.addObject("msg", "登陆成功！");
//		if("0".equals(loginuser.getUserType())) {
//		    mav.setViewName("/modules/backstage/index");
//		} else {
//			mav.setViewName("/modules/front/index");
//		}
////        map.put("code", "200");
////        map.put("msg", "");
////        map.put("login_user", loginuser);
////		request.getSession().setAttribute(Resources.LOGIN_USER , loginuser);
////		request.getSession().setAttribute("loginuser" , loginuser.getNickName());
////		session.setAttribute("loginUserId", loginuser.getUserId());
//		session.setAttribute("loginUser", loginuser);
//		Enumeration<String> e = session.getAttributeNames();
//      while (e.hasMoreElements()) {
//          String s = e.nextElement();
//          System.out.println(s + " == " + session.getAttribute(s));
//      }
////		map.put("loginuser", "admin");	
//		return mav;
////			return map;
//	}
//	
//	@RequestMapping(value = "/regist")
//	@ResponseBody
//	public Map<String, Object> registUser(UserManagement users) {
//		return usersService.saveUsers(users);
//	}
//	
//	@RequestMapping(value = "/logout")
//	public ModelAndView logout(HttpServletRequest request, HttpSession session){
//		ModelAndView mav = new ModelAndView();
//	    session.removeAttribute("loginUser");
//	    Cookie[] cookies = request.getCookies(); 
//	    for (Cookie c : cookies) {   
//            if (c.getName().equals("userId"))    
//                c.setMaxAge(0);     
//            if (c.getName().equals("password"))   
//                c.setMaxAge(0);     
//         } 
//		mav.addObject("msg", "退出成功！"); 
//		mav.addObject("code", "200"); 
//	    mav.setViewName("/modules/security/login");     
//         
//	    return mav;
//	}
//	

}
