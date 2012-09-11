package iad.rmi.chat.server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class ConferenceFactoryImpl extends UnicastRemoteObject implements ConferenceFactory {
	protected Registry _registry = null;
	protected String _password = "mypsd";
	
	protected ConferenceFactoryImpl(Registry reg) throws RemoteException {
		super();
		_registry = reg;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public boolean newConference(String name, String password)
			throws RemoteException {
		System.out.println("creating new conference on server side : "+name);
		if(password.matches(_password)) {
			ChatConference chatConference = new ChatConferenceImpl(name);
			chatConference.start();			
			System.out.println("conference "+name+" started.");
			_registry.rebind(name, chatConference);
			return true;
		}
		else return false;	
	}
}
