<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Home</title>
	<script type='text/javascript'src="https://code.jquery.com/jquery.min.js"></script>
	<script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/wavesurfer.js/1.2.3/wavesurfer.min.js"></script>
</head>
<body>
	<a href="#" onclick="musicOn('testmidi.mid');">(1)한마디 재생</a>
	<br>
	<a href="#" onclick="musicOn('testmidi2.mid');">(2)한마디 재생</a>
	<br>
	<br>
	<a href="test1.mid">다운로드</a>
	<br>
	<br> 합친파일 재생? ${file}
</body>
<script>
	var wavesurfer = WaveSurfer.create({
		container : '#waveform',
		waveColor : '#ffffff',
		progressColor : 'purple'
	});

	wavesurfer.load('iu.mp3');

	var slider = document.querySelector('#slider');

	slider.oninput = function() {
		var zoomLevel = Number(slider.value);
		wavesurfer.zoom(zoomLevel);
	};
</script>
</html>
