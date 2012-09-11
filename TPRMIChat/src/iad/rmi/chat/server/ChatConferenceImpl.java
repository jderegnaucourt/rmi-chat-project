package iad.rmi.chat.server;

import iad.rmi.chat.ChatMessage;
import iad.rmi.chat.client.ChatParticipant;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Set;

public class ChatConferenceImpl extends UnicastRemoteObject implements ChatConference {
	
	protected boolean _bRun;
	protected String _strDescription;
	protected HashMap<String, ChatParticipant> _vParticipants;
	private static final long serialVersionUID = 1L;

	protected ChatConferenceImpl() throws RemoteException {
		super();
		_vParticipants = new HashMap<String, ChatParticipant>();
	}	
	
	public void listParticipants() throws RemoteException {
		for(ChatParticipant p : _vParticipants.values()) {
			System.out.println(p.getName());
		}
	}

	@Override
	public String getDescription() throws RemoteException {
		return _strDescription;
	}

	@Override
	public boolean isStarted() throws RemoteException {
		return _bRun;
	}

	@Override
	public void addParticipant(ChatParticipant p) throws RemoteException {
		if(_vParticipants.isEmpty()) start();
		_vParticipants.put(p.getName(), p);
		broadcast(new ChatMessage("system", p.getName()+" has joined the room"));
		p.process(new ChatMessage("system", p.getName()+" you have joined a room with "+_vParticipants.size()+" participants"));
	}

	@Override
	public void removeParticipant(ChatParticipant p) throws RemoteException {
		_vParticipants.remove(p.getName());
		broadcast(new ChatMessage("system", p.getName()+" has left the room"));
	}

	@Override
	public void broadcast(ChatMessage message) throws RemoteException {
		if(_bRun) {
			for(ChatParticipant p : _vParticipants.values()) {
				// if(!message.getEmitter().matches(p.getName())) p.process(message);
				p.process(message);
			}
		}	
	}

	@Override
	public void start() throws RemoteException {
		_bRun = true;
		broadcast(new ChatMessage("Info", "the conversation has started"));
	}

	@Override
	public void stop() throws RemoteException {
		_bRun = false;
		broadcast(new ChatMessage("Info", "the conversation is now closed"));
	}

}
