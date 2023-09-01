<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="com.texoit.gra.vo.*" %>
<%@ page import="java.util.*" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Filmes</title>
</head>
<body>
    <br/>
    <h1>Listagem de Menor Intervalo Premiação</h1>
    <table border="1">
        <tr>
            <th>Producer</th>
            <th>Ano</th>
            <th>Ano</th>
            <th>Intervalo</th>
        </tr>
        <%
            Collection<ProdutorPremioVo> listaMenorIntervalo = (Collection<ProdutorPremioVo>) request.getAttribute("listaMenorIntervalo");
            for (ProdutorPremioVo produtor : listaMenorIntervalo) {
        %>
        <tr>
            <td><%= produtor.getProducer()%></td>
            <td><%= produtor.getPrimeiroAno()%></td>
            <td><%= produtor.getSegundoAno()%></td>
            <td><%= produtor.getIntervalo()%></td>
        </tr>
        <% } %>
    </table>
    <br/>
    <h1>Listagem de Maior Intervalo Premiação</h1>
    <table border="1">
        <tr>
            <th>Producer</th>
            <th>Ano</th>
            <th>Ano</th>
            <th>Intervalo</th>
        </tr>
        <%
            Collection<ProdutorPremioVo> listaMaiorIntervalo = (Collection<ProdutorPremioVo>) request.getAttribute("listaMaiorIntervalo");
            for (ProdutorPremioVo produtor : listaMaiorIntervalo) {
        %>
        <tr>
            <td><%= produtor.getProducer()%></td>
            <td><%= produtor.getPrimeiroAno()%></td>
            <td><%= produtor.getSegundoAno()%></td>
            <td><%= produtor.getIntervalo()%></td>
        </tr>
        <% } %>
    </table>    
    <br/>
    <h1>Listagem de Filmes</h1>
    <table border="1">
        <tr>
            <th>Seq</th>
            <th>Ano</th>
            <th>Title</th>
            <th>Studios</th>
            <th>Producer</th>
            <th>Winner</th>
        </tr>
        <%
            Collection<MovieVo> listaFilmes = (Collection<MovieVo>) request.getAttribute("listaFilmes");
            for (MovieVo movie : listaFilmes) {
        %>
        <tr>
            <td><%= movie.getSeq() %></td>
            <td><%= movie.getAno() %></td>
            <td><%= movie.getTitle() %></td>
            <td><%= movie.getStudios() %></td>
            <td><%= movie.getProducer()%></td>
            <td><%= movie.getWinner() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>