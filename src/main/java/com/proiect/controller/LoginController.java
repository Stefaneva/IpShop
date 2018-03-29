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

import com.proiectip.entity.Cos;
import com.proiectip.entity.Useri;

@WebServlet("/LoginCheck")
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/bd")
    private DataSource dbRes;
	 public LoginController() {
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
			try{
				con=dbRes.getConnection();
				String username=request.getParameter("username");
				String password=request.getParameter("password");
											
				PreparedStatement ps1=con.prepareStatement("select * from useri where email=?");  
				ps1.setString(1,username);  
				ResultSet rs1=ps1.executeQuery(); 
				
				if(rs1.next())
				{
					String parola=rs1.getString("parola");
					if(parola.charAt(0)=='-' && parola.charAt(1)=='1')
					{
						String mesaj="Contul este blocat.";
						
						HttpSession session=request.getSession(true);
						session.setAttribute("eroare", mesaj);
						response.sendRedirect("login.jsp");	
					}
					else 
					{
						PreparedStatement ps=con.prepareStatement("select * from useri where email=? and parola=?"); 
						ps.setString(1,username);
						ps.setString(2,password);  
						
						ResultSet rs=ps.executeQuery();
						if( rs.next())
						{
							String nume=rs.getString("nume_prenume");
							String tip=rs.getString("tip");
							int id= rs.getInt("id_user");
							
							Useri user=new Useri();
				        	user.setId_user(rs.getInt("id_user"));
				        	user.setNume_prenume(rs.getString("nume_prenume"));
				        	user.setParola(rs.getString("parola"));
				        	user.setEmail(rs.getString("email"));
				        	user.setTelefon(rs.getString("telefon"));
				        	user.setAdresa(rs.getString("adresa"));
				        	user.setTip(rs.getString("tip"));
				        	
							HttpSession session=request.getSession(true);
							session.setAttribute("iduser", id);
							session.setAttribute("userCurent", user);
							session.setAttribute("welcome", nume);
							session.setAttribute("tipUser", tip);
							session.setAttribute("listaCos", new ArrayList<Cos>());
			
							response.sendRedirect("index.jsp");		
						}
						else
						{
							String mesaj="Parola incorecta";
							
							HttpSession session=request.getSession(true);
							session.setAttribute("eroare", mesaj);
							response.sendRedirect("login.jsp");	
						}
					}	
				}
				else
				{
					String mesaj="User inexistent";
					
					HttpSession session=request.getSession(true);
					session.setAttribute("eroare", mesaj);
					response.sendRedirect("login.jsp");	
				}
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
}
