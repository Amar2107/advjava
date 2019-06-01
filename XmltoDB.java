package domParsing;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class XmltoDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String userName="", Email="", passWord="", Name="";
		
		try {
			File ipfile = new File("userInfo.xml");
			SAXReader sreader = new SAXReader();
			Document doc = sreader.read(ipfile);
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","neonowl");
			String insert_user = "insert into ws_user values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(insert_user);
			
			Element selement = doc.getRootElement();
			
			List<Node> nodes = doc.selectNodes("/userdb/user");
			
			for(Node node : nodes)
			{
			   userName = node.valueOf("@userName") ;
			   Name=node.selectSingleNode("Name").getText();
			   Email=node.selectSingleNode("Email").getText();
			   passWord=node.selectSingleNode("passWord").getText();
			   ps.setString(1, Name);
			   ps.setString(2, Email);
			   ps.setString(3, userName);
			   ps.setString(4,passWord);
			   ps.executeUpdate();
			   System.out.println("user inserted");
			   
			}
			
		}catch(Exception e) {e.printStackTrace();}

	}

}
