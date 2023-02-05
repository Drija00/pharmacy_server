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
public class GetAllMedicines extends AbstractGenericOperation{
    List<Medicine> meds;
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        meds = reposity.getAll((Medicine)param);
    }

    public List<Medicine> getMeds() {
        return meds;
    }
    
}
