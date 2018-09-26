package com.pollra.server;

import com.pollra.client.ClientTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port = 12345;
    private ServerSocket serverSocket;
    private ExecutorService executorService;

    private Server(){
        try{
            serverSocket = new ServerSocket();
            executorService = Executors.newFixedThreadPool(10);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Server getInstance() { return SingletonHolder.instanse; }

    public static class SingletonHolder{ private static final Server instanse = new Server(); }

    public void start() throws IOException{
        while(true){
            Socket client = serverSocket.accept();
            Runnable task = new ClientTask(client);
            executorService.execute(task);
        }
    }

}
