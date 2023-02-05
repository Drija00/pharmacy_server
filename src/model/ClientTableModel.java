/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Pharmacist;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author LENOVO
 */
public class ClientTableModel extends AbstractTableModel{

    String[] columnNames = new String[]{"username","firstname","lastname"};
    List<Pharmacist> pharmacists;

    public ClientTableModel() {
        pharmacists=new ArrayList<>();
    }
    
    public ClientTableModel(List<Pharmacist> pharmacists) {
        this.pharmacists = pharmacists;
    }

    @Override
    public String getColumnName(int column) {
    if(column>columnNames.length) return "n/a";
    return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    
    
    @Override
    public int getRowCount() {
        return pharmacists.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
   
        Pharmacist p = pharmacists.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getUsername();
            case 1: return p.getName();
            case 2: return p.getLastname();
            default:return "n/a";
        }
    }

    public void addPharmacist(Pharmacist u){
        if(pharmacists.contains(u)) return;
        pharmacists.add(u);
        fireTableDataChanged();
    }
    
}
