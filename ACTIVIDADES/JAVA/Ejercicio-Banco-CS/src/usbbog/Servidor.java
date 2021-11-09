package usbbog;

import java.applet.Applet;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Applet {

    ServerSocket skServer;
    static final int puerto = 5000;

    class Server {

        Graphics g;

        public Server(Graphics g) {
            this.g = g;
            g.drawString("Se escucha el puerto " + puerto + " a los clientes", 100, 100);
        }

        void Emisor() {
            try {
                Socket skcliente = skServer.accept();
                InputStream aux1 = skcliente.getInputStream();
                DataInputStream hilo1 = new DataInputStream(aux1);
                String pr = hilo1.readUTF();
                hilo1.close();
                int salario = Integer.parseInt(pr), prestamo = 1000000;
                double interes = 1.2, cuota = interes * (prestamo / 6);
                String retorno = "";
                if (salario <= 3500000) {
                    retorno = "No se puede realizar el prestamo con un salario menor o igual a $3500000";
                } else {
                    retorno = "Cuota mensual: $" + String.valueOf(Math.round(cuota)) + "\n"
                            + "Valor prestado: $" + String.valueOf(prestamo) + "\n"
                            + "Valor total de las cuotas: (con un interes del 1.2%) $" + String.valueOf(prestamo * interes);
                }
                System.out.println(" - " + retorno);
                OutputStream os = skcliente.getOutputStream();
                DataOutputStream h2 = new DataOutputStream(os);
                h2.writeUTF(retorno);
                h2.close();
                skServer.close();
            } catch (Exception e) {
                g.drawString("Error en el envío de datos", 20, 20);
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
