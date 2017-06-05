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

 

import com.scap.vtnreport.service.LoginService;

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
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter(); 
		String hospitalcode = request.getParameter("hospitalcode");
		String passphrase = request.getParameter("hidPassphrase"); 

		 try {
		 
			HttpSession sessionPass = request.getSession(false); 
			String sessionInSrvl = sessionPass.getAttribute("passphrase").toString();
 
			if(sessionInSrvl != null && sessionInSrvl.equals(passphrase)){
				LoginService loginService = new LoginService();
				String isLogin = loginService.doLoginProcess(request);
				if (isLogin  != "FAIL") {
					HttpSession session = request.getSession();
					session.setAttribute("hospitalcode", hospitalcode);
					request.setAttribute("vaMessage","LOGIN");
					request.getRequestDispatcher("/WEB-INF/pages/forms/NewFile.jsp").include(request, response);
				     
 
				}else{
 
					request.setAttribute("vaMessage","FAIL"); 
					response.sendRedirect(request.getContextPath());
				} 
		
		}else{ 
			request.setAttribute("vaMessage","FAIL");
			response.sendRedirect(request.getContextPath());
		}
		pw.close();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("vaMessage","FAIL");
			response.sendRedirect(request.getContextPath());
		}
		
	}

}
