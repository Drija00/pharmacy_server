/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.receipt;

import domain.Receipt;
import domain.ReceiptItem;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Andrija - PC
 */
public class GetReceiptsByQuery extends AbstractGenericOperation{

    private List<Receipt> receipts;
    
    @Override
    protected void preconditions(Object param) throws Exception {
               if(param==null || !(param instanceof String))
           throw new Exception("Object is the wrong type!");

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        String query= (String) param;
        try {
            
            receipts= reposity.getByQuery(new Receipt(), query);
            for(Receipt r: receipts){
                List<ReceiptItem> items= reposity.getByQuery(new ReceiptItem(), " WHERE id_receipt="+r.getId());
                r.setItems(items);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while loading the members", e);
        }
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }
    
}
