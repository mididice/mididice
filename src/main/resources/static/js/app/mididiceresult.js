
//파일 공유 클릭 여부
var c = 0;

//유투브 업로드를 위한 파일 병합
function shareYtb(){
	if(c == 1){
		alert("파일 변환을 진행하였습니다.");
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

var fileName = $('#fileName').val();

wavesurfer.load('/mididice/music/'+fileName);
wavesurfer.on('ready',function(){
	_showPage();
	$("wave:first-child").css('height','25px');
});

wavesurfer.on('error',function(){
	alert("File not found");
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
	var enc = $('#fileName').val();
	window.open("/mididice/download/"+enc);
}
$(document).ready(function(){
	var fileName = $('#fileName').val();
	if(fileName != null){
		fileName = fileName.substring(0, fileName.length-4);
		var bbs_arr = fileName.split("-");
		var bbs = bbs_arr[bbs_arr.length - 1];
		var bbs_el = bbs.split("hk")
		$('#bar').text(bbs_el[1]);
		$('#bpm').text(bbs_el[2]);
		$('#seconds').text(bbs_el[0]);	
	}
});
