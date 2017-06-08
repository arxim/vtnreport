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
		HttpSession sessionPass = request.getSession(false);
		System.out.println("==============================logout=============================================");
		System.out.println("_user " + sessionPass.getAttribute("_user") ); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();

		String hospitalcode = request.getParameter("hospitalcode");
		String passphrase = request.getParameter("hidPassphrase"); 
		try {

			HttpSession sessionPass = request.getSession(false);
			String sessionInSrvl = sessionPass.getAttribute("passphrase").toString();

			if (sessionInSrvl != null && sessionInSrvl.equals(passphrase)) {
				LoginService loginService = new LoginService();
				UserView isLoginUser = loginService.doLoginProcess(request);
				if (isLoginUser != null) {

					HttpSession session = request.getSession();
					session.setAttribute("hospitalcode", hospitalcode);
					session.setAttribute("name", isLoginUser.getName());
					session.setAttribute("role", isLoginUser.getUserGroupCode());
					session.setAttribute("userid", isLoginUser.getLoginName());
				 
					session.setAttribute("_user",isLoginUser);
					// request.getRequestDispatcher(request.getContextPath()+"/HomeSrvl").include(request,
					// response);
					// response.sendRedirect(request.getContextPath()+"/MainMenuSrv");
					// request.getRequestDispatcher("/WEB-INF/pages/forms/home.jsp").include(request,
					// response);
					
					if(isLoginUser.getUserGroupCode() != null){
						MenuService menuItem = new MenuService();
						session.setAttribute("menuitem",menuItem.getMenuMappingDetail(isLoginUser.getUserGroupCode().toString()));
					}
					
					
					RequestDispatcher rd = request.getRequestDispatcher("/MainMenuSrv");
					rd.forward(request, response);

				} else { 
					 response.sendRedirect(request.getContextPath());
				}

			}

			else { 
 
				 response.sendRedirect(request.getContextPath()); 

			}
 
		} catch (Exception e) {
			e.printStackTrace();
 
			 response.sendRedirect(request.getContextPath());
		}
		pw.close();

	}

}
