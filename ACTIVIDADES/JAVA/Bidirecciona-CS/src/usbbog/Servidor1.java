package usbbog;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author 310
 */
public class Servidor1 extends Applet {

    ServerSocket skServer;
    static final int puerto = 5000;
    Label l1 = new Label("Digite mensaje");
    TextField t1 = new TextField(20);
    Button b1 = new Button("Enviar");
    String mensaje = "";

    @Override
    public void init() {
        add(l1);
        add(t1);
        add(b1);
    }

    @Override
    public boolean action(Event e, Object ob) {

        if (e.target == b1) {
            mensaje = t1.getText();
            return true;
        }

        return false;
    }

    class Server {

        Graphics g;

        public Server(Graphics g) {
            this.g = g;
            g.drawString("Se escucha el puerto " + puerto, 100, 100);
        }

        void Emisor() {

            try {
                Socket skcliente = skServer.accept();
                InputStream aux1 = skcliente.getInputStream();
                DataInputStream hilo1 = new DataInputStream(aux1);
                String pr = hilo1.readUTF();
                System.out.println("Cadena cifrada recibida: " + pr);
//                    OutputStream aux=skcliente.getOutputStream();
//                    DataOutputStream hilo=new DataOutputStream(aux);
//                    hilo.writeUTF("");
                skcliente.close();
            } catch (Exception e) {
                g.drawString("Error en el envío de datos", 20, 20);
                System.out.println(" - " + e.getMessage());
            }
        }
    }

    public void paint(Graphics g) {
        try {
            skServer = new ServerSocket(puerto);
        } catch (Exception e) {
            g.drawString("Error al crear el socket del server", 20, 20);
        }
        Server s = new Server(g);
        s.Emisor();
    }

}
