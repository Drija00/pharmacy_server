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
public class GetAllPatientsByQuery extends AbstractGenericOperation{

    List<Patient> pats;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        try{
        String query= (String) param;
        pats = reposity.getByQuery(new Patient(),query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while loading the patients", e);
        }
    }

    public List<Patient> getPats() {
        return pats;
    }
    
}
