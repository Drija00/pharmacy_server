/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.receipt;

import domain.Receipt;
import operation.AbstractGenericOperation;

/**
 *
 * @author Andrija - PC
 */
public class EditReceipt extends AbstractGenericOperation{
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof Receipt)){
            throw new Exception("Invalid receipt data");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        reposity.editComplex((Receipt)param);
    }
}
