/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

import java.util.List;


/**
 *
 * @author tauhid
 */
public class controller {
    
    JFUtama utama;
    dao_impl impl;
    List<model> lis;

    
    public controller(JFUtama utama){
        this.utama = utama;
        impl = new dao();
    }
    
    public void isiTabel(){
        lis = impl.getAll();
        tablemodel tm = new tablemodel(lis);
        utama.getDataTable().setModel(tm);
    } 
    
}
