package com.amit.webapplication.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProdLogout extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
	
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		Cookie ck=new Cookie("name","");  
        ck.setMaxAge(0);  
        res.addCookie(ck);
        out.print("you are successfully logged out!");  
        out.print("<br>");
        req.getRequestDispatcher("/prodindex.html").include(req,res);
	}
}
