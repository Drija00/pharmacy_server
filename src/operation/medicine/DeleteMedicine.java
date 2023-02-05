/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.medicine;

import controller.Controller;
import domain.Medicine;
import domain.Receipt;
import domain.ReceiptItem;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operation.AbstractGenericOperation;

/**
 *
 * @author Andrija - PC
 */
public class DeleteMedicine extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof Medicine)){
            throw new Exception("Invalid medicine data");
        }{
             Medicine m=(Medicine) param;
             checkValueConstraints(m);
         }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        reposity.delete((Medicine)param);
    }
    
    private void checkValueConstraints(Medicine m) throws Exception {
        boolean exists= checkMedicineIsInReceipt(m);
       if(exists){
            throw new Exception("Patient with that ID has created recipt and can not be deleted. Try again.");
       }
    }

    private boolean checkMedicineIsInReceipt(Medicine m) {
        try {
            List<Receipt> receipts = reposity.getAll(new Medicine());
            for (Receipt receipt : receipts) {
                List<ReceiptItem> items = receipt.getItems();
                for (ReceiptItem item : items) {
                    if(item.getMedicine().getJKL().equals(m.getJKL())){
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DeleteMedicine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
