/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio;

import java.awt.Graphics;

/**
 *
 * @author user
 */
public class LetraB extends Thread{
    
    Graphics g;
    int x = 40, y = 20, cont = 1;

    public LetraB(Graphics g) {
        this.g = g;
        
    }

    @Override
    public void run() {
        while(cont < 10){
            g.drawString("B", x, y);
            cont++; y+=15;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                
            }
        }
    }

}
