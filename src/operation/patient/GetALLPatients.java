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
public class GetALLPatients extends AbstractGenericOperation{

    List<Patient> patients;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        patients = reposity.getAll((Patient)param);
    }

    public List<Patient> getPatients() {
        return patients;
    }
    
}
