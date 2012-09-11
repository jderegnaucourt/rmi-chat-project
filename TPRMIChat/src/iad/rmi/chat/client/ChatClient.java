package iad.rmi.chat.client;

import iad.rmi.chat.server.ChatConference;

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
		myUser = new ChatParticipantImpl("roger");
		ChatConference chatConference = (ChatConference) java.rmi.Naming.lookup("//127.0.0.1:1099/ChatConference");

		ChatClientConsole consoleUsed = new ChatClientConsole(chatConference, myUser);
		consoleUsed.run();
	}

}
