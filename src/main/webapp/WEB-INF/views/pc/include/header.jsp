<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header_top">
	<div class="logo_wrap">
		<p id="menu_bar_btn"><img src="${pageContext.request.contextPath}/resources/pc/img/menu_bar_btn.png"></p>
		<p id="menu_logo"><a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/pc/img/logo.png"></a></p>
	</div>
</div>
<div id="header_menu">
	<div class="menu_wrap">
		<p id="menu_close_btn"><img src="${pageContext.request.contextPath}/resources/pc/img/menu_close_btn.png"></p>
		<ul class="gnb_wrap">
			<li class="gnb">
				<span class="gnb_txt">아리채병원소개</span>
				<span class="gnb_txt_below_bar"></span>
				<ul class="lnb_wrap">
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu01_01">아리채 소개</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu01_02">재단 이사장 인사말</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu01_03">병원장 인사말</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu01_04">의료진 안내</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu01_05">병원 둘러보기</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu01_06">산책로</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu01_07">오시는 길</a></li>
				</ul>
			</li>
			<li class="gnb">
				<span class="gnb_txt">의학적치료</span>
				<span class="gnb_txt_below_bar"></span>
				<ul class="lnb_wrap">
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu02_01">통합암면역치료</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu02_02">흉선추출물 면역주사요법</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu02_03">미슬토 주사요법</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu02_04">폴리펩타이드 면역치료</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu02_05">PMBL설하면역치료</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu02_05">셀레늄요법</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu02_06">영양 수액 비타민 요법</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu02_07">항암면역세포치료</a></li>
				</ul>
			</li>
			<li class="gnb">
				<span class="gnb_txt">특수치료</span>
				<span class="gnb_txt_below_bar"></span>
				<ul class="lnb_wrap">
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu03_01">고주파온열치료</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu03_02">암성통증치료</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu03_03">체외역박동치료</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu03_04">식이요법</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu03_05">해독요법</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu03_06">산소치료</a></li>
				</ul>
			</li>
			<li class="gnb">
				<span class="gnb_txt">한의학적 치료</span>
				<span class="gnb_txt_below_bar"></span>
				<ul class="lnb_wrap">
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu04_01">한약치료</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu04_02">약침치료</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu04_03">왕쑥뜸치료</a></li>
				</ul>
			</li>
			<li class="gnb">
				<span class="gnb_txt">맞춤형 프로그램</span>
				<span class="gnb_txt_below_bar"></span>
				<ul class="lnb_wrap">
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu05_01">맞춤형 신체관리</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu05_02">맞춤형 스트레스 관리</a></li>
				</ul>
			</li>
			<li class="gnb">
				<span class="gnb_txt">고객센터</span>
				<span class="gnb_txt_below_bar"></span>
				<ul class="lnb_wrap">
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu06_01">공지사항</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu06_02">비급여항목</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu06_02">병원소식</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu06_02">아리채 식단</a></li>
					<li class="lnb"><a href="${pageContext.request.contextPath}/menu06_02">건강상식</a></li>
				</ul>
			</li>
		</ul>
	</div><!-- menu-wrap -->
</div><!-- header_menu end -->
<script>
function statisticRegister(info){
	$.ajax({
		url:"${pageContext.request.contextPath}/statisticRegister",
		type:"post",
		data:JSON.stringify(info),
		contentType : "application/json; charset=UTF-8",
		dataType:"text",
		async:false,
		success:function(json){
			console.log(json);
		},
		error:function(request,status,error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

$(function(){
	var access_url = document.referrer;
	var ua = navigator.userAgent.toLowerCase();
    var browser = "";
    var os = "";
	if(access_url.indexOf("arichae.com") == -1 && access_url.indexOf("priveskin.cafe24") == -1){
		if(access_url.length < 4){
			access_url="직접 또는 즐겨찾기";
		}
		//browser
		if( ua.indexOf('msie 7') != -1 ) {
	        browser = "Internet Explorer7";
	    }else if( ua.indexOf('trident/4.0') != -1 ) {
	    	/* IE8 부터는 msie 값으로 브라우저 버전을 분별할수 없음 trident 값으로 해야한다. */
	    	browser = "Internet Explorer8";
	    }else if( ua.indexOf('trident/5.0') != -1 ) {
	        browser = "Internet Explorer9";
	    }else if( ua.indexOf('trident/6.0') != -1 ) {
	        browser = "Internet Explorer10";
	    }else if( ua.indexOf('trident/7.0') != -1 ) {
	        browser = "Internet Explorer11";
	    }else if( ua.indexOf('chrome') != -1 ) {
	        browser = "Chrome";
	    }else if( !!window.opera ) {
	        browser = "Opera";
	    }else if( ua.indexOf('safari') != -1 ) {
	        browser = "Safari";
	    }else if( ua.indexOf('applewebkit/5') != -1 ) {
	        browser = "Safari3";
	    }else if( ua.indexOf('mac') != -1 ) {
	        browser = "Mac";
	    }else if( ua.indexOf('firefox') != -1 ) {
	        browser = "Firefox";
	    }else{
	    	browser = "Unknown";
	    }
	    
	    //os
		if( ua.indexOf("NT 5.1") != -1 ) {
			os = "Windows XP";
	    }else if( ua.indexOf("nt 6.0") != -1 ) {
	    	os = "Windows Vista";
	    }else if( ua.indexOf("nt 6.1") != -1 ) {
	    	os = "Windows 7";
	    }else if( ua.indexOf("nt 6.2") != -1 ) {
	    	os = "Windows 8";
	    }else if(ua.indexOf("nt 10.0") != -1){
	    	os = "Windows 10";
	    }else if( ua.indexOf("linux") != -1 ) {
	    	os = "Linux";
	    }else if( ua.indexOf("mac") != -1 ) {
	    	os = "Mac";
	    }else{
	    	os = "Unknown";
	    }
	    
		var s_d = new Date();
		var s_y = s_d.getFullYear();
		var s_m = s_d.getMonth()+1;
		s_m = (s_m > 9) ? s_m+"":"0"+s_m;
		var s_dd = s_d.getDate();
		s_dd = (s_dd > 9) ? s_dd+"":"0"+s_dd;
		var s_date = s_y+"-"+s_m+"-"+s_dd;
		var s_t_h = s_d.getHours();
		var s_t_m = s_d.getMinutes();
		var s_dow_arr = new Array("일", "월", "화", "수", "목", "금", "토");
		var s_dow = s_dow_arr[s_d.getDay()];
		
		var info = {"date":s_date, "dayofweek":s_dow, "hour":s_t_h, "minute":s_t_m, "browser":browser, "os":os, "prev_url":access_url};
		//statisticRegister(info);
	}
});
</script>