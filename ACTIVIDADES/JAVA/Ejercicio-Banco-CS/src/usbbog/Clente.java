package usbbog;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 310
 */
public class Clente extends Applet {

    static final String HOST = "localhost";
    static final int puerto = 5000;
    Socket skcliente;
    Label l1 = new Label("Digite su salario:");
    TextField t1 = new TextField(20);
    Button b1 = new Button("Enviar");
    String mensaje = "", cc = "";
    Label l2 = new Label("Respuesta");
    TextArea ta = new TextArea(20, 40);
    Cliente c;

    public void init() {
        add(l1);
        add(t1);
        add(b1);
        add(l2);
        add(ta);
    }

    public boolean action(Event e, Object ob) {
        if (e.target == b1) {
            mensaje = t1.getText();
            t1.setText("");
            ta.setText("");
            try {
                c.proceso();
            } catch (IOException ex) {
                Logger.getLogger(Clente.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }

    class Cliente {

        Cliente(Graphics g) {
            //g.drawString("Escucho el puerto" + puerto, 100, 100);
        }

        void proceso() throws IOException {
            //try {
                OutputStream aux1 = skcliente.getOutputStream();
                DataOutputStream hilo1 = new DataOutputStream(aux1);
                hilo1.writeUTF(mensaje);
                //hilo1.close();
                System.out.println("- s" + mensaje);
                InputStream aux2 = skcliente.getInputStream();
                DataInputStream hilo2 = new DataInputStream(aux2);
                String m = hilo2.readUTF();
                System.out.println(" - " + m);
                ta.setText(m);
                skcliente.close();
            //} catch (Exception e) {
            //    System.out.println("Error en la comuniacion: "+e.toString());
            //}
        }
    }

    public void paint(Graphics g) {
        try {
            skcliente = new Socket(HOST, puerto);
            g.drawString("Creación del socket cliente", 20, 20);
        } catch (Exception e) {
            System.out.println("CONEXION CON PROBLEMAS "+e.getMessage());
        }
        c = new Cliente(g);
    }

}
