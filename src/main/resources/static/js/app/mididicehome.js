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
	$(this).children().find('.pattern_img').attr('src', '/resources/images/play_midi.png');
});
$(document).on("mouseleave",".another_love",function(){
	$(this).children().find('.pattern_img').attr('src', imgSrc);
});
function animateDice(purpleDice, pinkDice) {
    var i = 0;
    var timer = setInterval(function(){
    	if(i<10){
    		$('.midi_dice_right').children().first().attr('src', '/resources/images/dice/VioletDice01_0000'+i+'.png');	
    		$('.midi_dice_left').children().first().attr('src', '/resources/images/dice/PinkDice01_0000'+i+'.png');
    	}else if(i<60){
        	$('.midi_dice_right').children().first().attr('src', '/resources/images/dice/VioletDice01_000'+i+'.png');            
        	$('.midi_dice_left').children().first().attr('src', '/resources/images/dice/PinkDice01_000'+i+'.png');            
        }else{
        	$('.midi_dice_right').children().first().attr('src', '/resources/images/dice/VioletDice0'+purpleDice+'.png');
        	$('.midi_dice_left').children().first().attr('src', '/resources/images/dice/PinkDice0'+pinkDice+'.png');
        	clearInterval(timer);
        	return;
        }
        i++;
    }, 33)
};
var callback = function(midipath, pattern, seq){
	$('#patterns').append("<div class='another_love'><a href='#' id='samdasu' onClick='MIDIjs.play(\"/resources/midi/"+midipath+"\");'>"
			+"<img src='/resources/images/patterns/"
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
		
		var url = "rolldice";
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
						$('.roll_dice_bottom').append("<span class='roll_btn' id='result'><img src='/resources/images/result.png'></span>");
						$('.midi_dice_left').attr("id","result");
						$("#result > img").addClass("resh");
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