package com.pollra.client;

import com.pollra.client.http.HttpResponse;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
            /**
             * inputStream 으로 들어온 requestHeader를 객체로 저장하는 작업을 진행해야 함
             */



            /**
             * DataOutputStream 으로 보낼 데이터를 정리해서 보낸다
             */
            byte[] body = Files.readAllBytes(new File("src/main/resources/page/index.html").toPath());

            HttpResponse.ResponseBuilder builder = new HttpResponse.ResponseBuilder();
            HttpResponse httpResponse = builder
                    .addHeader("Content-Type: ", "text/html; charset=utf-8;")
                    .addHeader("Content-Length: ", body.length +"")
                    .build();

            DataOutputStream dos = new DataOutputStream(os);
            dos.writeBytes(httpResponse.getHttpProtocol() +" "+httpResponse.getStatus().getStatusMassage() + "\r\n");
            for(Map.Entry<String, String> kv : httpResponse.getResponseHeader().entrySet()){
                dos.writeBytes(kv.getKey()+": "+kv.getValue()+"\r\n");
            }
            dos.writeBytes("\r\n");

            dos.write(body, 0, body.length);
            dos.writeBytes("\r\n");

            dos.flush();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
