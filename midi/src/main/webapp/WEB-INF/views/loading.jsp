<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>file converting</title>
	<script type='text/javascript'src="https://code.jquery.com/jquery.min.js"></script>
	<style>
	.grad{
		text-align: center;
		width: 100%;
		height: 24em;
		background-color:#69349c;
		-webkit-animation-name: example; /* Safari 4.0 - 8.0 */
		-webkit-animation-duration: 2.5s; /* Safari 4.0 - 8.0 */
		animation-name: example;
		animation-duration: 2s;
		animation-iteration-count: infinite;
		animation-direction: alternate;
	}
	@-webkit-keyframes example {
		from {background-color: #69349c;}
		to {background-color: #d22f7c;}
	}

	@keyframes example {
		from {background-color: #69349c;}
		to {background-color: #d22f7c;}
	}

	</style>
</head>
<body>
<div class="grad">
<img src="${pageContext.servletContext.contextPath}/resources/images/801.gif" style="padding-top:10em;">
</div>
<script>
$(function(){
	$.ajax({
		type : 'post',
		url : '/midi/convert.do',
		data : {
			'i' : "${img}",
			'm' : "${mus}"			
		},
		dataType : 'json',
		success : function(data){
			console.log(data);
			if(data.code == 0){
				alert('파일 변환 완료');
				//결과페이지 유투브 업로드 완료
				//location.href="t2?ytbID="+data.id;
			}else{
				alert("에러가 발생하였습니다.");
				//window.close();	
			}
		},
		error : function(){
			alert("error!!!");
		}	
	});
})

</script>
</body>
</html>
