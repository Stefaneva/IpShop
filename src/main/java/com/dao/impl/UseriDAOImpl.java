package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.dao.UseriDAO;
import com.proiectip.entity.Useri;

public class UseriDAOImpl implements UseriDAO{
	public void saveUser(Useri u, Connection con) throws SQLException {
		PreparedStatement ps=con.prepareStatement("insert into useri(nume_prenume, parola, email, telefon, adresa, tip) values(?,?,?,?,?,?)");
		ps.setString(1,u.getNume_prenume()); 
		ps.setString(2,u.getParola()); 
		ps.setString(3,u.getEmail()); 
		ps.setString(4,u.getTelefon()); 
		ps.setString(5,u.getAdresa()); 
		ps.setString(6,u.getTip()); 
		ps.executeUpdate();
	}

	public Useri findUser(String email, Connection con) throws SQLException {
		PreparedStatement ps=con.prepareStatement("select * from useri where email=?");
		ps.setString(1,email);
		ResultSet rs=ps.executeQuery();
		Useri user = null;
		if(rs.next())
		{
			user = new Useri(rs.getString("nume_prenume"),rs.getString("parola"),
					rs.getString("email"),rs.getString("telefon"),
					rs.getString("adresa"),rs.getString("tip"));
			user.setId_user(rs.getInt("id_user"));
		}
		return user;
	}

	public int logging(Useri user, String userName, String password) {
		if(password.charAt(0)=='-' && password.charAt(1)=='1')
			return 1;
		return 0;
	}


}
