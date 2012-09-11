package iad.rmi.chat.client;

import iad.rmi.chat.ChatMessage;
import iad.rmi.chat.server.ChatConference;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatParticipant extends Remote {

	public abstract boolean join(ChatConference conference) throws RemoteException;

	public abstract void leave(ChatConference conference) throws RemoteException;

	public abstract void send(String txt) throws RemoteException;

	public abstract void process(ChatMessage msg) throws RemoteException;

	public abstract boolean isConnected() throws RemoteException;

	public String getName() throws RemoteException;

	public ChatMessage next() throws RemoteException;
}