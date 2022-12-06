/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uppercaseapp;
import java.io.*;
import java.util.Scanner;
import java.lang.Thread;
import java.net.*;

/**
 *
 * @author mateu
 */
public class Uppercaseapp {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
        // TODO code application logic here
        
        Scanner scanf = new Scanner(System.in);
        
        try{
            Socket clientSocket = new Socket("localhost", 123);             
            DataOutputStream   infoToServer = new DataOutputStream(clientSocket.getOutputStream());
            String msgToServer = scanf.nextLine();
            infoToServer.writeBytes(msgToServer + "\n");            
            BufferedReader infoServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String msgToClient = infoServer.readLine();           
            clientSocket.close();
            System.out.println(msgToClient);
            
        }catch(Exception e)
        {
            System.out.println("Erro: " + e.getMessage());
            
        }
       
    }
    
}
