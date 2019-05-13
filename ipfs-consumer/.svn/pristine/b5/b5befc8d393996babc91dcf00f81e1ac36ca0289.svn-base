package com.stys.ipfs.config;

import java.util.HashMap;

import org.apache.shiro.session.Session;

/***
 * 没有使用
 * @author Administrator
 *
 */
public class MySessionContext {
	private static MySessionContext instance;
	private HashMap<String, Session> sessionMap;

	private MySessionContext() {
		sessionMap = new HashMap<String, Session>();
	}

	public static MySessionContext getInstance() {
		if (instance == null) {
			instance = new MySessionContext(); 
		}
		return instance;
	}

	public synchronized void addSession(Session session) {
		if (session != null) {
			sessionMap.put(session.getId().toString(), session);
		}
	}

	public synchronized void delSession(Session session) {
		if (session != null) {
			sessionMap.remove(session.getId().toString());
		}
	}

	public synchronized Session getSession(String sessionID) {
		if (sessionID == null) {
			return null;
		}
		return sessionMap.get(sessionID);
	}

}