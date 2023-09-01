package com.texoit.gra.test;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.texoit.gra.util.Utils;

public class TestCargaArqDb {
    public static void main(String[] args) throws SQLException {
    	try {
	        String jdbcURL = "jdbc:h2:mem:test"; 
	        Connection connection = DriverManager.getConnection(jdbcURL); 
	        System.out.println("Conectado ao Banco de dados.");
	        
	        String sql = "drop table if exists movie";
	        Statement statement = connection.createStatement();
	        statement.execute(sql);         
	        System.out.println("Tabela movie apagada.");
	        
	        sql = "Create table movie (seq int primary key, no_year integer, title varchar(200), studios varchar(200), producer varchar(200), winner character(3))";
	        statement = connection.createStatement();
	        statement.execute(sql);         
	        System.out.println("Tabela movie criada.");
	         
	        String insertQuery = "insert into movie (seq, no_year, title, studios, producer, winner) values (?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	        
	        String csvFilePath = "C:\\Temp\\movielist.csv";
	        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
	        CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFilePath)).withCSVParser(csvParser).withSkipLines(1).build();
	        
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
		            preparedStatement.setInt(1, seq);
		            preparedStatement.setInt(2, no_year);
		            preparedStatement.setString(3, title.trim());
		            preparedStatement.setString(4, studios.trim());	            
		            preparedStatement.setString(5, producer.trim());	            
		            preparedStatement.setString(6, winner.trim());
		            
		            preparedStatement.executeUpdate();
	            }
	        }
	
	        System.out.println("CVS carregado com sucesso.");	
	        
	        sql = "select * from movie";	        
	        statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	 
	        int count = 0; 
	        while (resultSet.next()) {
	            count++;
	 
	            seq = resultSet.getInt("seq");
	            Integer year = resultSet.getInt("no_year");
	            String title = resultSet.getString("title");
	            String studios = resultSet.getString("studios");
	            String producers = resultSet.getString("producer");
	            String winner = (resultSet.getString("winner"));
	            
	            System.out.println("Movie #" + count + ": " + seq + "; " + "; " + year + "; " + title + "; " + studios + "; " + producers + "; " + (Utils.ehVazio(winner) ? "No" : winner));
	        }
	        
	        preparedStatement.close();
	        csvReader.close();
	        connection.close(); 
    	} catch (Exception e) {
            e.printStackTrace();
        }
    }
}
