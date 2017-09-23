<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!Doctype html>
<html><head>
<meta charset="UTF-8">
<meta name="description" content="LiST">
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	var bar = 8;
	
	$('input[name="measure"]').click(function(){
		bar = $('input[name="measure"]:checked').val();
	});
	
	$('#roll').click(function(){
		var seq = $('#seq').val();
		if(seq<bar){
		
		var url = "rollDice";
		var seq = $('#seq').val();
		$.ajax({
			method :'GET',
			url: url,
			data : "sequence="+seq,
			type : "json",
			beforeSend:function(data){
				
			},
			success:function(data){
				var obj = JSON.parse(data)
				
				for(var i = 0; i<obj.length;i++){
					$('#seq').val(obj[i].sequence);
					$('#dice1').text(obj[i].pupleDice);
					$('#dice2').text(obj[i].pinkDice);
					$('#path').text(obj[i].midiPath);
					$('#sequenceNumber').text(obj[i].sequence+"/"+bar);
				}
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
			<div class="midiyapp_reset">RESET</div>
		</div>
	</header>
	<!-- center -->
	<section>
		<div class="container">
			<div class="midi_content">
				<div class="midi_setting_left">
					
					<div style=" padding-top:1.5em; margin-bottom: 1.4em;">
						<div>
							<p class="play_condi_title">BAR</p>
							<p class="play_condi_result">16</p>
						</div>
					</div>
					<div style=" margin-bottom: 1.4em;">
						<div>
							<p class="play_condi_title">BPM</p>
							<p class="play_condi_result">120</p>
						</div>
					</div>
					<div style=" margin-bottom: 1.4em;">
						<div>
							<p class="play_condi_title">BEAT</p>
							<p class="play_condi_result">type A</p>
						</div>
					</div>
				</div>
				<div class="midi_dice_center">
					<div class="midi_dice_left" style="
    margin-right: 20px;
">
					</div>
					<div class="midi_dice_right">
					</div>
					
				</div>
				<div class="midi_current_right">
					<div id="midiResult" class="midi_result">
						
						<div class="sequence_number" id="sequenceNumber">8/8</div>
						<div class="sequence_bar">
							<div id="seqBar"></div>
						</div>
						<div class="roll_dice_bottom">
							<button type="button" id="roll" class="roll_btn">Roll</button>						
						</div>
						<input type="hidden" id="seq" name="sequence" value="8">
							<div id="dice1">1</div>
							<div id="dice2">6</div>
							<div id="path">61.midi</div>
					</div>
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
				/* var _showPage = function(){ 
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
				wavesurfer.load('iu.mp3');
				wavesurfer.on('ready',function(){
					_showPage();
					$("#muz").fadeIn();
				});
			
				$(function(){
					$("#play_btn, #share_btn, #down_btn").css('cursor','not-allowed');
					$("#play_btn, #share_btn, #down_btn").attr('disabled',true);
					var filter = "win16|win32|win64|mac";
					/*if(navigator.platform){ //loading event
						if(0 > filter.indexOf(navigator.platform.toLowerCase())){
							//ëª¨ë°”ì¼ì¼ ê²½ìš°
							setTimeout(_showPage,20000);
						}else{
							//PCí™”ë©´ì¼ ê²½ìš°
							setTimeout(_showPage,3500);
						}
					}*/
					/*
					$(window).resize(function(){
						wavesurfer.zoom(1);
					});
					
					$("#play_btn").click(function(){
						wavesurfer.playPause();
					});
					
					$("#down_btn").click(function(){
						location.href="iu.mp3";
					});
					
					$("#share_btn").click(function(){
						alert("ì¤€ë¹„ì¤‘ìž…ë‹ˆë‹¤ ^_^ ==> facebook, soundcloud, instagram, naver blog, kakaotalk, kakaostory, íŠ¸ìœ„í„°, ìŒì•…ê³µìœ&nbsp; sns ë“±");
					});
				}); */
						
			</script>

</body></html>