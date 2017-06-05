package com.scap.vtnreport.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		String hospitalcode = request.getParameter("hospitalcode");
		String passphrase = request.getParameter("hidPassphrase");
//		String iv = request.getParameter("hidIv");
//		String salt = request.getParameter("hidSalt");
//		int iterationCount = Integer.parseInt(request.getParameter("hidIterationCount"));
//		int keySize = Integer.parseInt(request.getParameter("hidKeySize"));
//		String view = null;

		 try {
		 
			HttpSession sessionPass = request.getSession(false); 
			String sessionInSrvl = sessionPass.getAttribute("passphrase").toString();
//			System.out.println("sessionPass "+sessionPass.toString());
//			System.out.println("sessionPass.getAttribute"+sessionPass.getAttribute("passphrase").toString());
//			System.out.println("passphrase "+passphrase);
//			System.out.println("sessionPass.equals(passphrase) "+sessionPass.equals(passphrase));
			if(sessionInSrvl != null && sessionInSrvl.equals(passphrase)){
				LoginService loginService = new LoginService();
				String isLogin = loginService.doLoginProcess(request);
				if (isLogin  != "FAIL") {
					HttpSession session = request.getSession();
					session.setAttribute("hospitalcode", hospitalcode);
					request.getRequestDispatcher("/WEB-INF/pages/forms/NewFile.jsp").include(request, response);
				     
//					 RequestDispatcher rd =
//							 request.getRequestDispatcher("/WEB-INF/pages/forms/NewFile.jsp");
//							 rd.forward(request, response);
				}else{
//					 RequestDispatcher rd =
//					 request.getRequestDispatcher("/index.html");
//					 rd.forward(request, response);
					response.sendRedirect(request.getContextPath());
				}
				
				
//				AesUtil aesUtil = new AesUtil(keySize, iterationCount);
//				
//				// decrypt aes from passphrase session
//		        String decryptPwd = aesUtil.decrypt(salt, iv, passphrase, password);
//			    
//		    	LoginService loginService = new LoginService();
//				String isLogin = loginService.doLoginProcess(username, decryptPwd,hospitalcode);
//				if (isLogin == "LDAPLOGIN" || isLogin == "SYSLOGIN") {
//					request.getRequestDispatcher("/WEB-INF/pages/forms/NewFile.jsp").include(request, response);
//					HttpSession session = request.getSession();
//					session.setAttribute("hospitalcode", hospitalcode);
//				} else {
//					// RequestDispatcher rd =
//					// request.getRequestDispatcher("/index.html");
//					// rd.forward(request, response);
//					request.getRequestDispatcher("/index.html").include(request,response);
//				}
			
			
			 

		
		}else{
//			 RequestDispatcher rd =
//					 request.getRequestDispatcher("/index.html");
//					 rd.forward(request, response);
			response.sendRedirect(request.getContextPath());
		}
//		pw.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}

}
