package iad.rmi.chat.server;

import iad.rmi.chat.ChatMessage;
import iad.rmi.chat.client.ChatParticipant;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatConference extends Remote {

	public String getDescription() throws RemoteException;
	
	public abstract boolean isStarted() throws RemoteException;

	public abstract void addParticipant(ChatParticipant p) throws RemoteException;

	public abstract void removeParticipant(ChatParticipant p) throws RemoteException;

	public abstract void broadcast(ChatMessage message) throws RemoteException;

	public abstract void start() throws RemoteException;

	public abstract void stop() throws RemoteException;

}