<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	  	<script type='text/javascript' src="//code.jquery.com/jquery.min.js"></script>
  		<script type='text/javascript' src='//www.midijs.net/lib/midi.js'></script>
</head>
<body>

<form action="/midi/res/a" method="GET">
<input type="text" name="filename">
<input type="submit" value="submit">
</form>




</body>
</html>
