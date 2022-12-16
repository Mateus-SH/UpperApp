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

    public Uppercaseapp(String nomeCliente)
    {
        this.nome = nomeCliente;
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
        clients = new Uppercaseapp[nThreads];
        
        for(i = 0; i < nThreads; i++)
        {
            clients[i] = new Uppercaseapp("Cliente" + String.valueOf(i));
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
            /*DataOutputStream   infoToServer = new DataOutputStream(clientSocket.getOutputStream());
            String msgToServer = this.mgs;
            System.out.println(this.nome + " - Manda Mensagem: " + this.mgs);
            infoToServer.writeBytes(msgToServer + "\n"); */           
            BufferedReader infoServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            
            String msgToClient = null; 
            String path = "C:\\Users\\mateu\\Documents\\NetBeansProjects\\uppercaseapp\\src\\uppercaseapp\\";

            FileOutputStream arq = new FileOutputStream(path + "index"+ Thread.currentThread().getName()+".html");
            PrintWriter pw = new PrintWriter(arq);

            System.out.println("Servidor responde a " + this.nome);
            while((msgToClient = infoServer.readLine()) != null)
            {
                pw.print(msgToClient + "\n");          
                System.out.println(msgToClient);
            }
            pw.close();
            arq.close();
            infoServer.close();
            //infoToServer.close();
            clientSocket.close();
            
            Runtime.getRuntime().exec("cmd.exe /C start brave.exe C:\\Users\\mateu\\Documents\\NetBeansProjects\\uppercaseapp\\src\\uppercaseapp\\index" + Thread.currentThread().getName()+ ".html");
            
            
                        
        }catch(Exception e)
        {
            System.out.println("Erro: " + e.getMessage());
            
        }    
    }
    
    
}
