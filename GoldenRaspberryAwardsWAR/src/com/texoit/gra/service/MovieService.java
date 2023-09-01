package com.texoit.gra.service;

import java.io.FileReader;
import java.util.Collection;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.texoit.gra.dao.MovieDao;
import com.texoit.gra.vo.MovieVo;

public class MovieService {
	private static MovieService INSTANCE;

	private MovieService() {
	}

	public static MovieService getInstance() {
		if (INSTANCE==null)	INSTANCE = new MovieService();
		
		return INSTANCE;
	}
	
	public void criarTabela() throws Exception {
		MovieDao dao = MovieDao.getInstance();
		
		dao.criarTabela();
	}
	
	public void limparTabela() throws Exception {
		MovieDao dao = MovieDao.getInstance();
		
		dao.limparTabela();
	}
	
	public void inserirTabela(MovieVo movie) throws Exception {
		MovieDao dao = MovieDao.getInstance();
		

		dao.inserirTabela(movie);
	}
	
	public void carregarTabela() throws Exception {
		MovieDao dao = MovieDao.getInstance();
		
        String csvFilePath = "C:\\Temp\\movielist.csv";
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFilePath)).withCSVParser(csvParser).withSkipLines(1).build();
        
        MovieVo movie = new MovieVo();
        String[] nextLine;
        int seq = 0; 
        while ((nextLine = csvReader.readNext()) != null) {
            int no_year = Integer.parseInt(nextLine[0]);
            String title = nextLine[1];
            String studios = nextLine[2];
            String producers = nextLine[3];
            String winner = nextLine[4];
            
            producers = producers.replaceAll(" And ", ",").replaceAll(" and ", ",").replaceAll(",,", ", ");	            
            String[] producersArray = producers.split(",");
            for (String producer : producersArray) {
            	seq++;
            	movie.setSeq(seq);
            	movie.setAno(no_year);
            	movie.setTitle(title);
            	movie.setStudios(studios);
            	movie.setProducer(producer);
            	movie.setWinner(winner);        	
              
                dao.inserirTabela(movie);
            }
		}
	}
	
	public Collection<MovieVo> listarTabela() throws Exception {
		MovieDao dao = MovieDao.getInstance();
		
		return dao.listarTabela();
	}
}
