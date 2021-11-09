package rpc_ejemplo;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.rmi.Remote;
import java.rmi.RemoteException;

@WebService
@SOAPBinding(style = Style.RPC)
public interface InterfazServicios extends Remote {
    @WebMethod String getMessage(String mensaje) throws RemoteException;
}
