package com.proiect.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.dao.UseriDAO;
import com.dao.impl.UseriDAOImpl;
import com.proiectip.entity.Cos;
import com.proiectip.entity.Useri;

@WebServlet("/LoginCheck")
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/proiectip")
    private DataSource dbRes;
	private static final UseriDAO useriDAO = new UseriDAOImpl();

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		try {
			con = dbRes.getConnection();
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			Useri user = null;
			user = useriDAO.findUser(username, password, con);
			if (user == null) {
				String mesaj = "Username/parola incorecte";
				HttpSession session = request.getSession(true);
				session.setAttribute("eroare", mesaj);
				response.sendRedirect("login.jsp");
			}
			if (useriDAO.logging(user, username, password) == 1) {
				String mesaj = "Contul este blocat.";
				HttpSession session = request.getSession(true);
				session.setAttribute("eroare", mesaj);
				response.sendRedirect("login.jsp");
			} if(user != null){
				HttpSession session = request.getSession(true);
				session.setAttribute("iduser", user.getId_user());
				session.setAttribute("userCurent", user);
				session.setAttribute("welcome", user.getNume_prenume());
				session.setAttribute("tipUser", user.getTip());
				session.setAttribute("listaCos", new ArrayList<Cos>());
				response.sendRedirect("index.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
