package com.example.autopilot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPThread extends Thread{

    String server;
    int port;
    Socket s;
    DataOutputStream dos;
    OutputStream os;
    DataInputStream dis;
    InputStream is;
    boolean activate = false;
    double gasBrakeSteering = 0;



    public TCPThread(String server, int port,  boolean activate){
        this.server = server;
        this.port = port;
        this.activate = activate;
    }


    public void run(){
        try {
            s = new Socket(server, port);
            os = s.getOutputStream();
            dos = new DataOutputStream(os);
            is = s.getInputStream();
            dis = new DataInputStream(is);


            dos.writeBoolean(activate);

            try {
                sleep(5000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

            while (s.isConnected()){
                gasBrakeSteering = dis.readDouble();

                if(gasBrakeSteering == 1){
                    dos.writeBoolean(false);
                    try {
                        sleep(500);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    dis.close();
                    dos.close();
                    s.close();
                }
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }



    }


}

