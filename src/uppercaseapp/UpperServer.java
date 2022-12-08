/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uppercaseapp;
import java.net.*;
import java.io.*;


/**
 *
 * @author mateu
 */
public class UpperServer{
    
    private ServerSocket socket;
    private int port = 123;
    /**
     * @return the port
     */
    private int getPort()   {
        return port;
    }

    /**
     * @param port the port to set
     */
    private void setPort(int port) {
        this.port = port;
    }
    
    
    
    void executar() throws UnknownHostException{
        
        byte [] end = InetAddress.getByName("localhost").getAddress();
        
        
        System.out.println("Servidor Iniciado!");
        System.out.printf("Endereço do Servidor: %d.%d.%d.%d:%d\n", end[0], end[1], end[2], end[3], this.port);
        System.out.println("Esperando Conexão...");
        

            while(true)
            {
                try
                {                
                    Socket clientConnetion = this.socket.accept();
                    UpperLogic thread = new UpperLogic(clientConnetion);
                 }
                 catch(Exception e){
                    System.out.println("Erro: " + e.getMessage());
                }
            }   
    }
    
 
    public UpperServer(int port)
    {
        try{
            this.socket = new ServerSocket(port);
            this.setPort(port);
        }
        catch(Exception e)
        {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) throws InterruptedException, UnknownHostException 
    {
        // TODO code application logic here 
        UpperServer servidor = new UpperServer(5000);
        servidor.executar();
        
    }

}
