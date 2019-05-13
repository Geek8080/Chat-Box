/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.box;

/**
 *
 * @author Prashant Prakash
 */
import java.net.*;
import java.io.*;

public class IPAddress {

    public IPAddress() {
        String publicIPAddress, systemIPAddress;
        publicIPAddress = getPublicIPAddress();
        systemIPAddress = getSystemIPAddress();
        javax.swing.JOptionPane.showMessageDialog(null, "System IP: " + systemIPAddress + "\nPublic IP : " + publicIPAddress + "\n");
    }

    public String getSystemIPAddress() {
        String systemIPAddress = "Unable to recognise... ";
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            systemIPAddress = localhost.getHostAddress().trim();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        return systemIPAddress;
    }

    public String getPublicIPAddress() {
        String systemIPAddress = "Cannot read from the server... ";
        try {
            URL urlName = new URL("http://bot.whatismyipaddress.com");
            BufferedReader sc = new BufferedReader(new InputStreamReader(urlName.openStream()));
            systemIPAddress = sc.readLine().trim();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return systemIPAddress;
    }
}
