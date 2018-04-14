/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.JDBC;
import javax.swing.JOptionPane;

/**
 *
 * @author tauhid
 */

public class koneksi {
    
  private static Connection koneksi;
    
  public static Connection connect() throws IOException {
      
        try {
            String input = "db/socketdb.db";
            String url = "jdbc:sqlite:"+input;			
             try {
                Class.forName("org.sqlite.JDBC");
                koneksi = DriverManager.getConnection(url);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null,"Driver Tidak ditemukan","GAGAL",JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Koneksi ke Database GAGAL","GAGAL",JOptionPane.WARNING_MESSAGE);
        } 
        
        return koneksi;
    }
}

