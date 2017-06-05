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

import com.scap.vtnreport.utils.AesUtil;
import com.scap.vtnreport.utils.Encrytion;

/**
 * Servlet implementation class LoadLogin
 */
@WebServlet("/LoadLoginSrvl")
public class LoadLoginSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadLoginSrvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
//		request.getRequestDispatcher("/index.html").include(request,response);
		String passphrase = AesUtil.random(16); 
		
		try { 
			HttpSession session = request.getSession();
			session.setAttribute("passphrase", Encrytion.encrypt(passphrase));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/forms/login.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
