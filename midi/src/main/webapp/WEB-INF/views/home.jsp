<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Midiyapp</title>
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
<h1>Midi Create</h1>
<input type="radio" name="measure" value="8">8
<input type="radio" name="measure" value="16">16
<input type="radio" name="measure" value="24">24

<form action="" method="get">

<div id="midiResult">
<div id="dice1"></div>
<div id="dice2"></div>
<div id="path"></div>
<div id="sequenceNumber"></div>
</div>
<input type="hidden" id="seq" name="sequence" value="0">
<button type="button" id="roll">Roll</button>

<button type="submit">generate</button>
</form>
</body>
</html>