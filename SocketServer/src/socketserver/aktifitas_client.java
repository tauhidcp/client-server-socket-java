/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

/**
 *
 * @author tauhid
 */

import socket.api.entity;
import socket.api.service;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author tauhid
 */

public class aktifitas_client implements service {

    Connection koneksi;
    

    public List<entity> getAllFile() {


        Statement statement = null;
        List<entity> list = new ArrayList<entity>();
        
        try {
            koneksi = new koneksi().connect();
            statement = koneksi.createStatement();
            
            ResultSet result = statement.executeQuery("SELECT * FROM user");
            while(result.next()){
                entity fs = new entity();
                fs.setId(result.getString("id"));
                fs.setNama(result.getString("nama"));
                fs.setHp(result.getString("hp"));
                list.add(fs);
            }

            result.close();
            
            koneksi.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        } catch (IOException ex) {
            Logger.getLogger(aktifitas_client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;

    }

    public void simpan(entity e){
               
        try{
            koneksi = new koneksi().connect();
            String sql="insert into user(id,nama,hp) values(?,?,?)";
            PreparedStatement stmt=koneksi.prepareStatement(sql);

                stmt.setString(1,e.getId());
                stmt.setString(2,e.getNama());
                stmt.setString(3,e.getHp());

                stmt.executeUpdate();
                
            stmt.close();
            koneksi.close();
        }catch(Exception err){
            JOptionPane.showMessageDialog(null,"User GAGAL disimpan!","Kesalahan",JOptionPane.ERROR_MESSAGE);
        }
    }    

    public void update(entity e){
        
        
        try{
            koneksi = new koneksi().connect();
            String sql="update user set nama=?,hp=? where id=?";
            PreparedStatement stmt=koneksi.prepareStatement(sql);

                stmt.setString(1,e.getNama());
                stmt.setString(2,e.getHp());
                stmt.setString(3,e.getId());

                stmt.executeUpdate();
                
            stmt.close();
            koneksi.close();
        }catch(Exception err){
            JOptionPane.showMessageDialog(null,"User GAGAL diupdate!","Kesalahan",JOptionPane.ERROR_MESSAGE);
        }
    }        
    
    public void delete(entity e){
        
        
        try{
            koneksi = new koneksi().connect();
            String sql="delete from user where id=?";
            PreparedStatement stmt=koneksi.prepareStatement(sql);

                stmt.setString(1,e.getId());

                stmt.executeUpdate();
                
            stmt.close();
            koneksi.close();
        }catch(Exception err){
            JOptionPane.showMessageDialog(null,"User GAGAL dihapus!","Kesalahan",JOptionPane.ERROR_MESSAGE);
        }
    }    
}

