package com.texoit.gra.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.texoit.gra.service.MovieService;

public class GoldenRaspberryAwardsInit extends MainServlet {
	private static final long serialVersionUID = 1L;

	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
		MovieService service = MovieService.getInstance();
        
        service.carregarTabela();
        System.out.println("CVS carregado com sucesso.");
        
	    callPage(req, resp, "/GoldenRaspberryAwardsList");	    
	  }
}
