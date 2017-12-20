/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


/**
 *
 * @author SONU
 */
public class Html extends HttpServlet {
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//           PrintWriter out=response.getWriter();
           String sname,cty,userid,pwd,courseid;
           sname=request.getParameter("Name");
           cty=request.getParameter("City");
           userid=request.getParameter("User");
           pwd=request.getParameter("password");
           courseid=request.getParameter("Id");
           
           System.out.println(sname + "\t" + cty + "\t" + userid + "\t" + pwd + "\t" + courseid);
                   
           if((sname.equals("")) || (cty.equals("")) || (userid.equals("")) || (pwd.equals("")) || (courseid.equals(""))) {
           response.sendRedirect("ErrorPage");
           }
           else
           {
               try{
                            String url="jdbc:mysql://localhost:3306/db1?useSSL=false";
                            String pass="root";
                            String user="root";
                           response.setContentType("text/html;charset=UTF-8");
                          String sql;
                          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                          Connection connect=DriverManager.getConnection(url,pass,user);
                          Statement stmt=connect.createStatement();
                          sql="insert into student(StudentName,city,CourseId) values('"+sname+"','"+cty+"','"+courseid+"')";
                          stmt.executeUpdate(sql);
                          System.out.println("insertion successful");
                          connect.close();
                          stmt.close();
                         response.sendRedirect("welcome");
                           }catch(Exception e){e.printStackTrace();}
           }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}