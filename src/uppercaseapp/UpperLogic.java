/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uppercaseapp;

import java.net.*;
import java.io.*;
import java.lang.Thread;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mateu
 */
public class UpperLogic implements Runnable{
    
    private Socket connection;
    
    public UpperLogic(Socket cntSocket)
    {
        this.connection = cntSocket; 
        Thread t = new Thread(this);
        t.start();
    }
    
    
    @Override
    public void run()
    {
        try{
            BufferedReader clientInfo = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            DataOutputStream infoToClient = new DataOutputStream(this.connection.getOutputStream());
            
            System.out.println("Cliente Conectado: " + this.connection.getInetAddress().getHostAddress() + "-" + Thread.currentThread().getName());
            String test = clientInfo.readLine();
            System.out.println("Mensagem Recebida(Server): " + test);

            String newName = test.toUpperCase();
            System.out.println("New name: " + newName);
            
            
            BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\LAR\\Documents\\GitHub\\UpperApp\\src\\uppercaseapp\\index.html"));
            String linha;
            System.out.println("Eu li essa merda!");
            while((linha = bf.readLine()) != null)
            {
                //System.out.println(linha);
                infoToClient.writeBytes(linha + "\r\n");

            }
            bf.close();
           
            Thread.sleep(5000);
        }catch(IOException e)
        {
            e.getStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(UpperLogic.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    


    
}
