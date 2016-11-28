package rmiServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
	
	Object getContent(String cityName, int days) throws RemoteException;;

}
