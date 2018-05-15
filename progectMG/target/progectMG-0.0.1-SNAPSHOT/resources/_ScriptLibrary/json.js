function tableListJson(tableName){
	var table = document.getElementById(tableName);
	var rows = table.rows;
	var cellObj = null;
	var text = "[";
	for(var i=1;i<rows.length;i++){
		var temp = form2json(rows[i]);
		text += "{"+temp+"},";
	}
	text = text.substring(0,text.length-1);
	text += "]"
	return text;				
}

function form2json(form) {
	var txt = "";
	 //input text
		$(form).find("input[type=\"text\"]").each(function () {
			if ($(this).attr("name")) {
				if($(this).attr("class")=="money"){
					var inputVal = $(this).val().replace(/,/g, "");

					var lxName = $(this).attr("id") ? $(this).attr("id") : $(this).attr("name");
					lxName = lxName+"lx";
					var lxVal = $("input[name='"+lxName+"'][checked]").val();
					//alert("lxVal"+lxVal);
					if(inputVal!=""){
						if(lxVal=="2"){
							inputVal = parseFloat(inputVal)*10000;		
						}else if(lxVal =="0"){
							inputVal = "";
						}
					}
					//alert(inputVal);
					txt += "\"" + $(this).attr("name") + "\":\"" + inputVal + "\",";		
				}else{
					var inputVal = $(this).val();
					var lxName = $(this).attr("id") ? $(this).attr("id") : $(this).attr("name");
					lxName = lxName+"lx";
					var lxVal = $("input[name='"+lxName+"'][checked]").val();
					//alert("lxVal"+lxVal);
					if(inputVal!=""){
						if(lxVal=="2"){
							inputVal = parseFloat(inputVal)*10000;		
						}else if(lxVal =="0"){
							inputVal = "";
						}
					}
					//alert(inputVal);
					txt += "\"" + $(this).attr("name") + "\":\"" + inputVal + "\",";
				}
			}
		});
	 //input hidden
		$(form).find("input[type=\"hidden\"]").each(function () {
			if ($(this).attr("name")) {
				txt += "\"" + $(this).attr("name") + "\":\"" + serialize($(this).val()) + "\",";
			}
		});	
	 //checkbox
		var chknames = "";
		$(form).find("input[type=\"checkbox\"]").each(function () {
			if (this.name) {
				if (this.checked) {
					if (chknames.toUpperCase().indexOf(this.name.toUpperCase()) >= 0) {
						var reg = new RegExp("\"" + this.name + "\":" + "\"(.+?)\",", "i");
						txt = txt.replace(reg, "\"" + this.name + "\":" + "\"$1," + this.value + "\",");
					} else {
						chknames += this.name + ",";
						txt += "\"" + this.name + "\":\"" + this.value + "\",";
					}
				}
			}
		});
	 //radio
		var rdnames = "";
		$(form).find("input[type=\"radio\"]").each(function () {
			if (this.name) {
				if (this.checked) {
					if (rdnames.toUpperCase().indexOf(this.name.toUpperCase()) >= 0) {
						var reg = new RegExp("\"" + this.name + "\":" + "\"(.+?)\",", "i");
						txt = txt.replace(reg, "\"" + this.name + "\":" + "\"" + this.value + "\",");
					} else {
						rdnames += this.name + ",";
						txt += "\"" + this.name + "\":\"" + this.value + "\",";
					}
				}
			}
		});

	 //textArea
		$(form).find("textArea").each(function () {
			if ($(this).attr("name")) {
				txt += $(this).attr("name") + ":\"" + serialize($(this).val()) + "\",";
			}
		});
	 //select
		$(form).find("select").each(function () {
			if ($(this).attr("name") && $(this).attr("size")==0) {
				var value = "";
				if ($(this).attr("selectedIndex") >= 0) {
					if (this.options[this.selectedIndex].value) {
						value = this.options[this.selectedIndex].value;
					} 
					/*else {
						value = this.options[this.selectedIndex].text;
					}*/
				}
				txt += $(this).attr("name") + ":\"" + serialize(value) + "\",";
			}
		});
	 //td
		$(form).find("td").each(function() {
			if($(this).attr("name")) {
				var htmlVal = $(this).html().replace(/,/g, "");
				txt += $(this).attr("name") + ":\"" + serialize(htmlVal) + "\",";
			}
			
		});
	//span
		$(form).find("span").each(function() {
			if($(this).attr("name") && $(this).attr("tojson") == "") {
				txt += $(this).attr("name") + ":\"" + serialize($(this).html().replace(/,/g, "")) + "\",";
			}
			
		});
	 //file
		txt = txt.replace(/,$/, "");
		txt = txt.replace(/\n/g, "\\n");
		txt = txt.replace(/\r/g, "\\r");
		var xobj = null;
		eval("xobj={" + txt + "}");
		return txt;
}

