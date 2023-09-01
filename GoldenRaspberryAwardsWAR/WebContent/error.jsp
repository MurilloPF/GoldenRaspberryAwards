<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page isErrorPage="true" %>
<%@ page import = "java.io.*" %>
<% String message = (String) request.getAttribute("message");%>
<% Throwable e = (Throwable)request.getAttribute("obj_excecao");%>

<HTML>
<HEAD>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Página de Erro</TITLE>
</HEAD>
<BODY aLink=#ff0000 bgColor=#ffffff link=#000066 text=#000000 vLink=#666666>
<table border=0 cellpadding=0 cellspacing=0 width="100%">
<tbody>
<tr>
<td width=10>&nbsp;</td>
<td class=titulo colspan=2>Erro</td>
<td width=25>&nbsp;</td>
</tr>
<tr>
<td width=10>&nbsp;</td>
<td colspan=2>&nbsp;</td>
<td width=25>&nbsp;</td>
</tr>
<tr>
<td width=10>&nbsp;</td>
<td align=Justify class=errorMsg colspan=2 valign=middle>
<p><strong>
<% if ( message != null) { %>
      <font class="textob">Class Name: </font><%=exception.getClass().getName()%> <br><br>
      <pre>Message: <%=message%></pre>
<% } else if ( e != null) { %>
      <font class="textob">Class Name: </font><%=e.getClass().getName()%> <br><br>
      <pre><%=e.getMessage()%></pre>
      <h3> <font class="subtit">Stack Trace para Depuração (Temporário)</font> </h3>
	  <pre><%StringWriter sw = new StringWriter();
         PrintWriter pw = new PrintWriter(sw);
         e.printStackTrace(pw);
         out.println(sw);%>
	  </pre>
<% } else  if ( exception != null) { %>
      <font class="textob">Class Name: </font><%=exception.getClass().getName()%> <br><br>
      <pre><%=exception.getMessage()%></pre>
      <h3> Stack Trace para Depuração (Temporário) </h3>
	  <pre><%StringWriter sw = new StringWriter();
	         PrintWriter pw = new PrintWriter(sw);
	         exception.printStackTrace(pw);
	         out.println(sw);%>
	  </pre>
<% } else { %>
    Erro na Montagem da p&aacute;gina.
<% } %>
</strong></p>
</td>
<td width=25>&nbsp;</td>
</tr>
</tbody>
</table>
<BR>
</BODY>
</HTML>