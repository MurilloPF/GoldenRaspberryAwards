package com.texoit.gra.ws;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.texoit.gra.service.MovieService;
import com.texoit.gra.vo.ProdutorPremioVo;

@Path("/")
public class PioresProdutoresService {
	@GET
	@Path("/piores/produtores")
	@Produces("application/json")
	public String getProdutores() {
	   String pattern = "{\"min\":\"%s\", \"max\":\"%s\"}";
	   
	   MovieService service = MovieService.getInstance();
	   
	   try {
		   Integer minIntervalo = service.getMinIntervalo();
		   Integer maxIntervalo = service.getMaxIntervalo();
		   
		   Collection<ProdutorPremioVo> listaMenorIntervalo = service.listarProdutoresPremiadosMenorIntervalo();
		   Collection<ProdutorPremioVo> listaMaiorIntervalo = service.listarProdutoresPremiadosMaiorIntervalo();
		   
		   return String.format(pattern, minIntervalo, maxIntervalo);   
	   } catch (Exception e) {
		   e.printStackTrace();
		   
		   return e.getMessage();
	   }
	}
}
