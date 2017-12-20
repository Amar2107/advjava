package com.proj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sign_in
 */
@WebServlet("/Sign_in")
public class Sign_in extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sign_in() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
    Connection con;
    Statement stmt;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306/proj";
        try {
        	Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,"root","root");
			stmt=con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try{
		con.close();
		stmt.close();
	}catch(Exception e){e.printStackTrace();
	}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String fname,lname,uname,password;
		String msg,sql;
		fname=request.getParameter("fname");
		lname=request.getParameter("lname");
		uname=request.getParameter("uname");
		password=request.getParameter("password");
		if( fname.equals("") || lname.equals("") ||uname.equals("") || password.equals(""))
		{
			msg="please fill up all the fields";
			response.sendRedirect("proj.jsp?msg="+msg);
		}
		else
		{
			sql="insert into sign_in(fname,lname,uname,pwd) values('"+fname+"','"+lname+"','"+uname+"','"+password+"')";
			try{
				stmt.execute(sql);
			}catch(Exception e){e.printStackTrace();}
			msg="Your account has been created";
			response.sendRedirect("proj.jsp?msg="+msg);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
