package ren.yuxiang.part02.E06_Servlet;

import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class MyGenericServlet implements Servlet, ServletConfig, Serializable {


	/**
	 * 由子类实现
	 */
	public abstract void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException;	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ServletConfig config;
	
	
	public void init() {
		
	}
	
	public void init(ServletConfig arg0) throws ServletException {
		this.config = arg0;
		init();
	}
	

	/**
	 * 将config 暴露给子类使用
	 */
	public ServletConfig getServletConfig() {

		return this.config;
	}

	public String getServletInfo() {

		return null;
	}
	

	public void destroy() {

		
	}


	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return config.getServletContext();
	}

	@Override
	public String getInitParameter(String arg0) {
		// TODO Auto-generated method stub
		return config.getInitParameter(arg0);
	}
	
	@Override
	public Enumeration<String> getInitParameterNames() {
		// TODO Auto-generated method stub
		return config.getInitParameterNames();
	}

	@Override
	public String getServletName() {
		// TODO Auto-generated method stub
		return config.getServletName();
	}

}
