/*
none				: 단독으로 사용불가 (none,kor) 값이 있을때 체크
required			: 필수항목
kor					: 한글만
eng					: 영어만
kor_eng				: 한글&영어
first_eng_number	: 첫자는 영어로 시작하는 영문&숫자
kor_number			: 한글&숫자
eng_number			: 영문&숫자
kor_eng_number		: 한글&영문&숫자
number				: 숫자만
number_dot			: 숫자&닷(.)
number_comma		: 숫자&콤마(,)
number_hyphen		: 숫자&하이픈(-)
ip					: 아이피
email				: 이메일
tel					: 전번형식
phone				: 핸드폰형식
image				: 이미지 (jpg|gif|tif|bmp|png)
date				: 날짜 (YYYY-mm-dd)
url					: 도메인
id_pw				: 아이디 & 비번 (첫자는 영문으로)
time				: 시간 (00:00)
phone_tel33			: 숫자만 3자리
phone_tel34			: 숫자만 3 ~ 4자리
phone_tel44			: 숫자만 4자리
min					: min-3 (하이픈형태로 최소자리)
max					: max-9 (하이픈형태로 최대자리)
compare				: compare-m_name (해당비교하는 값의 아이디값)
editor				: editor-content (ckeditor 내용)

if( !$("#id_value").validate() ) return;				//onClick
if( !$("#id_value").validate() ) return false;		//submit
*/


