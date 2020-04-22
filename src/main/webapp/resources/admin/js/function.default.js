$(function(){
	$.ajaxSetup({
		cache : false
	});

	$('.favorite').on('click', function(e) {
        var bookmarkURL = $(this).attr("element-url");
        var bookmarkTitle = $(this).attr("element-title");
        var triggerDefault = false;

        if (window.sidebar && window.sidebar.addPanel) {
            window.sidebar.addPanel(bookmarkTitle, bookmarkURL, '');
        } else if ((window.sidebar && (navigator.userAgent.toLowerCase().indexOf('firefox') > -1)) || (window.opera && window.print)) {
            var $this = $(this);
            $this.attr('href', bookmarkURL);
            $this.attr('title', bookmarkTitle);
            $this.attr('rel', 'sidebar');
            $this.off(e);
            triggerDefault = true;
        } else if (window.external && ('AddFavorite' in window.external)) {
            window.external.AddFavorite(bookmarkURL, bookmarkTitle);
        } else {
            alert((navigator.userAgent.toLowerCase().indexOf('mac') != -1 ? 'Cmd' : 'Ctrl') + '+D 키를 눌러 즐겨찾기에 등록하실 수 있습니다.');
        }

        return triggerDefault;
    });
});

function TRIM($Value){
	return $Value.replace(/\s|　/gi, '');
}

function instance_iframe($Page){
	$("div").filter("[id^=instance_d]").remove();
	$("#Mask").after("<div id='instance_div'><iframe name='instance_iframe' id='instance_iframe' src='"+$Page+"' width='0' height='0' style='display:none'></iframe></div>");
}

function instance_iframe2($Page){
	$("div").filter("[id^=instance_d]").remove();

	$("#Mask").after("<div id='instance_div2'><iframe name='instance_iframe' id='instance_iframe' src='"+$Page+"' width='800' height='400'></iframe></div>");
}

//주소
function zipcode_it($datas){
	var $zip = ($datas.z == undefined) ? "" : $datas.z,
		$zip1 = ($datas.z1 == undefined) ? "" : $datas.z1,
		$zip2 = ($datas.z2 == undefined) ? "" : $datas.z2,
		$addr = ($datas.a == undefined) ? "" : $datas.a,
		$addr1 = ($datas.a1 == undefined) ? "" : $datas.a1,
		$addr2 = ($datas.a2 == undefined) ? "" : $datas.a2,
		$buildingName = "";

	new daum.Postcode({
		oncomplete: function(data) {
			$buildingName = (data.buildingName == "") ? "" : " (" + data.buildingName + ")";

			if($zip)	(data.zonecode == undefined) ? $("#" + $zip).val(data.postcode1 + "-" + data.postcode2) : $("#" + $zip).val(data.zonecode);
			if($zip1)	$("#" + $zip1).val(data.postcode1);
			if($zip2)	$("#" + $zip1).val(data.postcode2);
			if($addr)	$("#" + $addr).val(data.address + $buildingName);
			if($addr1)	$("#" + $addr1).val(data.roadAddress + $buildingName);
			if($addr2)	$("#" + $addr2).val(data.address2).focus();
		}
	}).open();
}

//팝업
function WinOpen($URL, $ID, $Title, $OPTION){
    WinPOpen($URL, $ID, $Title, $OPTION);
}

function WinPOpen($URL, $ID, $Title, $OPTION){
	var $T = ($OPTION.TOP) ? $OPTION.TOP : 10,
		$L = ($OPTION.LEFT) ? $OPTION.LEFT : 10,
		$W = parseInt($OPTION.WIDTH),
		$H = parseInt($OPTION.HEIGHT),
		$S = ($OPTION.SCROLL) ? $OPTION.SCROLL : 1,
		$OPT = "width="+$W+"px, height="+$H+"px, top="+$T+", left="+$L+", toolbar=0, status=0, scrollbars="+$S+", resizeable=0, menubar=0";

	window.open($URL, $ID, $OPT);
}

//selected _ checked
function selected_checked($Attr, $Value){
	try{
		var $Type = document.getElementById($Attr).type;

		if($Type == "select-one"){
			var $MAX = $("#"+$Attr+" option").size();

			for(var i=0; i<$MAX; i++){
				var $Opt_Value = $("#"+$Attr+" option:eq("+i+")").val();

				if($Opt_Value == $Value){
					$("#"+$Attr+" option:eq("+i+")").attr("selected",true);
				}
			}
		}else if($Type == "checkbox"){
			if($("#"+$Attr).val() == $Value){
				$("#"+$Attr).attr("checked",true);
			}
		}else{
			$(":radio").filter("[id*="+$Attr+"]").each(function(){
				if($(this).val() == $Value){
					$(this).attr("checked",true);
				}
			});
		}
	}catch(e){}
}

