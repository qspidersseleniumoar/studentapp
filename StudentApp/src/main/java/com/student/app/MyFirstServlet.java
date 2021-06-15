package com.student.app;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFirstServlet extends HttpServlet{


	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String currentDate = new Date().toString();
    	
    	String htmlResponse = "<html>"
    			+ "   <title>mypage</title>"
    			+ "   <body>"
    			+ "       <h1>Today data & time is :"
    			+ "          <font color='red'>"+currentDate+"</font>"
    			+ "        </h1>"
    			+ "   </body>"
    			+ "</html>";
    	resp.setHeader("refresh", "1");
    	resp.setContentType("text/html");
    	resp.getWriter().print(htmlResponse);
    }
}
