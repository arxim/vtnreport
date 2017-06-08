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

import com.scap.vtnreport.utils.Encrytion;

/**
 * Servlet implementation class LogoutSrvl
 */
@WebServlet("/LogoutSrvl")
public class LogoutSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutSrvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		//old session
		HttpSession session = request.getSession(false);
		 
		System.out.println("==============================logout=============================================");
		System.out.println("_user : " + session.getAttribute("_user")  ); 
		
		if(session.getAttribute("_user") != null){
			    //destroy old sesion
				session.invalidate(); 
				//create new sesion
				HttpSession newSession = request.getSession(true);
				System.out.println("==============================just logout=============================================");
				System.out.println("_user : " + newSession.getAttribute("_user")  ); 
		}
	
		request.setAttribute("vaMessage","LOGOUT");
		RequestDispatcher rd = request.getRequestDispatcher("/index.html");
	    rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		System.out.println("==============================logout=============================================");
		System.out.println("_user : " + session.getAttribute("_user")  ); 
	}

}
