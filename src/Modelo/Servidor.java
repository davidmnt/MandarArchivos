package Modelo;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;


public class Servidor {
    /*

    ESTA CLASE ERA LA PRIMERA VERSION DEL EJERCICIO, DESPUES LE HE IMPLEMENTADO UN INTERFAZ GRAFICO PARA HACERLO MAS AMENO :)

     */



/*
    public static void main(String[] args) {

        LocalDateTime ldt = LocalDateTime.now();



        while(true) {
            System.out.println("Esperando archivo");
            try (ServerSocket serverSocket = new ServerSocket(2020)){


                 Socket socket = serverSocket.accept();
                 DataInputStream dis = new DataInputStream(socket.getInputStream());

                String nombreArchivo = dis.readUTF();
                System.out.println(nombreArchivo);

                //Esto devuelve la extension del archivo
                String extension = "";
                int lastIndex = nombreArchivo.lastIndexOf('.');
                if (lastIndex != -1) {
                    extension = nombreArchivo.substring(lastIndex);
                }

                String nombreSinExtension = "";
                int index = nombreArchivo.lastIndexOf('.');
                if (index > 0) {
                    nombreSinExtension = nombreArchivo.substring(0, index);
                }


                FileOutputStream fileOutputStream = new FileOutputStream("src/filesReceived/" + nombreSinExtension + "_"
                        + ldt.getHour() + "_" + ldt.getMinute() + "_" + ldt.getSecond() + extension);

                System.out.println("La extension es: " + extension);

                InputStream inputStream = socket.getInputStream();
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
    }*/


}
