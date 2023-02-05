/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.pharmacist;

import domain.Pharmacist;
import operation.AbstractGenericOperation;

/**
 *
 * @author Andrija - PC
 */
public class SetLoggedPharmacist extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof Pharmacist)){
            throw new Exception("Invalid pharmacist data");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        reposity.edit((Pharmacist)param);
    }
    
}
