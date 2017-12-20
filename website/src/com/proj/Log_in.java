package com.proj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Log_in
 */
@WebServlet("/Log_in")
public class Log_in extends HttpServlet {
	Connection con;
	Statement stmt;
	ResultSet rset,rs;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log_in() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306/proj";
        try {
        	Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,"root","root");
			stmt=con.createStatement();
		}catch(Exception e){e.printStackTrace();}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try{
		con.close();
		stmt.close();
		rset.close();
		rs.close();
		}catch(Exception e){e.printStackTrace();}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String uname,password,msg;
		uname=request.getParameter("uname");
		password=request.getParameter("password");
		try{
		String check1="select * from sign_in where uname='"+uname+"'";
		String check2="select * from sign_in where pwd='"+password+"'";
		rset=stmt.executeQuery(check1);
		rs=stmt.executeQuery(check2);
		if(rset.absolute(0) && rs.absolute(0))
		{
			msg="Login Successful";
			response.sendRedirect("proj.jsp?msg="+msg);
		}
		else{
		if(rset.absolute(0))
		{
			msg="User_name not valid";
			response.sendRedirect("proj.jsp?msg="+msg);
		}
		if(rs.absolute(0))
		{
			msg="Password not valid";
			response.sendRedirect("proj.jsp?msg="+msg);
		}
		}
		}catch(Exception e){e.printStackTrace();}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
