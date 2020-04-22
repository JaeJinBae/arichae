$(function(){

	//좌측메뉴
	$(".left_menu dt").click(function(e){
		if($(this).parent().find("dd").css("display") == "block"){
			$(".left_menu dd").slideUp();
		}else{
			$(".left_menu dd").slideUp();
			$(this).nextUntil('dt').slideDown();
		}
		
		return false;
	});


	//이메일 selectbox
	$("#m_emailcode").on("change", function(){
		var $val = $(this).val();

		if(!$val)	$("#m_email2").val('');
		else		$("#m_email2").val( $(this).children("option:selected").text() );
	});

    $("#r_emailcode").on("change", function(){
        var $text = $(this).children("option:selected").text(),
            $val = $(this).val();

        if(!$val)	$("#r_email2").val('');
        else		$("#r_email2").val($text);
    });

	menu_it('A');		//메뉴
	bcode_it('A');		//기초코드
	usermenu_it('A');		//메뉴
});


//검색버튼
function search_it(fm){
	if(TRIM(fm.input_key.value) == ""){
		alert("검색어를 입력하세요");
		$("input[name=input_key]").val('').focus();
		return false;
	}
}


//검색 reload
function search_reload_it($param){
	location.href = (window.location.pathname) + "?" + $param;
}


//관리자 로그인
function login_it(fm){
	if( !$("#login").validate() )	return false;
}


//메뉴관리
function menu_it($target, $param){
	$param = ($param == undefined) ? "" : $param;

	if($target == "A"){
		$("#menu_B").empty();
	}else if($target == "B"){
		$("#menu_C").empty();
	}

	$("#menu_" + $target).load("menu_"+$target+".html?type=" + $target + "&" + $param);
}

//사용자메뉴관리
function usermenu_it($target, $param){
	$param = ($param == undefined) ? "" : $param;

	if($target == "A"){
		$("#usermenu_B").empty();
	}else if($target == "B"){
		$("#usermenu_C").empty();
	}

	$("#usermenu_" + $target).load("usermenu_"+$target+".html?type=" + $target + "&" + $param);
}



//기본환경설정
function setting_it($mode, $param){
	var fm = document.setting,
		answer = "";

	if($mode == "submit"){
		fm.submit();

	}else if($mode == "reset"){
		fm.reset();

	}else if($mode == "attach_del"){
		answer = confirm("삭제하시겠습니까?");

		if(answer){
			$.post("setting_proc.php", {mode : $mode, column : $param}, function(data){
				if(data == 0){
					alert("오류가 발생하였습니다");
					return;
				}else{
					$("#file_" + $param).empty();
				}
			});
		}

	}else if($mode == "smtp"){
		instance_iframe("setting_download.php?str=smtp&kor=메일계정설정");
	}
}


//기초코드
function bcode_it($target, $param){
	$param = ($param == undefined) ? "" : $param;

	if($target == "A")			$("#bcode_B, #bcode_C").empty();
	else if($target == "B")		$("#bcode_C").empty();

	$("#bcode_" + $target).load("bcode_"+$target+".html?type=" + $target + "&" + $param);
}


