/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import domain.Pharmacist;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import thread.ProcessClientRequest;

/**
 *
 * @author Andrija - PC
 */
public class Server extends Thread{
private ServerSocket serverSocket;
public static List<ProcessClientRequest> clientRequests;

    public Server() throws IOException {
        serverSocket = new ServerSocket(12000);
        clientRequests=new ArrayList<>();
    }

    @Override
    public void run() {
        while(!serverSocket.isClosed()){
        try {
            //System.out.println("Cekam klijente");
            Socket socket = serverSocket.accept();
            //System.out.println("Klijent se povezao");
            handleClient(socket);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }


    private void handleClient(Socket socket) {
        ProcessClientRequest clientRequest = new ProcessClientRequest(this,socket);
        clientRequests.add(clientRequest);
        clientRequest.start();
    }

    public ServerSocket getServerSocket(){
        return serverSocket;
    }
    
    public List<Pharmacist> getActivePharmacists(){
    List<Pharmacist> pharmacists = new ArrayList<>();
    for(ProcessClientRequest k: clientRequests){
        if(k.getPharmacist()!=null){
        pharmacists.add(k.getPharmacist());
        }
    }
    return pharmacists;
    }
    
    public List<ProcessClientRequest> getActiveClients(){
    return clientRequests;
    }
    
    public void closeActivePharmacists(){
    for(ProcessClientRequest k: clientRequests){
        try {
            k.getSocket().close();
            clientRequests.remove(k);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
    public void logout(ProcessClientRequest k){
        try {
            clientRequests.remove(k);
            k.getSocket().close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}

