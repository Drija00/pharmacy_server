/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.SaleStatistics;

import domain.SaleStatistics;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Andrija - PC
 */
public class GetAllSaleStatistics extends AbstractGenericOperation{

    List<SaleStatistics> stats;
    
   @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        stats = reposity.getAll((SaleStatistics)param);
    }

    public List<SaleStatistics> getStats() {
        return stats;
    }
    
}
