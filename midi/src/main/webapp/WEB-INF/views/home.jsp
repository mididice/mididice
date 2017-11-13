<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!Doctype html>
<html><head>
<meta charset="UTF-8">
<meta name="description" content="midi">
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
    background-size: cover; height: 46.7em;}
.midi_content{  background-color:#311a47; display:flex;}
.midi_setting_left{ display: inline-block;background-color:#69349c; width:12%; padding: 0 65px;
    height: 20em;
    margin: 7em;
    color: #ffffff; text-align:center;
    border-radius: 15px;
}
.play_condi_title{    font-size: 1.0em;}
.play_condi_result{    font-size: 1.8em;}

.midi_dice_center{ display: inline-block;  width:48%;  margin-top: 8%; margin-right:2%; }
.midi_dice_left{  border-radius: 50%;
    width: 48%; display: inline-block; margin-right: 3%;
}
.midi_dice_right{  border-radius: 50%;
    width: 48%; background-position: center center;
    background-size: cover;     display: inline-block;}
.midi_dice_left img{ width: 100%;}
.midi_dice_right img{ width: 100%;}
.sk_nights_first{ margin-top:2em; }
.sk_nights{margin-top:1em;}
/*background: linear-gradient( to right, #69349c, #d17cac );*/
.midi_current_right{ display: inline-block; width:18%; margin-top: 8%;}
.midi_result{ margin-top:40px; }
.sequence_number{ font-size:3em; color:white;     text-align: center;    margin-bottom: 0.5em;}
.sequence_bar{width: 100%;
    background-color: #69349c;
    border:1px solid #d17cac;
    border-radius:6px;}
#seqBar{width:0px;
    height: 20px;
    background: linear-gradient( to top right, #d17cac, #d17cac );
    border-radius:6px;}
.roll_dice_bottom{margin-top: 85px;}
.roll_btn{ cursor:pointer;}
.roll_btn > img{ width:100%;}
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
    width: 1250px; /* Could be more or less, depending on screen size */
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
	height: 20%;
	padding: 5%;
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
	margin-top:8%;
	height:110px;
}
.start_btn{ cursor:pointer;}

.holic_num{ width: 11%;
    padding-left: 84px;
    margin: 0; float:left; height:inherit; color:#311a47; font-size:20px; font-weight:bold;}
.progressNumber{
	height:30px;
	background-color: #69349c;
}
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
	
 	/* visibility: hidden; */
}
.play_btn_pattern{
	width: 170px;
    height: 170px;
    
}
.another_love{
	width:170px;
	display: inline-block;
}

/*responsive media css*/
@media (max-width: 1920px) {

}
@media (max-width: 1350px) {

}
@media (max-width: 1024px) {

}
@media (max-width: 860px) {
/* modal */
.modal{ width: 100%;}
.modal-content{ height: 90%; width:80%;}
.modal-content>p{    text-align: center;}
.set_div{padding:0; height:25%; width:100%; text-align: center;}
.set_title{ font-size:14px; height:20px;}
.set_title_time{font-size: 14px; height:20px;}

.bar,.bpm{margin-left:11%;}
.set_btn{margin:0 auto;}
.start_btn>img{width:100%;}

/*content*/
.container{height:100%;}
.midi_content{display:block;}
.midi_setting_left{ width: 100%; margin:0; height:110px; padding: 20px 1px;}
.midi_dice_center{margin:0; width:100%;}
.sk_nights_first{ margin:0; padding:0; display:inline-block;}
.sk_nights{margin:0; padding:0; display:inline-block;}
.midi_dice_right{ display:none;}
.midi_current_right{width:100%;margin:0;height:10%;}
.midi_dice_left{ width:100%; height:10%; text-align: center; margin:20px 0 15px 0;}
.midi_dice_left >img{ width: 70%; }
.roll_dice_bottom{margin-top:5%;}
.midi_result{ display:none;}
#waveform_set{width:100%; height:120px}
.pattern_img{width:100%;}
.another_love{ width: 33%;}

#holic1{ display:none;}
#holic2{ display:none;}
#holic3{ display:none;}
#holic7{ display:none;}
#holic8{ display:none;}
#holic9{ display:none;}
}

