package com.student.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.Driver;

public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String browserUsername = req.getParameter("regno");
	    String PAssword = req.getParameter("pass");
		
	    String htmlResponse = null;
					    
					    Connection con = null;	
						try {
						// step 1 : load/register mysql jdbc driver 
						Driver driverref = new Driver();
						DriverManager.registerDriver(driverref);
						
						// step 2 : connect to database 		
						 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "root");
						
						// step 3 : issue database query
						Statement stat = con.createStatement();
						String query = "select * from students_info";
						
						// step 4 : execute query
						  ResultSet result = stat.executeQuery(query);		
						             //process & verify the result 
						               while (result.next()) {
						            	    String dbusername = result.getString(2);
						            	    if(browserUsername.equals(dbusername)) {
						            	   	 htmlResponse = "<html>"
											    		+ "<title>Home</title>"
											    		+ "<body>"
											    		+ "	<h1>"
											    		+ "           <font color= \"red\">welcome to Home Page......! "+browserUsername+"</font>"
											    		+ "       </h1>"
											    		+ "   <a href = 'http://localhost:8080/StudentApp/Login.html'>Login</a>"
											    		+ " </br>"
											    		+ "</body>"
											    		+ "</html>";
						            	   	  break;
						            	    	
						            	    }else {
						            	    	 htmlResponse = "<html>"
												    		+ "<title>Home</title>"
												    		+ "<body>"
												    		+ "	<h1>"
												    		+ "           <font color= \"red\">user is not exist </font>"
												    		+ "Please try again..!"
												    		+ "       </h1>"
												    		+ " </br>"
												    		+ "   <a href = 'http://localhost:8080/StudentApp/Login.html'>Login</a>"
												    		+ "</body>"
												    		+ "</html>";
						            	    }
						            	    
									}
						}catch (Exception e) {
							System.out.println("handle the problems");
						}finally {			
						// step 5 : close the connection
						  try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						  System.out.println("close done");
					   }
	    
	    
	    
	    
	    
	
	    
    	resp.setContentType("text/html");
    	resp.getWriter().print(htmlResponse);
	}

}
