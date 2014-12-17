package com.yooli.autoloan.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

public class LocationFilter extends OncePerRequestFilter implements Filter{

	
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		
		String currentURL = request.getRequestURI();
		currentURL = currentURL.endsWith("/") ? currentURL.substring(0,
				(currentURL.length() - 1)) : currentURL;
		
		String loadingRequestPath = request.getContextPath() + "/logoutLoading";
		
		//判断当前是否有Session，如果没有，则先跳转到一个过渡页面，再进行跳转
		HttpSession session = request.getSession(false);
		if(!currentURL.endsWith(request.getContextPath())
				&& !currentURL.contains("services/dataUpdateService")
				&& !currentURL.endsWith(loadingRequestPath) 
				&& !currentURL.endsWith("j_spring_cas_security_check")
				&& !currentURL.endsWith(request.getContextPath())){
			Object o = null;
			if(session != null){
//				o=session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
			}
        	if(null==o){
        		PrintWriter out = response.getWriter();
        		try{
        			out.write("<script>top.location='"+ request.getContextPath() + "'</script>");
        			out.flush();
        		} catch(Exception e ){
        			e.printStackTrace();
        		} finally{
        			out.close();
        		}
        		return;
        	}
        } 
        chain.doFilter(request, response);
	}
}
