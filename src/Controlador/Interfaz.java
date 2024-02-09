package Controlador;

import Modelo.ObjArchivo;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Interfaz {
    private JPanel PanelPrinc;
    private JPanel PanelMenu;
    private JMenu File;
    private JMenuItem Abrir;
    private JLabel NombreArchivo;
    private JButton btn_enviar;
    private JLabel Titulo;
    java.io.File selectedFile;
    String nombreArchivo = "";
    String fileExtension = "";
    JFileChooser fileChooser = null;
    ObjArchivo o = new ObjArchivo();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Interfaz");
        frame.setContentPane(new Interfaz().PanelPrinc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0,0,450,500);
        frame.setVisible(true);

    }

    public Interfaz(){

        Abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();

                // Filtrar solo archivos de imagen
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Texto", "txt");
                fileChooser.setFileFilter(filter);

                // Mostrar el diálogo para seleccionar el archivo
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {

                    // Obtener el archivo seleccionado
                     selectedFile = fileChooser.getSelectedFile();
                    nombreArchivo = selectedFile.getName();
                    NombreArchivo.setText(nombreArchivo);



                    int index = nombreArchivo.lastIndexOf('.');
                    if (index > 0) {
                        fileExtension = nombreArchivo.substring(index + 1);
                        System.out.println(fileExtension);

                        o = new ObjArchivo(nombreArchivo,fileExtension);

                    }

                }
            }
        });




        btn_enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final File FILE_PATH = selectedFile; // Cambia esto por la ruta de tu archivo

                try (Socket socket = new Socket("localhost", 2020);
                     OutputStream outputStream = socket.getOutputStream();
                     FileInputStream fileInputStream = new FileInputStream(FILE_PATH)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    System.out.println("Archivo enviado con éxito.");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });



    }


}
