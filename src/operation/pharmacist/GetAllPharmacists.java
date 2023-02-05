/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.pharmacist;

import domain.Pharmacist;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Andrija - PC
 */
public class GetAllPharmacists extends AbstractGenericOperation{
    List<Pharmacist> pharmacists;
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        pharmacists = reposity.getAll((Pharmacist)param);
    }

    public List<Pharmacist> getPharmacists() {
        return pharmacists;
    }
    
    
}
