package com.edut.springboot.vedio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		resp.getWriter().println(resp.getContentType()+": 你好 我的 - Servlet <br> ");
		resp.setContentType("text/html");
		resp.getWriter().println(resp.getContentType()+": 你好 我的 - Servlet <br> ");
		log.debug("my servlet service ... " + req.getRequestURI() + ":"+req.getServerPort());
	};
	
}
