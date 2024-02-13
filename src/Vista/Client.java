package Vista;

import javax.swing.*;

public class Client {
    private JPanel PanelPrin;
    private JLabel IPLabel;
    private JTextField textField2;
    private JTextField textField1;
    private JLabel Titulo;
    private JButton btn_conectar;
    private JLabel NombreLabel;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        frame.setContentPane(new Client().PanelPrin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(300,300,280,280);
        frame.setVisible(true);
    }
}
