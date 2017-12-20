package com.website1;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class response
 */
@WebServlet("/response")
public class response extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public response() {
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
    	String url="jdbc:mysql://localhost:3306/db1";
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
		try {
			stmt.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType("text/html");
		String name,email,password;
		name=request.getParameter("name");
		email=request.getParameter("email");
		password=request.getParameter("password");
		String msg=" ";
		  if((name==null)||(email==null)||(password==null))
		  {
			  msg="Fill up all the fields";
			res.sendRedirect("page.jsp?msg="+msg);
		  }
		else
		{
			msg="login successful";
		res.sendRedirect("page.jsp?msg="+msg);
		}
		 String sql="insert into login(name,email,password) values('"+name+"','"+email+"','"+password+"')";
	      try {
			stmt.execute(sql);
			System.out.println("data inserted");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      try {
			ResultSet rset=stmt.executeQuery("select * from login");
			while(rset.next())
		      {
		    	System.out.println(rset.getString("name")+"\t"+rset.getString("email")+"\t"+rset.getString("password"));  
		      }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	    	  
	}
	private static PrintStream getWriter() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse res)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, res);
	}
}
