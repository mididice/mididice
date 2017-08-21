<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!Doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="LiST">
<!-- Mobile Specific Meta -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'>
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
.midi_content{background-color:purple;}
.midi_setting_left{ display: inline-block;background-color:blue; width:22%; height:67%; padding: 0 100px;}
.initial_set{ width: 210px; background-color:black;     height: 380px; margin-top: 120px; border-radius: 20px;}
.midi_dice_center{ display: inline-block; background-color:white; width:52%; height:67%; }
.midi_current_right{ display: inline-block; background-color:red; width:25%; height: 67%;}
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
		<div class="header">
			<div class="midiyapp_logo">midiYAPP</div>
			<div class="midiyapp_reset">RESET</div>
		</div>
	</header>
	<!-- center -->
	<section>
		<div class="container">
			<div class="midi_content">
				<div class="midi_setting_left">
					<div class="initial_set">
						<input type="radio" name="measure" value="8">8 
						<input type="radio" name="measure" value="16">16 
						<input type="radio" name="measure" value="24">24
					</div>
				</div>
				<div class="midi_dice_center"></div>
				<div class="midi_current_right">
					<div id="midiResult">
						<div id="dice1"></div>
						<div id="dice2"></div>
						<div id="path"></div>
						<div id="sequenceNumber"></div>
						<input type="hidden" id="seq" name="sequence" value="0">
						<button type="button" id="roll">Roll</button>
					</div>
				</div>
			</div>
		</div>
		<!-- bottom -->
		<div>
		
		</div>
	</section>
</body>
</html>