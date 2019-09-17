package com.scap.vtnreport.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class setMailScheduleSrvl
 */
@WebServlet("/setMailScheduleSrvl")
public class setMailScheduleSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setMailScheduleSrvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); 
		HttpSession session = request.getSession(false); 
		String page = "";
		if(session.getAttribute("_user") != null){
			page = "/WEB-INF/pages/menu_set_schedule/set_schedule.jsp";  
		}else{
		    page = "/SessionTimeoutSrvl";
		} 
		System.out.println(page);
		RequestDispatcher rd = request.getRequestDispatcher(page); 
		rd.forward(request, response);
	}
	

}
