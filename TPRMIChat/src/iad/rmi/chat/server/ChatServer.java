package iad.rmi.chat.server;

import iad.rmi.chat.client.ChatParticipantImpl;

import java.rmi.registry.*;
import java.rmi.*;

public class ChatServer {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	
	public static void main(String[] args) throws Exception {
		try {
			System.out.println("Creating ChatConference");
			ChatConference chatConference;
			chatConference = new ChatConferenceImpl();
			chatConference.start();
			
			
			Registry reg = LocateRegistry.createRegistry(1099);
			System.out.println("Server is ready");
			reg.rebind("ChatConference", chatConference);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
