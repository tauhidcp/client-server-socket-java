/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.api;

import java.util.List;

/**
 *
 * @author tauhid
 */
public interface service {
    
    List<entity> getAllFile();
    
    public void simpan(entity e);
    
    public void update(entity e);
    
    public void delete(entity e);
}
