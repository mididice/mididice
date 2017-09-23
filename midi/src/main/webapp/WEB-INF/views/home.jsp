<%@ page contentType="text/html;charset=UTF-8" %>
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
<title>midiYAPP</title>
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
.header{ height: 60px; border-bottom: 1px solid black;}
.midiyapp_reset{ text-align:right; }
.container{    background: linear-gradient( to right, #ff0076, #540069 );
    background-position: center center;
    background-size: cover; height: 46em;}
.midi_content{ padding-top: 20px; background-color:#330033; display:flex;}
.midi_setting_left{ display: inline-block;background-color:#4d004d; width:12%; padding: 0 65px;
    height: 20em;
    margin: 7em;
    color: #ffffff; text-align:center;
    border-radius: 15px;
}
.play_condi_title{    font-size: 1.0em;}
.play_condi_result{    font-size: 1.8em;}
.initial_set{ width: 210px; background-color:black;     height: 380px; margin-top: 120px; border-radius: 20px;}
.midi_dice_center{ display: inline-block;  width:48%;  margin-top: 7em; }
.midi_dice_left{ height: 20em;background: linear-gradient( to right, #540069, #ff0076 ); border-radius: 50%;
    width: 330px;     display: inline-block;
}
.midi_dice_right{ height:20em; background: linear-gradient( to right, #540069, #ff0076 ); border-radius: 50%;
    width: 330px; background-position: center center;
    background-size: cover;     display: inline-block;}
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
    background: linear-gradient( to right, #ff0076, #540069 );
    border-radius:6px;}
.roll_dice_bottom{margin-top: 140px;}
.roll_btn{ height: 30px;
    width: 95%;
    border:none;
    color: #ccc;
    background: linear-gradient( to right, #540069, #ff0076 )}
/* The Modal (background) */
.modal {
    display: block; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 15% auto; /* 15% from the top and centered */
    padding: 20px;
    border: 1px solid #888;
    width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button */
.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}
.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}
.pattern_img{
	width: 170px;
}
#waveform{
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type='text/javascript' src='//www.midijs.net/lib/midi.js'></script>
<script>
$(document).ready(function(){
	var bar = 8;
	var bpm = 120;
	var seconds = 30;
	$('input[name="measure"]').click(function(){
		bar = $('input[name="measure"]:checked').val();
	});
	$('.bar').click(function(){
		bar = $(this).text();
		bar = bar*=1;
	});
	$('.bpm').click(function(){
		bpm = $(this).text();
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
	
	$('#roll').click(function(){
		var seq = $('#seq').val();
		console.log(seq,bar)
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
					$('#seq').val(obj[i].sequence);
					$('#dice1').text(obj[i].pupleDice);
					$('#dice2').text(obj[i].pinkDice);
					$('#path').text(obj[i].midiPath);
					$('#midiNames').append("<input type='hidden' name='midis' value='"+obj[i].midiPath+"'>");
					$('#sequenceNumber').text(obj[i].sequence+"/"+bar);
					/* $('#patterns').append(obj[i].pattern); */
					$('#patterns').append("<a href='#' onClick='MIDIjs.play(\"${pageContext.servletContext.contextPath}/resources/midi/sample1.mid\");'>"
						+"<img src='${pageContext.servletContext.contextPath}/resources/images/patterns/"
						+obj[i].pattern+"' class='pattern_img' id='pattern"+seq+"'>");
					/*
					"<div class='play-button-icon'>"+
					"<img class='play_img' src='http://wptf.com/wp-content/uploads/2014/05/play-button.png'></div>")
					+obj[i].midiPath+
					*/
					var targetImg = $('#patterns img')
					if ($(targetImg).length >= 6) {
						targetImg.eq(0).remove()
					}
					if(seq==bar-1){
						$('.roll_dice_bottom').empty();
						$('.roll_dice_bottom').append("<button type='button' id='roll' class='roll_btn'>Result</button>");
					}
				}
			},
			error: function(){
				console.log('error:')
			}
		});
		}else{
			
		}
	});	
});
</script>

</head>
<body>
<!-- header -->
	<header>
		<div class="header" id="header">
			<div class="midiyapp_logo">midiYAPP</div>
			<div class="midiyapp_reset"><a href="/midi">RESET</a></div>
		</div>
	</header>
	<!-- center -->
	<section>
		<div class="container">
		<div id="myModal" class="modal">
  			<!-- Modal content -->
  			<div class="modal-content">
    		<p>시작 전 곡의 길이 정하기</p>
    		마디 : <span class="bar">8</span>|<span class="bar">12</span>|<span class="bar">16</span>
    		<p></p>
    		bpm: <span class="bpm">80</span>|<span class="bpm">120</span>|<span class="bpm">150</span>
    		<button id="startBtn" class="start_btn">시작하기</button>
  			</div>
		</div>
			<div class="midi_content">
				<div class="midi_setting_left">
					<div style="margin-bottom: 1.4em;padding-top:1.5em; ">
						<div>
							<p class="play_condi_title">second</p>
							<p class="play_condi_result" id="second"></p>
						</div>
					</div>
					<div style="margin-bottom: 1.4em;">
						<div>
							<p class="play_condi_title">BAR</p>
							<p class="play_condi_result" id="bar"></p>
						</div>
					</div>
					<div style=" margin-bottom: 1.4em;">
						<div>
							<p class="play_condi_title">BPM</p>
							<p class="play_condi_result" id="bpm"></p>
						</div>
					</div>
				</div>
				<div class="midi_dice_center">
					<div class="midi_dice_left" style="margin-right: 20px;">
					</div>
					<div class="midi_dice_right">
					</div>
					
				</div>
				<div class="midi_current_right">
					<div id="midiResult" class="midi_result">
						
						<div class="sequence_number" id="sequenceNumber"></div>
						<div class="sequence_bar">
							<div id="seqBar"></div>
						</div>
						<form action="append" method="get">
							<div class="roll_dice_bottom">
								<button type="button" id="roll" class="roll_btn">Roll</button>						
							</div>
							<div id="midiNames"></div>
							<input type="hidden" id="secondData" name="seconds" value="">
							<input type="hidden" id="bpmData" name="bpm" value="">
							<input type="hidden" id="barData" name="bar" value="">
							<div id="dice1">1</div>
							<div id="dice2">6</div>
							<div id="path">61.midi</div>		
						</form>
						<input type="hidden" id="seq" name="sequence" value="0">
					</div>
				</div>
			</div>
			<!-- pattern -->
			<div id="waveform">
				<div id="patterns">
				</div>
			</div>
			</div>
	</section>
	</body>
</html>