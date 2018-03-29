package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
