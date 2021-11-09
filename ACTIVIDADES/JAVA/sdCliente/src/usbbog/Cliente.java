package usbbog;

import java.applet.Applet;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class Cliente extends Applet {

    static final String HOST = "localhost";
    static final int port = 7000;
    Socket skc;

    public class Client {

        Graphics g;
        
        public Client(Graphics g) {
            this.g = g;
            g.drawString("Se Activo el cliente", 50, 50);
        }

        public void proceso() {
            try {
                InputStream aux = skc.getInputStream();
                DataInputStream flujo = new DataInputStream(aux);
                g.drawString("DATO DEL SERVIDOR: "+flujo.readUTF(),50,70);
                skc.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                g.drawString("No se activo el cliente",50,70);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        try {
            skc = new Socket(HOST, port);
        } catch (Exception e) {
            g.drawString("No se activo el cliente", 50,70);
        }
        Client c = new Client(g);
        c.proceso();
    }
}
