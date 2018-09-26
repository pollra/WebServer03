package com.pollra.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class ClientTask implements Runnable {
    private Socket socket;
    public ClientTask (Socket socket){
        this.socket = socket;
    }

    public void run() {
        try(InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream()){
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
