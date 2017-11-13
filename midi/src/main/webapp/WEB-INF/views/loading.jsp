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
	<div>
		<img id="youtUpload" src="${pageContext.servletContext.contextPath}/resources/images/youtube_uploading.gif"  style="padding-top:10em; display:none;">
	</div>
	<div>
		<img id="i" src="${pageContext.servletContext.contextPath}/resources/images/801.gif" style="padding-top:10em;">
	</div>
</div>
<script>
function process(){
	$("#i").css("display","none");
	$("#youtUpload").fadeIn(2000);
}


function uploading(){
	$.ajax({
		type : 'post',
		url : '/midi/upload.do',
		data : {
			'v' : "${mus}"			
		},
		dataType : 'json',
		success : function(data){
			console.log(data);
			if(data.code == 0){
				//alert('업로드 완료');
				location.href="youtbs?id="+data.id;
			}else{
				alert("에러가 발생하였습니다.");
				window.close();	
			}
		},
		error : function(request,status,error){
			console.log("code:"+request.status);
			console.log("message:"+request.responseText);
			console.log("error:"+error);
		}	
	});
}

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
				process();
				uploading(); //유투브 업로드 진행
			}else{
				alert("에러가 발생하였습니다.");
				window.close();	
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
