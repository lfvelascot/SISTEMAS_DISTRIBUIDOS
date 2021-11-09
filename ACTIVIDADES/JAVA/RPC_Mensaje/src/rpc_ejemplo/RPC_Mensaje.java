package rpc_ejemplo;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class RPC_Mensaje {

    public static void main(String[] args) throws NotBoundException, RemoteException {
        try {
            Service service = Service.create(new URL("http://localhost:7779/ws/servicio?wsdl"),
                    new QName("http://rpc_ejemplo/", "ImplServiciosService"));
            InterfazServicios rpc = service.getPort(InterfazServicios.class),
                    rmi = (InterfazServicios) Naming.lookup("rmi://192.168.0.14:7777/ABC");
            System.out.println(rpc.getMessage("RPC"));
            System.out.println(rmi.getMessage("RMI"));
        } catch (MalformedURLException ex) {
            System.out.println("WSDL document url error: " + ex);
        } catch (RemoteException ex) {
            System.out.println("RMI ERROR " + ex);
        }
    }
}