//관리자
function adm_it($mode, $param){
	var fm = document.adm,
		$cnt = $("#adm input:checkbox:checked").not("#selectall").size(),
		answer = "";

	if($mode == "insert_form"){
		location.href = "adm_form.html?mode=insert&" + $param;

	}else if($mode == "list"){
		location.href = "adm_list.html?" + $param;

	}else if($mode == "reset"){
		fm.reset();
		answer = confirm("리스트페이지로 이동하시겠습니까?");
		if(answer)	adm_it('list', $param);

	}else if($mode == "delete"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("삭제할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("삭제하시겠습니까?");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_delete"){
		answer = confirm("삭제하시겠습니까?");
		if(answer)	location.href = "adm_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "submit"){
		if( !$("#adm").validate() ) return;

		if( fm.m_email1.value || fm.m_email2.value ){
			if(!reg_exp("email", fm.m_email1.value + "@" + fm.m_email2.value)){
				alert("이메일 형식이 틀립니다");
				$("#m_email1").select();
				return;
			}
		}

		fm.submit();
	}
}


//가입회원
function member_it($mode, $param){
	var fm = document.member,
		$cnt = $("#member input:checkbox:checked").not("#selectall").size(),
		answer = "";

	if($mode == "insert_form"){
		location.href = "member_form.html?mode=insert&" + $param;

	}else if($mode == "list"){
		location.href = "member_list.html?" + $param;

	}else if($mode == "reset"){
		fm.reset();
		answer = confirm("리스트페이지로 이동하시겠습니까?");
		if(answer)	jmember_it('list', $param);

	}else if($mode == "delete"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("삭제할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("삭제하시겠습니까?");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_delete"){
		answer = confirm("삭제하시겠습니까?");
		if(answer)	location.href = "member_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "submit"){
		if( !$("#member").validate() ) return;

		if( fm.m_email1.value && fm.m_email2.value ){
			if(!reg_exp("email", fm.m_email1.value + "@" + fm.m_email2.value)){
				alert("이메일 형식이 틀립니다");
				$("#m_email1").focus();
				return;
			}
		}

		fm.submit();

	}else if($mode == "download"){
		instance_iframe("member_download.php?seq="+$param);

	}else if($mode == "attach_del"){
		answer = confirm("삭제하시겠습니까?");

		if(answer){
			$.post("member_proc.php", {mode : $mode, seq : $param}, function(data){
				if(data == 0){
					alert("오류가 발생하였습니다");
					return;
				}else{
					$("#file_" + $param).html("<input type='file' name='upload[]' style='width:450px' /><br>");
				}
			});
		}

	}else if($mode == "each_withdraw"){
		answer = confirm("탈퇴처리하시겠습니까?");
		if(answer)	location.href = "member_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "withdraw"){
		fm.mode.value = $mode;
		fm.withdraw.value = "Y";

		if($cnt <= 0){
			alert("탈퇴할 회원을 선택하세요");
			return;
		}else{
			answer = confirm("탈퇴처리하시겠습니까");
			if(answer)	fm.submit();
		}

	}else if($mode == "excel_all"){
		var $total = Number( $("input[name=total]").val() );

		if($total <= 0){
			alert("엑셀로 출력할 항목이 없습니다");
			return;
		}else{
			instance_iframe("member_excel.php?mode="+ $mode +"&" + $param);
		}

	}else if($mode == "excel"){
		var $seq_list = "",
			$total = Number( $("input[name=total]").val() );

		if($total <= 0){
			alert("엑셀로 출력할 항목이 없습니다");
			return;
		}else{
			$("#member input:checkbox:checked").not("#selectall").each(function(){
				$seq_list += $(this).val() + ",";
			});

			$seq_list = $seq_list.replace(/,$/g, '');

			instance_iframe("member_excel.php?"+$param+"&seq_list="+$seq_list+"&cnt="+$cnt+"&mode="+$mode);
		}
	}
}



//탈퇴회원
function withdrawal_it($mode, $param){
	var fm = document.withdrawal,
		$cnt = $("#withdrawal input:checkbox:checked").not("#selectall").size(),
		answer = "";

	if($mode == "insert_form"){
		location.href = "withdrawal_form.html?mode=insert&" + $param;

	}else if($mode == "list"){
		location.href = "withdrawal_list.html?" + $param;

	}else if($mode == "delete"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("삭제할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("삭제하시겠습니까?");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_delete"){
		answer = confirm("삭제하시겠습니까?");
		if(answer)	location.href = "withdrawal_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "withdraw"){
		fm.mode.value = $mode;
		fm.withdraw.value = "N";

		if($cnt <= 0){
			alert("탈퇴취소할 회원을 선택하세요");
			return;
		}else{
			answer = confirm("탈퇴취소처리하시겠습니까");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_withdraw"){
		answer = confirm("탈퇴취소처리하시겠습니까?");
		if(answer)	location.href = "withdrawal_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "excel_all"){
		var $total = Number( $("input[name=total]").val() );

		if($total <= 0){
			alert("엑셀로 출력할 항목이 없습니다");
			return;
		}else{
			instance_iframe("withdrawal_excel.php?mode="+ $mode +"&" + $param);
		}

	}else if($mode == "excel"){
		var $seq_list = "",
			$total = Number( $("input[name=total]").val() );

		if($total <= 0){
			alert("엑셀로 출력할 항목이 없습니다");
			return;
		}else{
			$("#withdrawal input:checkbox:checked").not("#selectall").each(function(){
				$seq_list += $(this).val() + ",";
			});

			$seq_list = $seq_list.replace(/,$/g, '');

			instance_iframe("withdrawal_excel.php?"+$param+"&seq_list="+$seq_list+"&cnt="+$cnt+"&mode="+$mode);
		}
	}
}


//게시판설정
function bs_it($mode, $param){
	var fm = document.bs,
		$cnt = $("#bs input:checkbox:checked").not("#selectall").size(),
		answer = "";

	if($mode == "insert_form"){
		location.href = "bs_form.html?mode=insert&" + $param;

	}else if($mode == "list"){
		location.href = "bs_list.html?" + $param;

	}else if($mode == "reset"){
		fm.reset();
		answer = confirm("리스트페이지로 이동하시겠습니까?");
		if(answer)	bs_it('list', $param);

	}else if($mode == "delete"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("삭제할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("삭제하시겠습니까?");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_delete"){
		answer = confirm("삭제하시겠습니까?");
		if(answer)	location.href = "bs_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "submit"){
		if( !$("#bs").validate() ) return;
		fm.submit();

	}else if($mode == "attach_del"){
		answer = confirm("삭제하시겠습니까?");

		if(answer){
			$.post("bs_proc.php", {mode : $mode, seq : $param}, function(data){
				if(data == 0){
					alert("오류가 발생하였습니다");
					return;
				}else{
					$("#noimage").html("<input type='file' name='upload' style='width:450px' />");
				}
			});
		}

	}else if($mode == "download"){
		instance_iframe("bs_download.php?seq="+$param);

	}
}


//페이지설정
function page_it($mode, $param){
	var fm = document.pagesetting,
		$cnt = $("#pagesetting input:checkbox:checked").not("#selectall").size(),
		answer = "";

	if($mode == "insert_form"){
		location.href = "page_form.html?mode=insert&" + $param;

	}else if($mode == "list"){
		location.href = "page_list.html?" + $param;

	}else if($mode == "reset"){
		fm.reset();
		answer = confirm("리스트페이지로 이동하시겠습니까?");
		if(answer)	page_it('list', $param);

	}else if($mode == "delete"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("삭제할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("삭제하시겠습니까?");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_delete"){
		answer = confirm("삭제하시겠습니까?");
		if(answer)	location.href = "pagesetting_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "submit"){
		if( !$("#pagesetting").validate() ) return;
		fm.submit();

	}else if($mode == "attach_del"){
		answer = confirm("삭제하시겠습니까?");

		if(answer){
			$.post("pagesetting_proc.php", {mode : $mode, seq : $param}, function(data){
				if(data == 0){
					alert("오류가 발생하였습니다");
					return;
				}else{
					$("#noimage").html("<input type='file' name='upload' style='width:450px' />");
				}
			});
		}

	}else if($mode == "download"){
		instance_iframe("bs_download.php?seq="+$param);

	}
}


//INCLUDE 설정
function include_it($mode, $param){
    var fm = document.include,
        $cnt = $("#include input:checkbox:checked").not("#selectall").size(),
        answer = "";

    if($mode == "insert_form"){
        location.href = "include_form.html?mode=insert&" + $param;

    }else if($mode == "list"){
        location.href = "include_list.html?" + $param;

    }else if($mode == "reset"){
        fm.reset();
        answer = confirm("리스트페이지로 이동하시겠습니까?");
        if(answer)	include_it('list', $param);

    }else if($mode == "delete"){
        fm.mode.value = $mode;

        if($cnt <= 0){
            alert("삭제할 내역을 선택하세요");
            return;
        }else{
            answer = confirm("삭제하시겠습니까?");
            if(answer)	fm.submit();
        }

    }else if($mode == "each_delete"){
        answer = confirm("삭제하시겠습니까?");
        if(answer)	location.href = "include_proc.php?mode="+ $mode +"&" + $param;

    }else if($mode == "submit"){
        if( !$("#include").validate() ) return;
        fm.submit();

    }else if($mode == "attach_del"){
        answer = confirm("삭제하시겠습니까?");

        if(answer){
            $.post("include_proc.php", {mode : $mode, seq : $param}, function(data){
                if(data == 0){
                    alert("오류가 발생하였습니다");
                    return;
                }else{
                    $("#noimage").html("<input type='file' name='upload' style='width:450px' />");
                }
            });
        }

    }else if($mode == "download"){
        instance_iframe("bs_download.php?seq="+$param);

    }
}


//게시판
function board_it($mode, $param){
	var fm = document.board,
		$cnt = $("#board input:checkbox:checked").not("#selectall").size(),
		answer = "";

	if($mode == "insert_form"){
		location.href = "board_form.html?mode=insert&" + $param;

	}else if($mode == "list"){
		location.href = "board_list.html?" + $param;

	}else if($mode == "reply"){
		location.href = "board_form.html?" + $param;

	}else if($mode == "reset"){
		fm.reset();
		answer = confirm("리스트페이지로 이동하시겠습니까?");
		if(answer)	board_it('list', $param);

	}else if($mode == "delete"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("삭제할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("삭제하시겠습니까?");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_delete"){
		answer = confirm("삭제하시겠습니까?");
		if(answer)	location.href = "board_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "submit"){
		if( !$("#board").validate() ) return;
		fm.submit();

	}else if($mode == "image_modify"){
		$.post("board_proc.php", {mode : "crop_image_info", seq : $param}, function(data){
			var $JSON = $.parseJSON(data);

			if($JSON.TorF == "false"){
				alert("오류가 발생하였습니다 재시도 하여 주시기 바랍니다");
				return;
			}else{
				$("#tr_crop_image").show();
				$("#crop_image").html("<img src='"+ $JSON.URL +"' width='"+ $JSON.WIDTH +"' height='"+ $JSON.HEIGHT +"'>").css("text-align", "center");
				setTimeout(function(){
					vboard_it("crop");
					document.board_crop.seq.value = $param;
				}, 1000);
			}
		});

	}else if($mode == "attach_del"){
		var $index = $val = "";

		$("[id^=file_]").each(function(i){
			$val = $(this).attr("id").replace("file_", "");

			if($val == $param)		$index = i;
		});

		answer = confirm("삭제하시겠습니까?");

		$.post("board_proc.php", {mode : $mode, seq : $param}, function(data){
			if(data == 0){
				alert("오류가 발생하였습니다");
				return;
			}else{
				$("#file_" + $param).html("<input type='file' name='upload["+ $index +"]' style='width:450px' />");
			}
		});

	}else if($mode == "download"){
		instance_iframe("board_download.php?seq="+$param);

	}else if($mode == "attach_remove"){
		$("#attach_" + $param).remove();

	}else if($mode == "tap_change"){
		if($cnt <= 0){
			alert("이동할 게시물을 선택하세요");
			return;
		}else{
			var $seq_list = "";

			$(":checkbox").not("#selectall").each(function(i){
				if( $(this).is(":checked") )	$seq_list += $(this).val() + "_";
			});

			$seq_list = $seq_list.replace(/_$/g, '');

			Layer_Popup("board_move.html", "Scod=" + $param + "&seq_list=" + $seq_list);
		}

	}
}


//상담관리
function inquire_it($mode, $param){
	var fm = document.inquire,
		$cnt = $("#inquire input:checkbox:checked").not("#selectall").size(),
		answer = "";

	if($mode == "insert_form"){
		location.href = "inquire_form.html?mode=insert&" + $param;

	}else if($mode == "list"){
		location.href = "inquire_list.html?" + $param;

	}else if($mode == "reset"){
		fm.reset();
		answer = confirm("리스트페이지로 이동하시겠습니까?");
		if(answer)	inquire_it('list', $param);

	}else if($mode == "delete"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("삭제할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("삭제하시겠습니까?");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_delete"){
		answer = confirm("삭제하시겠습니까?");
		if(answer)	location.href = "inquire_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "submit"){
		if( !$("#inquire").validate() ) return;

		if( fm.m_email1.value && fm.m_email2.value ){
			if(!reg_exp("email", fm.m_email1.value + "@" + fm.m_email2.value)){
				alert("이메일 형식이 틀립니다");
				$("#m_email1").focus();
				return;
			}
		}

		fm.submit();

	}else if($mode == "excel_all"){
		var $total = Number( $("input[name=total]").val() );

		if($total <= 0){
			alert("엑셀로 출력할 항목이 없습니다");
			return;
		}else{
			instance_iframe("inquire_excel.php?mode="+$mode);
		}


	}else if($mode == "excel"){
		var $seq_list = "",
			$total = Number( $("input[name=total]").val() );

		if($total <= 0){
			alert("엑셀로 출력할 항목이 없습니다");
			return;
		}else{
			$("#inquire input:checkbox:checked").not("#selectall").each(function(){
				$seq_list += $(this).val() + ",";
			});

			$seq_list = $seq_list.replace(/,$/g, '');

			instance_iframe("inquire_excel.php?"+$param+"&seq_list="+$seq_list+"&cnt="+$cnt+"&mode="+$mode);
		}

	}else if($mode == "download"){
		instance_iframe("inquire_download.php?seq="+$param);

	}else if($mode == "attach_del"){
		answer = confirm("삭제하시겠습니까?");

		if(answer){
			$.post("inquire_proc.php", {mode : $mode, seq : $param}, function(data){
				if(data == 0){
					alert("오류가 발생하였습니다");
					return;
				}else{
					$("#file_" + $param).html("<input type='file' name='upload[]' style='width:450px' /><br>");
				}
			});
		}

	}
}

//예약관리
function reserve_it($mode, $param){
    var fm = document.reserve,
        $cnt = $("#reserve input:checkbox:checked").not("#selectall").size(),
        answer = "";

    if($mode == "insert_form"){
        location.href = "reserve_form.html?mode=insert&" + $param;

    }else if($mode == "list"){
        location.href = "reserve_list.html?" + $param;

    }else if($mode == "reset"){
        fm.reset();
        answer = confirm("리스트페이지로 이동하시겠습니까?");
        if(answer)	reserve_it('list', $param);

    }else if($mode == "delete"){
        fm.mode.value = $mode;

        if($cnt <= 0){
            alert("삭제할 내역을 선택하세요");
            return;
        }else{
            answer = confirm("삭제하시겠습니까?");
            if(answer)	fm.submit();
        }

    }else if($mode == "each_delete"){
        answer = confirm("삭제하시겠습니까?");
        if(answer)	location.href = "reserve_proc.php?mode="+ $mode +"&" + $param;

    }else if($mode == "submit"){
        if( !$("#reserve").validate() ) return;

        if( fm.m_email1.value && fm.m_email2.value ){
            if(!reg_exp("email", fm.m_email1.value + "@" + fm.m_email2.value)){
                alert("이메일 형식이 틀립니다");
                $("#m_email1").focus();
                return;
            }
        }

        fm.submit();

    }else if($mode == "excel_all"){
        var $total = Number( $("input[name=total]").val() );

        if($total <= 0){
            alert("엑셀로 출력할 항목이 없습니다");
            return;
        }else{
            instance_iframe("reserve_excel.php?mode="+$mode);
        }


    }else if($mode == "excel"){
        var $seq_list = "",
            $total = Number( $("input[name=total]").val() );

        if($total <= 0){
            alert("엑셀로 출력할 항목이 없습니다");
            return;
        }else{
            $("#reserve input:checkbox:checked").not("#selectall").each(function(){
                $seq_list += $(this).val() + ",";
            });

            $seq_list = $seq_list.replace(/,$/g, '');

            instance_iframe("reserve_excel.php?"+$param+"&seq_list="+$seq_list+"&cnt="+$cnt+"&mode="+$mode);
        }

    }else if($mode == "download"){
        instance_iframe("reserve_download.php?seq="+$param);

    }else if($mode == "attach_del"){
        answer = confirm("삭제하시겠습니까?");

        if(answer){
            $.post("reserve_proc.php", {mode : $mode, seq : $param}, function(data){
                if(data == 0){
                    alert("오류가 발생하였습니다");
                    return;
                }else{
                    $("#file_" + $param).html("<input type='file' name='upload[]' style='width:450px' /><br>");
                }
            });
        }

    }
}

//파트너관리
function partner_it($mode, $param){
	var fm = document.partner,
		$cnt = $("#partner input:checkbox:checked").not("#selectall").size(),
		answer = "";

	if($mode == "insert_form"){
		location.href = "partner_form.html?mode=insert&" + $param;

	}else if($mode == "list"){
		location.href = "partner_list.html?" + $param;

	}else if($mode == "reset"){
		fm.reset();
		answer = confirm("리스트페이지로 이동하시겠습니까?");
		if(answer)	partner_it('list', $param);

	}else if($mode == "delete"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("삭제할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("삭제하시겠습니까?");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_delete"){
		answer = confirm("삭제하시겠습니까?");
		if(answer)	location.href = "partner_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "submit"){
		if( !$("#partner").validate() ) return;

		if( fm.m_email1.value && fm.m_email2.value ){
			if(!reg_exp("email", fm.m_email1.value + "@" + fm.m_email2.value)){
				alert("이메일 형식이 틀립니다");
				$("#m_email1").focus();
				return;
			}
		}

		fm.submit();

	}else if($mode == "excel_all"){
		var $total = Number( $("input[name=total]").val() );

		if($total <= 0){
			alert("엑셀로 출력할 항목이 없습니다");
			return;
		}else{
			instance_iframe("partner_excel.php?mode="+$mode);
		}


	}else if($mode == "excel"){
		var $seq_list = "",
			$total = Number( $("input[name=total]").val() );

		if($total <= 0){
			alert("엑셀로 출력할 항목이 없습니다");
			return;
		}else{
			$("#partner input:checkbox:checked").not("#selectall").each(function(){
				$seq_list += $(this).val() + ",";
			});

			$seq_list = $seq_list.replace(/,$/g, '');

			instance_iframe("partner_excel.php?"+$param+"&seq_list="+$seq_list+"&cnt="+$cnt+"&mode="+$mode);
		}

	}else if($mode == "download"){
		instance_iframe("partner_download.php?seq="+$param);

	}else if($mode == "attach_del"){
		answer = confirm("삭제하시겠습니까?");

		if(answer){
			$.post("partner_proc.php", {mode : $mode, seq : $param}, function(data){
				if(data == 0){
					alert("오류가 발생하였습니다");
					return;
				}else{
					$("#file_" + $param).html("<input type='file' name='upload[]' style='width:450px' /><br>");
				}
			});
		}

	}
}


//컨텐츠관리
function content_it($mode, $param){
	var fm = document.content,
		$cnt = $("#inquire input:checkbox:checked").not("#selectall").size(),
		answer = "";

	if($mode == "insert_form"){
		location.href = "content_form.html?mode=insert&" + $param;

	}else if($mode == "list"){

		location.href = "content_list.html?" + $param;

	}else if($mode == "reset"){
		fm.reset();
		answer = confirm("리스트페이지로 이동하시겠습니까?");
		if(answer)	content_it('list', $param);

	}else if($mode == "delete"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("삭제할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("삭제하시겠습니까?");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_delete"){
		answer = confirm("삭제하시겠습니까?");
		if(answer)	location.href = "content_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "submit"){

		//if( !$("#content").validate() ) return;
		fm.submit();

	}else if($mode == "excel_all"){
		var $total = Number( $("input[name=total]").val() );

		if($total <= 0){
			alert("엑셀로 출력할 항목이 없습니다");
			return;
		}else{
			instance_iframe("content_excel.php?mode="+$mode);
		}


	}else if($mode == "excel"){
		var $seq_list = "",
			$total = Number( $("input[name=total]").val() );

		if($total <= 0){
			alert("엑셀로 출력할 항목이 없습니다");
			return;
		}else{
			$("#inquire input:checkbox:checked").not("#selectall").each(function(){
				$seq_list += $(this).val() + ",";
			});

			$seq_list = $seq_list.replace(/,$/g, '');

			instance_iframe("content_excel.php?"+$param+"&seq_list="+$seq_list+"&cnt="+$cnt+"&mode="+$mode);
		}

	}else if($mode == "download"){
		instance_iframe("content_download.php?seq="+$param);

	}else if($mode == "attach_del"){
		answer = confirm("삭제하시겠습니까?");

		if(answer){
			$.post("content_proc.php", {mode : $mode, seq : $param}, function(data){
				if(data == 0){
					alert("오류가 발생하였습니다");
					return;
				}else{
					$("#file_" + $param).html("<input type='file' name='upload[]' style='width:450px' /><br>");
				}
			});
		}

	}else if($mode == "restore"){

		answer = confirm("복원하시겠습니까?");

		if(answer){
			$.post("content_proc.php", {mode : $mode, seq : $param}, function(data){
				if(data == 0){
					alert("오류가 발생하였습니다");
					return;
				}else{
					//$("#file_" + $param).html("<input type='file' name='upload[]' style='width:450px' /><br>");
					location.href = "content_form.html?mode=modify&" + $param;
				}
			});
		}



		//fm.submit();
		//location.href = "content_list.html?" + $param;
	}
}



//팝업관리
function popup_it($mode, $param){
	var fm = document.popup,
		$cnt = $("#popup input:checkbox:checked").not("#selectall").size(),
		answer = "";

	if($mode == "insert_form"){
		location.href = "popup_form.html?mode=insert&" + $param;

	}else if($mode == "list"){
		location.href = "popup_list.html?" + $param;

	}else if($mode == "reset"){
		fm.reset();
		answer = confirm("리스트페이지로 이동하시겠습니까?");
		if(answer)	popup_it('list', $param);

	}else if($mode == "delete"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("삭제할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("삭제하시겠습니까?");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_delete"){
		answer = confirm("삭제하시겠습니까?");
		if(answer)	location.href = "popup_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "submit"){
		if( !$("#popup").validate() ) return;
		fm.submit();
	}
}


//메일관리
function mail_it($mode, $param){
	var fm = document.mail,
		$cnt = $("#mail input:checkbox:checked").not("#selectall").size(),
		answer = "";

	if($mode == "insert_form"){
		location.href = "mail_form.html?mode=insert&" + $param;

	}else if($mode == "list"){
		location.href = "mail_list.html?" + $param;

	}else if($mode == "reset"){
		fm.reset();
		answer = confirm("리스트페이지로 이동하시겠습니까?");
		if(answer)	mail_it('list', $param);

	}else if($mode == "delete"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("삭제할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("삭제하시겠습니까?");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_delete"){
		answer = confirm("삭제하시겠습니까?");
		if(answer)	location.href = "mail_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "submit"){
		var $type = $("input[name=m_type]:checked").val();

		if($type == "P"){
			if(TRIM(fm.m_to.value) == ""){
				alert("수신인 항목은 필수입니다");
				$("#m_to").val('').focus();
				return;
			}
		}

		if( !$("#mail").validate() ) return;
		fm.submit();

	}else if($mode == "search"){
		Layer_Popup("mail_layer_member.html");

	}else if($mode == "download"){
		instance_iframe("mail_download.php?seq="+$param);

	}else if($mode == "attach_del"){
		answer = confirm("삭제하시겠습니까?");

		if(answer){
			$.post("mail_proc.php", {mode : $mode, seq : $param}, function(data){
				if(data == 0){
					alert("오류가 발생하였습니다");
					return;
				}else{
					$("#file_" + $param).html("<input type='file' name='upload[]' style='width:450px' /><br>");
				}
			});
		}

	}else if($mode == "send"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("발송할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("발송하시겠습니까?");
			if(answer)	fm.submit();
		}
	}
}


//메인이미지관리
function mainimage_it($mode, $param){
	var fm = document.mainimage,
		$cnt = $("#mainimage input:checkbox:checked").not("#selectall").size(),
		answer = "";

	if($mode == "insert_form"){
		location.href = "mainimage_form.html?mode=insert&" + $param;

	}else if($mode == "list"){
		location.href = "mainimage_list.html?" + $param;

	}else if($mode == "reset"){
		fm.reset();
		answer = confirm("리스트페이지로 이동하시겠습니까?");
		if(answer)	mainimage_it('list', $param);

	}else if($mode == "delete"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("삭제할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("삭제하시겠습니까?");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_delete"){
		answer = confirm("삭제하시겠습니까?");
		if(answer)	location.href = "mainimage_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "submit"){
		if( !$("#mainimage").validate() ) return;
		fm.submit();

	}else if($mode == "download"){
		instance_iframe("mainimage_download.php?seq="+$param);

	}else if($mode == "attach_del"){
		answer = confirm("삭제하시겠습니까?");

		if(answer){
			$.post("mainimage_proc.php", {mode : $mode, seq : $param}, function(data){
				if(data == 0){
					alert("오류가 발생하였습니다");
					return;
				}else{
					$("#file_" + $param).html("<input type='file' name='upload[]' style='width:450px' /><br>");
				}
			});
		}

	}
}

//이벤트목록관리
function eventlist_it($mode, $param){
    var fm = document.eventlist,
        $cnt = $("#eventlist input:checkbox:checked").not("#selectall").size(),
        answer = "";

    if($mode == "insert_form"){
        location.href = "event_form.html?mode=insert&" + $param;

    }else if($mode == "list"){
        location.href = "event_list.html?" + $param;

    }else if($mode == "reset"){
        fm.reset();
        answer = confirm("리스트페이지로 이동하시겠습니까?");
        if(answer)	eventlist_it('list', $param);

    }else if($mode == "delete"){
        fm.mode.value = $mode;

        if($cnt <= 0){
            alert("삭제할 내역을 선택하세요");
            return;
        }else{
            answer = confirm("삭제하시겠습니까?");
            if(answer)	fm.submit();
        }

    }else if($mode == "each_delete"){
        answer = confirm("삭제하시겠습니까?");
        if(answer)	location.href = "event_proc.php?mode="+ $mode +"&" + $param;

    }else if($mode == "submit"){
        if( !$("#eventlist").validate() ) return;
        fm.submit();

    }else if($mode == "download"){
        instance_iframe("event_download.php?seq="+$param);

    }else if($mode == "attach_del"){
        answer = confirm("삭제하시겠습니까?");

        if(answer){
            $.post("event_proc.php", {mode : $mode, seq : $param}, function(data){
                if(data == 0){
                    alert("오류가 발생하였습니다");
                    return;
                }else{
                    $("#file_" + $param).html("<input type='file' name='upload[]' style='width:450px' /><br>");
                }
            });b
        }

    }
}

//이벤트 문의하기 관리
function eventapply_it($mode, $param){
    var fm = document.eventapply,
        $cnt = $("#eventapply input:checkbox:checked").not("#selectall").size(),
        answer = "";

    if($mode == "insert_form"){
        location.href = "eventapply_form.html?mode=insert&" + $param;

    }else if($mode == "list"){
        location.href = "eventapply_list.html?" + $param;

    }else if($mode == "reset"){
        fm.reset();
        answer = confirm("리스트페이지로 이동하시겠습니까?");
        if(answer)	mainimage_it('list', $param);

    }else if($mode == "delete"){
        fm.mode.value = $mode;

        if($cnt <= 0){
            alert("삭제할 내역을 선택하세요");
            return;
        }else{
            answer = confirm("삭제하시겠습니까?");
            if(answer)	fm.submit();
        }

    }else if($mode == "each_delete"){
        answer = confirm("삭제하시겠습니까?");
        if(answer)	location.href = "eventapply_proc.php?mode="+ $mode +"&" + $param;

    }else if($mode == "submit"){
        if( !$("#eventapply").validate() ) return;
        fm.submit();

    }else if($mode == "download"){
        instance_iframe("eventapply_download.php?seq="+$param);

    }else if($mode == "attach_del"){
        answer = confirm("삭제하시겠습니까?");

        if(answer){
            $.post("eventapply_proc.php", {mode : $mode, seq : $param}, function(data){
                if(data == 0){
                    alert("오류가 발생하였습니다");
                    return;
                }else{
                    $("#file_" + $param).html("<input type='file' name='upload[]' style='width:450px' /><br>");
                }
            });
        }

    }
}


//마케팅분석
function counter_it(){
	var $select_key = $("#select_key option:selected").val(),
		$start = $end = $diff = "",
		$torf = true;

	if( ($select_key == "browser") || ($select_key == "os") ){
		$diff = jDate_Diff({diff : {a : 'start_'+$select_key+'_key', b : 'end_'+$select_key+'_key'}});

		if($diff < 0)	$torf = false;

	}else if($select_key == "month"){
		var $sy = $("#start_"+ $select_key +"y_key option:selected").val(),
			$sm = $("#start_"+ $select_key +"m_key option:selected").val(),
			$ey = $("#end_"+ $select_key +"y_key option:selected").val(),
			$em = $("#end_"+ $select_key +"m_key option:selected").val();

		$start = $sy + jDate_addZero($sm);
		$start = Number($start);
		$end = $ey + jDate_addZero($em);
		$end = Number($end);

		if($start > $end)	$torf = false;

	}else{
		$start = Number( $("#start_"+ $select_key +"_key option:selected").val() );
		$end = Number( $("#end_"+ $select_key +"_key option:selected").val() );

		if($start > $end)	$torf = false;
	}

	if(!$torf){
		alert("검색할 수 없는 값이 입력되었습니다");
		$("#start_"+ $select_key +"_key").focus();
		return false;
	}
}


//태그관리
function tag_it($mode, $param){
    var fm = document.board,
        $cnt = $("#board input:checkbox:checked").not("#selectall").size(),
        answer = "";

    if($mode == "insert_form"){
        location.href = "tag_form.html?mode=insert&" + $param;

    }else if($mode == "list"){
        location.href = "tag_list.html?" + $param;
    }else if($mode == "reset"){
        fm.reset();
        answer = confirm("리스트페이지로 이동하시겠습니까?");
        if(answer)	tag_it('list', $param);

    }else if($mode == "delete"){
        fm.mode.value = $mode;

        if($cnt <= 0){
            alert("삭제할 내역을 선택하세요");
            return;
        }else{
            answer = confirm("삭제하시겠습니까?");
            if(answer)	fm.submit();
        }

    }else if($mode == "each_delete"){
        answer = confirm("삭제하시겠습니까?");
        if(answer)	location.href = "tag_proc.php?mode="+ $mode +"&" + $param;

    }else if($mode == "submit") {
        if (!$("#board").validate()) return;
        fm.submit();
    }
}


//제품관리
function product_it($mode, $param){
	var fm = document.product,
		$cnt = $("#product input:checkbox:checked").not("#selectall").size(),
		answer = "";

	if($mode == "insert_form"){
		location.href = "product_form.html?mode=insert&" + $param;

	}else if($mode == "list"){
		location.href = "product_list.html?" + $param;

	}else if($mode == "reset"){
		fm.reset();
		answer = confirm("리스트페이지로 이동하시겠습니까?");
		if(answer)	product_it('list', $param);

	}else if($mode == "delete"){
		fm.mode.value = $mode;

		if($cnt <= 0){
			alert("삭제할 내역을 선택하세요");
			return;
		}else{
			answer = confirm("삭제하시겠습니까?");
			if(answer)	fm.submit();
		}

	}else if($mode == "each_delete"){
		answer = confirm("삭제하시겠습니까?");
		if(answer)	location.href = "product_proc.php?mode="+ $mode +"&" + $param;

	}else if($mode == "submit"){
		if( !$("#product").validate() ) return;

		fm.submit();

	}else if($mode == "excel_all"){
		var $total = Number( $("input[name=total]").val() );

		if($total <= 0){
			alert("엑셀로 출력할 항목이 없습니다");
			return;
		}else{
			instance_iframe("product_excel.php?mode="+$mode);
		}


	}else if($mode == "excel"){
		var $seq_list = "",
			$total = Number( $("input[name=total]").val() );

		if($total <= 0){
			alert("엑셀로 출력할 항목이 없습니다");
			return;
		}else{
			$("#product input:checkbox:checked").not("#selectall").each(function(){
				$seq_list += $(this).val() + ",";
			});

			$seq_list = $seq_list.replace(/,$/g, '');

			instance_iframe("product_excel.php?"+$param+"&seq_list="+$seq_list+"&cnt="+$cnt+"&mode="+$mode);
		}

	}else if($mode == "download"){
		instance_iframe("product_download.php?seq="+$param);

	}else if($mode == "attach_del"){
		answer = confirm("삭제하시겠습니까?");

		if(answer){
			$.post("product_proc.php", {mode : $mode, seq : $param}, function(data){
				if(data == 0){
					alert("오류가 발생하였습니다");
					return;
				}else{
					$("#file_" + $param).html("<input type='file' name='upload[]' style='width:450px' /><br>");
				}
			});
		}

	}
}


/** CMS Tree SET **/
function getCmsTree($sql, $parentcode) {
	//eval('$query = "'.$sql.'";');
	//$cmsList = mysql_query($query);
	//if (mysql_num_rows($cmsList) > 0) echo '<ul>';
	//while($cms = mysql_fetch_array($cmsList)) {
	//	echo '<li id="'.$cms[PAGECODE].'" '.(($cms[FOLDERYN]=='Y')?"":"class='jstree-no-icons'").' ><a href="?action=AD0010M&pagecode='.$cms[PAGECODE].'">'.$cms[PAGENAME].'</a>';
	//	getCmsTree($sql, $cms[PAGECODE]);
	//	echo '</li>';
	//}
	//if (mysql_num_rows($cmsList) > 0) echo '</ul>';
}