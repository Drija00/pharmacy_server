/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import domain.ReceiptItem;
import java.util.List;

/**
 *
 * @author Andrija - PC
 */
public interface Repository<T> {
    List<T> getAll(T param) throws Exception;
    void add(T param) throws Exception;
    void edit(T param) throws Exception;
    void addComplex(T t) throws Exception;
    void editComplex(T t) throws Exception;
    void delete(T param)throws Exception;
    void deleteComplex(T param)throws Exception;
    List<T> getAll();
    List<T> getByQuery(T t,String query) throws Exception;
    
    T get(T t, String string) throws Exception;

    public void addBoundObjects(ReceiptItem receipt);
}
