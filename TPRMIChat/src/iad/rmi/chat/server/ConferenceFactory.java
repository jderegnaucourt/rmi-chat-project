package iad.rmi.chat.server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConferenceFactory extends Remote {
	public boolean newConference(String name, String password) throws RemoteException;
}