function formtojson(form, cellNum) {
	var txt = "\"bs\"\:{";
	
	for(var i = 0; i < cellNum; i++) {
		eval("var " + String.fromCharCode(i + 65) + " = \"\{\\\"alxh\\\"\:\\\"" + (i + 1) + "\\\"\\\,\"\;");
	}
	var DG = "{\"alxh\"\:\"99\"\,";
	
	//for(var i = 0; i < cellNum; i++) {
	$(form).find("td").each(function() {
		if(!$(this).attr("belong")) {
			//text
			if($(this).children().attr("type") == "text") {
				if ($(this).children().attr("name")) {
					txt += "\"" + $(this).children().attr("name") + "\":\"" + serialize($(this).children().val()) + "\",";
				}
			}
			//input hidden
			if($(this).children().attr("type") == "hidden") {
				if ($(this).children().attr("name")) {
					txt += "\"" + $(this).children().attr("name") + "\":\"" + serialize($(this).children().val()) + "\",";
				}
			}
			//checkbox
			else if($(this).children().attr("type") == "checkbox") {
				var chknames = "";
				if ($(this).children().attr("name")) {
					var che = $(this).children().get(0);
					if (che.checked) {
						if (chknames.toUpperCase().indexOf(che.name.toUpperCase()) >= 0) {
							var reg = new RegExp("\"" + che.name + "\":" + "\"(.+?)\",", "i");
							txt = txt.replace(reg, "\"" + che.name + "\":" + "\"$1," + che.value + "\",");
						} else {
							chknames += che.name + ",";
							txt += "\"" + che.name + "\":\"" + che.value + "\",";
						}
					}
				}
			}
			//radio
			else if($(this).children().attr("type") == "radio") {
				var rdnames = "";
				if ($(this).children().attr("name")) {
					var rad = $(this).children().get(0);
					if (rad.checked) {
						if (rdnames.toUpperCase().indexOf(rad.name.toUpperCase()) >= 0) {
							var reg = new RegExp("\"" + rad.name + "\":" + "\"(.+?)\",", "i");
							txt = txt.replace(reg, "\"" + rad.name + "\":" + "\"" + rad.value + "\",");
						} else {
							rdnames += rad.name + ",";
							txt += "\"" + rad.name + "\":\"" + rad.value + "\",";
						}
					}
				}
			}
			//textArea
			else if($(this).children().attr("type") == "textarea") {
				if ($(this).children().attr("name")) {
					txt += "\"" + $(this).children().attr("name") + "\":\"" + serialize($(this).children().val()) + "\",";
				}
			}
			//select
			else if($(this).children().attr("type") == "select") {
				if ($(this).children().attr("name")) {
					var value = "";
					var sel = $(this).children().get(0);
					if (sel.selselectedIndex >= 0) {
						if (sel.options[sel.selectedIndex].value) {
							value = sel.options[sel.selectedIndex].value;
						} 
					}
					txt += "\"" + $(this).children().attr("name") + "\":\"" + serialize(value) + "\",";
				}
			}
			//td
			else if($(this).attr("name")) {
				txt += "\"" + $(this).attr("name") + "\":\"" + serialize($(this).html()) + "\",";
			}
		}else {
			if($(this).children().attr("type")){
				//select 
				if($(this).children().attr("type") == "select") {
					var value = "";
					var sel = $(this).children().get(0);
					if (sel.selselectedIndex >= 0) {
						if (sel.options[sel.selectedIndex].value) {
							value = sel.options[sel.selectedIndex].value;
						} 
					}
					eval($(this).attr("belong") + " += \"\\\"" + $(this).children().attr("name") + "\\\"\\\:\\\"" + serialize(value) + "\\\"\\\,\"");
				} 
				//text hidden textarea
				else {
					eval($(this).attr("belong") + " += \"\\\"" + $(this).children().attr("name") + "\\\"\\\:\\\"" + $(this).children().val() + "\\\"\\\,\"");
				}
			}else {
				eval($(this).attr("belong") + " += \"\\\"" + $(this).attr("name") + "\\\"\\\:\\\"" + $(this).html() + "\\\"\\\,\"");
			}
		}
	});
	txt = txt.replace(/,$/, "");
	txt += "\}\,";
	
	//input hidden
	$(form).find("input[type=\"hidden\"]").each(function () {
		if ($(this).attr("name")) {
			txt += "\"" + $(this).attr("name") + "\":\"" + serialize($(this).val()) + "\",";
		}
	});
	
	txt += "\"childs\"\:\[";
	for(var i = 0; i < cellNum; i++) {
		eval("txt += (" + String.fromCharCode(i + 65) + ".replace(/,$/, \"\") + \"\}\,\")");
	}
	txt += DG.replace(/,$/, "") + "}]";
	//txt = txt.replace(/,$/, "");
	var xobj = null;
	eval("xobj={" + txt + "}");
	return txt;
}

