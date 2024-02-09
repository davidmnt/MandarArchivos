package Controlador;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class server {
    private JPanel panelPrin;
    private JTextArea textArea;
    private JLabel Servirdor;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("server");
                frame.setContentPane(new server().panelPrin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setBounds(0,0,480,380);
                ImageIcon icon = new ImageIcon("src/resources/servidor_icono.png");

                // Establecer el icono del JFrame
                frame.setIconImage(icon.getImage());

                frame.setVisible(true);



                //Iocno del servidor



            }
        });

    }

    public server(){
        textArea.setLineWrap(true);

        conexion();

    }

    private void conexion(){
        LocalDateTime ldt = LocalDateTime.now();

            System.out.println("Esperando archivo");
            textArea.append("Esperando archivo..." + "\n");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try (ServerSocket serverSocket = new ServerSocket(2020)) {


                            Socket socket = serverSocket.accept();
                            textArea.append("Conexion de la ip: " + socket.getInetAddress().getHostAddress() + "\n");
                            DataInputStream dis = new DataInputStream(socket.getInputStream());

                            String nombreArchivo = dis.readUTF();
                            textArea.append("Nombre del archivo enviado: " + nombreArchivo + "\n");
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

                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

    }
}