(function($){

	$.fn.validate = function(){
		var faultElement = null,
			target = $.getValidateTargets(this);

		for(var i=0; i<target.length; i++){
			var element = target[i],
				option = element.validOption,
				etype = element.type;

			if( (etype == "text") || (etype == "textarea") ){
				$(element).val( jQuery.trim( $(element).val() ) );
			}

			for(targetOption in option){
				if(targetOption == 'trim')	continue;

				if( jQuery.isFunction($.validator[targetOption]) && option[targetOption] ){
					var result = $.validator[targetOption](element);

					if(!result.valid){
						result.message = $.validator.getMessage(result.option.elementName, result.type, result.option.length);
						faultElement = result;
						break;
					}
				}
			};

			if(faultElement != null){
				$.validateAlerts(faultElement);
				return false;
			}
		}

		return true;
	};


	$.getValidateTargets = function(elements){
		var target = [];

		for(var i=0; i<elements.length; i++){
			$(elements[i]).find('[valid]').each(function(n){
				$.makeValidate(this, $(this).attr('valid').split(','));
				target.push(this);
			});
		};

		return target;
	};


	$.makeValidate = function(caller, options){
		for(var i=0; i<options.length; i++){
			var $opt = options[i].split("-")[0],
				$length = (options[i].split("-")[1] = undefined) ? "" : options[i].split("-")[1];

			switch($opt){
				case "none"				:	$(caller).addValidItem('none');						break;
				case "required"			:	$(caller).addValidItem('required');					break;
				case "kor"				:	$(caller).addValidItem('kor');						break;
				case "eng"				:	$(caller).addValidItem('eng');						break;
				case "kor_eng"			:	$(caller).addValidItem('kor_eng');					break;
				case "first_eng_number"	:	$(caller).addValidItem('first_eng_number');			break;
				case "kor_number"		:	$(caller).addValidItem('kor_number');				break;
				case "eng_number"		:	$(caller).addValidItem('eng_number');				break;
				case "kor_eng_number"	:	$(caller).addValidItem('kor_eng_number');			break;
				case "number"			:	$(caller).addValidItem('number');					break;
				case "number_dot"		:	$(caller).addValidItem('number_dot');				break;
				case "number_comma"		:	$(caller).addValidItem('number_comma');				break;
				case "number_hyphen"	:	$(caller).addValidItem('number_hyphen');			break;
				case "ip"				:	$(caller).addValidItem('ip');						break;
				case "email"			:	$(caller).addValidItem('email');					break;
				case "tel"				:	$(caller).addValidItem('tel');						break;
				case "phone"			:	$(caller).addValidItem('phone');					break;
				case "image"			:	$(caller).addValidItem('image');					break;
				case "date"				:	$(caller).addValidItem('date');						break;
				case "url"				:	$(caller).addValidItem('url');						break;
				case "id_pw"			:	$(caller).addValidItem('id_pw');					break;
				case "time"				:	$(caller).addValidItem('time');						break;
				case "phone_tel33"		:	$(caller).addValidItem('phone_tel33');				break;
				case "phone_tel34"		:	$(caller).addValidItem('phone_tel34');				break;
				case "phone_tel44"		:	$(caller).addValidItem('phone_tel44');				break;
				case "min"				:	$(caller).addValidItem('minlength', $length);		break;
				case "max"				:	$(caller).addValidItem('maxlength', $length);		break;
				case "compare"			:	$(caller).addValidItem('compare', $length);			break;
				case "editor"			:	$(caller).addValidItem('editor', $length);			break;
				default					:
			};
		};

		caller.validOption['elementName'] = $(caller).attr('element-name');
	};


	$.fn.addValidItem = function(optionName, length) {
		return this.each(function() {
            if(!this.validOption) this.validOption = {};
            this.validOption[optionName] = true;
			this.validOption['length'] = length;
        });
    };


	$.validateAlerts = function(faultElement){
		alert(faultElement.message);

		//if(faultElement.cursor == "focus")	$(faultElement.element).focus();
		//else								$(faultElement.element).select();
		$(faultElement.element).focus();
	};


	$.validator = {
        regExpPatten : {
            kor					:	/[ㄱ-ㅎㅏ-ㅣ가-힣]$/,
			eng					:	/[a-zA-Z]$/,
			kor_eng				:	/[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z]$/,
			first_eng_number	:	/^[a-zA-Z][0-9a-zA-Z]*$/,
			kor_number			:	/[ㄱ-ㅎㅏ-ㅣ가-힣0-9]$/,
			eng_number			:	/[a-zA-Z0-9]$/,
			kor_eng_number		:	/[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]$/,
			number				:	/[0-9]$/,
			number_dot			:	/^[0-9\.]+$/,
			number_comma		:	/^[0-9\,]+$/,
			number_hyphen		:	/^[0-9\-]+$/,
			ip					:	/^(1|2)?\d?\d([.](1|2)?\d?\d){3}$/,
			email				:	/([0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)\.([0-9a-zA-Z_-]+)/,
			tel					:	/\d{2,3}-\d{3,4}-\d{4}$/,
			phone				:	/[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/,
			image				:	/\.(jpg|gif|tif|bmp|png)$/i,
			date				:	/[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])/,
			url					:	/(http\:\/\/)?((\w+)[.])+(asia|biz|cc|cn|com|de|eu|in|info|jobs|jp|kr|mobi|mx|name|net|nz|org|travel|tv|tw|uk|us)(\/(\w*))*$/i,
			id_pw				:	/^[a-z]{1}[a-z0-9]{3,13}$/,
			time				:	/^[0-9]{2}:[0-9]{2}$/,
			phone_tel33			:	/^[0-9]{3}$/,
			phone_tel34			:	/^[0-9]{3,4}$/,
			phone_tel44			:	/^[0-9]{4}$/
        },

        defaultMessage : {
			required			:	"{elementName} 항목은 필수입니다",
			kor					:	"한글만 입력하세요",
			eng					:	"영문만 입력하세요",
			kor_eng				:	"한글 영문만 입력하세요",
			first_eng_number	:	"영문 숫자만 입력가능하며 영문으로 시작하여야 합니다",
			kor_number			:	"한글 숫자만 입력하세요",
			eng_number			:	"영문 숫자만 입력하세요",
			kor_eng_number		:	"한글 영문 숫자만 입력하세요",
			number				:	"숫자만 입력하세요",
			number_dot			:	"숫자 점(.)만 입력하세요",
			number_comma		:	"숫자 콤마(,)만 입력하세요",
			number_hyphen		:	"숫자 하이픈(-)만 입력하세요",
			ip					:	"IP 형식이 틀립니다",
			email				:	"이메일 형식이 틀립니다",
			tel					:	"{elementName} 형식이 틀립니다\n\n000-000(0)-0000 형식으로 입력하세요",
			phone				:	"{elementName} 형식이 틀립니다\n\n000-000(0)-0000 형식으로 입력하세요",
			image				:	"이미지만 입력할 수 있습니다",
			date				:	"날짜 형식이 틀립니다\n\nYYYY-MM-DD 형식으로 입력하세요",
			url					:	"주소 형식이 틀립니다",
			id_pw				:	"{elementName}는 영문으로 시작하는 4 ~ 14자로 영문 또는 숫자이어야 합니다",
			time				:	"00:00 형식으로 입력하세요",
			phone_tel33			:	"{elementName}는 3자리 숫자로 입력하세요",
			phone_tel34			:	"{elementName}는 3 ~ 4자리 숫자로 입력하세요",
			phone_tel44			:	"{elementName}는 4자리 숫자로 입력하세요",
			minlength			:	"{elementName} 항목은 {length} 자리 이하로 입력하세요",
			maxlength			:	"{elementName} 항목은 {length} 자리 이상으로 입력하세요",
			compare				:	"{elementName} 항목의 입력된 내용이 일치하지 않습니다"
		},

		getMessage : function(elementName, type, length){
			var $msg = "";

			$msg = $.validator.defaultMessage[type].replace('{elementName}', elementName);
			$msg = (length == undefined) ? $msg : $msg.replace('{length}', length);

			return $msg;
		},


		/* none */
		none : function(element){
			var result = true,
				next_valid = $("input[name="+element.name+"]").attr("valid").replace("none", "").replace(/^,/g, "").replace(/,$/g, "").split(",")[0],
				length = $.trim($(element).val()).length,
				temp = "";

			if(length > 0){
				temp = $.validator[next_valid](element);
				result = temp.valid;
			}

			return {
                valid	: result,
                option	: element.validOption,
                element	: element,
                type	: next_valid,
				cursor	: 'select'
            };
		},


		/* editor */
		editor : function(element){
			var result = false,
				$editor = CKEDITOR.instances[ element.validOption.length ];

			result = $editor.getData().length > 0;

			return {
                valid	: result,
                option	: element.validOption,
                element	: element,
                type	: 'required',
				cursor	: 'focus'
            };
		},


		/*비교*/
		compare : function(element){
			var result = false,
				$length =$.trim($(element).val()).length;

			result = ( ($.trim($(element).val()) == $.trim($("#" + element.validOption.length).val())) && ($length > 0) ) ? true : false;

			return {
                valid	: result,
                option	: element.validOption,
                element	: element,
                type	: 'compare',
				cursor	: 'select'
            };
		},

		/* 최소자리 */
		minlength : function(element){
			var result = false,
				$length =$.trim($(element).val()).length;

			result = ( ($length > 0) && ($length <= element.validOption.length) ) ? true : false;

			return {
                valid	: result,
                option	: element.validOption,
                element	: element,
                type	: 'minlength',
				cursor	: 'select'
            };
		},

		/* 최대자리 */
		maxlength : function(element){
			var result = false,
				$length =$.trim($(element).val()).length;

			result = ( ($length > 0) && ($length >= element.validOption.length) ) ? true : false;

			return {
                valid	: result,
                option	: element.validOption,
                element	: element,
                type	: 'maxlength',
				cursor	: 'select'
            };
		},

		/* 일반검사 */
		required : function(element){
			var result = false,
				type = element.type;

			if( (type == "radio") || (type == "checkbox") || (type == "select-one") ){
				if(type != "select-one"){
					result = $("input[name="+element.name+"]").is(":checked");
				}else{
					result = $("select[name="+element.name+"] option:selected").val().length > 0;
				}
			}else{
				result = $.trim($(element).val()).length > 0;
			}

			return {
                valid	: result,
                option	: element.validOption,
                element	: element,
                type	: 'required',
				cursor	: 'focus'
            };
		},

		/* 한글만 */
		kor : function(element){
			var result = false,
				type = "kor",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 영문만 */
		eng : function(element){
			var result = false,
				type = "eng",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 한글 영문 */
		kor_eng : function(element){
			var result = false,
				type = "kor_eng",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 영문 숫자 */
		first_eng_number : function(element){
			var result = false,
				type = "first_eng_number",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 한글 숫자 */
		kor_number : function(element){
			var result = false,
				type = "kor_number",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 영문 숫자 */
		eng_number : function(element){
			var result = false,
				type = "eng_number",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 한글 영문 숫자 */
		kor_eng_number : function(element){
			var result = false,
				type = "kor_eng_number",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 숫자만 */
		number : function(element){
			var result = false,
				type = "number",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 숫자 점만 */
		number_dot : function(element){
			var result = false,
				type = "number_dot",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 숫자 콤마만 */
		number_comma : function(element){
			var result = false,
				type = "number_comma",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 숫자 하이픈만 */
		number_hyphen : function(element){
			var result = false,
				type = "number_hyphen",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* ip */
		ip : function(element){
			var result = false,
				type = "ip",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 이메일 */
		email : function(element){
			var result = false,
				type = "email",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 전화번호 */
		tel : function(element){
			var result = false,
				type = "tel",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 전화번호 */
		phone : function(element){
			var result = false,
				type = "phone",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 이미지만 */
		image : function(element){
			var result = false,
				type = "image",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 날짜 */
		date : function(element){
			var result = false,
				type = "date",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 주소 */
		url : function(element){
			var result = false,
				type = "url",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 아이디 비밀번호 */
		id_pw : function(element){
			var result = false,
				type = "id_pw",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 시간 */
		time : function(element){
			var result = false,
				type = "time",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 전화번호(숫자3자리) */
		phone_tel33 : function(element){
			var result = false,
				type = "phone_tel33",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 전화번호(숫자3 ~ 4자리) */
		phone_tel34 : function(element){
			var result = false,
				type = "phone_tel34",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		},

		/* 전화번호(숫자4자리) */
		phone_tel44 : function(element){
			var result = false,
				type = "phone_tel44",
				regExp = $.validator.regExpPatten[type],
				patten = new RegExp(regExp);

			result = patten.test( $(element).val() );

			if( $(element).val().length == 0 )	result = true;

			return {
				valid	: result,
                option	: element.validOption,
                element	: element,
                type	: type,
				cursor	: 'select'
			};
		}
    };

})(jQuery);