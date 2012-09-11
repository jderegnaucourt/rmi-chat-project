package iad.rmi.chat.client;

import iad.rmi.chat.ChatMessage;
import iad.rmi.chat.server.ChatConference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ChatClientConsole extends Thread {
	
	protected ChatConference conference;
	protected ChatParticipant participant;
	protected BufferedReader reader; 
	
	public ChatClientConsole(ChatConference c, ChatParticipant p) {
		conference = c;
		participant = p;
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void run() {
		String line; 
		
	    while(true) {
	       try {
				printNextMessage();	
			    line = reader.readLine();
			    if (line.equals("/stop")) {
			    	participant.leave(conference);
					reader.close(); 
					System.exit(0);
			    }
			    else if (line.equals("/join")) {
			    	participant.join(conference);
			    }
			    else if (line.equals("/list")) {
			    	listChatRooms();
			    }
			    else if (line.equals("/leave")) {
			    	participant.leave(conference);
			    }
			    else {
			    	participant.send(line);
			    }
		   } catch (Exception e) {
				e.printStackTrace();
		   }
	    }
	}
	
	private void listChatRooms() {
		try {
			String[] noms = Naming.list("rmi://127.0.0.1:1099");
			int l = noms.length;
			for(int i = 0; i<l ; i++) {
				System.out.println(noms[i]);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void printNextMessage() throws IOException {
		while (! reader.ready()) {
			   ChatMessage msg = null;
		        try {
					msg = participant.next();
		    	}
		    	catch(RemoteException e) {
		    		e.printStackTrace();
		    	}
				if (msg != null) {
					if(msg.getEmitter().matches("system")) System.out.println("*** "  + msg.getContent());
					else System.out.println("["+msg.getEmitter() + "] "  + msg.getContent());
				}
		}
	}
}

