/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import controller.Controller;
import domain.AbstractDO;
import domain.ReceiptItem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import repository.db.DBConnectionFactory;
import repository.db.DBRepository;

/**
 *
 * @author Andrija - PC
 */
public class RepositoryDBGeneric implements DBRepository<AbstractDO>{

    @Override
    public List<AbstractDO> getAll(AbstractDO param) throws Exception {
        List<AbstractDO> abstractDOs= new ArrayList<>();
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement statement = connection.createStatement();
        String query = param.getStatementSelectAllQuery();
        ResultSet rs =statement.executeQuery(query);
        while(rs.next()){
                abstractDOs.add(param.getEntityFromResultSet(rs));
            }
        return abstractDOs;
    }

    @Override
    public void add(AbstractDO param) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(param.getClassName())
                    .append(" (")
                    .append(param.getAttributeList())
                    .append(")")
                    .append(" VALUES (")
                    .append(param.getAttributeValues())
                    .append(")");
            String query = sb.toString();
            //System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                param.setId(id);
            }
            statement.close();
            rsKey.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void edit(AbstractDO param) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(param.getClassName())
                    .append(" SET ")
                    .append(param.setAttributeValues())
                    .append(" WHERE ")
                    .append(param.getQueryCondition());
            String query = sb.toString();
            //System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
                
            
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void delete(AbstractDO param) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ")
                    .append(param.getClassName())
                    .append(" WHERE ")
                    .append(param.getQueryCondition());
            String query = sb.toString();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<AbstractDO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addComplex(AbstractDO t) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(t.getClassName())
                    .append(" (").append(t.getAttributeList()).append(")")
                    .append(" VALUES (")
                    .append(t.getAttributeValues())
                    .append(")");
            String query = sb.toString();
            //System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                t.setId(id);
                
                for(int j=0; j<t.getNumberOfBountObject(); j++){
                AbstractDO abstractDO;
                for(int i=0; i<t.getNumberOfAttributesBoundObjects(); i++){
                    abstractDO=(AbstractDO) t.getBoundObject(j,i);
                    //System.out.println(abstractDO.toString());
                    abstractDO.setForeignId(id);
                    StringBuilder sbBoundObject = new StringBuilder();
                    sbBoundObject.append("INSERT INTO ")
                            .append(abstractDO.getClassName())
                            .append(" (").append(abstractDO.getAttributeList()).append(")")
                            .append(" VALUES (")
                            .append(abstractDO.getAttributeValues())
                            .append(")");
                    String queryBound = sbBoundObject.toString();
                    //System.out.println(queryBound);
                    statement.executeUpdate(queryBound);
                }
                
                 }
            }

            
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void editComplex(AbstractDO t) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(t.getClassName())
                    .append(" SET ")
                    .append(t.setAttributeValues())
                    .append(" WHERE ")
                    .append(t.getQueryCondition());
            String query = sb.toString();
            //System.out.println("AAAAAAAAAAAAAAAAAA"+query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
                
                for(int j=0; j<t.getNumberOfBountObject(); j++){
                AbstractDO abstractDO;
                for(int i=0; i<t.getNumberOfAttributesBoundObjects(); i++){
                    abstractDO=(AbstractDO) t.getBoundObject(j,i);
                    //System.out.println("1 U PETLJIII"+abstractDO.getAction());
                    switch (abstractDO.getAction()) {
                        case ADD:
                            abstractDO.setForeignId(t.getId());
                            StringBuilder sbBoundObject = new StringBuilder();
                            sbBoundObject.append("INSERT INTO ")
                            .append(abstractDO.getClassName())
                            .append(" (").append(abstractDO.getAttributeList()).append(")")
                            .append(" VALUES (")
                            .append(abstractDO.getAttributeValues())
                            .append(")");
                            String queryBound = sbBoundObject.toString();
                           // System.out.println("2 U PETLJIII"+queryBound);
                            statement.executeUpdate(queryBound);
                            break;
                        case DELETE:
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("DELETE FROM ")
                           .append(abstractDO.getClassName())
                           .append(" WHERE ")
                           .append(abstractDO.getQueryCondition());
                            String query2 = sb2.toString();
                           // System.out.println(query2);
                            statement = connection.createStatement();
                            statement.executeUpdate(query2);
                            break;
                        case UPDATE:
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("UPDATE ")
                            .append(abstractDO.getClassName())
                            .append(" SET ")
                            .append(abstractDO.setAttributeValues())
                            .append(" WHERE ")
                            .append(abstractDO.getQueryCondition());
                            String query3 = sb3.toString();
                           // System.out.println(query3);
                            statement = connection.createStatement();
                            statement.executeUpdate(query3);
                            break;
                        default:
                            throw new AssertionError();
                        }
                    
                    }
                
                 }

            
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<AbstractDO> getByQuery(AbstractDO t, String query) throws Exception {
        List<AbstractDO> abstractDOs= new ArrayList<>();
            Connection connection= DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            //System.out.println(t.getStatementSelectAllQuery()+query);
            ResultSet resultSet = statement.executeQuery(t.getStatementSelectAllQuery()+query);
            while(resultSet.next()){
                abstractDOs.add(t.getEntityFromResultSet(resultSet));
            }
        
            return abstractDOs;
    }

    @Override
    public AbstractDO get(AbstractDO t, String string) throws Exception {
        AbstractDO abstractDO=null;
            Connection connection= DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(t.getStatementSelectAllQuery()+string);
            while(resultSet.next()){
                abstractDO=t.getEntityFromResultSet(resultSet);
            }
        
            return abstractDO;
    }

    @Override
    public void addBoundObjects(ReceiptItem receipt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteComplex(AbstractDO param) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ")
                    .append(param.getBoundType())
                    .append(" WHERE ")
                    .append(param.getBoundQueryCondition());
            String query = sb.toString();
            //System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            sb = new StringBuilder();
            sb.append("DELETE FROM ")
                    .append(param.getClassName())
                    .append(" WHERE ")
                    .append(param.getQueryCondition());
            query = sb.toString();
            //System.out.println(query);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
}
