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
	private static Connection connection;
	private static MovieDao INSTANCE;

	private MovieDao() throws Exception {
		
	}

	public static MovieDao getInstance() throws Exception {
		if (INSTANCE==null) {
			INSTANCE = new MovieDao();			
		}
		setConnection();
		return INSTANCE;
	}
	
	public static void setConnection() throws Exception {
		if (connection == null) {
	        String jdbcURL = "jdbc:h2:~/test"; 
	    	Class.forName ("org.h2.Driver"); 
	    	connection = DriverManager.getConnection (jdbcURL, "sa",""); 
		} 
	}
	
	public void criarTabela() throws Exception {
        String sql = "drop table if exists movie";
        Statement statement = connection.createStatement();
        statement.execute(sql);         
        System.out.println("Tabela movie apagada.");
        
        sql = "Create table movie (seq int primary key, no_year integer, title varchar(200), studios varchar(200), producer varchar(200), winner character(3))";
        statement = connection.createStatement();
        statement.execute(sql);         
        System.out.println("Tabela movie criada.");
        
        statement.close();
    }
    
    public void limparTabela() throws Exception {
        String deleteQuery = "delete from movie";
        Statement  statement = connection.createStatement();
        
        statement.executeUpdate(deleteQuery);
        
        connection.commit();
        
        statement.close();
    }
    
    public void inserirTabela(MovieVo movie) throws Exception {
        String insertQuery = "insert into movie (seq, no_year, title, studios, producer, winner) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        
        preparedStatement.setInt(1, movie.getSeq());
        preparedStatement.setInt(2, movie.getAno());
        preparedStatement.setString(3, movie.getTitle());
        preparedStatement.setString(4, movie.getStudios());
        preparedStatement.setString(5, movie.getProducer());
        preparedStatement.setString(6, movie.getWinner());
        
        preparedStatement.executeUpdate();
        
        connection.commit();
        
        preparedStatement.close();
    }
    
    public Collection<MovieVo> listarTabela() throws Exception {
    	ArrayList<MovieVo> lista = new ArrayList<MovieVo>();
    	
    	String  sql = "select * from movie";	        
        Statement  statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {        	
            MovieVo movie = new MovieVo();

            movie.setSeq(resultSet.getInt("seq"));
        	movie.setAno(resultSet.getInt("no_year"));
        	movie.setTitle( resultSet.getString("title"));
        	movie.setStudios(resultSet.getString("studios"));
        	movie.setProducer(resultSet.getString("producer"));
        	movie.setWinner(resultSet.getString("winner"));
        	
        	lista.add(movie);
        }
        
        statement.close();
        
        return lista;
    }
}
