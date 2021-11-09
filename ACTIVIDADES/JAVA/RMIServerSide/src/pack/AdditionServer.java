package pack;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AdditionServer {

    public static void main(String[] argv) {
        try {
            System.setSecurityManager(new SecurityManager());
            Registry registry = LocateRegistry.createRegistry(7777);
            Addition add = new Addition();
            registry.bind("ABC", add);
            System.out.println("Addition Server is ready.");
        } catch (AlreadyBoundException | RemoteException e) {
            System.out.println("Addition Server failed: " + e);
        }
    }

}
