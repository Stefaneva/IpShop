package com.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.proiectip.entity.Useri;

public interface UseriDAO {
	 void saveUser(Useri u, Connection con) throws SQLException;
	 Useri findUser(String email,String password, Connection con) throws SQLException;
	 int logging(Useri user, String userName, String password);
}
