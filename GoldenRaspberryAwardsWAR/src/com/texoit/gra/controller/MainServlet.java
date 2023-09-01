package com.texoit.gra.controller; 

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.texoit.gra.service.MovieService;

public abstract class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	public void service(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
	    try {
	        Boolean tabelaCriada = (Boolean) getSession(request).getAttribute("tabelaCriada");

	        if (tabelaCriada == null || !tabelaCriada) {
		        MovieService service = MovieService.getInstance();
		        
		        service.criarTabela();
		        
		        getSession(request).setAttribute("tabelaCriada", true);
	    	}
	
	        doProcess(request, response);
	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	this.callErrorPage(request, response, e);
	    }
	}

	abstract protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception;

	protected void callPage(HttpServletRequest req, HttpServletResponse res, String pNomeJsp) throws Exception {
		getServletConfig().getServletContext().getRequestDispatcher(pNomeJsp).forward(req, res);
	}

	protected void callErrorPage(HttpServletRequest req, HttpServletResponse res, Throwable pExc) {
	    try {
	      req.setAttribute("obj_except", pExc);
	      getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
	    } catch (Exception e1) {
	      e1.printStackTrace();
	
	      req.setAttribute("message", "Erro na chamada da página de erro!");
	      try {
	        getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
	      } catch (Exception e2) {
	        e1.printStackTrace();
	      }
	    }
	}

  	protected HttpSession getSession(HttpServletRequest req) throws Exception {
	    HttpSession session = req.getSession(false);
	
	    if (session == null) {
	      session = req.getSession(true);
	      session.setMaxInactiveInterval(-1);
	    }
	
	    return session;
  	}

  	public final String getParameter(HttpServletRequest request, String name) {
	    String result = request.getParameter(name);
	
	    if (result == null) {
	      throw new IllegalArgumentException(name + "=null");
	    } else {
	      return result;
	    }
  	}

  	public final String getParameter(HttpServletRequest request, String name, String defaultValue) {
	    String result;
	
	    try {
	      result = getParameter(request, name);
	    } catch (IllegalArgumentException ignored) {
	      result = defaultValue;
	    }
	    
	    return result;
  	}
}
