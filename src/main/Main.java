/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.Controller;
import domain.Medicine;
import domain.Patient;
import domain.Pharmacist;
import domain.Receipt;
import domain.ReceiptItem;
import domain.SaleStatistics;
import form.FrmMain;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import server.Server;

/**
 *
 * @author Andrija - PC
 */
public class Main {
    public static void main(String[] args) {
            try {
            FrmMain fm = new FrmMain();
            fm.setVisible(true);
            fm.setExtendedState(MAXIMIZED_BOTH);
            } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    }
}
