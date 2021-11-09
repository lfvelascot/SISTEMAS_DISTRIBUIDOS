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
import java.net.Socket;

public class Clente2 extends Applet {

    static final String HOST = "localhost";
    static final int puerto = 5000;
    Socket skcliente;
    Label l1 = new Label("Digite mensaje");
    TextField t1 = new TextField(20);
    Button b1 = new Button("Enviar");
    String mensaje = "", cc = "";
    Cliente c;

    public void init() {
        add(l1);
        add(t1);
        add(b1);
    }

    public boolean action(Event e, Object ob) {
        if (e.target == b1) {
            mensaje = t1.getText();
            c.proceso();
            t1.setText("");
            return true;
        }

        return false;
    }

    class Cliente {

        Cliente(Graphics g) {
            g.drawString("Escucho el puerto" + puerto, 100, 100);
        }

        void proceso() {
            try {

                OutputStream aux1 = skcliente.getOutputStream();
                DataOutputStream hilo1 = new DataOutputStream(aux1);
                hilo1.writeUTF(mensaje);
                System.out.println("Contenido de cc" + mensaje);
//            InputStream aux2 = skcliente.getInputStream ();
//            DataInputStream hilo2 = new DataInputStream (aux2);
//            System.out.println (hilo2.readUTF ());
                //skcliente.close();
            } catch (Exception e) {
                System.out.println(" - " + e.getMessage());
            }
        }
    }

    public void paint(Graphics g) {
        try {
            skcliente = new Socket(HOST, puerto);
            g.drawString("Creación del socket cliente", 20, 20);
        } catch (Exception e) {
        }
        c = new Cliente(g);
    }

}
