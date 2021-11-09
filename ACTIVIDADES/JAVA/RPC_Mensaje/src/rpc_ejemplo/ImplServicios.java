package rpc_ejemplo;

import javax.jws.WebService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@WebService(endpointInterface = "rpc_ejemplo.InterfazServicios")
public class ImplServicios extends UnicastRemoteObject implements InterfazServicios {

    public ImplServicios() throws RemoteException { }

    @Override
    public String getMessage(String mensaje) throws RemoteException {
        System.out.println(mensaje + " SOLICITUD");
        return "Cliente servidor " + mensaje;
    }
}
