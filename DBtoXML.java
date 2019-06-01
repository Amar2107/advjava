package domParsing;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class DBtoXML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","neonowl");
			
			String getAllUser = "select * from ws_user";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getAllUser);
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("userdb");
			while(rs.next()) {
			Element rootelement = root.addElement("userdb").addAttribute("userName",rs.getString("userName"));
			rootelement.addElement("Name").addText(rs.getString("name"));
			rootelement.addElement("Email").addText(rs.getString("email"));
			rootelement.addElement("passWord").addText(rs.getString("passWord"));
			}
			
			OutputFormat opfrmt = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(System.out,opfrmt);
			writer.write(doc);
			XMLWriter filewriter = new XMLWriter(new FileOutputStream("D://new.xml"),opfrmt);
			filewriter.write(doc);
		}catch(Exception e) {e.printStackTrace();}
	}

}
