package   com.scap.vtnreport.controller;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();  
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hospitalcode = request.getParameter("hospitalcode");
 
		LoginService  loginService = new  LoginService(); 
		String isLogin = loginService.doLoginProcess(username, password, hospitalcode);
		if(isLogin =="LDAPLOGIN" || isLogin =="SYSLOGIN" ){ 
			  request.getRequestDispatcher("/WEB-INF/pages/forms/NewFile.jsp").include(request, response);  
			  HttpSession session=request.getSession();  
		      session.setAttribute("hospitalcode",hospitalcode);  
		} 
		else{
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.forward(request, response);
			
		} 
		pw.close();  
	}
	 	

}
