package com.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.ProduseDAO;
import com.proiectip.entity.Produse;

public class ProduseDAOImpl implements ProduseDAO {

	public List<Produse> produseCategorii(int id_categ,Connection con)throws SQLException
	{
		List<Produse> listaProduse = new ArrayList<Produse>();
		
		PreparedStatement ps = con.prepareStatement("select * from produse where id_categorie="+id_categ);

        ResultSet rs;
        rs = ps.executeQuery();
        while(rs.next()){
        	Produse produs=new Produse();
        	produs.setId_produs(rs.getInt("id_produs"));
            produs.setNume_produs(rs.getString("nume_produs"));
            produs.setStoc(rs.getInt("stoc"));
            produs.setPret(rs.getInt("pret"));
            produs.setId_categorie(rs.getInt("id_categorie"));
            listaProduse.add(produs);
        	}
        return listaProduse;
	}
	
}
