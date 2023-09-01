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
        	seq++;
        	
        	movie.setSeq(seq);
        	movie.setAno(Integer.parseInt(nextLine[0]));
        	movie.setTitle(nextLine[1]);
        	movie.setStudios(nextLine[2]);
        	movie.setProducers(nextLine[3]);
        	movie.setWinner(nextLine[4]);        	
          
            dao.inserirTabela(movie);
		}
	}
	
	public Collection<MovieVo> listarTabela() throws Exception {
		MovieDao dao = MovieDao.getInstance();
		
		return dao.listarTabela();
	}
}
