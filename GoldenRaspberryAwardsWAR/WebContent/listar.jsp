<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="com.texoit.gra.vo.*" %>
<%@ page import="java.util.*" %>

<jsp:useBean id="lista" scope="request" type="java.util.Collection" />

<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Filmes</title>
</head>
<body>
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
            Collection<MovieVo> movies = (Collection<MovieVo>) request.getAttribute("lista");
            for (MovieVo movie : movies) {
        %>
        <tr>
            <td><%= movie.getSeq() %></td>
            <td><%= movie.getAno() %></td>
            <td><%= movie.getTitle() %></td>
            <td><%= movie.getStudios() %></td>
            <td><%= movie.getProducer()%></td>
            <td><%= movie.getWinner() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>