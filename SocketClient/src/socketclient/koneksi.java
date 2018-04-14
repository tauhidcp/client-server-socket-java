/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author tauhid
 */
public class koneksi {
    
    private static Socket connection;

    public static Socket getConnection() {

            try {
         //   connection = new Socket("192.168.220.1",1112);    
           connection = new Socket("127.0.0.1",1112);    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Koneksi ke Server GAGAL. Aplikasi akan ditutup! \n"+ex,"GAGAL",JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        

        return connection;
    }
    
}
