/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.patient;

import domain.Patient;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Andrija - PC
 */
public class EditPatient extends AbstractGenericOperation{

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
        reposity.edit((Patient)param);
    }
    
    private boolean checkIfExists(Patient patient) throws Exception {
        
        List<Patient> patients= reposity.getByQuery(new Patient(), "");
        
        for(Patient p: patients){
            if(p!=null){
                if(p.equalsWithoutID(patient)){
                    return true;
                }
            }
        } 
        return false;
    }

    private void checkValueConstraints(Patient p) throws Exception {
        boolean exists= checkIfExists(p);
       if(exists){
            throw new Exception("Patient with that ID already exists. Try again.");
       }
    }
}
