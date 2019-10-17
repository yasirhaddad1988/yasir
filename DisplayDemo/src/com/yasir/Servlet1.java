package com.yasir;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Servlet1() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//display data into the browser
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		out.print("<h1>Display the records</h1>");
		out.print("<table border='1'><tr><th>Id</th><th>Name</th><th>Address</th></tr>");
		try
		{
			//here we write the database connectivity
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "yasirhaddad",
					"Yasir@1988");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from t12 where id="+id+"");
			while(rs.next())
			{
				out.print("<tr><td>");
				out.println(rs.getInt(1));
				out.print("</td>");
				out.print("<td>");
				out.println(rs.getString(2));
				out.print("</td>");
				out.print("<td>");
				out.println(rs.getString(3));
				out.print("</td>");
				out.print("</tr>");
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		out.print("</table>");
	}

	

}