function serialize(text) {
	text = text.replace(/(")/g, "\\\"");
	return text;
}



function processItem(id,value){
	var element = $("#"+id);
	if(element.attr("type") == "radio"){
		$("input[name='"+id+"'][value='"+value+"']").attr("checked", true);            
	}else if(element.attr("type") == "checkbox"){
		if(value == "1"){
			element.attr("checked", true); 
		}
	}else if(element.attr("type") == "select-one"){
		if(value != null || value != "") {
			element.val(value);
		}
	}else
		element.attr("value",value);
}

function processItemGZ(name,value){
	var element = $(document.getElementsByName(name));
	if(element.attr("type") == "radio"){
		$("input[name='"+id+"'][value='"+value+"']").attr("checked", true);            
	}else if(element.attr("type") == "checkbox"){
		if(value == "1"){
			element.attr("checked", true); 
		}
	}else{
		if(!element.attr("type")) {
			$("[name='"+name+"']").html(value);
		}else {
			element.attr("value",value);
		}
	}
}

function processItemGZS(childs) {
	for(var i=0; i<childs.length; i++) {
		var index = childs[i]["alxh"];
		$("td[belong='"+String.fromCharCode(index + 64)+"']").each(function() {
			if($(this).children().attr("type")) {
				$(this).children().attr("value", childs[i][$(this).children().attr("name")]);
			}else {
				$(this).html(childs[i][$(this).attr("name")]);
			}
		});
		if(index == 99) {
			$("td[belong='DG']").each(function() {
				if($(this).children().attr("type")) {
					$(this).children().attr("value", childs[i][$(this).children().attr("name")]);
				}else {
					$(this).html(childs[i][$(this).attr("name")]);
				}
			});
		}
	}
}

function permission(){
	$(document.body).find("INPUT").each(function() {
		if($(this).attr("type")=="button") {
			$(this).attr("disabled",true)
		}
		if($(this).attr("type")=="text") {
			$(this).attr("disabled",true)
		}
		if($(this).attr("type")=="radio") {
			$(this).attr("disabled",true)
		}
	});
	$(document.body).find("SELECT").each(function() {
			$(this).attr("disabled",true)
	});
	$(document.body).find("A").each(function() {
			$(this).attr("href","javascript:void(0)")
			$(this).attr("disabled",true)
	});
}
