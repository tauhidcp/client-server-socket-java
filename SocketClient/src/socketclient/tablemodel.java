/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import socket.api.entity;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author tauhid
 */
public class tablemodel extends AbstractTableModel{
    
    private List<entity> list = new ArrayList<entity>();

    public tablemodel() {
    }

    /*
    public Person get(int row){
        return list.get(row);
    }

    public void insert(Person person) {
        list.add(person);
        fireTableDataChanged();
    }

    public void update(int row, Person person) {
        list.set(row, person);
        fireTableDataChanged();
    }

    public void delete(int row) {
        list.remove(row);
        fireTableDataChanged();
    }
*/
    public void setData(List<entity> list) {
        this.list = list;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Nama";
            case 2:
                return "HP";
            default:
                return null;
        }
    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getId();
            case 1:
                return list.get(rowIndex).getNama();
            case 2:
                return list.get(rowIndex).getHp();
            default:
                return null;
        }
    }
}
