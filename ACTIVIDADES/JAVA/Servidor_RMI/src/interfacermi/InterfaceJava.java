
package interfacermi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceJava extends Remote{
    
    public String getData(String name) throws RemoteException;
    public String getSuma(String arr) throws RemoteException;
}
