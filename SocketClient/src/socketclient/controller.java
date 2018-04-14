/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import socket.api.entity;

/**
 *
 * @author tauhid
 */
public class controller {
    
    JFUtama utama;
    private Socket con;
    ObjectInputStream objectInput;
    Object object;
    List<entity> list;
    static DataInputStream din;
    static DataOutputStream dout;
    static ObjectOutputStream objectOutput;
    
    public controller(JFUtama utama){
        this.utama = utama;
    }
    
    
    public void ambilData(JFUtama utama){
    
        con = new koneksi().getConnection();
        
        try {
            
            dout = new DataOutputStream(con.getOutputStream());
            dout.writeUTF("ambil");
            
            try {
                objectInput = new ObjectInputStream(con.getInputStream());
                object = objectInput.readObject();
                list = (ArrayList<entity>) object;
                tablemodel tableModel = new tablemodel();
                tableModel.setData(list);
                utama.getDataTable().setModel(tableModel);
                utama.getDataTable().setPreferredScrollableViewportSize(utama.getDataTable().getPreferredSize());
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }     
        con.close();    
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

    
    public void simpanData(JFUtama utama){
    
        con = new koneksi().getConnection();
        
        try {
            dout = new DataOutputStream(con.getOutputStream());
            dout.writeUTF("input");
            try {
                objectOutput = new ObjectOutputStream(con.getOutputStream());
                entity en = new entity();
                en.setHp(utama.getHP().getText().trim());
                en.setNama(utama.getNama().getText().trim());
                en.setId(utama.getID().getText().trim());
                objectOutput.writeObject(en);
                 JOptionPane.showMessageDialog(null, "Simpan Data Berhasil","Sukses",JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }           
        con.close();    
        } catch (IOException e) {
            e.printStackTrace();
        }   
        
    }

    public void UpdateData(JFUtama utama){
    
        con = new koneksi().getConnection();
        
        try {
            dout = new DataOutputStream(con.getOutputStream());
            dout.writeUTF("update");
            try {
                objectOutput = new ObjectOutputStream(con.getOutputStream());
                entity en = new entity();
                en.setHp(utama.getHP().getText().trim());
                en.setNama(utama.getNama().getText().trim());
                en.setId(utama.getID().getText().trim());
                objectOutput.writeObject(en);
                 JOptionPane.showMessageDialog(null, "Update Data Berhasil","Sukses",JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }          
        con.close();    
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }    
    
    public void deleteData(JFUtama utama){
    
        con = new koneksi().getConnection();
        
        try {
            dout = new DataOutputStream(con.getOutputStream());
            dout.writeUTF("delete");
            try {
                objectOutput = new ObjectOutputStream(con.getOutputStream());
                entity en = new entity();
                en.setId(utama.getID().getText().trim());
                objectOutput.writeObject(en);
                JOptionPane.showMessageDialog(null, "Delete Data Berhasil","Sukses",JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }  
         con.close();   
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }    
}
