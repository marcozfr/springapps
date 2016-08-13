<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1><s:message code="spittr.welcome" /></h1>
    
    <s:url value="/spitter/register" var="register"/>
    <s:url value="/spittles" var="spittles">
    	<s:param name="max" value="60"></s:param>
    	<s:param name="count" value="20"></s:param>
    </s:url>
	
    <a href="${spittles}">Spittles</a> | 
    <a href="${register}">Register</a>
  </body>
</html>
