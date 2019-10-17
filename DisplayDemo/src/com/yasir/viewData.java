package com.yasir;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewData")
public class viewData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public viewData() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "yasirhaddad",
					"Yasir@1988");
			String sql = "select client_id, client_name, address_id from client ";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			String str = "<table border=1><tr><th>client_id</th><th>client_name</th><th>address_id</th><th><a href=AddToCart>Add_To_Cart</a></th></tr>";
			while(rs.next())
			{
				str +="<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td></tr>";
				
		}
			str += "</table>";
			out.println(str);
			con.close();
		}catch (Exception e) {

			System.err.println(e);
		}
		finally {
			out.close();
	}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
