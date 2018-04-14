/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tauhid
 */
public class dao implements dao_impl{
    
        Connection koneksi;
    
    @Override
    public List<model>getAll(){
        
        List<model> lis = null;
        
        try{
            
          String sql="select * from user order by id asc";
          koneksi = new koneksi().connect();
          lis = new ArrayList<model>();
          
          Statement stmt=koneksi.createStatement();
          ResultSet rslt=stmt.executeQuery(sql);
          
               while(rslt.next()){
                   model fi = new model();
                   fi.setId(rslt.getString("id"));
                   fi.setNama(rslt.getString("nama"));
                   fi.setHp(rslt.getString("hp"));
                   lis.add(fi);   
               }
          
          koneksi.close();
          
        }catch(Exception err){
            err.printStackTrace();
        }
         
        return lis;
    }
}
