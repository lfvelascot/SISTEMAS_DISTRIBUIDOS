package usbbog;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Applet {

    static final int port = 7000;
    static TextField t1;
    Button b1;
    static boolean flag = false;
    ServerSocket sks;

    public class Server {

        Graphics g;

        public Server(Graphics g) {
            this.g = g;
            g.drawString("Servidor en el puerto: " + port, 50, 50);
        }

        public void ProcesoMensajes(String m) {
            try {
                Socket skc1 = sks.accept();
                g.drawString("SE ATIENDE AL CLIENTE: ", 50, 70);
                OutputStream os = skc1.getOutputStream();
                DataOutputStream h1 = new DataOutputStream(os);
                h1.writeUTF(m);
                h1.close();
            } catch (Exception e) {
                System.out.println(" - " + e.getMessage());
            }
        }
    }

    private static class BotonPListener implements ActionListener {
        
        static TextField t1;

        public BotonPListener(TextField t1) {
            this.t1 = t1;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            flag = !t1.getText().isEmpty();
        }
    }

    public void init() {
        t1 = new TextField(10);
        add(t1);
        t1.setText("");
        b1 = new Button("ENVIAR");
        b1.addActionListener(new BotonPListener(t1));
        add(b1);
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("MENSAJE: ", 40, 50);
        String m = "";
        try {
            sks = new ServerSocket(port);
        } catch (Exception e) {
            g.drawString("No se activo el servidor", 60, 60);
        }
        Server s = new Server(g);
        if (flag) {
            s.ProcesoMensajes(t1.getText());
        }
    }
}
