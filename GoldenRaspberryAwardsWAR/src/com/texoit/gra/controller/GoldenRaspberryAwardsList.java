package com.texoit.gra.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.texoit.gra.service.MovieService;
import com.texoit.gra.vo.MovieVo;
import com.texoit.gra.vo.ProdutorPremioVo;

public class GoldenRaspberryAwardsList extends MainServlet {
	private static final long serialVersionUID = 1L;

	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		MovieService service = MovieService.getInstance();
		
		Collection<MovieVo> listaFilmes = service.listarTabela();
		Collection<ProdutorPremioVo> listaMenorIntervalo = service.listarProdutoresPremiadosMenorIntervalo();
		Collection<ProdutorPremioVo> listaMaiorIntervalo = service.listarProdutoresPremiadosMaiorIntervalo();
        
        req.setAttribute("listaFilmes", listaFilmes);
        req.setAttribute("listaMenorIntervalo", listaMenorIntervalo);
        req.setAttribute("listaMaiorIntervalo", listaMaiorIntervalo);
        
	    callPage(req, resp, "/listar.jsp");	    
	  }
}
