package com.scap.vtnreport.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scap.vtnreport.model.UserView;
import com.scap.vtnreport.service.LoginService;
import com.scap.vtnreport.service.MenuService;
import com.scap.vtnreport.utils.AesUtil;
import com.scap.vtnreport.utils.Encrytion;
import com.scap.vtnreport.utils.MD5;

/**
 * Servlet implementation class LoginAuthenticationSrvl
 */
@WebServlet("/LoginSrvl")
public class LoginSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginSrvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		response.setContentType("text/html; charset=UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/LoadLoginSrvl"); 
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		// request.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password"); 
		String hospitalcode = request.getParameter("hospitalcode");		

		//new sesion
		HttpSession session = request.getSession();
		
		try {
			if (username != null && !username.isEmpty() && password != null && !password.isEmpty() && hospitalcode != null && !hospitalcode.isEmpty()) {
				LoginService loginService = new LoginService();
				UserView isLoginUser = loginService.doLoginProcess(request);
				System.out.println(isLoginUser.getName());

				if (isLoginUser != null) { 
					 
					session.setAttribute("hospitalcode", hospitalcode);
					session.setAttribute("name", isLoginUser.getName());
					session.setAttribute("role", isLoginUser.getUserGroupCode());
					session.setAttribute("userid", isLoginUser.getLoginName()); 
					session.setAttribute("message", "PASS");
					session.setAttribute("_user",isLoginUser);
					
					if(isLoginUser.getUserGroupCode() != null){
						MenuService menuItem = new MenuService();
						session.setAttribute("menuitem",menuItem.getMenuMappingDetail(isLoginUser.getUserGroupCode().toString()));
					}
					
					RequestDispatcher rd = request.getRequestDispatcher("/MainMenuSrv");
					rd.forward(request, response);
				} else { 
					session.setAttribute("message", "FAIL");
					RequestDispatcher rd = request.getRequestDispatcher("/LoadLoginSrvl");
					rd.forward(request, response);
				}
			} else { 
				session.setAttribute("message", "FAIL");
				RequestDispatcher rd = request.getRequestDispatcher("/LoadLoginSrvl");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			session.setAttribute("message", "FAIL");
			RequestDispatcher rd = request.getRequestDispatcher("/LoadLoginSrvl");
			rd.forward(request, response);
		}
		pw.close();
	}
}
