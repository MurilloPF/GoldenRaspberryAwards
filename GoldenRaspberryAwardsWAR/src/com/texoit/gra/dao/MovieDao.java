package com.texoit.gra.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.texoit.gra.vo.MovieVo;
import com.texoit.gra.vo.ProdutorPremioVo;

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
        preparedStatement.setString(3, movie.getTitle().trim());
        preparedStatement.setString(4, movie.getStudios().trim());
        preparedStatement.setString(5, movie.getProducer().trim());
        preparedStatement.setString(6, movie.getWinner().trim().toLowerCase());
        
        preparedStatement.executeUpdate();
        
        connection.commit();
        
        preparedStatement.close();
    }
    
    public Collection<MovieVo> listarTabela() throws Exception {
    	ArrayList<MovieVo> lista = new ArrayList<MovieVo>();
    	
    	String  sql = "select * from movie order by seq";
    	
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
    
    public Collection<ProdutorPremioVo> listarProdutoresPremiados() throws Exception {
    	ArrayList<ProdutorPremioVo> lista = new ArrayList<ProdutorPremioVo>();
    	
    	String  sql = "select t3.producer, t3.no_year, t3.prox_ano, (t3.prox_ano - t3.no_year) intervalo "  
    			     + " from (select t1.producer, t1.no_year, (select aux.no_year from movie aux where aux.producer = t1.producer and aux.no_year > t1.no_year and aux.winner = 'yes' and rownum=1 order by aux.no_year) prox_ano from movie t1, (select producer, count(no_year) from movie where winner = 'yes' group by producer having count(no_year) > 1) t2 where t1.producer = t2.producer and t1.winner = 'yes') t3 "
    		         + "where t3.prox_ano is not null order by intervalo";	
    	
        Statement  statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {        	
        	ProdutorPremioVo vo = new ProdutorPremioVo();

            vo.setProducer(resultSet.getString("producer"));
            vo.setPrimeiroAno(resultSet.getInt("no_year"));
            vo.setSegundoAno(resultSet.getInt("prox_ano"));
            vo.setIntervalo(resultSet.getInt("intervalo"));
        	
        	lista.add(vo);
        }
        
        statement.close();
        
        return lista;
    }
    
    public Integer getMinIntervalo() throws Exception {
    	Integer minIntervalo = 0;
    	
    	String  sql = "select min(intervalo) minintervalo from "
    			     + "(select t3.producer, t3.no_year, t3.prox_ano, (t3.prox_ano - t3.no_year) intervalo "  
    			     + " from (select t1.producer, t1.no_year, (select aux.no_year from movie aux where aux.producer = t1.producer and aux.no_year > t1.no_year and aux.winner = 'yes' and rownum=1 order by aux.no_year) prox_ano from movie t1, (select producer, count(no_year) from movie where winner = 'yes' group by producer having count(no_year) > 1) t2 where t1.producer = t2.producer and t1.winner = 'yes') t3 "
    		         + "where t3.prox_ano is not null order by intervalo)";	 
    	
        Statement  statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        if (resultSet != null) {  
        	resultSet.next();
        	minIntervalo = resultSet.getInt("minintervalo");
        } 
        
        statement.close();
        
        return minIntervalo;
    }
    
    public Integer getMaxIntervalo() throws Exception {
    	Integer maxIntervalo = 0;
    	
    	String  sql = "select max(intervalo) maxintervalo from "
    			     + "(select t3.producer, t3.no_year, t3.prox_ano, (t3.prox_ano - t3.no_year) intervalo "  
    			     + " from (select t1.producer, t1.no_year, (select aux.no_year from movie aux where aux.producer = t1.producer and aux.no_year > t1.no_year and aux.winner = 'yes' and rownum=1 order by aux.no_year) prox_ano from movie t1, (select producer, count(no_year) from movie where winner = 'yes' group by producer having count(no_year) > 1) t2 where t1.producer = t2.producer and t1.winner = 'yes') t3 "
    		         + "where t3.prox_ano is not null order by intervalo)";	   
    	
        Statement  statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        if (resultSet != null) {  
        	resultSet.next();
        	maxIntervalo = resultSet.getInt("maxintervalo");
        } 
        
        statement.close();
        
        return maxIntervalo;
    }
}
