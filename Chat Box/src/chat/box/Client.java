/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.box;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prashant Prakash
 */
public class Client {

    BufferedReader reader;
    PrintWriter writer;
    Socket sock;
    String name;

    Client(String Name) {
        name = Name;
    }

    
    public void go() {
        setUpNetworking();
        Thread t = new Thread(new IncomingReader());
        t.start();
    }

    public void setUpNetworking() {
        try {
            System.out.println("Entered the setUpNetworking() method...");
            sock = new Socket(Splash.ip, 5000);
            System.out.println("sock initialised... ");
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            System.out.println("Initialised reader... ");
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("Initialised writer... ");
            javax.swing.JOptionPane.showMessageDialog(null, "Connection Established... ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void close() {
        try {
            sock.close();
            reader.close();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class IncomingReader implements Runnable {

        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    ChatClient.tam.append(message + "\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
}
