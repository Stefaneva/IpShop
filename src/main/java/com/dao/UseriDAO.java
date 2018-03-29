package com.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.proiectip.entity.Useri;

public interface UseriDAO {
	 void saveUser(Useri u, Connection con) throws SQLException;
}
