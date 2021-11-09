package ejercicio;

import java.applet.Applet;
import java.awt.Graphics;

public class ProcesosConcurrentes extends Applet {

    @Override
    public void paint(Graphics g) {
        LetraA lta = new LetraA(g);
        LetraB ltb = new LetraB(g);
        // Incorporar un proceso de imagenes o iconos
        lta.start();
        ltb.start();;
        while(lta.isAlive() || ltb.isAlive()){
            g.drawString("Procesos en ejecución", 200, 200);
        } 
    }
}