//0추가하기
function addZero(i){
	var rtn = i + 100;
	return rtn.toString().substring(1,3);
}




//정규식
function reg_exp($type, $value){
	if(!$type)	return;

	var $pattern = "",
		$return_value = false;

	switch($type){
		case "ip"			:	$pattern = /^(1|2)?\d?\d([.](1|2)?\d?\d){3}$/;																								break;	//IP
		case "num"			:	$pattern = /[0-9]$/;																														break;	//숫자
		case "num_dot"		:	$pattern = /^[0-9\.]+$/;																														break;	//숫자+점
		case "num_comma"	:	$pattern = /^[0-9\,]+$/;																														break;	//숫자+콤마
		case "kor"			:	$pattern = /[ㄱ-ㅎㅏ-ㅣ가-힣]$/;																											break;	//한글
		case "eng"			:	$pattern = /[a-zA-Z]$/;																														break;	//영문
		case "kor_eng"		:	$pattern = /[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z]$/;																										break;	//한글+영문
		case "first_eng"	:	$pattern = /^[a-zA-Z][0-9a-zA-Z]*$/;																										break;	//첫자는 영문
		case "kor_num"		:	$pattern = /[ㄱ-ㅎㅏ-ㅣ가-힣0-9]$/;																											break;	//한글+숫자
		case "eng_num"		:	$pattern = /[a-zA-Z0-9]$/;																													break;	//영문+숫자
		case "kor_eng_num"	:	$pattern = /[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]$/;																									break;	//한글+영문+숫자
		case "email"		:	$pattern = /([0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)\.([0-9a-zA-Z_-]+)/;																			break;	//이메일
		case "tel"			:	$pattern = /\d{2,3}-\d{3,4}-\d{4}$/;																										break;	//전번
		case "zipcode"		:	$pattern = /\d{3}-\d{3}$/;																													break;	//우편번호
		case "phone"		:	$pattern = /[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;																								break;	//핸드폰
		case "image"		:	$pattern = /\.(jpg|gif|tif|bmp|png)$/i;																										break;	//이미지
		case "url"			:	$pattern = /(http\:\/\/)?((\w+)[.])+(asia|biz|cc|cn|com|de|eu|in|info|jobs|jp|kr|mobi|mx|name|net|nz|org|travel|tv|tw|uk|us)(\/(\w*))*$/i;	break;	//url
		case "date"			:	$pattern = /\d{4}-\d{2}-\d{2}$/;																											break;	//YYYY-MM-DD
		case "time"			:	$pattern = /^[0-9]{2}:[0-9]{2}$/;																											break;	//00:00
		case "id_pw"		:	$pattern = /^[a-z]{1}[a-z0-9]{3,13}$/;																										break;
		case "phone_tel33"	:	$pattern = /^[0-9]{3}$/;																													break;
		case "phone_tel34"	:	$pattern = /^[0-9]{3,4}$/;																													break;
		case "phone_tel44"	:	$pattern = /^[0-9]{4}$/;																													break;
	}

	if(!$pattern.test($value))	$return_value = false;
	else						$return_value = true;

	return $return_value;
}

//콤마
function number_format(val){
	var reg = /(^[+-]?\d+)(\d{3})/;

	val += "";

	while (reg.test(val)){
		val = val.replace(reg, '$1' + ',' + '$2');
	}

	return val;
}

//콤마제거
function remove_comma(data){
	return data.replace(/,/gi,'');
}

//$pos단위 올림
function ceiling($price, $pos){
	$price = parseInt($price);
	return Math.ceil($price / 100) * $pos;
}


//숫자만 onKeyDown="javascript:onlyNumberInput()"
function onlyNumberInput(){
	var code = window.event.keyCode;
	if ((code > 34 && code < 41) || (code > 47 && code < 58) || (code > 95 && code < 106) || code == 8 || code == 9 || code == 13 || code == 46){
		window.event.returnValue = true;
		return;
	}
	window.event.returnValue = false;
}



//팝업레이어
var $popup_layer_index = 9000;
function popup_layer($id, $seq, $option){

	if(!$id || !$seq) return;
	if(!$option) $option = {};
	$("#"+$id).load("/popup/popup_layer.html", {seq : $seq, zindex : $popup_layer_index});
	$popup_layer_index++;
}


//스크립트제거
function non_script($value){
	$value = $value.replace(/<(no)?script[^>]*>.*?<\/(no)?script>/ig, "");
	$value = $value.replace(/<style[^>]*>.*<\/style>/ig, "");
	$value = $value.replace(/<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>/ig, "");
	$value = $value.replace(/<\\w+\\s+[^<]*\\s*>/ig, "");
	$value = $value.replace(/&[^;]+;/ig, "");
	$value = $value.replace(/\\s\\s+/ig, "");

	return $value;
}



//게시판 내용부분 이미지 축소
function cmm_img_resize(max) {
	var content = document.getElementById("cmm_view_content");
	if (content) {
		max = (max) ? max : parseInt(content.clientWidth);
		var img_height = 0;
		var img = content.getElementsByTagName("img");

		for(i=0; i<img.length; i++) {
			if (img[i].width > max) {
				var rate = parseFloat(max / img[i].width);
				img_height = parseInt(img[i].height * rate);
				img[i].width = max;
				img[i].height = img_height;

				img[i].style.width = '';
				img[i].style.height = '';
			}
		}
	}
}


//길이
function check_length($str, $min, $max, $is_null){
	if($str.length >= $min && $str.length <= $max){
		return true;
	}else{
		if($is_null && $str.length == 0)	return true;
		else								return false;
	}
}

//sms 바이트체크
function reCount(str){
	var i,
		msglen=0;

	for(i=0; i<str.length; i++){
		var ch=str.charAt(i);

		if(escape(ch).length >4)	msglen += 2;
		else						msglen++;
	}

	return msglen;
}

function cutMsg(str, $Byte){
	$Byte = ($Byte) ? parseInt($Byte) : 80;

	var ret = i = "",
		msglen=0;

	for(i=0; i<str.length; i++){
		var ch=str.charAt(i);

		if(escape(ch).length >4)	msglen += 2
		else						msglen++;

		if(msglen > $Byte) break;
		ret += ch;
	}

	return ret;
}

function fnChkRemark(obj, strCnt) {
	var $value = obj.value,
		$len = $value.length;

	if($len > strCnt){
		alert(strCnt + '자 이하로 입력해 주세요. ');
		obj.value = $value.substring(0, strCnt);
        obj.focus();
	}
}


function get_filename($filename, returnType){
	returnType = (!returnType) ? "filename" : returnType;

	var filepath = filename = fileext = filetemp = "",
		$Arr = new Object(),
		file_arr = $filename.split("/");

	for(var i=0; i<file_arr.length-1; i++){
		filepath += file_arr[i]+'/';
	}

	filetemp = file_arr[file_arr.length-1].split(".");
	filename = filetemp[0];
	fileext = filetemp[1];

	switch(returnType){
		case "ext"		:	return	fileext;				break;
		case "filename"	:	return	filename+'.'+fileext;	break;
		case "filepath"	:	return	filepath;				break;
		case "all"		:
			$Arr.filepath = filepath;
			$Arr.filename = filename;
			$Arr.fileext = fileext;

			return	$Arr;
	}
}



function phone_format($val){
	$val = $val.replace(/[^0-9]/g, '');

	var tmp = '';

	if($val.length < 4){
		return $val;
	}else if($val.length < 7){
		tmp += $val.substr(0, 3);
		tmp += '-';
		tmp += $val.substr(3);
		return tmp;
	}else if($val.length < 11){
		tmp += $val.substr(0, 3);
		tmp += '-';
		tmp += $val.substr(3, 3);
		tmp += '-';
		tmp += $val.substr(6);
		return tmp;
	}else{
		tmp += $val.substr(0, 3);
		tmp += '-';
		tmp += $val.substr(3, 4);
		tmp += '-';
		tmp += $val.substr(7);
		return tmp;
	}

	return $val;
}

function getSecurimage(id){
	$.get("/lib/securimage/securimage_init.php",function(r){
		$("#"+id).html(r);
	});
}

function comma(number) {
     
    var minusText = "";
     
    if (parseFloat(number) < 0) {
        minusText = "-";
    }
     
    number = '' + number;
    number = number.replace("-", "");
    if (number.length > 3) {
 
        var mod = number.length % 3;
        var output = (mod > 0 ? (number.substring(0,mod)) : '');
        for (var i=0 ; i < Math.floor(number.length / 3); i++) {
            if ((mod == 0) && (i == 0))
                output += number.substring(mod+ 3 * i, mod + 3 * i + 3);
            else
                output+= ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
        }
        return (minusText + '' + output);
    }
    else return minusText + '' + number;
}
 