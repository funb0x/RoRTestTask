package com.tymchak.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tymchak.DAO.UserDAO;
import com.tymchak.entity.User;

@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UserDAO userDAO;

	public Test() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD>");
		out.println("<TITLE>ROR TEST</TITLE>");
		out.println("</HEAD><BODY>");
		out.println("HELLO FROM ROR TEST");

		// roleDAO.create(new Role("CLIENT"));

	
		//userDAO.create(new User("barak", "111", "USER"));

		List<User> userList = userDAO.getAll();
		for (User user : userList) {
			out.println(user);
			out.println("<br>");
		}

		out.println("HELLO FROM ROR TEST");
		out.println("</BODY></HTML>");
		out.close();
	}

}