/*result*/
.container{ height: 100%; }
.midi_result_img{width:100%;}
.midi_res_img{ width: 100%}
#waveform{ display:none;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type='text/javascript' src='//www.midijs.net/lib/midi.js'></script>
<script>
const mq = window.matchMedia( "(max-width: 860px)" );
function calc_sec(bar,bpm){
	if(bar==9){
		if(bpm==80){
			seconds = 16;
		}else if(bpm==120){
			seconds = 13;
		}else{
			seconds = 11;
		}
	}else if(bar == 16){
		if(bpm==80){
			seconds = 32;
		}else if(bpm==120){
			seconds = 26;
		}else{
			seconds = 21;
		}
	}else{
		if(bpm==80){
			seconds = 50;
		}else if(bpm==120){
			seconds = 40;
		}else{
			seconds = 33;
		}
	}
	return seconds;
}
$(document).on("click","#result",function() {
	$('#doResult').submit();
});
var imgSrc;
$(document).on("mouseenter",".another_love",function(){
	imgSrc = $(this).children().find('.pattern_img').attr("src");
	$(this).children().find('.pattern_img').attr('src', '${pageContext.servletContext.contextPath}/resources/images/play_midi.png');
});
$(document).on("mouseleave",".another_love",function(){
	$(this).children().find('.pattern_img').attr('src', imgSrc);
});
function animateDice(purpleDice, pinkDice) {
    var i = 0;
    var timer = setInterval(function(){
    	if(i<10){
    		$('.midi_dice_right').children().first().attr('src', '${pageContext.servletContext.contextPath}/resources/images/dice/VioletDice01_0000'+i+'.png');	
    		$('.midi_dice_left').children().first().attr('src', '${pageContext.servletContext.contextPath}/resources/images/dice/PinkDice01_0000'+i+'.png');
    	}else if(i<60){
        	$('.midi_dice_right').children().first().attr('src', '${pageContext.servletContext.contextPath}/resources/images/dice/VioletDice01_000'+i+'.png');            
        	$('.midi_dice_left').children().first().attr('src', '${pageContext.servletContext.contextPath}/resources/images/dice/PinkDice01_000'+i+'.png');            
        }else{
        	$('.midi_dice_right').children().first().attr('src', '${pageContext.servletContext.contextPath}/resources/images/dice/VioletDice0'+purpleDice+'.png');
        	$('.midi_dice_left').children().first().attr('src', '${pageContext.servletContext.contextPath}/resources/images/dice/PinkDice0'+pinkDice+'.png');
        	clearInterval(timer);
        	return;
        }
        i++;
    }, 33)
};
var callback = function(midipath, pattern, seq){
	$('#patterns').append("<div class='another_love'><a href='#' id='samdasu' onClick='MIDIjs.play(\"${pageContext.servletContext.contextPath}/resources/midi/"+midipath+"\");'>"
			+"<img src='${pageContext.servletContext.contextPath}/resources/images/patterns/"
			+pattern+"' class='pattern_img' id='pattern"+seq+"'>");
		
		if(seq==0){
			$('#holic5').text("1");
			$('#holic6').text("2");
			$('#holic7').text("3");
			$('#holic8').text("4");
			$('#holic9').text("5");
		}else if(seq==1){
			
			$('#holic4').text("1");
			$('#holic5').text("2");
			$('#holic6').text("3");
			$('#holic7').text("4");
			$('#holic8').text("5");
			$('#holic9').text("6");
		}else if(seq==2){
			$('#holic3').text("1");
			$('#holic4').text("2");
			$('#holic5').text("3");
			$('#holic6').text("4");
			$('#holic7').text("5");
			$('#holic8').text("6");
			$('#holic9').text("7");
		}else if(seq==3){
			$('#holic2').text("1");
			$('#holic3').text("2");
			$('#holic4').text("3");
			$('#holic5').text("4");
			$('#holic6').text("5");
			$('#holic7').text("6");
			$('#holic8').text("7");
			$('#holic9').text("8");
		}else{
			var text= seq-3;
			$('#holic1').text(text++);
			$('#holic2').text(text++);
			$('#holic3').text(text++);
			$('#holic4').text(text++);
			$('#holic5').text(text++);
			$('#holic6').text(text++);
			$('#holic7').text(text++);
			$('#holic8').text(text++);
			$('#holic9').text(text++);
		}
		var targetDiv = $('#patterns div')
		if(mq.matches){
			if ($(targetDiv).length >= 3) {
				targetDiv.eq(0).remove()
			}
		}else{
			if ($(targetDiv).length >= 6) {
				targetDiv.eq(0).remove()
			}
		}
}
$(document).ready(function(){
	var bar = 9;
	var bpm = 80;
	var seconds = 16;
	var graph = 0;
	$('input[name="measure"]').click(function(){
		bar = $('input[name="measure"]:checked').val();
	});
	
	$('#result').on("click","#doResult",function(){
		$('#doResult').submit();
	});
	   
	$('.bar').click(function(){
		$('.bar').removeClass('selected_bar');
		$(this).addClass('selected_bar');
		bar = $(this).text();
		bar = bar*=1;
		seconds = calc_sec(bar,bpm);
		$('#second').empty();
		$('#second').val(seconds);
		$('#setTime').empty();
		$('#setTime').text(seconds+"sec");
	});
	$('.bpm').click(function(){
		$('.bpm').removeClass('selected_bpm');
		$(this).addClass('selected_bpm');
		bpm = $(this).text();
		seconds = calc_sec(bar,bpm);
		$('#second').empty();
		$('#second').val(seconds);
		$('#setTime').empty();
		$('#setTime').text(seconds+"sec");
	});
	$('#startBtn').click(function(){
		
		$('#second').append(seconds);
		$('#bar').append(bar);
		$('#bpm').append(bpm);
		
		$('#secondData').val(seconds);
		$('#barData').val(bar);
		$('#bpmData').val(bpm);
		
		$('#sequenceNumber').append("0/"+bar);
		$('#myModal').css('display','none');
	});
	
	var clickRoll = function(){
		var seq = $('#seq').val();
		var per = 100/bar;
		
		if(seq<bar){
		
		var url = "rollDice";
		var seq = $('#seq').val();
		$.ajax({
			method :'GET',
			url: url,
			data : "sequence="+seq,
			type : "json",
			async:false,
			beforeSend:function(data){
				
			},
			success:function(data){
				
				var obj = JSON.parse(data)
				
				for(var i = 0; i<obj.length;i++){
					var purpleDice = obj[i].pupleDice;
					var pinkDice = obj[i].pinkDice;
					animateDice(purpleDice, pinkDice);
					var samdasu = obj[i];
					$('#seq').val(obj[i].sequence);
					$('#midiNames').append("<input type='hidden' name='midis' value='"+obj[i].midiPath+"'>");
					$('#sequenceNumber').text(obj[i].sequence+"/"+bar);
					
					var midipath = obj[i].midiPath;
					var pattern = obj[i].pattern;
					
					window.setTimeout(callback,2000,midipath,pattern,seq);
					
					if(seq==bar-1){
						$('.roll_dice_bottom').empty();
						$('.roll_dice_bottom').append("<span class='roll_btn' id='result'><img src='${pageContext.servletContext.contextPath}/resources/images/result.png'></span>");
						$('.midi_dice_left').attr("id","result");
					}
					graph=graph+per;
					$('#seqBar').width(graph+"%");
				}
				
			
			},
			error: function(){
				console.log('error:')
			}
		});
		}else{
			
		}
	};

	
	$('.midi_dice_left').click(clickRoll);
	$('#roll').click(clickRoll)
;});
</script>
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
		<div id="myModal" class="modal">
  			<!-- Modal content -->
  			<div class="modal-content">
    		<p>시작 전 곡의 길이 정하기</p>
    		<div class="set_div">
				<p class="set_title_time">Length</p>
				<div class="set_time" id="setTime">
					16sec
				</div>
    		</div>
    		<div class="set_div">
				<p class="set_title">Bar</p>
				<p class="">
					<span class="bar selected_bar">9</span><span class="bar">16</span><span class="bar">25</span>
				</p>
    		</div>
    		<div class="set_div">
				<p class="set_title">Bpm</p>
				<p class="">
					<span class="bpm selected_bpm">80</span><span class="bpm">120</span><span class="bpm">150</span>
				</p>
    		</div>
			<div class="set_btn">
				<span id="startBtn" class="start_btn"> 
					<img src="${pageContext.servletContext.contextPath}/resources/images/start.png">
				</span>
			</div>
			</div>
		</div>
			<div class="midi_content">
				<div class="midi_setting_left">
					<div class="sk_nights_first">
						<div>
							<p class="play_condi_title">second</p>
							<p class="play_condi_result" id="second"></p>
						</div>
					</div>
					<div class="sk_nights">
						<div>
							<p class="play_condi_title">BAR</p>
							<p class="play_condi_result" id="bar"></p>
						</div>
					</div>
					<div class="sk_nights">
						<div>
							<p class="play_condi_title">BPM</p>
							<p class="play_condi_result" id="bpm"></p>
						</div>
					</div>
				</div>
				<div class="midi_dice_center">
					<div class="midi_dice_left">
						<img src="${pageContext.servletContext.contextPath}/resources/images/midipink2.png">
					</div>
					<div class="midi_dice_right">
						<img src="${pageContext.servletContext.contextPath}/resources/images/midipurple2.png">
					</div>
					
				</div>
				<div class="midi_current_right">
					<div id="midiResult" class="midi_result">
						
						<div class="sequence_number" id="sequenceNumber"></div>
						<div class="sequence_bar">
							<div id="seqBar"></div>
						</div>
						<form action="save" method="post" id="doResult">
							<div class="roll_dice_bottom">
								<span class="roll_btn" id="roll">
									<img src="${pageContext.servletContext.contextPath}/resources/images/roll.png">
								</span>
							</div>
							<div id="midiNames"></div>
							<input type="hidden" id="secondData" name="seconds" value="">
							<input type="hidden" id="bpmData" name="bpm" value="">
							<input type="hidden" id="barData" name="bar" value="">
						</form>
						<input type="hidden" id="seq" name="sequence" value="0">
					</div>
				</div>
			</div>
			<!-- pattern -->
			<div class="progressNumber">
				<div class="holic_num" id="holic1"></div>
				<div class="holic_num" id="holic2"></div>
				<div class="holic_num" id="holic3"></div>
				<div class="holic_num" id="holic4"></div>
				<div class="holic_num" id="holic5">1</div>
				<div class="holic_num" id="holic6">2</div>
				<div class="holic_num" id="holic7">3</div>
				<div class="holic_num" id="holic8">4</div>
				<div class="holic_num" id="holic9">5</div>
			</div>
			<div id="waveform_set">
				<div id="patterns">
				</div>
			</div>
			</div>
	</section>
	</body>
</html>