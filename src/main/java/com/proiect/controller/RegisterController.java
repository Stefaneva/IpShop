package com.proiect.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.dao.UseriDAO;
import com.dao.impl.UseriDAOImpl;
import com.proiectip.entity.Useri;

@WebServlet("/RegisterServlet")
public class RegisterController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/proiectip")
    private DataSource dbRes;
	
	public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;
		try {
			con = dbRes.getConnection();
			Useri u=new Useri(request.getParameter("nume_prenume"),request.getParameter("parola"),request.getParameter("email"),
					request.getParameter("telefon"),request.getParameter("adresa"),request.getParameter("tip"));
			UseriDAO useriDao = new UseriDAOImpl();
			useriDao.saveUser(u, con);
			response.sendRedirect("index.jsp");		
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}
}
