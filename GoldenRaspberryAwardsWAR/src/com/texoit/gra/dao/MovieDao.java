package com.texoit.gra.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.texoit.gra.vo.MovieVo;

public class MovieDao {
	private Connection connection;
	private static MovieDao INSTANCE;

	private MovieDao() throws Exception {
		setConnection();
	};

	public static MovieDao getInstance() throws Exception {
		if (INSTANCE==null) {
			INSTANCE = new MovieDao();			
		}
		
		return INSTANCE;
	}
	
	public static Connection setConnection() throws Exception {
        String jdbcURL = "jdbc:h2:mem:test"; 
        return DriverManager.getConnection(jdbcURL); 
	}

	public void criarTabela() throws Exception {
        String sql = "drop table if exists movie";
        Statement statement = connection.createStatement();
        statement.execute(sql);         
        System.out.println("Tabela movie apagada.");
        
        sql = "Create table movie (seq int primary key, no_year integer, title varchar(200), studios varchar(200), producers varchar(200), winner character(3))";
        statement = connection.createStatement();
        statement.execute(sql);         
        System.out.println("Tabela movie criada.");
    }
    
    public void inserirTabela(MovieVo movie) throws Exception {
        String insertQuery = "insert into movie (seq, no_year, title, studios, producers, winner) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        
        preparedStatement.setInt(1, movie.getSeq());
        preparedStatement.setInt(2, movie.getAno());
        preparedStatement.setString(3, movie.getTitle());
        preparedStatement.setString(4, movie.getStudios());
        preparedStatement.setString(5, movie.getProducers());
        preparedStatement.setString(6, movie.getWinner());
        
        preparedStatement.executeUpdate();
    }
    
    public Collection<MovieVo> listarTabela() throws Exception {
    	ArrayList<MovieVo> lista = new ArrayList<MovieVo>();
    	
    	String  sql = "select * from movie";	        
        Statement  statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        MovieVo movie = new MovieVo();
        while (resultSet.next()) {
        	
        	movie.setSeq(resultSet.getInt("seq"));
        	movie.setAno(resultSet.getInt("no_year"));
        	movie.setTitle( resultSet.getString("title"));
        	movie.setStudios(resultSet.getString("studios"));
        	movie.setProducers(resultSet.getString("producers"));
        	movie.setWinner(resultSet.getString("winner"));
        }
        
        return lista;
    }
}
