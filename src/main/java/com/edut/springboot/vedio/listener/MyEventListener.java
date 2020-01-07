package com.edut.springboot.vedio.listener;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyEventListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		log.debug("my listenrer listening ... session be created id="+se.getSession().getId());
		HttpSessionListener.super.sessionCreated(se);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		log.debug("my listenrer listening ... session be destroyed id="+se.getSession().getId());
		HttpSessionListener.super.sessionDestroyed(se);
	}
	
}
