package database_connection;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Webpage {

	public static Connection createconnection()throws Exception
	{
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","neonowl");
			return conn;
		}catch(Exception e)
		{e.printStackTrace();}
		return null;
	}
	
	
	public static String createTable()throws Exception
	{
		String query1,str="";
		int count=0;
		try
		{
			query1="select count(*) as count1 from information_schema.TABLES where (TABLE_SCHEMA = 'db1') and (table_name = 'student')";
			//query1="show tables like 'student'";
			Connection conn = createconnection();
			Statement stmt = conn.createStatement();
			ResultSet rst1 = stmt.executeQuery(query1);
			if(rst1.next())
				str= "Table exists";
			else {
				System.out.println("Creating table");
				String query="Create table Student1(ID int, FirstName varchar(255), LastName varchar(255), Class varchar(255))";
				stmt.executeUpdate(query);
			str= "table created";
			}
			rst1.close();
			stmt.close();
			conn.close();
		}catch(Exception e){e.printStackTrace();}
		return str;
	}

	public static void insertrecords()throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			Connection conn = createconnection();
			Statement stmt = conn.createStatement();
			System.out.println("Enter the number of records to be inserted");
			int a = Integer.parseInt(br.readLine());
			while(a>0) {
			System.out.print(" ID = ");
			int id1 = Integer.parseInt(br.readLine());
			System.out.print(" First Name = ");
			String fname1 = br.readLine();
			System.out.print(" Last Name = ");
			String lname1 = br.readLine();
			System.out.print(" Class = ");
			String Klass1 = br.readLine();
			String query1="Insert into Student values( "+id1+" , '"+fname1+"' , '"+lname1+"' , '"+Klass1+"')";
			stmt.executeUpdate(query1);
			a--;
			}
			stmt.close();
			conn.close();
			System.out.println("Record inserted");
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public static void showrecords()
	{
		try {
		Connection conn = createconnection();
		Statement stmt = conn.createStatement();
		String query2 = "Select * from Student";
		ResultSet rst = stmt.executeQuery(query2);
		while(rst.next())
		{
			int id = rst.getInt("ID");
			String fname =rst.getString("FirstName");
			String lname =rst.getString("LastName");
			String klass =rst.getString("Class");
			System.out.println("ID "+id+" FirstName "+fname+" LastName "+lname+" class "+klass);
		}
		rst.close();
		stmt.close();
		conn.close();
	}catch(Exception e){e.printStackTrace();}
	}
	public static void getRecord()throws SQLException
	{
		try {
		Connection conn = createconnection();
		PreparedStatement stmt = conn.prepareStatement("Select * from student where class = ? and FirstName = ?");
		stmt.setString(1,"Cse");
		stmt.setString(2, "Amar");
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			int id = rs.getInt("ID");
			String fname =rs.getString("FirstName");
			String lname =rs.getString("LastName");
			String klass =rs.getString("Class");
			System.out.println("ID "+id+" FirstName "+fname+" LastName "+lname+" class "+klass);
		}
		rs.close();
		stmt.close();
		conn.close();
		}catch(Exception e) {e.printStackTrace();}
	}
	public static void checkmarks()throws SQLException
	{
		PreparedStatement stmt=null;
		ResultSet rs = null;
		int id=0;
		try {
			Connection conn = createconnection();
			stmt = conn.prepareStatement("Select id from marks where marks > ? and subject = ?");
			stmt.setInt(1,80);
			stmt.setString(2,"Data Structure");
			rs = stmt.executeQuery();
			rs.next();
			id = rs.getInt(1);
			if(id != 0)
			{
				stmt = conn.prepareStatement("select FirstName from student where id = ?");
				stmt.setInt(1,id);
				rs = stmt.executeQuery();
				while(rs.next())
				{
					System.out.println("Name "+rs.getString(1));
				}
			}
			
		}catch(Exception e) {e.printStackTrace();}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(createTable());	
			insertrecords();
			showrecords();
			getRecord();
			checkmarks();
		}catch(Exception e) {e.printStackTrace();}
	}
}
