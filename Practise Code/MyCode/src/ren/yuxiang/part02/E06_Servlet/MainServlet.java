package ren.yuxiang.part02.E06_Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		super.service(arg0, arg1);
		System.out.println(arg0.getRequestURL());
		System.out.println(arg0.getHeader("User-Agent"));
		HttpSession session = arg0.getSession();
		System.out.println(session.getId());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.addHeader("Content-Type", "text/html;charset=utf-8");
		System.out.println(req.getParameter("account"));
		System.out.println(req.getParameter("password"));
		
		PrintWriter writer = resp.getWriter();
		
		writer.write("Hello world");
		writer.write("123");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		File file = new File("/Users/yuxiang/Desktop/db_baixiaotu.sql");
		Files.copy(file.toPath(), resp.getOutputStream());
		
	}

}
