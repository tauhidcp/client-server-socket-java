/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import socket.api.entity;
import socket.api.service;

/**
 *
 * @author tauhid
 */
public class controller_client {
    
    static controller cnt;
    static JFUtama utama;
    static List<entity> list;
    static service sv;
    
    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    static ObjectOutputStream objectOutput;
    
    static ObjectInputStream objectInput;
    static Object object;
    
    public controller_client(JFUtama utama){
        this.utama = utama;
         sv = new aktifitas_client();
         cnt = new controller(utama);
    }
    
    public static List<entity>ambilData(){
        list = sv.getAllFile();
        return list;
    }
    
    private static String getTanggal() { 
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
        Date date = new Date(); 
        return dateFormat.format(date); 
    } 
    
    // Membaca Aktifitas Client
    public static void serviceServer(){  
        try {
                ss = new ServerSocket(1112);
                while (true) {
                    s = ss.accept(); 
                    din = new DataInputStream(s.getInputStream());
                    dout = new DataOutputStream(s.getOutputStream());
                    // Cek Aktifitas Client
                    switch(din.readUTF()){
                        // input data
                        case "input" :
                            try {    
                                objectInput = new ObjectInputStream(s.getInputStream());  
                                object = objectInput.readObject();
                                entity e = (entity) object;
                                entity en = new entity();
                                en.setHp(e.getHp());
                                en.setNama(e.getNama());
                                en.setId(e.getId());
                                sv.simpan(en);
                                utama.getTA().setText(utama.getTA().getText().trim()+"\n["+getTanggal()+"] Client melakukan proses input "+s);
                                cnt.isiTabel();
                            } catch(Exception e){
                                e.printStackTrace();
                            }
                        break;    
                        // update data
                        case "update" :
                            try {    
                                objectInput = new ObjectInputStream(s.getInputStream());  
                                object = objectInput.readObject();
                                entity e = (entity) object;
                                entity en = new entity();
                                en.setHp(e.getHp());
                                en.setNama(e.getNama());
                                en.setId(e.getId());
                                sv.update(en);
                                utama.getTA().setText(utama.getTA().getText().trim()+"\n["+getTanggal()+"] Client melakukan proses update "+s);
                                cnt.isiTabel();
                            } catch(Exception e){
                                e.printStackTrace();
                            } 
                        break;    
                        // delete data
                        case "delete" :
                            try {    
                                objectInput = new ObjectInputStream(s.getInputStream());  
                                object = objectInput.readObject();
                                entity e = (entity) object;
                                entity en = new entity();
                                en.setId(e.getId());
                                sv.delete(en);
                                utama.getTA().setText(utama.getTA().getText().trim()+"\n["+getTanggal()+"] Client melakukan proses delete "+s);
                                cnt.isiTabel();
                            } catch(Exception e){
                                e.printStackTrace();
                            } 
                        break;    
                        // ambil data
                        case "ambil" :
                            try { 
                                objectOutput = new ObjectOutputStream(s.getOutputStream());
                                objectOutput.writeObject(ambilData());   
                                utama.getTA().setText(utama.getTA().getText().trim()+"\n["+getTanggal()+"] Client melakukan proses pengambilan data "+s);
                            } catch (IOException e){
                                e.printStackTrace();
                            } 
                        break;    
                    }
                }    
            } catch (IOException e){
                e.printStackTrace();
            }    
    }

}
