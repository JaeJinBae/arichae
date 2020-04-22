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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/pc/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/pc/slick/slick-theme.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/pc/css/main.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/pc/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/pc/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/pc/js/cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/pc/slick/slick.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0, viewport-fit=cover">
<style type="text/css">
html{
	width: 100%;
	min-width: 1200px;
	height: 100%;
}
body{
	width: 100%;
	height: 100%;
	background: gray;
}
#all_wrap{
	width: 100%;
	height: 100%;
	position: relative;
}
/* header */
.menu_bg{
	display: none;
	position: fixed;
	top:0;
	width: 100%;
	height: 100%;
	background: #222;
	opacity: 0.7;
	z-index: 9; 
}
#header_section{
	width: 100%;
	position: absolute;
	top: 30px;
	z-index: 15;
}

#header_top{
	width: 100%;	
}
#header_top > .logo_wrap{
	width: 100%;
	position: relative;
	text-align: center;
}
#header_top > .logo_wrap > #menu_bar_btn{
	position: absolute;
	left: 100px;
	cursor: pointer;
}
#header_top > .logo_wrap > #menu_logo{
	
}
#header_menu{
	display: none;
	position: absolute;
	top: 0;
	left: 50%;
	transform:translateX(-50%);
	width: 90%;
	min-width: 1200px;
	background: #fff;
	margin: 0 auto;
}
#header_menu > .menu_wrap{
	padding: 30px 0;
	padding-bottom: 100px;
}
#header_menu > .menu_wrap > #menu_close_btn{
	/* width: 100%; */
	text-align: right;
	padding: 0 30px;
	padding-bottom: 70px;
}
#header_menu > .menu_wrap > #menu_close_btn > img{
	cursor: pointer;
}
#header_menu > .menu_wrap > .gnb_wrap{
	width: 100%;
	overflow: hidden;
}
#header_menu > .menu_wrap > .gnb_wrap > .gnb{
	float: left;
	width: 16.5%;
	min-height: 350px;
	text-align: center;
	border-right: 1px solid #cfcfcf;
}
#header_menu > .menu_wrap > .gnb_wrap > .gnb:last-child{
	border: 0;
}
#header_menu > .menu_wrap > .gnb_wrap > .gnb > .gnb_txt{
	font-size: 20px;
	font-weight: 500;
}
#header_menu > .menu_wrap > .gnb_wrap > .gnb > .gnb_txt_below_bar{
	display: block;
	width: 30px;
	height:2px;
	background: #444;
	margin: 20px auto;
}
#header_menu > .menu_wrap > .gnb_wrap > .gnb > .lnb_wrap{
	
}
#header_menu > .menu_wrap > .gnb_wrap > .gnb > .lnb_wrap > .lnb{
	font-size: 16px;
	margin: 20px 0;
}
/* body */
#body_section{
	width: 100%;
	height: 100%;
	position: relative;
}
.section1{
	width: 100%;
	height: 100%;
	overflow: hidden;
}
.mainBanner1_txt{
	position: absolute;
	top: 15%;
	left: 10%;
	width: 500px;
}
.mainBanner_common{
	position: absolute;
	bottom: 30%;
	right: 5%;
	width: 220px;
}

.section2{
	width: 100%;
	position: absolute;
	bottom: 0;
}
.section2 > .box_wrap{
	width: 100%;
	overflow: hidden;
}
.section2 > .box_wrap > .box{
	width: 25%;
	float: left;
}
.section2 > .box_wrap > .box > a > img{
	width: 100%;
}
</style>
<script>
function replaceAll(str, searchStr, replaceStr) {
	return str.split(searchStr).join(replaceStr);
}

$(function(){
	
	$("#menu_bar_btn").click(function(){
		$("#header_top").css("visibility","hidden");
		$("#header_menu").fadeIn("fast");
		$(".menu_bg").css("display", "block");
	});
	
	$("#menu_close_btn").click(function(){
		$("#header_menu").fadeOut("fast");
		$("#header_top").css("visibility","visible");
		$(".menu_bg").css("display", "none");
	})
	
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
		/* .css("opacity", "0.1") */
	});
	
	$(".main_banner").slick({
		arrows:true,
		dots:true,
		infinite:true,
		speed:500,
		fade:true,
		cssEase:'linear',
		autoplay:true,
		autoplaySpeed:4000
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
					<%-- <div class="banner_img"><img src="${pageContext.request.contextPath}/resources/pc/img/mainBanner2.jpg"></div> --%>
					<div class="banner_img">
						<img src="${pageContext.request.contextPath}/resources/pc/img/1-1.png">
					</div>
					<div class="banner_img">
						<img src="${pageContext.request.contextPath}/resources/pc/img/2-1.png">
					</div>
					<div class="banner_img">
						<img src="${pageContext.request.contextPath}/resources/pc/img/3-1.png">
					</div>
					<div class="banner_img">
						<img src="${pageContext.request.contextPath}/resources/pc/img/4-1.png">
					</div>
				</div>
			</div>
			<div class="section2">
				<div class="box_wrap">
					<%-- <div class="box link_box"><a href="${pageContext.request.contextPath}/menu02_01"><img src="${pageContext.request.contextPath}/resources/pc/img/main_box1_1.jpg"></a></div>
					<div class="box link_box"><a href="${pageContext.request.contextPath}/menu05_01"><img src="${pageContext.request.contextPath}/resources/pc/img/main_box2_1.jpg"></a></div>
					<div class="box link_box"><a href="${pageContext.request.contextPath}/menu05_02"><img src="${pageContext.request.contextPath}/resources/pc/img/main_box3_1.jpg"></a></div>
					<div class="box"><a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/pc/img/main_box4.jpg"></a></div> --%>
				</div>
				<div class="bottom_board_wrap">
					<img src="${pageContext.request.contextPath}/resources/pc/img/main_bottom.jpg">
				</div>
			</div>
		</div>
		
		<div id="footer_section">
			<img src="${pageContext.request.contextPath}/resources/pc/img/footer_all.jpg">
		</div>
	</div>
</body>
</html>