/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.Medicine;
import domain.Patient;
import domain.Pharmacist;
import domain.Receipt;
import domain.ReceiptItem;
import domain.SaleStatistics;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.db.DBRepository;
import server.Server;

/**
 *
 * @author Andrija - PC
 */
public class ProcessClientRequest extends Thread{
    private Sender sender;
    private Receiver receiver;
    private Socket socket;
    private Patient patient;
    private Medicine medicine;
    private Pharmacist pharmacist;
    private Receipt receipt;
    private Server server;


    public ProcessClientRequest(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        this.sender = new Sender(socket);
        this.receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        try{
        handleClientRequests(socket);
        }catch (Exception ex){
            try {
                pharmacist.setLogged(false);
                Controller.getInstance().editPharmacist(pharmacist);
                server.logout(this);
                System.out.println("Client aborted the program.");
            } catch (Exception ex1) {
                ex.getMessage();
            }
        }
    }

    private void handleClientRequests(Socket socket) throws Exception {
        try {
            while(true){
            Response response = new Response();
            try {
                Request request = (Request) receiver.receive();
                switch (request.getOperation()) {
                    case LOGIN:
                        Pharmacist p = (Pharmacist) request.getRequest();
                        pharmacist= Controller.getInstance().login(p.getUsername(), p.getPassword());
                        response.setResult(pharmacist);
                        break;
                    case LOG_OUT:
                        pharmacist.setLogged(false);
                        Controller.getInstance().editPharmacist(pharmacist);
                        server.logout(this);
                        break;
                    case GET_ALL_PATIENTS:
                        response.setResult(Controller.getInstance().getAllPatients());
                        break;
                    case GET_ALL_MEDICINES:
                        response.setResult(Controller.getInstance().getAllMedicines());
                        break;
                    case GET_ALL_MEDICINES_BY_QUERY:
                        String query = (String) request.getRequest();
                        response.setResult(Controller.getInstance().getAllMedicinesByQuery(query)); 
                        break;
                    case GET_ALL_PATIENTS_BY_QUERY:
                        String query1 = (String) request.getRequest();
                        response.setResult(Controller.getInstance().getAllPatientsByQuery(query1)); 
                        break;
                    case REMOVE_PATIENT:
                        patient = (Patient) request.getRequest();
                        Controller.getInstance().deletePatient(patient);
                        break;
                    case REMOVE_MEDICINE:
                        medicine= (Medicine) request.getRequest();
                        Controller.getInstance().deleteMedicine(medicine);
                        break;
                    case ADD_PATIENT:
                        patient= (Patient) request.getRequest();
                        Controller.getInstance().addPatient(patient);
                        break;
                    case ADD_MEDICINE:
                        medicine= (Medicine) request.getRequest();
                        Controller.getInstance().addMedicine(medicine);
                        break;
                    case EDIT_PATIENT:
                        patient= (Patient) request.getRequest();
                        Controller.getInstance().editPatient(patient);
                        break;
                    case EDIT_MEDICINE:
                        medicine= (Medicine) request.getRequest();
                        Controller.getInstance().editMedicine(medicine);
                        break;
                    case GET_ALL_RECEIPTS:
                        String query2 = (String) request.getRequest();
                        response.setResult(Controller.getInstance().getReceiptsByQuery(query2));
                        break;
                    case ADD_RECEIPT:
                        receipt = (Receipt) request.getRequest();
                        Controller.getInstance().addReceipt(receipt);
                        break;
                    case GET_RECEIPT:
                        receipt = (Receipt) request.getRequest();
                        List<Receipt> rs = Controller.getInstance().getReceiptsByQuery("");
                        for (Receipt r : rs) {
                            if(r.equals(receipt)){
                                response.setResult(r);
                            }
                        }
                        break;
                    case EDIT_RECEIPT:
                        receipt = (Receipt) request.getRequest();
                        Controller.getInstance().editReceipt(receipt);
                        break;
                    case REMOVE_RECEIPT:
                        receipt = (Receipt) request.getRequest();
                        Controller.getInstance().removeReceipt(receipt);
                        break;
                    case GET_ALL_SALE_STATISTICS:
                        response.setResult(Controller.getInstance().getAllSaleStatistics());
                        break;
                    case ADD_SALE_STATISTIC:
                        SaleStatistics s = (SaleStatistics) request.getRequest();
                        Controller.getInstance().addSaleStatistics(s);
                        break;
                    case EDIT_SALE_STATISTIC:
                        SaleStatistics s1 = (SaleStatistics) request.getRequest();
                        Controller.getInstance().editSaleStatistics(s1);
                        break;
                    case REFRESH_MEDS:
                        List<ProcessClientRequest> clients = server.getActiveClients();
                        for (ProcessClientRequest c : clients) {
                            if(!c.getSocket().equals(socket)){
                                String queryS = (String) request.getRequest();
                                c.sender.send("Uspeloooo");
                                //c.sender.send(Controller.getInstance().getAllMedicinesByQuery(queryS));
                            }
                        }
                        break;
                    default:
                        System.out.println("Greska");
                }
            } catch (Exception ex) {
                response.setException(ex);
            }
            sender.send(response);
            }
            }catch (SocketException ex) {
                    System.out.println("Klijent je prekinuo program.");
                    //throw new Exception();
            }
    }
    
    public Pharmacist getPharmacist(){
        return pharmacist;
    }
    
    public Socket getSocket(){
        return socket;
    }
    
    public void closeSocket(){
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ProcessClientRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void abortedApp() {
        try {
                pharmacist.setLogged(false);
                Controller.getInstance().editPharmacist(pharmacist);
                closeSocket();
                System.out.println("Client aborted the program.");
            } catch (Exception ex1) {
                Logger.getLogger(ProcessClientRequest.class.getName()).log(Level.SEVERE, null, ex1);
            }
    }

   
    
}
