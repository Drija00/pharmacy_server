/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.receipt;

import domain.Receipt;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Andrija - PC
 */
public class GetAllReceipt extends AbstractGenericOperation{
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Receipt> r = reposity.getAll((Receipt)param);
    }
}
