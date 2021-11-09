package server;

import java.rmi.RemoteException;
import lib.CifradoDato;

/**
 *
 * @author user
 */
public class Implementacion implements interfacermi.InterfaceJava {

    CifradoDato cd;

    public Implementacion() throws RemoteException {
        this.cd = new CifradoDato();
    }

    @Override
    public String getData(String name) throws RemoteException {
        System.out.println("PETICION DATA");
        return cd.getCifrado("Retorno desde el servidor = " + cd.getDescifrado(name) + "");
    }

    @Override
    public String getSuma(String arr) throws RemoteException {
        int x = 0;
        String[] ar = cd.getDescifrado(arr).split(",");
        System.out.println("PETICION SUMA");
        for (String j : ar) {
            x += Integer.parseInt(j);
        }
        return cd.getCifrado(String.valueOf(x));
    }

}
