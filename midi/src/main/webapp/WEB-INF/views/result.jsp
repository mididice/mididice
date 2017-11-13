<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!Doctype html>
<html><head>
<meta charset="UTF-8">
<meta name="description" content="LiST">
<!-- Mobile Specific Meta -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>midiDICE</title>
<style>
html {
  font-family: sans-serif;
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%;
}
* {
  margin: 0;
  padding: 0;
  
}
div{
	margin: 0;
	box-sizing:border-box;
    -moz-box-sizing:border-box;
    -webkit-box-sizing:border-box;
}
.midiyapp_logo{background:url("/midi/resources/images/main.png") no-repeat;
background-size: 140px 22px;
    margin-right: 0;
    margin-left: 50px;
    height:50px; width:160px;
    display:inline-block;
    margin-top: 14px;}
.midiyapp_reset{background:url("/midi/resources/images/reset.png") no-repeat;
height:25px; width:90px;
display:inline-block; float:right;    margin-left: 30px;
    margin-top: 18px;
background-size: 40px 15px;}
.header{ height: 50px; border-bottom: 1px solid black;}
.midiyapp_reset{ text-align:right; }
.container{    background: linear-gradient( to right, #d17cac, #69349c );
    background-position: center center;
    background-size: cover; height: 46em;}
.midi_content{ padding-top: 20px; background-color:#311a47; display:flex;}
.midi_setting_left{ display: inline-block;background-color:#69349c; width:12%; padding: 0 65px;
    height: 20em;
    margin: 8%;
    color: #ffffff; text-align:center;
    border-radius: 15px;
}
.play_condi_title{    font-size: 1.0em;}
.play_condi_result{    font-size: 1.8em;}
.initial_set{ width: 210px; background-color:black;     height: 380px; margin-top: 120px; border-radius: 20px;}
.midi_dice_center{ display: inline-block;  width:48%;  margin-top: 8%; }
.midi_dice_left{ height: 20em; border-radius: 50%;
    width: 340px; display: inline-block; 
}
.midi_dice_right{ height:20em; border-radius: 50%;
    width: 340px; background-position: center center;
    background-size: cover;     display: inline-block;}
.midi_dice_left img{ width: 100%;}
.midi_dice_right img{ width: 100%;}
/*background: linear-gradient( to right, #69349c, #d17cac );*/
.midi_current_right{ display: inline-block; width:240px; margin-top: 7em;
height:20em;}
.midi_result{ margin-top:40px; }
.sequence_number{ font-size:3em; color:white;     text-align: center;    margin-bottom: 0.5em;}
.sequence_bar{width: 100%;
    background-color: #3d3d5c;
    border:1px solid #ccc;
    border-radius:6px;}
#seqBar{width: 33%;
    height: 20px;
    background: linear-gradient( to top right, #69349c, #540069 );
    border-radius:6px;}
.roll_dice_bottom{margin-top: 85px;}
.roll_btn{ cursor:pointer;}
/* The Modal (background) */
.modal {
    display: block; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: hidden; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
    background-color: #69349c;
    margin: 8% auto; /* 15% from the top and centered */
    padding: 30px;
    border:0;
    border-radius:10px;
    width: 80%; /* Could be more or less, depending on screen size */
}
.bpm{ display: inline-block;
    height: 40px;
    width: 40px;
    line-height: 60px;
    -moz-border-radius: 30px; /* or 50% */
    border-radius: 30px; /* or 50% */
    background-color: #d17cac;
    color: #69349c;
    text-align:center;
    margin-left: 42px;
    font-size:20px}
.bar{ display: inline-block;
    height: 40px;
    width: 40px;
    line-height: 60px;
    -moz-border-radius: 30px; /* or 50% */
    border-radius: 30px; /* or 50% */
    background-color: #d17cac;
    color: #69349c;
    text-align:center;
    margin-left: 42px;
    font-size:20px}
.set_title{
	height:110px;
	text-align:center;
	color: #311a47;
	font-size: 2em;
	font-weight: bold;
}
.set_title_time{
	height:60px;
	text-align:center;
	color: #311a47;
	font-size: 2em;
	font-weight: bold;
}
.set_div{
	width: 33%;
	height: 360px;
	padding: 100px 50px 100px 50px;
	display:inline-block;
}
.set_time{
	font-size: 80px;
	text-align:center;
}
.selected_bar{background-color:#311a47;}
.selected_bpm{background-color:#311a47;}
.pattern_img{
	width: 170px;
}
.set_btn{
	margin-left:40%;
	height:110px;
}
.start_btn{ cursor:pointer;}
#waveform_set{
	width:850px;
	height:170px;
	overflow-x: hidden;
	overflow-y: hidden;
}
#patterns{
	float:right;
}
.play-button-icon {	
	opacity:0;
  position: absolute; z-index: 2;
  width: 50px; height: 50px;
  left: 0; right: 0; bottom: 65px; margin: auto; /* center */
}
.play-button-icon:hover {
  opacity: 1;
}
.play_img{
	width:50px;
}

/* result */
.set_result{
	height: 110px;
	padding: 20px 100px 20px 10px;
	display:inline-block;}
.midi_res_img{ display:inline-block; color: #fff; width:620px;}
.midi_res_btn{ display:inline-block;}
.set_title_result{height: 30px;
    text-align: center;
    color: #fff;
    font-weight:100}
.set_value_result{ font-size:1.8em; color:#fff; text-align:center;}
.result_content_wrapper{background-color:#311a47;}
.result_content{ 
width: 1140px;
    max-width: 100%;
    margin-right: auto;
    margin-left: auto;
    padding-right: 15px;
    padding-left: 15px;
    height:570px;}
.midi_result_img{ width:64%}

@media (max-width: 860px) {
/*result*/
.container{ height: 100%; }
.result_content{ width: 100%; height: 100%}
.set_result{ height: 100%; padding: 20px;}
.midi_result_img{width:100%;}
.midi_res_img{ width: 100%; text-align:center;}
.midi_res_btn{ padding: 1%; height: 160px; width:100%;}
.midi_res_btn_center{ text-align:center;}
#waveform{ display:none;}
.play_m:hover, .down_m:hover, .share_m:hover{
	transform:scale(1.1);             /*  default */
	-webkit-transform:scale(1.1);  /*  chrome */
	-moz-transform:scale(1.1);     /* FireFox */
	-o-transform:scale(1.1);        /* Opera */
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
<!-- header -->
	<header>
		<div class="header" id="header">
			<div class="midiyapp_logo"></div>
			<a href="/midi"><span class="midiyapp_reset"></span></a>
		</div>
	</header>
	<!-- center -->
	<section>
		<div class="container">
			<div class="result_content_wrapper">
			<div class="result_content">
				<div class="midi_res_img">
					<div class="set_result">
						<p class="set_title_result">Bar</p>
						<p class="set_value_result">${bar}</p>
					</div>
					<div class="set_result">
						<p class="set_title_result">Bpm</p>
						<p class="set_value_result">${bpm}</p>
					</div>
					<div class="set_result">
						<p class="set_title_result">Length</p>
						<p class="set_value_result">${seconds}</p>
					</div>
					<div class="">
						<img class="midi_result_img" src="${pageContext.servletContext.contextPath}/resources/resimg/${resImg}">
					</div>
				</div>
				
				<div class="midi_res_btn">
					<div>
						<a href="javascript:wavesurfer.playPause()"><img src="${pageContext.servletContext.contextPath}/resources/images/play.png" class="play_m"></a>
						<a id="down" href="javascript:downMp3();"><img src="${pageContext.servletContext.contextPath}/resources/images/download.png" class="down_m"></a>
						<a href="javascript:shareYtb();"><img src="${pageContext.servletContext.contextPath}/resources/images/share.png" class="share_m"></a>
					</div>
				</div>
				<form action="" name="merge" method="post">
					<input type="hidden" name="i" value="${resImg}">
					<input type="hidden" name="m" value="${filename}">
				</form>
				</div>
			</div>
			<div id="waveform">
      <wave style="display: block; position: relative; user-select: none; height: 128px; overflow-x: auto; overflow-y: hidden;"><canvas width="0" height="160" style="position: absolute; z-index: 1; left: 0px; top: 0px; bottom: 0px; width: 0px;"></canvas><wave style="position: absolute; z-index: 2; left: 0px; top: 0px; bottom: 0px; overflow: hidden; width: 0px; display: block; box-sizing: border-box; border-right: 1px solid rgb(255, 255, 255);"><canvas width="0" height="160" style="width: 0px;"></canvas></wave></wave></div>
		<!-- bottom -->
		<div>
		</div>
		</div>
	</section>
	<!-- Scripts -->
			
		<!--<script src="assets/js/jquery.dropotron.min.js"></script>
		<script src="assets/js/skel.min.js"></script>
		<script src="assets/js/util.js"></script>-->
		<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
		<!--<script src="assets/js/main.js"></script>-->
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/wavesurfer.js/1.2.6/wavesurfer.min.js"></script>
		<script>
		
			//파일 공유 클릭 여부
			var c = 0;
			
			//유투브 업로드를 위한 파일 병합
			function shareYtb(){
				if(c == 1){
					alert("파일 변환 및 업로드를 진행하였습니다.");
				}
				var open = window.open("","loading","resizable=no, width=500, height=400");
				var f = document.merge;
				f.action = "convert";
				f.target = "loading";
				f.submit();
				c = 1;
			}
			
			var _showPage = function(){ 
				var loader = $("div.loader"); 
				loader.css("display","none");
				$("#play_btn, #share_btn, #down_btn").css('cursor','default');
				$("#play_btn, #share_btn, #down_btn").attr('disabled',false);
			};
	
			var wavesurfer = WaveSurfer.create({
				container: '#waveform',
				waveColor: 'rgba(211, 211, 211, 0.48)',
				progressColor: 'rgba(255, 51, 126, 0.59)',
				cursorColor: '#fff'
			});
			wavesurfer.load('/midi/resources/save/${filename}.mp3');
			//wavesurfer.load('/midi/resources/save/iu.mp3');
			wavesurfer.on('ready',function(){
				_showPage();
				$("wave:first-child").css('height','25px');
			});
			
			wavesurfer.on('error',function(){
				alert("파일이 존재하지 않습니다^^ 올바른경로로 접속하였는지 다시한번 확인해주세요~");
				// ==> 추가이벤트 입력(리다이렉트, 창을 종료?, 버튼 비활성화 등등)
			});
		
			$(function(){
				$("wave:first-child").css('height','25px');
				$("#play_btn, #share_btn, #down_btn").css('cursor','not-allowed');
				$("#play_btn, #share_btn, #down_btn").attr('disabled',true);
				var filter = "win16|win32|win64|mac";
				
				$(window).resize(function(){
					wavesurfer.zoom(1);
				});
	
			});
			
		//파일 다운로드
		function downMp3(){
			window.open("../res/download.do?fe=${enc}");
		}
		
		</script>
	</body>
</html>