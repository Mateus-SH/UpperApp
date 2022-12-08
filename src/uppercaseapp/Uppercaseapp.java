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
public class Uppercaseapp extends Thread{

    private String nome;
    private String mgs;
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the mgs
     */
    public String getMgs() {
        return mgs;
    }

    /**
     * @param mgs the mgs to set
     */
    public void setMgs(String mgs) {
        this.mgs = mgs;
    }

    public Uppercaseapp(String nomeCliente, String msg)
    {
        this.nome = nomeCliente;
        this.mgs = msg;
    }
   
    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     * @throws java.net.UnknownHostException
     */
    public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {  
        int i, nThreads;
        Uppercaseapp clients[];
        Scanner scanf = new Scanner(System.in);
        
        System.out.println("Digite o número de clientes: ");
        nThreads = scanf.nextInt();
     
        scanf.nextLine();
        
        clients = new Uppercaseapp[nThreads];
        
        for(i = 0; i < nThreads; i++)
        {
            System.out.println("Mensagem do Cliente" + String.valueOf(i) + ":");
            clients[i] = new Uppercaseapp("Cliente" + String.valueOf(i), scanf.nextLine());
        }
        
        for(i = 0; i < nThreads; i++)
        {
            clients[i].start();
        }
    }
    
    
    
    @Override
    public void run()
    {
        try{
            Socket clientSocket = new Socket("localhost", 5000);             
            DataOutputStream   infoToServer = new DataOutputStream(clientSocket.getOutputStream());
            String msgToServer = this.mgs;
            System.out.println(this.nome + " - Manda Mensagem: " + this.mgs);
            infoToServer.writeBytes(msgToServer + "\n");            
            BufferedReader infoServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            
            String msgToClient; 
            String path = "C:\\Users\\LAR\\Documents\\GitHub\\UpperApp\\src\\uppercaseapp\\";
            
            BufferedWriter escritor = new BufferedWriter(new FileWriter(path + "index2.html"));
            
            System.out.println("Servidor responde: ");            
            while((msgToClient = infoServer.readLine()) != null)
            {
                System.out.println(msgToClient);
                escritor.write("OI " + "\r\n");            
            }
            
            escritor.close();
            
            
            
            clientSocket.close();
            
            
        }catch(Exception e)
        {
            System.out.println("Erro: " + e.getMessage());
            
        }    
    }
    
    
}
