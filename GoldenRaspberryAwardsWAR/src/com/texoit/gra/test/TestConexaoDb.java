package com.texoit.gra.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConexaoDb {
    public static void main(String[] args) throws SQLException {
        String jdbcURL = "jdbc:h2:mem:test"; 
        Connection connection = DriverManager.getConnection(jdbcURL); 
        System.out.println("Conectado ao Banco de dados.");
        
        String sql = "drop table if exists movie";
        Statement statement = connection.createStatement();
        statement.execute(sql);         
        System.out.println("Tabela movie apagada.");
        
        sql = "Create table movie (seq int primary key, no_year integer, title varchar(200), studios varchar(200), producers varchar(200), winner character(3))";
        statement = connection.createStatement();
        statement.execute(sql);         
        System.out.println("Tabela movie criada.");
         
        sql = "insert into movie (seq, no_year, title, studios, producers, winner) values (1, '1942', 'E o vento Levou', 'MGM', 'Max', 'yes')";
        int rows = statement.executeUpdate(sql);         
        if (rows > 0) {
            System.out.println("Nova linha inserida em movie.");
        }
        
        sql = "SELECT * FROM movie";        
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
 
        int count = 0; 
        while (resultSet.next()) {
            count++;
 
            int seq = resultSet.getInt("seq");
            String title = resultSet.getString("title");
            System.out.println("Movie #" + count + ": " + seq + ", " + title);
        }
        
        connection.close(); 
    }
}
