package com.pollra.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

public class ClientTask implements Runnable {
    private Socket socket;
    public ClientTask (Socket socket){
        this.socket = socket;
    }

    public void run() {
        try(InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream()){
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            // GET / HTTP/1.1
            String protocolLine = br.readLine();
            if(protocolLine == null){
                return;
            }
            // 전체적인 헤더를 읽어들임
            // LinkedList 사용이유 : 새로운 값을 추가하는데에 ArrayList보다 적은 메모리를 사용.
            List<String> requestHeader = new LinkedList<>();
            String requestTemp;
            while(!((requestTemp = br.readLine()).isEmpty())){
                requestHeader.add(requestTemp);
            }




        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
