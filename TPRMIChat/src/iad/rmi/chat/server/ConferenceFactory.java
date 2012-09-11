package iad.rmi.chat.server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConferenceFactory extends Remote {
	public boolean newConfernce(String name, String password) throws RemoteException;
}
