
//파일 공유 클릭 여부
var c = 0;

//유투브 업로드를 위한 파일 병합
function shareYtb(){
	if(c == 1){
		//alert("파일 변환 및 업로드를 진행하였습니다.");
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
wavesurfer.load('/resources/save/'+fileName+'.mp3');
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
	var enc = $('#fe').val();
	window.open("/download/"+enc);
}
