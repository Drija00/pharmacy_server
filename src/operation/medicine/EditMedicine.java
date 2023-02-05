/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.medicine;

import domain.Medicine;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Andrija - PC
 */
public class EditMedicine extends AbstractGenericOperation{
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof Medicine)){
            throw new Exception("Invalid medicine data");
        }else{
             Medicine m=(Medicine) param;
             checkValueConstraints(m);
         }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        reposity.edit((Medicine)param);
    }
    
    private boolean checkIfExists(Medicine medicine) throws Exception {
        
        List<Medicine> medicines= reposity.getByQuery(new Medicine(), "");
        
        for(Medicine m: medicines){
            if(m!=null){
                if(m.equals(medicine)){
                    return true;
                }
            }
        } 
        return false;
    }

    private void checkValueConstraints(Medicine m) throws Exception {
        boolean exists= checkIfExists(m);
       if(exists){
            throw new Exception("Medicine with that JKL already exists. Try again.");
       }
    }
}
