package com.yooli.autoloan.common.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yooli.autoloan.common.PropertyFactory;


/**
 * author:  徐韬
 * 拦截器,用于存放渲染视图时需要的的共享变量
 * 
 */
public class CommonRenderVariableInterceptor  extends HandlerInterceptorAdapter implements InitializingBean{

	//日志
	static Logger log = Logger.getLogger(CommonRenderVariableInterceptor.class);
	
	// 系统启动并初始化一次的变量
	private Map<String, Object> globalRenderVariables = new HashMap<String, Object>();
	
	// 在系统启动时会执行
	public void afterPropertiesSet() throws Exception {
		initSharedRenderVariables();
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("请求处理完成后，设置共享变量");

		if (modelAndView == null) {
			return;
		}

		String viewName = modelAndView.getViewName();
		if (viewName != null && !viewName.startsWith("redirect:")) {
			modelAndView.addAllObjects(globalRenderVariables);
			modelAndView.addAllObjects(perRequest(request, response));
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return super.preHandle(request, response, handler);
	}

	protected Map<String, Object> perRequest(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> model = new HashMap<String, Object>();
		
		/*Session 封装
		Object userObj = request.getSession(false) == null ? null : request.getSession(false).
				getAttribute(AppConstants.UserInSession.SESSIONKEY);
		if(userObj != null){
			model.put("userInSession", (UserInSession)userObj);
		} else {
			model.put("userInSession", null);
		}
		*/
		model.put("share_current_request_time", new Date());
		model.put("ctx", request.getContextPath());
		
		return model;
	}
	
	// 用于初始化 sharedRenderVariables, 全局共享变量请尽量用global前缀
	private void initSharedRenderVariables() {
		
		// 也可以存放一些共享的工具类,以便视图使用,如StringUtils
		globalRenderVariables.put("global_system_start_time", new Date());
		globalRenderVariables.put("system_url", PropertyFactory.getProperty("system.url"));
	}
	
}
