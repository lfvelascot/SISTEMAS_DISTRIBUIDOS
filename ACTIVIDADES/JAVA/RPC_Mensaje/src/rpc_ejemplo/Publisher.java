package rpc_ejemplo;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteObject;
import javax.xml.ws.Endpoint;

public class Publisher {

    public static void main(String[] args) throws RemoteException,
            AlreadyBoundException {
        System.out.println(" - INICIANDO SERVIDOR - ");
        Endpoint.publish("http://localhost:7779/ws/servicio",
                new ImplServicios());
        LocateRegistry.createRegistry(7777).bind("ABC",
                RemoteObject.toStub(new ImplServicios()));
    }
}
