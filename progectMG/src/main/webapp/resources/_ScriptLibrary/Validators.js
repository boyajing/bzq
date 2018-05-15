/*----------------------------------------------------------------------\
|  Copyright (c) 1999 - 2003 Convea Ltd.  All Rights Reserved           |
|-----------------------------------------------------------------------|
| This program is free software; you can redistribute it and/or modify  |
| it under the terms of the GNU General Public License as published by  |
| the Free Software Foundation; either version 2 of the License, or     |
| (at your option) any later version.                                   |
|                                                                       |
|  This program is distributed in the hope that it will be useful,      |
|  but WITHOUT ANY WARRANTY; without even the implied warranty of       |
|  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the        |
|  GNU General Public License for more details.                         |
|                                                                       |
|  You should have received a copy of the GNU General Public License    |
|  along with this program; if not, write to the Free Software          |
|  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA             |
|  02111-1307  USA                                                      |
|----------------------------------------------------------------------*/

	var strAlphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	var strAlpha 		= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	var strNumeric 		= "0123456789";
	
	function validate_checkAlphaNum(checkStr, custom)
	{
		var Valid = true;
		strAlphaNumeric+=custom;
		for (i = 0;  i < checkStr.length;  i++)
		{
			ch = checkStr.charAt(i);
			for (j = 0;  j < strAlphaNumeric.length;  j++)
				if (ch == strAlphaNumeric.charAt(j)) break;
				if (j == strAlphaNumeric.length)
				{
					Valid = false;
					break;
				}
		}
		return Valid;
	}
	
	function validate_checkAlpha(checkStr, custom)
	{
		var Valid = true;
		strAlpha+=custom;
		for (i = 0;  i < checkStr.length;  i++)
		{
			ch = checkStr.charAt(i);
			for (j = 0;  j < strAlpha.length;  j++)
				if (ch == strAlpha.charAt(j)) break;
				if (j == strAlpha.length)
				{
					Valid = false;
					break;
				}
		}
		return Valid;
	}
	
	function validate_checkNumeric(checkStr, custom)
	{
		var Valid = true;
		strNumeric+=custom;
		for (i = 0;  i < checkStr.length;  i++)
		{
			ch = checkStr.charAt(i);
			for (j = 0;  j < strNumeric.length;  j++)
			if (ch == strNumeric.charAt(j)) break;
			if (j == strNumeric.length)
			{
				Valid = false;
				break;
			}
		}
		return Valid;
	}
	
	function checkEmail(checkStr) 
	{
		if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(checkStr))
		{
			return (true)
		}
		return (false)
	}

	var g_dropdownlist 				   		  = "请选择[?]";
	var g_textbox 					   		  = "请填写[?]";
	var g_datemodule 				   	      = "请处理[?]";
	var g_datemodule_empty 			   		  = "请填写[?]";
	var g_inputfloatdatamodule_process 		  = "请处理[?]";
	var g_inputfloatdatamodule_num   		  = "请在[?]中输入小数";
	var g_inputfloatdatamodule_num_greazero   = "[?]应大于0";
	var g_inputfloatdatamodule_num_greaeqzero = "[?]应大于等于0";
	var g_inputfloatdatamodule_int   		  = "请在[?]中输入整数";
	var g_inputfloatdatamodule_lengh   		  = "请处理[?]的长度";

	/*******************************************************************************
	 * 为空验证
	 * 
	 * @author 李然
	 ******************************************************************************/
	function isBlank(str) {
	  if (trim(str) == "") {
	    return true;
	  } else {
	    return false;
	  }
	}

	/*******************************************************************************
	 * 删除字符串左边空格
	 * 
	 * @author 李然
	 ******************************************************************************/
	function ltrim(str) { 
		if(str.length==0)
			return(str);
		else {
			var idx=0;
			while(str.charAt(idx).search(/\s/)==0)
				idx++;
			return(str.substr(idx));
		}
	}

	/*******************************************************************************
	 * 删除字符串右边空格
	 * 
	 * @author 李然
	 ******************************************************************************/
	function rtrim(str) { 
		if(str.length==0)
			return(str);
		else {
			var idx=str.length-1;
			while(str.charAt(idx).search(/\s/)==0)
				idx--;
			return(str.substring(0,idx+1));
		}
	}

	/*******************************************************************************
	 * 删除字符串左右两边空格
	 * 
	 * @author 李然
	 ******************************************************************************/
	function trim(str) { 
		return(rtrim(ltrim(str)));
	}

	/*******************************************************************************
	 * 日期比较（相等：0；大于：1；小于：-1）
	 * 
	 * @author 李然
	 ******************************************************************************/
	function compareDate(date1, date2) {
		if (trim(date1) == trim(date2))  	
			return 0;
		if (trim(date1) > trim(date2))  	
			return 1;
		if (trim(date1) < trim(date2))  	
			return -1;
	}

	/*******************************************************************************
	 * 数值比较（相等：0；大于：1；小于：-1）
	 * 
	 * @author 李然
	 ******************************************************************************/
	function compareNum(num1, num2) {
		if (trim(num1) == trim(num2))  	
			return 0;
		if (trim(num1) > trim(num2))  	
			return 1;
		if (trim(num1) < trim(num2))  	
			return -1;
	}

	/*******************************************************************************
	 * 邮件验证
	 * 
	 * @author 李然
	 ******************************************************************************/
	function isEmail(eml) {
		if(trim(eml) != '') {
		  var re = new RegExp("@[\\w]+(\\.[\\w]+)+$");
		  return(re.test(eml));
		}
		else
		  return true;
	}

	/*******************************************************************************
	 * 电话验证（未对长度验证）
	 * 
	 * @author 李然
	 ******************************************************************************/
	function isTel(tel) {
		var charcode;
		for (var i = 0; i < tel.length; i++) {
			charcode = tel.charCodeAt(i);
			if (charcode < 48 && charcode != 45 || charcode > 57)	
				return false;
		}
		return true;
	}

	/*******************************************************************************
	 * 是否数字（包括小数）
	 * 
	 * @author 李然
	 ******************************************************************************/
	function isnumber(num) {
		num = num.replace(/,/g, "");
		//var re = new RegExp("^-?[\\d]*(\\.?[\\d]{1,2})?$");
		var re = new RegExp("^-?[\\d]*(\\.?[\\d]*)?$");
		if(re.test(num))
			return(!isNaN(parseFloat(num)));
		else
			return false;
	}

	/*******************************************************************************
	 * 是否整数
	 * 
	 * @author 李然
	 ******************************************************************************/
	function isinteger(num)	{
		var re=new RegExp("^-?[\\d]*$");
		if(re.test(num))
			return(!isNaN(parseInt(num)));
		else
			return false;
	}

	function validateKEY() {
		var flag = true;
		$("form").each(function() {
			$(this).find("input").each(function() {
				if($(this).attr("type") == "text" && $(this).attr("class")!="money" && !($(this).attr("num") == "") && !($(this).attr("int") == "")) {
					flag = validateOneKEY(this);
					if(!flag){
						return false;
					}
				}
			});
		});
		
		return flag;
	}

	function validateOneKEY(obj){
	  var val = $(obj).val();	
	  var re = new RegExp("[%]");
	  if(re.test(val)) {
		$(obj).val($(obj).val().replace(/%/g,"％"));
	  }else {
		if($(this).attr("required") == "") {
			changeCSS_blue($(obj).attr("name") ? $(obj).attr("name") : $(obj).attr("id"));
		}else {
			changeCSS_black($(obj).attr("name") ? $(obj).attr("name") : $(obj).attr("id"));
		}
	  }
	  return true;
	}


	function validateForm() {
		var flag = true;
		$("*").each(function() {
			if(!validate($(this))) {
				flag = false;
				return false;
			}
		});
		
		return flag;
	}

	function validate(obj) {
		if($("#"+(obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx").attr("checked")) {alert(2);
			if(obj.attr("required") == "") {
				changeCSS_blue(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
			}else {
				changeCSS_black(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
			}
			return true;
		}
		
		if(obj.attr("required") == "") {
			if(obj.attr("type") == "text") {
				if(isBlank(obj.val())) {
					alert(g_textbox.replace("?",$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]));
					changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
					obj.focus();
					return false;
				}else if(document.getElementsByName((obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx").length > 0) {
					if(!$("input[name='"+(obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx'][checked]").val()) {
						alert("请处理["+$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]+"]金额单位");
						changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
						obj.focus();
						return false;
					}
				}//else {
				//	changeCSS_blue(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
				//}
			}else if(obj.attr("type") == "select-one") {
				if(obj.val() == "-1" || isBlank((obj.val()))) {
					alert(g_dropdownlist.replace("?",$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]));
					changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
					obj.focus();
					return false;
				}else {
					changeCSS_blue(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
				}
			}
		}
		
		if(obj.attr("int") == "") {
			if(!isBlank(obj.val())) {
				if(!isinteger(obj.val())) {
					alert(g_inputfloatdatamodule_int.replace("?",$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]));
					changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
					obj.focus();
					return false;
				}else {
					if(obj.attr("required") == "") {
						changeCSS_blue(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
					}else {
						changeCSS_black(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
					}
				}
			}
		}
		
		if(obj.attr("num") == "") {
			if(obj.attr("canblank")!=""){
					if(!isBlank(obj.val())) {
						//2011-10-12增加点击缺失时不验证数据
						if(document.getElementsByName((obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx").length>0){
							if($("input[name='"+(obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx'][checked]").val()=="0"){
								return true;
							}
						}
									
						if(!isnumber(obj.val())) {
							alert(g_inputfloatdatamodule_num.replace("?",$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]));
							changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
							obj.focus();
							return false;
						}
						if(document.getElementsByName((obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx").length > 0) {
							if(!$("input[name='"+(obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx'][checked]").val()) {
								alert("请处理["+$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]+"]金额单位");
								changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								obj.focus();
								return false;
							}else {
								if(obj.attr("required") == "") {
									changeCSS_blue(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								}else {
									changeCSS_black(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								}
							}
						}
						
						if(Number(getVal(obj.val())) > 9999999999) {
							alert("["+$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]+"]超出范围");
							changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
							obj.focus();
							return false;
						}
						if($("input[name='"+(obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx'][checked]").val() == "2") {
							if((Number(getVal(obj.val())) * 10000) > 9999999999) {
								alert("["+$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]+"]超出范围");
								changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								obj.focus();
								return false;
							}else {
								if(obj.attr("required") == "") {
									changeCSS_blue(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								}else {
									changeCSS_black(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								}
							}
						}
						
						if(obj.attr("greazero") == "") {
							if(getVal(obj.val()) <= 0) {
								alert(g_inputfloatdatamodule_num_greazero.replace("?",$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]));
								changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								obj.focus();
								return false;
							}else {
								if(obj.attr("required") == "") {
									changeCSS_blue(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								}else {
									changeCSS_black(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								}
							}
						}else if(obj.attr("greaeqzero") == "") {
							if(getVal(obj.val()) < 0) {
								alert(g_inputfloatdatamodule_num_greaeqzero.replace("?",$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]));
								changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								obj.focus();
								return false;
							}else {
								if(obj.attr("required") == "") {
									changeCSS_blue(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								}else {
									changeCSS_black(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								}
							}
						}else {
							if(obj.attr("required") == "") {
								changeCSS_blue(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
							}else {
								changeCSS_black(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
							}
						}
					}else {
						if(document.getElementsByName((obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx").length > 0) {
						if(!$("input[name='"+(obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx'][checked]").val()) {
							alert("请处理["+$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]+"]");
							changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
							obj.focus();
							return false;
						}else if($("input[name='"+(obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx'][checked]").val() == "1") {
							alert("请填写["+$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]+"]");
							changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
							obj.focus();
							return false;
						}else if($("input[name='"+(obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx'][checked]").val() == "2") {
							alert("请填写["+$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]+"]");
							changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
							obj.focus();
							return false;
						}else {
							if(obj.attr("required") == "") {
								changeCSS_blue(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
							}else {
								changeCSS_black(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
							}
						}
						}
					}
				}else{
					if(!isBlank(obj.val())){
						if(!isnumber(obj.val())) {
							alert(g_inputfloatdatamodule_num.replace("?",$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]));
							changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
							obj.focus();
							return false;
						}
						else if(document.getElementsByName((obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx").length > 0) {
							if(!$("input[name='"+(obj.attr("name") ? obj.attr("name") : obj.attr("id"))+"lx'][checked]").val()) {
								alert("请处理["+$("#td" + (obj.attr("name") ? obj.attr("name") : obj.attr("id"))).html().replace("：","").replace(":","").replace(/\s*/g, "").replace(/&nbsp;/g, "").split("(")[0]+"]");
								changeCSS_red(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								obj.focus();
								return false;
							}else {
								if(obj.attr("required") == "") {
									changeCSS_blue(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								}else {
									changeCSS_black(obj.attr("name") ? obj.attr("name") : obj.attr("id"));
								}
							}
						}
					}
				}	
		}
		
		return true;
	}

	function changeCSS_red(id) {
		$("#td" + id).css("color","red").css("font-weight","bold");
	}

	function changeCSS_blue(id) {
		$("#td" + id).css("color","blue").css("font-weight","normal");
	}

	function changeCSS_black(id) {
		$("#td" + id).css("color","black").css("font-weight","normal");
	}

	/****
	* 文本域 非必填项大于等于0验证  >=0
	*
	*/
	function greatEq0(id,msg){
		var value = $.trim($("#"+id).val());
		var name_lx = id+"lx";
		var valueLx = $("input[type='radio'][name='"+name_lx+"']:checked").val();
		if(valueLx!=null&&valueLx!=''&&valueLx==0){
			return true;
		}
		if(value!=""){
			if(!isnumber(value)){
				alert(msg);
				changeCSS_red(id);
				$("#"+id).focus();
				return false;
			}
			if(parseFloat(value)<0){
				alert(msg);
				changeCSS_red(id);
				$("#"+id).focus();
				return false;
			}else{
				changeCSS_black(id);
			}
		}
		return true;
	}
	/**文本域 非必填项大于0验证   >0
	* 
	*/
	function great0(id,msg){
		var value = $.trim($("#"+id).val());
		var name_lx = id+"lx";
		var valueLx = $("input[type='radio'][name='"+name_lx+"']:checked").val();
		if(valueLx!=null&&valueLx!=''&&valueLx==0){
			return true;
		}
		if(value!=""){
			if(!isnumber(value)){
				alert(msg);
				changeCSS_red(id);
				$("#"+id).focus();
				return false;
			}
			if(parseFloat(value)<=0){
				alert(msg);
				changeCSS_red(id);
				$("#"+id).focus();
				return false;
			}else{
				changeCSS_black(id);
			}
		}
		return true;
	}
	/****
	* 文本域  必填项大于等于0验证
	*
	*/
	function reqGreatEq0(id,msg){
		var value = $.trim($("#"+id).val());
		var name_lx = id+"lx";
		var valueLx = $("input[type='radio'][name='"+name_lx+"']:checked").val();
		if(valueLx!=null&&valueLx!=''&&valueLx==0){
			return true;
		}
		if(!isnumber(value)){
			alert(msg);
			changeCSS_red(id);
			$("#"+id).focus();
			return false;
		}
		if(parseFloat(value)<0){
			alert(msg);
			changeCSS_red(id);
			$("#"+id).focus();
			return false;
		}else{
			changeCSS_blue(id);
		}
		return true;
	}
	/****
	*文本域 必填项大于0验证  >0
	*
	*/
	function reqGreat0(id,msg){
		var value = $.trim($("#"+id).val());
		var name_lx = id+"lx";
		var valueLx = $("input[type='radio'][name='"+name_lx+"']:checked").val();
		if(valueLx!=null&&valueLx!=''&&valueLx==0){
			return true;
		}
		if(!isnumber(value)){
			alert(msg);
			changeCSS_red(id);
			$("#"+id).focus();
			return false;
		}
		if(parseFloat(value)<=0){
			alert(msg);
			changeCSS_red(id);
			return false;
		}else{
			changeCSS_blue(id);
		}
		return true;
	}
	/* SELECT 必须选择*/
	function reqSelect(id,msg){
		var value = $.trim($("#"+id).val());
		if(value=="-1" || value=="" || value=="-2" ){
			alert(msg);
			changeCSS_red(id);
			$("#"+id).focus();
			return false;
		}else{
			changeCSS_blue(id);
		}
		return true;
	}
	/* radio 必须选择 */
	function reqRadio(name,msg){
		var value = $("input[name='"+name+"'][checked]").val();
		if(!value){
			alert(msg);
			changeCSS_red(name);
			return false;
		}else{
			changeCSS_blue(name);
		}
		return true;
	}

	/*  验证 万元 元 缺失 */
	function reqRadioJe(name,msg1){
		var valueJe = $.trim($("#"+name).val().replace(/,/g, ""));
		var name_lx = name+"lx";
		var valueLx = $("input[name='"+name_lx+"'][checked]").val();
		//alert(valueJe);
		if(!valueLx){
			alert("请处理["+msg1+"]");
			changeCSS_red(name);
			$("#"+name).focus();
			return false;
		}else if((valueLx!="0" && valueJe=="")){
			alert("请在["+msg1+"]中输入数字");
			changeCSS_red(name);
			$("#"+name).focus();
			return false;
		}else if(valueLx=='1' && valueJe.length>=22){
			alert("请处理["+msg1+"]的长度");
			changeCSS_red(name);
			$("#"+name).focus();
			return false;
		}else if(valueLx=="2" && valueJe.length>=18){
			alert("请处理["+msg1+"]的长度");
			changeCSS_red(name);
			$("#"+name).focus();
			return false;
		}

		if($("#td"+name).attr("tagName")=='TD'){
			changeCSS_black(name);
		}else if($("#td"+name).attr("tagName")=='FONT'){
			changeCSS_blue(name);
		}
	}

	/* TEXT 必填*/
	function reqText(id,msg){
		var value = $.trim($("#"+id).val());
		if(value==""){
			alert(msg);
			changeCSS_red(id);
			$("#"+id).focus();
			return false;
		}else{
			changeCSS_blue(id);
		}
		return true;
	}

	/** 非必填 整数验证 **/
	function isIntnum(id,msg){
		var value = $.trim($("#"+id).val());
		if(value!=""){
			var   r   =   /^\+?[1-9][0-9]*$/;　　//正整数     
			if(!r.test(value)){
				alert(msg);
				changeCSS_red(id);
				$("#"+id).focus();
				return false;
			}
			if(parseFloat(value)<=0){
				alert(msg);
				changeCSS_red(id);
				$("#"+id).focus();
				return false;
			}else{
				changeCSS_black(id);
			}
		}
		return true;
	}



	//格式话金额    
	function fmoney(s, n)//将数字转换成逗号分隔的样式,保留两位小数s:value,n:小数位数      
	{   	
		s = getVal(s);
		s = Number(s).toFixed(n);
		s = s + "";
		var s1 = s.split(".")[0];
		var s2 = s.split(".")[1];
		var re = /(\d{1,3})(?=(\d{3})+(?:$|\D))/g;///(-?\d+)(\d{3})/
	    s1 = s1.replace(re,"$1,");
	    return s1 + "." + s2;  

	}


	/**
	* 格式化金额域
	*/
	function formatMoney(){
		var moneyEl = $("input[class='money']");
		moneyEl.each(function(){
			if($(this).val()){
				var m = fmoney($(this).val(),4);
				$(this).attr("value",m);
			}
		});
		moneyEl.blur(function(){
			if($(this).val()){
				if(isnumber($(this).val())==true){
					var m = fmoney($(this).val(),4);
					$(this).attr("value",m);
				}
			}
		});
		moneyEl.focus(function(){
			var m = $(this).val().replace(/,/g, "");
			$(this).attr("value",m);
		});
		
		$("input[type='text']").each(function(){
			$(this).attr("style","border:1 solid #A5BEDD;FONT-SIZE:12px;FONT-STYLE:normal;FONT-VARIANT:normal;FONT-WEIGHT:normal;HEIGHT:20px;LINE-HEIGHT:19px;background-color:#F9FAFF;"+$(this).attr("style"));
		    if(!$(this).attr("readonly")){
				$(this).blur(function(){
					$(this).get(0).style.borderColor='#A5BEDD';
					$(this).get(0).style.backgroundColor='#F9FAFF';
				});
				$(this).focus(function(){
					$(this).get(0).style.borderColor='#3767CD';
					$(this).get(0).style.backgroundColor='#F2FDE9';		
				});
		    }
		});
	}
	/**
	 * 得到金额的值
	 * @param num
	 * @returns
	 */
	function getVal(num) {
		return num.toString().replace(/,/g, "");
	}


	/**
	add by charles.Lin to create pop cover DIV
	modify at 2010-12-24
	modify start
	*/
	function showPopCover(){
	   if (document.getElementById("popDiv")) {
	   //如果存在遮盖层，则让其显示 
	    document.getElementById("popDiv").style.display = 'block';
	   } else {
	   //否则创建遮盖层
	    var coverDiv = document.createElement('div');
	    document.body.appendChild(coverDiv);
	    coverDiv.id = 'popDiv';
	    with(coverDiv.style) {
	     position = 'absolute';
	     background = '#CCCCCC';
	     left = '0px';
	     top = '0px';
	     var bodySize = getBodySize();
	     width = bodySize[0] + 'px'
	     height = bodySize[1] + 'px';
	     zIndex = 9999;
	     if (isIE()) {
	      filter = "Alpha(Opacity=60)";//IE逆境
	     } else {
	      opacity = 0.6;
	     }
	    }
	   }
	}

	function removePopCover(){
		if (document.getElementById("popDiv")) { 
	        document.getElementById("popDiv").style.display = 'none';
	    } 
	}

	function getBodySize(){
	   var bodySize = [];
	   with(document.documentElement) {
	    bodySize[0] = (scrollWidth>clientWidth)?scrollWidth:clientWidth;//如果滚动条的宽度大于页面的宽度，取得滚动条的宽度，否则取页面宽度
	    bodySize[1] = (scrollHeight>clientHeight)?scrollHeight:clientHeight;//如果滚动条的高度大于页面的高度，取得滚动条的高度，否则取高度
	    bodySize[0] = bodySize[0]-21;
	    bodySize[1] = bodySize[1]-21;
	   }
	   return bodySize;
	}

	//判断浏览器是否为IE
	function isIE(){
	      return (document.all && window.ActiveXObject && !window.opera) ? true : false;
	}
	/**
	modify end
	*/


	//固定电话号码检验
	function CheckTel(id,msg)
	{
		var phone = $.trim($("#"+id).val());
	    if (phone != ""){
	        var p1 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	        var me = false;
	        if (p1.test(phone))me=true;
	        if (!me){
	            alert(msg);
				changeCSS_red(id);
				$("#"+id).focus();
				return false;
	        }else{
	        	changeCSS_blue(id);
	        }
	    }
	    return true;
	}
	
