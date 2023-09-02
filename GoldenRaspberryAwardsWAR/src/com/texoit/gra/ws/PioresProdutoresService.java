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
	@Path("/piores/maxmin")
	@Produces("application/json")
	public String getMinMax() {
		String pattern = "{\"min\":\"%s\", \"max\":\"%s\"}";
	   
	    MovieService service = MovieService.getInstance();
	   
	    try {
		    Integer minIntervalo = service.getMinIntervalo();
		    Integer maxIntervalo = service.getMaxIntervalo();
		   
		    return String.format(pattern, minIntervalo, maxIntervalo);   
	    } catch (Exception e) {
	    	e.printStackTrace();
		   
		    return "Erro na execução do Webservice. Causa: " + e.getMessage();
	    }
	}
	
	@GET
	@Path("/piores/produtores")
	@Produces("application/json")
	public String getProdutores() {
		String pattern = "{\"producer\":\"%s\", \"interval\":%s, \"previousWin\":%s, \"followingWin\":%s}";
		
	    StringBuffer sb = new StringBuffer("{\"min\":[");
	   
	    MovieService service = MovieService.getInstance();
	   
	    try {
		   Collection<ProdutorPremioVo> listaMenorIntervalo = service.listarProdutoresPremiadosMenorIntervalo();
		   for (ProdutorPremioVo produtor : listaMenorIntervalo) {
			   sb.append(String.format(pattern, produtor.getProducer(), produtor.getIntervalo(), produtor.getPrimeiroAno(), produtor.getSegundoAno())).append(", ");
		   }
		   
		   if (listaMenorIntervalo != null && !listaMenorIntervalo.isEmpty()) sb.delete(sb.length()-2,sb.length());
		   sb.append("], \"max\": [");
		   
		   Collection<ProdutorPremioVo> listaMaiorIntervalo = service.listarProdutoresPremiadosMaiorIntervalo();
		   for (ProdutorPremioVo produtor : listaMaiorIntervalo) {
			   sb.append(String.format(pattern, produtor.getProducer(), produtor.getIntervalo(), produtor.getPrimeiroAno(), produtor.getSegundoAno())).append(", ");
		   }
		   	   
		   if (listaMaiorIntervalo != null && !listaMaiorIntervalo.isEmpty()) sb.delete(sb.length()-2,sb.length());
		   sb.append("]}");
		   
		   return sb.toString();   
	    } catch (Exception e) {
		   e.printStackTrace();
		   
		   return "Erro na execução do Webservice. Causa: " + e.getMessage();
	    }
	}
}
