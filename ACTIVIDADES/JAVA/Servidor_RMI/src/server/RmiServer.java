package server;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.AlreadyBoundException;

public class RmiServer {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        System.out.println(" - INICIANDO SERVIDOR - ");
        Remote skeleton = UnicastRemoteObject.exportObject(new Implementacion(), 0);
        Registry registry = LocateRegistry.createRegistry(7777);
        registry.bind("ABC", skeleton );
    }
}
