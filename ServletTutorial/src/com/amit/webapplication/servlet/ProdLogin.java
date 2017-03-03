package com.amit.webapplication.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProdLogin extends	HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
		res.setContentType("text/html");  
		PrintWriter out = res.getWriter();
		
		String n=req.getParameter("userName");  
		String p=req.getParameter("userPass");
				
		try{  
			 
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","amit");  
			  
			PreparedStatement ps=con.prepareStatement(  
			"select name,pass from registeruser where name=?");  
			ps.setString(1, n);
			
			ResultSet rs=ps.executeQuery();
			if(!rs.next()){
				out.println("User not Found in Database. Please Register First!!");
				out.println("<br>");
				RequestDispatcher rd=req.getRequestDispatcher("/prodindex.html");
				rd.include(req, res);
			}
			else{
				String user=rs.getString(1);
				String pass=rs.getString(2);
				if(!(p.equals(pass))){
					out.println("Wrong password. please enter correct Password !!");
					out.println("<br>");
					RequestDispatcher rd=req.getRequestDispatcher("/prodindex.html");
					rd.include(req, res);
				}
				else
				{
					out.println("you successfully logged in!!");
					out.print("<br>");
		            Cookie ck=new Cookie("name",user);  
		            res.addCookie(ck);
		            req.getRequestDispatcher("/home.html").include(req, res);
				}
			}
			
			}catch (Exception e2) {System.out.println(e2);}
	}
}
