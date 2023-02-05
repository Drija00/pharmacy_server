/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.patient;

import domain.Patient;
import domain.Receipt;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operation.AbstractGenericOperation;

/**
 *
 * @author Andrija - PC
 */
public class DeletePatient extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof Patient)){
            throw new Exception("Invalid patient data");
        }else{
             Patient p=(Patient) param;
             checkValueConstraints(p);
         }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        reposity.delete((Patient)param);
    }
    
    public boolean checkPatientHasReceipt(Patient p){
        try {
            List<Receipt> receipts = reposity.getAll(new Receipt());
            for (Receipt receipt : receipts) {
                if(receipt.getPatient().getId().equals(p.getId())){
                    return true;
                }
            }
        } catch (Exception ex) {
            
        }
        return false;
    }
    
    private void checkValueConstraints(Patient p) throws Exception {
        boolean exists= checkPatientHasReceipt(p);
       if(exists){
            throw new Exception("Patient with that ID has created recipt and can not be deleted. Try again.");
       }
    }
    
}
