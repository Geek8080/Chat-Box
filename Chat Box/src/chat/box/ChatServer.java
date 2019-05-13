package chat.box;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Prashant Prakash
 */

import java.io.*;
import java.util.*;
import java.net.*;

public class ChatServer {
    ArrayList clientOutputStreams;
    
    public static void main(String []args){
        new ChatServer().go();
    }
    
    public void go(){
        clientOutputStreams = new ArrayList();
        
        try{
            ServerSocket serverSock= new ServerSocket(5000);
            IPAddress ips = new IPAddress();
            while(true){
                Socket clientSocket = serverSock.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);
                
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.setName("" + clientOutputStreams.size());
                t.start();
                javax.swing.JOptionPane.showMessageDialog(null,"The connection has been Established... ");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void tellEveryone(String message){
        Iterator it = clientOutputStreams.iterator();
        while(it.hasNext()){
            try{
                PrintWriter writer = (PrintWriter)it.next();
                writer.println(message);
                writer.flush();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public class ClientHandler implements Runnable{
        BufferedReader reader;
        Socket sock;
        
        public ClientHandler(Socket clientSocket){
            try{
                sock = clientSocket;
                InputStreamReader is= new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(is);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        @Override
        public void run(){
            String message;
            try{
                while((message = reader.readLine())!=null){
                    tellEveryone(message);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
