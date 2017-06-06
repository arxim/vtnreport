package com.scap.vtnreport.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scap.vtnreport.utils.DbConnector;
import com.scap.vtnreport.utils.ReadProperties;
import com.scap.vtnreport.dao.DropDownDao;

/**
 * Servlet implementation class DropDownListGeneratorSrvl
 */
@WebServlet("/DropDownListGeneratorSrvl")
public class DropDownListGeneratorSrvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DropDownListGeneratorSrvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doProcess(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String writeOut = "";
		DropDownDao dropDown = new DropDownDao();

		switch (request.getParameter("url")) {
		case "getHospital":
			writeOut = dropDown.getHospital();
			break;
		case "getYYYY":
			writeOut = dropDown.getYYYY();
			break;

		default:
			break;
		}

		out.println(writeOut);
		out.close();
	}
}
