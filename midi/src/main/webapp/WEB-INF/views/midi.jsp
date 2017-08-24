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
	<a href="#">(1)한마디 재생</a>
	<br>
	<a href="#">(2)한마디 재생</a>
	<br>
	<br>
	<button id="down">다운로드</button>
	<br><br><br>
	<div id="waveform"></div>
<script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/wavesurfer.js/1.2.3/wavesurfer.min.js"></script>
<script>
	
	var wavesurfer = WaveSurfer.create({
		container: '#waveform',
		waveColor: 'rgba(211, 211, 211, 0.48)',
		progressColor: '#ffffff'
	});
	var path = "${p}"+"${filename}"+".mp3";
	wavesurfer.load(path);
	wavesurfer.on('error',function(){
		alert("파일이 존재하지 않습니다^^ 올바른경로로 접속하였는지 다시한번 확인해주세요~");
		// ==> 추가이벤트 입력(리다이렉트, 창을 종료?, 버튼 비활성화 등등)
	});
	
	function downMp3(){
		window.open("../res/download.do?fe=${enc}");
	}
	
	$(function(){
		$("#down").on('click',function(){
			downMp3();
		});
	})
</script>
</body>
</html>
