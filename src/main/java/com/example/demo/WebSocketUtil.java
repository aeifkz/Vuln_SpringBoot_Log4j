package com.example.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.Session;


public class WebSocketUtil {

	private static final Map<String, Session> ONLINE_SESSION = new ConcurrentHashMap<>();

	public static void addSession(String userNick, Session session) {
		ONLINE_SESSION.put(userNick, session);
	}

	public static void remoteSession(String userNick) {
		ONLINE_SESSION.remove(userNick);
	}

	public static void sendMessage(Session session, String message) {
		if (session == null) {
			return;
		}
		Async async = session.getAsyncRemote();
		async.sendText(message);
	}

	public static void sendMessageForAll(String message) {
		ONLINE_SESSION.forEach((sessionId, session) -> sendMessage(session, message));
	}
	
}
