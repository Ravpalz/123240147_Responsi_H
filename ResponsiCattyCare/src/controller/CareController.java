/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import daocat.dao;
import model.Cats;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class CareController implements dao {
    private Connection conn;
    private List<Cats> cats;

    public CareController() {
        try {
            // Pastikan username "root" dan password "" sesuai dengan konfigurasi XAMPP/MySQL Anda
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cattycare", "root", "");
        } catch (SQLException e) {
        }
    }

    @Override
    public void createCats(Cats cats) {
        try {
            // Kolom 'id' diabaikan karena AUTO_INCREMENT di database
            String query = "INSERT INTO penitipan (nama_pemilik, nama_kucing, nomor_telepon, lama_penitipan, biaya) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, cats.getOwner());
            pst.setString(2, cats.getName());
            pst.setInt(3, cats.getNumber());
            pst.setInt(4, cats.getDays());
            pst.setInt(5, cats.getFee());
            pst.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public List<Cats> readCats() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM penitipan");
            while (rs.next()) {
               
                Cats b = new Cats(
                    rs.getString("nama_pemilik"), 
                    rs.getString("nama_kucing"), 
                    rs.getInt("nomor_telepon"),
                    rs.getInt("lama_penitipan"), 
                    rs.getInt("biaya"));
                    cats.add(b);
            }
        } catch (SQLException e) {
        }
        return cats;
    }

    @Override
    public void updateCats(Cats cats) {
        try {
            String query = "UPDATE penitipan SET nama_pemilik=?, nomor_telepon=?, lama_penitipan=?,  biaya=? WHERE nama_kucing=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, cats.getOwner());
            pst.setString(2, cats.getName());
            pst.setInt(3, cats.getNumber());
            pst.setInt(4, cats.getDays());
            pst.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void deleteBook(String name) {
        try {
            String query = "DELETE FROM penitipan WHERE nama_kucing=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteCats(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
