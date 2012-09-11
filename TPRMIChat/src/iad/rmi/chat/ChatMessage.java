package iad.rmi.chat;

import java.io.Serializable;

public class ChatMessage implements Serializable {
	protected String emitter;
	protected String content;
	
	public ChatMessage(String e, String c) {
		emitter = e;
		content = c;
	}

	public String getEmitter() {
		return emitter;
	}

	public String getContent() {
		return content;
	}
}
