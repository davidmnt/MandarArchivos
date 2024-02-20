package Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Cliente {
    private JPanel PanelPrin;
    private JPanel PanelMenu;
    private JMenu File;
    private JMenuItem Abrir;
    private JLabel NombreArchivo;
    private JButton btn_enviar;
    private JTextArea textArea1;
    private JTextField labelIp;
    private JLabel IP;
    private JButton btn_conectar;

    java.io.File selectedFile;
    String nombreArchivo = "";

    Socket socket = null;


    JFileChooser fileChooser = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cliente");
        frame.setContentPane(new Cliente().PanelPrin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0,0,450,500);

        ImageIcon icon = new ImageIcon("src/resources/client.png");

        // Establecer el icono del JFrame
        frame.setIconImage(icon.getImage());

        frame.setVisible(true);
    }

    public Cliente(){

        ImageIcon i = new ImageIcon("src/resources/subir_1.png");
        Image image = i.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        btn_enviar.setIcon(scaledIcon);

        Abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mostrarDialogoSeleccionArchivo();

            }
        });



        btn_enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(socket != null) {
                    enviarArchivo(socket);
                }else {
                    JOptionPane.showMessageDialog(null,"La conexion es nula");
                }

            }
        });

        btn_conectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                conexion();
                labelIp.setText(" ");

            }
        });

    }

    private void mostrarDialogoSeleccionArchivo() {
        fileChooser = new JFileChooser();

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            nombreArchivo = selectedFile.getName();
            NombreArchivo.setText(nombreArchivo);
            textArea1.append("El archivo enviado es: " + nombreArchivo);
            textArea1.append("\n");
        }
    }

    private void conexion(){
        String ip = labelIp.getText().toString();

        try {
            socket = new Socket(ip, 2020);

            textArea1.append("Te has conectado al servidor: " + socket.getInetAddress().getHostAddress());
            textArea1.append("\n");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void enviarArchivo(Socket socket) {
        final File FILE_PATH = selectedFile;

        if(FILE_PATH == null){
            JOptionPane.showMessageDialog(null,"Tienes que seleccionar un archivo previamente");
        }

        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(selectedFile.getName());

            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            OutputStream outputStream = socket.getOutputStream();


            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            fileInputStream.close();

            System.out.println("Archivo enviado con éxito.");

            textArea1.append("Archivo enviado con éxito.");
            textArea1.append("\n");
        }catch (IOException e){
            e.getStackTrace();
        }
    }



}
