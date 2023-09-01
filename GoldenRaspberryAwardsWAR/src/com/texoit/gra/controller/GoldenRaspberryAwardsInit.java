package com.texoit.gra.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.texoit.gra.service.MovieService;
import com.texoit.gra.util.Utils;
import com.texoit.gra.vo.MovieVo;

public class GoldenRaspberryAwardsInit extends MainServlet {
	private static final long serialVersionUID = 1L;

	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		MovieService service = MovieService.getInstance();
        
		service.limparTabela();
        service.carregarTabela();
        
        Collection<MovieVo> lista = service.listarTabela();		
		int count = 1;
        for (MovieVo movieVo : lista) {
            System.out.println("Movie #" + (count++) + ": " + movieVo.getSeq() + "; " + "; " + movieVo.getAno() + "; " + movieVo.getTitle() + "; " + movieVo.getStudios() + "; " + movieVo.getProducer() + "; " + (Utils.ehVazio(movieVo.getWinner()) ? "No" : movieVo.getWinner()));
        }
        
        System.out.println("CVS carregado com sucesso.");
        
	    callPage(req, resp, "/GoldenRaspberryAwardsList");	    
	  }
}
