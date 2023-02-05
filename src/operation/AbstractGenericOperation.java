/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation;

import repository.Repository;
import repository.db.DBRepository;
import repository.db.impl.RepositoryDBGeneric;

/**
 *
 * @author Andrija - PC
 */
public abstract class AbstractGenericOperation {
   protected final Repository reposity;

    public AbstractGenericOperation() {
        this.reposity = new RepositoryDBGeneric();
    }
   
    public final void execute(Object param) throws Exception {
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    protected abstract void preconditions(Object param) throws Exception;

    private void startTransaction() throws Exception {
        ((DBRepository)reposity).connect();
    }

    protected abstract void executeOperation(Object param) throws Exception;

    private void commitTransaction() throws Exception {
        ((DBRepository)reposity).commit();
        }

    private void rollbackTransaction() throws Exception {
        ((DBRepository)reposity).rollback();
    }

    private void disconnect() throws Exception {
        ((DBRepository)reposity).disconnect();
    }
   
}
