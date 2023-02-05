/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.SaleStatistics;

import domain.SaleStatistics;
import operation.AbstractGenericOperation;

/**
 *
 * @author Andrija - PC
 */
public class EditSaleStatistics extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof SaleStatistics)){
            throw new Exception("Invalid sale statistics data");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        reposity.edit((SaleStatistics)param);
    }
    
}
