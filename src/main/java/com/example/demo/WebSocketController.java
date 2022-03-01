package com.example.demo;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint(value = "/WebSocketServer/{usernick}")
public class WebSocketController {
	
	private static final Logger logger = LogManager.getLogger("com.example.demo");

	@OnOpen
    public void onOpen(@PathParam(value = "usernick") String userNick, Session session) {
		
        System.out.println("session.getRequestURI():"+session.getRequestURI().getQuery());
        logger.info(session.getRequestURI().getQuery()) ;
        
        String message = "new member [" + userNick + "] !";
        System.out.println(message);
        WebSocketUtil.addSession(userNick, session);
        WebSocketUtil.sendMessageForAll(message);
        
    }
	
	@OnClose
	public void onClose(@PathParam(value = "usernick") String userNick, Session session) {
		String message = " member [" + userNick + "] quit room!";
		logger.info(message) ;
		System.out.println(message);		
		WebSocketUtil.remoteSession(userNick);
		WebSocketUtil.sendMessageForAll(message);
	}
	
	@OnMessage
	public void OnMessage(@PathParam(value = "usernick") String userNick, String message) {
		String info = "member [" + userNick + "] : " + message;
		logger.info(info) ;
		System.out.println(info);
		WebSocketUtil.sendMessageForAll(message);
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("error: " + throwable);
		logger.info("error: " + throwable) ;
		try {
			session.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		throwable.printStackTrace();
	}	
	
}
