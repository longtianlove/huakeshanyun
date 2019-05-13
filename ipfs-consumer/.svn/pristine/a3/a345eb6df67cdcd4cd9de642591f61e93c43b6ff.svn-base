package com.stys.ipfs.config;

import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionListener implements org.apache.shiro.session.SessionListener {  

	private Logger logger = LoggerFactory.getLogger(this.getClass());
        private MySessionContext myc = MySessionContext.getInstance();  

		@Override
		public void onStart(Session session) {
			String sessionId = session.getId().toString();
			logger.info("[addSession]:"+sessionId); 
		    myc.addSession(session); 
			
		}

		@Override
		public void onStop(Session session) {
			 myc.delSession(session);  
			
		}

		@Override
		public void onExpiration(Session session) {
			 myc.delSession(session);  
		}  

    }  
