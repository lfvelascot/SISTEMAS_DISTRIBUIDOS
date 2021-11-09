/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author user
 */
public class EmisorJframe {

    /**
     * Crea el nuevo formulario para enviar datos al ReceptorJframe
     */
    private JTextField campoIntroducir;

    private JTextArea areaPantalla;

    private DatagramSocket socket;

    // configurar GUI y DatagramSocket
    public EmisorJframee() {
        super(EmisorJframe);

        Container contenedor = new Container();

        campoIntroducir = new JTextField("Escriba aquí el mensaje");

        campoIntroducir.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent evento) {

                // crear y enviar el paquete
                try {

                    areaPantalla.append("\nEnviando paquete que contiene: "
                            + evento.getActionCommand() + "\n");

                    // obtener mensaje del campo de texto y convertirlo en arreglo byte
                    String mensaje = evento.getActionCommand();

                    byte datos[] = mensaje.getBytes();

                    // crear enviarPaquete
                    DatagramPacket enviarPaquete = new DatagramPacket(datos, datos.length, InetAddress.getLocalHost(), 7000);

                    socket.send(enviarPaquete); // enviar paquete

                    areaPantalla.append("Paquete enviado\n");

                    areaPantalla.setCaretPosition(areaPantalla.getText().length());

                } // procesar los problemas que pueden ocurrir al crear o enviar el paquete
                catch (IOException excepcionES) {

                    mostrarMensaje(excepcionES.toString() + "\n");

                    excepcionES.printStackTrace();

                }

            } // fin de actionPerformed


        } // fin de la clase interna

        ); // fin de la llamada a addActionListener

        contenedor.add(campoIntroducir, BorderLayout.NORTH);

        areaPantalla = new JTextArea();

        contenedor.add(new JScrollPane(areaPantalla),
                BorderLayout.CENTER);

        setSize(400, 300);

        setVisible(true);

        // crear objeto DatagramSocket para enviar y recibir paquetes
        try {

            socket = new DatagramSocket();

        } // atrapar los problemas que pueden ocurrir al crear objeto DatagramSocket
        catch (SocketException excepcionSocket) {

            excepcionSocket.printStackTrace();

            System.exit(1);

        }

    } // fin del constructor de Emisor

    private void esperarPaquetes() {

        while (true) { // iterar infinitamente

            // recibir el paquete y mostrar su contenido
            try {

                // establecer el paquete
                byte datos[] = new byte[100];

                DatagramPacket recibirPaquete = new DatagramPacket(
                        datos, datos.length);

                socket.receive(recibirPaquete); // esperar un paquete

                // mostrar el contenido del paquete
                mostrarMensaje("\nPaquete recibido:"
                        + "\nDel host: " + recibirPaquete.getAddress()
                        + "\nPuerto del host: " + recibirPaquete.getPort()
                        + "\nLongitud: " + recibirPaquete.getLength()
                        + "\nContenido:\n\t" + new String(recibirPaquete.getData(), 0, recibirPaquete.getLength()));

            } // procesar los problemas que pueden ocurrir al recibir o mostrar el paquete
            catch (IOException excepcion) {

                mostrarMensaje(excepcion.toString() + "\n");

                excepcion.printStackTrace();

            }

        } // fin de instrucción while

    } // fin del método esperarPaquetes

    // método utilitario que es llamado desde otros subprocesos para manipular a
    // areaPantalla en el subproceso despachador de eventos
    private void mostrarMensaje(final String mensajeAMostrar) {

        // mostrar mensaje del subproceso de ejecución despachador de eventos
        SwingUtilities.invokeLater(
                new Runnable() {  // clase interna para asegurar que la GUI se actualice apropiadamente

            public void run() // actualiza areaPantalla
            {

                areaPantalla.append(mensajeAMostrar);

                areaPantalla.setCaretPosition(
                        areaPantalla.getText().length());

            }

        } // fin de la clase interna

        ); // fin de la llamada a SwingUtilities.invokeLater

    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmisorJframe().setVisible(true);
            }
        });
        JFrame.setDefaultLookAndFeelDecorated(true);

        EmisorJframe aplicacion = new EmisorJframe();

        aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        aplicacion.esperarPaquetes();

    }

}
