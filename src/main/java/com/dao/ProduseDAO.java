package com.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.proiectip.entity.Produse;

public interface ProduseDAO {

	List<Produse> produseCategorii(int id_categ,Connection con)throws SQLException;
}
