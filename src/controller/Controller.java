/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import domain.Medicine;
import domain.Patient;
import domain.Pharmacist;
import domain.Receipt;
import domain.ReceiptItem;
import domain.SaleStatistics;
import domain.Substance;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operation.AbstractGenericOperation;
import operation.SaleStatistics.AddSaleStatistics;
import operation.SaleStatistics.EditSaleStatistics;
import operation.SaleStatistics.GetAllSaleStatistics;
import operation.medicine.AddMedicine;
import operation.medicine.DeleteMedicine;
import operation.medicine.EditMedicine;
import operation.medicine.GetAllMedicines;
import operation.medicine.GetAllMedicinesByQuery;
import operation.patient.AddPatient;
import operation.patient.DeletePatient;
import operation.patient.EditPatient;
import operation.patient.GetALLPatients;
import operation.patient.GetAllPatientsByQuery;
import operation.pharmacist.GetAllPharmacists;
import operation.pharmacist.SetLoggedPharmacist;
import operation.receipt.AddReceipt;
import operation.receipt.DeleteReceipt;
import operation.receipt.EditReceipt;
import operation.receipt.GetReceiptsByQuery;
import repository.Repository;
import repository.db.DBRepository;


/**
 *
 * @author Andrija - PC
 */
public class Controller {

    private static Controller controller;

    private Controller() {
        
    }
    
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    
    public Pharmacist login(String username,String password) throws Exception{
        AbstractGenericOperation operation = new GetAllPharmacists();
        operation.execute(new Pharmacist());
        List<Pharmacist> pharmacists = ((GetAllPharmacists)operation).getPharmacists();
        for(Pharmacist p: pharmacists){
            System.out.println(p.isLogged());
            if(p.getUsername().equals(username) && p.getPassword().equals(password)){
                if(p.isLogged()){
                    throw new Exception("User already logged in!");
                }else{
                    p.setLogged(true);
                    editPharmacist(p);
                    return p;
                }
            }
        }
        throw new Exception("Unknown user!");
    }
    
    public void editPharmacist(Pharmacist p) throws Exception{
        AbstractGenericOperation operation = new SetLoggedPharmacist();
        operation.execute(p);
    }
    
    public List<Patient> getAllPatients() throws Exception{
        AbstractGenericOperation operation = new GetALLPatients();
        operation.execute(new Patient());
        return ((GetALLPatients)operation).getPatients();
    }
    
    public List<Pharmacist> getPharmacists() throws Exception{
        AbstractGenericOperation operation = new GetAllPharmacists();
        operation.execute(new Pharmacist());
        return ((GetAllPharmacists)operation).getPharmacists();
    }
    
    
    public List<Receipt> getReceiptsByQuery(String query) throws Exception{
    AbstractGenericOperation operation= new GetReceiptsByQuery();
       operation.execute(query);
       List<Receipt> receipts= ((GetReceiptsByQuery)operation).getReceipts();
       return receipts;
    }
    
    public List<Medicine> getAllMedicines() throws Exception{
        AbstractGenericOperation operation = new GetAllMedicines();
        operation.execute(new Medicine());
        return ((GetAllMedicines)operation).getMeds();
    }
    public List<Medicine> getAllMedicinesByQuery(String query) throws Exception{
        AbstractGenericOperation operation = new GetAllMedicinesByQuery();
        operation.execute(query);
        return ((GetAllMedicinesByQuery)operation).getMeds();
    }
    
    public List<Patient> getAllPatientsByQuery(String query) throws Exception{
        AbstractGenericOperation operation = new GetAllPatientsByQuery();
        operation.execute(query);
        return ((GetAllPatientsByQuery)operation).getPats();
    }
    
    public List<SaleStatistics> getAllSaleStatistics() throws Exception{
        AbstractGenericOperation operation = new GetAllSaleStatistics();
        operation.execute(new SaleStatistics());
        return ((GetAllSaleStatistics)operation).getStats();
    }
   
    
    public void addPatient(Patient patient) throws Exception{
        AbstractGenericOperation operation = new AddPatient();
        operation.execute(patient);
    }
    public void editPatient(Patient patient) throws Exception{
        AbstractGenericOperation operation = new EditPatient();
        operation.execute(patient);
    }
    public void editSaleStatistics(SaleStatistics statistic) throws Exception{
        AbstractGenericOperation operation = new EditSaleStatistics();
        operation.execute(statistic);
    }
    
    public void addSaleStatistics(SaleStatistics statistic) throws Exception{
        AbstractGenericOperation operation = new AddSaleStatistics();
        operation.execute(statistic);
    }
    
    public void deletePatient(Patient patient) throws Exception{
        AbstractGenericOperation operation = new DeletePatient();
        operation.execute(patient);
    }
    public void addMedicine(Medicine medicine) throws Exception{
        AbstractGenericOperation operation = new AddMedicine();
        operation.execute(medicine);
    }
    public void editMedicine(Medicine medicine) throws Exception{
        AbstractGenericOperation operation = new EditMedicine();
        operation.execute(medicine);
    }
    public void deleteMedicine(Medicine medicine) throws Exception{
       AbstractGenericOperation operation = new DeleteMedicine();
        operation.execute(medicine);
    }
    public void addReceipt(Receipt receipt) throws Exception{
        AbstractGenericOperation operation = new AddReceipt();
        operation.execute(receipt);
    }
    
    public void editReceipt(Receipt receipt) throws Exception{
        AbstractGenericOperation operation = new EditReceipt();
        operation.execute(receipt);
    }
    
    public void removeReceipt(Receipt receipt) throws Exception{
        AbstractGenericOperation operation = new DeleteReceipt();
        operation.execute(receipt);
    }
    
    
}
