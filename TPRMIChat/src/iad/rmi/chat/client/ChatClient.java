package iad.rmi.chat.client;
import iad.rmi.chat.server.ConferenceFactory;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


/**
 * @author jderegnaucourt
 * Test Class
 * Creates a client and a console client
 * then launch the console client
 */

public class ChatClient {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		ChatParticipant myUser;
		myUser = new ChatParticipantImpl(args[0]);
		ConferenceFactory cf = (ConferenceFactory) java.rmi.Naming.lookup("//172.27.161.160:1099/ChatConferenceFactory");
		
		ChatClientConsole consoleUsed = new ChatClientConsole(cf, myUser);
		consoleUsed.run();
	}

}
