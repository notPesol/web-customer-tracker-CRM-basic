package com.pesol.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// set up connection variable
		String user = "springstudent";
		String pass = "springstudent";
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=FALSE&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
			
		
		Connection conn = null;
		
		// get connection to database
		try {
			PrintWriter writer = response.getWriter();
			
			writer.println("Connecting to database: " + jdbcUrl);
			
			Class.forName(driver);
						
			conn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			writer.println("SUCCESS!!!");
			
			conn.close();
			
			// play...
//			Map<String, String[]> parameters = request.getParameterMap();
//			
//			for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
//				String key = entry.getKey();
//				String[] val = entry.getValue();
//				System.out.println(key + ":");
//				for (String string : val) {
//					System.out.println("\t: " + string);
//				}
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
