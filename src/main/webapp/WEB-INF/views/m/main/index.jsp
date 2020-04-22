<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아리채요양병원</title>
<!-- <meta name="naver-site-verification" content="d53046e7ecfe442e3b973c1baeb37ec7fc75031d"/>
<meta name="description" content="대구 암재활요양병원,통합의학적 면역치유, 암면역치료, 고주파 온열암 치료, 고압산소, 식이치료">
<meta property="og:type" content="website">
<meta property="og:title" content="아리채요양병원">
<meta property="og:description" content="대구 암재활요양병원,통합의학적 면역치유, 암면역치료, 고주파 온열암 치료, 고압산소, 식이치료">
<meta property="og:image" content="http://www.arichae.com/logo.png">
<meta property="og:url" content="http://www.arichae.com">
<link rel="canonical" href="http://www.arichae.com">
<link rel="apple-touch-icon" href="http://www.arichae.com/logo.png"/>
<link rel="shortcut icon" href="http://www.arichae.com/logo.png"/> -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/pc/css/style.reset.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/pc/css/style.common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/m/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/m/slick/slick-theme.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/m/css/main.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/pc/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/m/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/m/js/cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/m/slick/slick.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0, viewport-fit=cover">
<style type="text/css">
body{
	background: gray;
}
#all_wrap{
	width: 100%;
	position: relative;
	padding-top: 50px;
}
.menu_bg{
	display: none;
	position: fixed;
	top: 0;
	width: 100%;
	height: 2000px;
	background: #222;
	opacity: 0.7;
	z-index: 9;
}
#header_section{
	width: 100%;
	position: fixed;
	top: 0px;
	z-index: 15;
}

#header_top{
	width: 100%;
	background: #fff;
	border-bottom: 1px solid #ccc;
}
#header_top > .logo_wrap{
	width: 100%;
	position: relative;
	text-align: center;
	overflow: hidden;
}

#header_top > .logo_wrap > #menu_logo{
	float: left;
	width: 200px;
	padding-left: 20px;
}
#header_top > .logo_wrap > #menu_bar_btn{
	float: right;
	width: 60.5px;
}
#header_menu{
	display: none;
	width: 100%;
	background: #fff;
	margin: 0 auto;
}
#header_menu > .menu_wrap{
	width: 100%;
}
#header_menu > .menu_wrap > #menu_close_btn > img{
	cursor: pointer;
}
#header_menu > .menu_wrap > .gnb_wrap{
	width: 100%;
	text-align: left;
}
#header_menu > .menu_wrap > .gnb_wrap > .gnb{
	width: 100%;
	border-bottom: 1px solid #cfcfcf;
}
#header_menu > .menu_wrap > .gnb_wrap > .gnb > .gnb_txt{
	display:inline-block;
	width: 100%;
	font-size: 15px;
	font-weight: 400;
	padding: 17px 20px;
	
}
#header_menu > .menu_wrap > .gnb_wrap > .gnb > .lnb_wrap{
	display: none;
	padding-left: 50px;
	background: #f5f5f5;
}
#header_menu > .menu_wrap > .gnb_wrap > .gnb > .lnb_wrap > .lnb{
	font-size: 14px;
	line-height: 40px;
}
#header_menu > .menu_wrap > .gnb_wrap > .gnb > .lnb_wrap > .lnb:last-child{
	/* padding: 0; */
}

.section1{
	width: 100%;
}


.section2{
	width: 100%;
}
.section2 > .box_wrap{
	width: 100%;
}
.section2 > .box_wrap > .box{
	width: 100%;
}
.section2 > .box_wrap > .box > a > img{
	width: 100%;
}

#footer_section{
	padding-bottom: 55px;
}

.quick_counseling{
	position: fixed;
	bottom:0;
	width: 100%;
}
</style>
<script>
function replaceAll(str, searchStr, replaceStr) {
	return str.split(searchStr).join(replaceStr);
}

$(function(){
	$("#menu_bar_btn").click(function(){
		var tag_state = $("#header_menu").css("display");
		$("#header_menu").slideToggle("fast");
		$(".menu_bg").slideToggle("fast");
		var tag_src = $(this).find("img").attr("src");
		var new_src;
		if(tag_state == "none"){
			new_src = replaceAll(tag_src, "bar", "close");
			$(this).find("img").attr("src", new_src);
		}else{
			new_src = replaceAll(tag_src, "close", "bar");
			$(this).find("img").attr("src", new_src);
		}
	});
	
	$(".gnb_txt").click(function(){
		$(this).parent().find(".lnb_wrap").slideToggle("fast");
	});
	
	$(".link_box").mouseover(function(){
		var idx = $(this).index();
		var src1 = $(this).find("a > img").attr("src");
		var src2 = replaceAll(src1, "_1.jpg", "_2.jpg");
		
		$(this).find("a > img").stop().attr("src", src2).animate({opacity:1},500);

	}).mouseout(function(){
		var idx = $(this).index();
		var src1 = $(this).find("a > img").attr("src");
		var src2 = replaceAll(src1, "_2.jpg", "_1.jpg");
		
		$(this).find("a > img").stop().attr("src", src2).animate({opacity:1},500);
		/*$(this).find("a > img").css("opacity", "0.1").stop().attr("src", src2).animate({opacity:1},500); */
	});
});
</script>
</head>
<body>
	<!-- <div id="popup_wrap">
	
	</div> -->
	<div id="all_wrap">
		<div id="header_section">
			<jsp:include page="../include/header.jsp"></jsp:include>
		</div>
		<div class="menu_bg"></div>
		<div id="body_section">
			<div class="section1">
				<div class="main_banner">
					<div class="banner_img"><img src="${pageContext.request.contextPath}/resources/m/img/mainBanner1.jpg"></div>
				</div>
			</div>
			<div class="section2">
				<div class="box_wrap">
					<div class="box link_box"><a href="${pageContext.request.contextPath}/m/menu02_01"><img src="${pageContext.request.contextPath}/resources/m/img/main_box1_1.jpg"></a></div>
					<div class="box link_box"><a href="${pageContext.request.contextPath}/m/menu05_01"><img src="${pageContext.request.contextPath}/resources/m/img/main_box2_1.jpg"></a></div>
					<div class="box link_box"><a href="${pageContext.request.contextPath}/m/menu05_02"><img src="${pageContext.request.contextPath}/resources/m/img/main_box3_1.jpg"></a></div>
				</div>
			</div>
		</div>
		<div class="section3">
			<div class="sec3_wrap">
				<img src="${pageContext.request.contextPath}/resources/m/img/main_section3.jpg">
			</div>
		</div>
		<div class="section4">
			<div class="sec4_wrap">
				<img src="${pageContext.request.contextPath}/resources/m/img/main_section4.jpg">
			</div>
		</div>
		<div id="footer_section">
			<img src="${pageContext.request.contextPath}/resources/m/img/footer_all.jpg">
		</div>
		<div class="quick_counseling">
			<img src="${pageContext.request.contextPath}/resources/m/img/quick_counseling.png">
		</div>
	</div><!-- all_wrap end -->
</body>
</html>