package com.proiect.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.dao.ProduseDAO;
import com.dao.UseriDAO;
import com.dao.impl.ProduseDAOImpl;
import com.dao.impl.UseriDAOImpl;
import com.proiectip.entity.Produse;

/**
 * Servlet implementation class AfisareProduse
 */
public class AfisareProduse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/proiectip")
    private DataSource dbRes;
	private static final ProduseDAO produseDAO = new ProduseDAOImpl();
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfisareProduse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;
		try{
			List<Produse> listaProduse = new ArrayList<Produse>();
			con=dbRes.getConnection();
			
			int id_categ = Integer.parseInt(request.getParameter("idCategorie"));
			listaProduse = produseDAO.produseCategorii(id_categ, con);
	        
	        request.getSession().setAttribute("listaProduse",listaProduse);
	        response.sendRedirect("afisareProduse.jsp");
	        
	        HttpSession session1=request.getSession();
			session1.removeAttribute("negasit");
			session1.removeAttribute("gasit");	       
	      	
		    }
		catch (SQLException e){
			System.out.println(e);
		}
		finally{
			if(con!=null)
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
