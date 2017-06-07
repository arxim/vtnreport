package com.scap.vtnreport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomeSrvl
 */
@WebServlet("/HomeSrvl")
public class HomeSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeSrvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("home   !!!! "); 
		HttpSession session = request.getSession(false);
		session.setAttribute("hoisLoginRolespitalcode", session.getAttribute("hospitalcode"));
		session.setAttribute("role", session.getAttribute("role"));
		session.setAttribute("vaMessage", session.getAttribute("vaMessage"));
		request.setAttribute("vaMessage",request.getAttribute("vaMessage"));
		request.getRequestDispatcher("/WEB-INF/pages/forms/home.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
