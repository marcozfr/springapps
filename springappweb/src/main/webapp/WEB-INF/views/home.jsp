<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spittr</title>
<link rel="stylesheet" type="text/css" href="<c:url value="resources/styles.css" />">
</head>
<body>
	
	<a href="<c:url value="/spittles"/>">Spittles</a>
	<a href="<c:url value="/spitter/register"/>">Register</a>
	
</body>
</html>