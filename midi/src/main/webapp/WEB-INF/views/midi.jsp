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
<a href="#" onclick="musicOn('testmidi.mid');">(1)한마디 재생</a><br>
<a href="#" onclick="musicOn('testmidi2.mid');">(2)한마디 재생</a><br>
<br><br><br>
합친파일 재생?
</body>
<script>
	var midiDir = "/midi/resources/midi/";
	function musicOn(filename){
		MIDIjs.play(midiDir+filename); //파일명
	}

</script>
</html>
