package iad.rmi.chat.client;

import iad.rmi.chat.ChatMessage;
import iad.rmi.chat.server.ChatConference;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class ChatParticipantImpl extends UnicastRemoteObject implements ChatParticipant {

	private static final long serialVersionUID = 1L;
	protected boolean _bIsInAConference;
	protected String _strUsername;
	protected ChatConference currentConference;
	protected BlockingQueue<ChatMessage> messageQueue;
	
	public ChatParticipantImpl(String userName) throws RemoteException {
		super();
		_bIsInAConference = false;
		currentConference = null;
		messageQueue = new PriorityBlockingQueue<ChatMessage>();
		_strUsername = userName;
	}

	@Override
	public boolean join(ChatConference conference) throws RemoteException {
		if(isConnected()) {
			return false;
		}
		else {
			currentConference = conference;
			conference.addParticipant(this);
			_bIsInAConference = true;
		}
		return true;
	}

	@Override
	public void leave(ChatConference conference) throws RemoteException {
		conference.removeParticipant(this);
		_bIsInAConference = false;
		currentConference = null;
	}

	@Override
	public void send(String txt) throws RemoteException {
		if(isConnected()) currentConference.broadcast(new ChatMessage(this.getName(), txt));
	}

	@Override
	public void process(ChatMessage msg) throws RemoteException {
		if(isConnected()) messageQueue.add(msg);
	}

	@Override
	public boolean isConnected() throws RemoteException {
		return _bIsInAConference;
	}

	@Override
	public String getName() throws RemoteException {
		return _strUsername;
	}

	@Override
	public ChatMessage next() throws RemoteException {
		return messageQueue.poll();
	}

}
