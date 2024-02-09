package Modelo;

import Controlador.Interfaz;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Servidor {



    public static void main(String[] args) {
        ObjArchivo o = new ObjArchivo();
        LocalDateTime ldt = LocalDateTime.now();



        while(true) {
            System.out.println("Esperando archivo");
            try (ServerSocket serverSocket = new ServerSocket(2020);
                 Socket socket = serverSocket.accept();
                 InputStream inputStream = socket.getInputStream();



                 FileOutputStream fileOutputStream = new FileOutputStream("src/filesReceived/archivo"
                         + ldt.getHour() + "_" + ldt.getMinute() + "_" + ldt.getSecond())) {



                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);

                }


                System.out.println("Archivo recibido con éxito.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
