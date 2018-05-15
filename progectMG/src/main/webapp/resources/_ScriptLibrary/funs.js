/*	function addRow(table){
		//para: table name/id
		//desc: add a row

		var row = table.insertRow();
		var cellsTemplate = table.rows[1].cells;
		var cellCaption = table.rows[0].cells;
		for(var i=0;i<cellsTemplate.length;i++){
			var cell = row.insertCell();
			cell.innerHTML = cellsTemplate[i].innerHTML;
		}
	}*/
/*
 *dgjin add 2003-05-20d
 */
 function delRow(obj){
 	if(obj.parentNode.childNodes.length==4){
 		alert("这已经是最后一行了，不能删除！");
		return false
	}
    	obj.parentNode.removeChild(obj);   
    } 
function addRow(table){
		//para: table name/id
		//desc: add a row

		var row = table.insertRow();
		var cellsTemplate = table.rows[1].cells;
		var cellCaption = table.rows[0].cells;
		for(var i=0;i<cellsTemplate.length;i++){
			var cell = row.insertCell();
			cell.innerHTML = cellsTemplate[i].innerHTML;
			cell.attachEvent("onkeydown",attachEnter3);
		}
	}
	function trimTable(table){
		var tbl = document.createElement("table");
		var rows1=table.all.tags("tr");
		for(var i=3;i<rows1.length;i++){
			//alert(i);
			var cells=rows1[i].all.tags("td");
			var candel=true;
			for(var j=0;j<cells.length;j++){
				if(cells[j].children[0] && cells[j].children[0].value!=""){
					candel=false;
				}
			}
			if(candel){
				tbl.appendChild(table.rows[i]);
				i=2;
			}
		}
	}
	function mySubmit(){
		if(!checkValue()){
			//alert("域值检查没通过,请检查各域的输入情况!");
			return false
		}
		if(document.workbench.action.indexOf("actionname")<0){
			alert("请选择一个操作功能!");
			return false
		}
		var actionValue="";
		var pos1;
		pos1=document.workbench.action.lastIndexOf("=");
		actionValue=document.workbench.action.substring(pos1+1)

		if(actionValue=="print"){
			printPage();
			return false;
		}
/*
		var ss=document.workbench.action;	
		var pp1 = ss.indexOf("=");
		var pp2 = ss.indexOf("&");
		var pp = ss.substring(pp1+1,pp2);
		alert(pp);
		if ( pp != "dzshwtczd") 
*/
		var tables=document.all.tags("table");
		for(var i=0;i<tables.length;i++){
			if(tables[i].name)
				if(tables[i].name.substring(0,1)=="m")
					trimTable(tables[i]);
		}

		return true;
	}

	function myReset(){
		var inputs=document.all.tags("input");
		for(var i=0;i<inputs.length;i++){
			if(inputs[i].type=="text"){
				inputs[i].value="";
			}
			if(inputs[i].type=="checkbox"){
				inputs[i].checked=false;
			}
		}
		var selects=document.all.tags("select");
		for(var i=0;i<selects.length;i++){
			selects[i].value="";
			document.all(selects[i].name+"_select").value="";
		}
              //dgjin add 2001-12-30
		var textarea=document.all.tags("textarea");
		for(var i=0;i<textarea.length;i++){
			textarea[i].value="";
		}
		return false;
	}
/*
	function delRow(obj,table){
		//para: table name/id
		//desc: del the last row of table
		//dgjin update 2002-08-15
		var tbl = document.createElement("table");
		//dgjin update 2002-08-15
		if(table.rows.length == 4){
			alert("这已经是最后一行了，不能删除！");
			return false
		}
		for(var i=0;i<table.rows.length;i++){
			if(table.rows[i].contains(obj.parentElement)){
				//table.removeNode(table.rows[i]);
				if(i >=3){
					tbl.appendChild(table.rows[i]);
					//alert("aa");
				}
			}
		}
	}
*/
	function attachEnter2(obj,table){
	//obj anyone focused
	//table table id
	//desc: with addrow
		if(event.keyCode!=13)
			return true;

		if(obj.tagName=="TEXTAREA"){
			//alert(obj.tagName);
			return true;
		}

		var cells=table.all.tags("td");
		for(var i=0;i<cells.length;i++){
			//for(var j=0;j<cells[i].children.length;j++){
				if(cells[i].children.length==0)
					continue;
				if(cells[i].children[0]==obj){
					if(i==cells.length-1){
						addRow(table);
						if(cells[i+1].children.length>0){
							cells[i+1].children[0].focus();
							return false;
						}
						else{
							cells[i+1].focus();
							//cells[i+1].onkeydown();
							return false;
						}
					}else{
						if(cells[i+1].children.length>0){
							cells[i+1].children[0].focus();
							return false;
						}
						else{
							cells[i+1].focus();
							//cells[i+1].onkeydown();
							return false;
						}
					}
				}
			//}
		}
		return false;
	}

/*	function attachEnter3(obj,table){
	//obj cell focused
	//table table id
	//desc: without addrow
		if(event.keyCode!=13)
			return true;

		var cells=table.all.tags("td");
		for(var i=0;i<cells.length;i++){
				if(cells[i]==obj){

					if(i==cells.length-1){
						//do nothing???
						//getnext table???
						switchfocus(table);
					}
					else{
						if(cells[i+1].children.length==0)
							cells[i+1].onkeydown();
						else
							cells[i+1].children[0].focus();
					}
				}
		}
		return false;
	}
	*/
		function attachEnter3(){
	//obj cell focused
	//table table id
	//desc: without addrow
		if(event.keyCode!=13)
			return true;

		if(event.srcElement.tagName=="TEXTAREA"){
			return true;
		}
		var obj=event.srcElement.parentElement;
		var table=event.srcElement;

		while(table.tagName!="TABLE"){
			table=table.parentElement;
		}
		if(obj.tagName!="TD")
			obj=obj.parentElement;


		var cells=table.all.tags("td");
		for(var i=0;i<cells.length;i++){
			if(cells[i]==obj){
				if(i==cells.length-1){
					if(table.name.substring(0,1)=="m"){
						addRow(table);
						//cells[i+1].children[0].focus();
						if(!cells[i+1].children[0].disabled){
							cells[i+1].children[0].focus();
							break;
						}else{
							while(cells[i+1].children[0].disabled){
								i=i+1;
								if(i>=cells.length-1)
									break;
							}
							if(i>=cells.length-1){
								addRow(table);
								i++;
							}
							cells[i+1].children[0].focus();

							break;
						}
					}else
						switchfocus(table);
				}else{
					if(table.name.substring(0,1)=="m"){
						if(cells[i+1].children.length==0)
							cells[i+1].onkeydown();
						else{
							//cells[i+1].children[0].focus();
							if(!cells[i+1].children[0].disabled){
								cells[i+1].children[0].focus();
								break;
							}else{
								while(cells[i+1].children[0].disabled){
									i=i+1;
									if(i>=cells.length-1)
										break;
								}
								if(i>=cells.length-1){
									addRow(table);
									i++;
								}
								cells[i+1].children[0].focus();

								break;
							}

						}
					}else{
						if(cells[i+2].children.length==0 && i>cells.length-2){
							switchfocus(table);
						}
						else{
							//cells[i+2].children[0].focus();
							if(cells[i+2].children.length>0 && !cells[i+2].children[0].disabled)
								cells[i+2].children[0].focus();
							else{
								while(cells[i+2].children.length==0 || cells[i+2].children[0].disabled){
									i=i+2;
									if(i>=cells.length-1)
										break;
								}

								if(i>=cells.length-1){
									switchfocus(table);
									break;
								}
								if(cells[i+2].children.length>0 && cells[i+2].children[0].disabled){
									switchfocus(table);
									break;
								}
								cells[i+2].children[0].focus();

								break;
								//cells[i+2].onkeydown();
							}
						}
					}
				}
				break;
			}
		}
		return false;
	}

function setaction(obj){
	workbench.action=obj.value;
}
//日起检查函数 金定国添加于2002-01-23
function valDate(Y, M, D){
	Months= new Array(31,28,31,30,31,30,31,31,30,31,30,31);
	Leap = false;
     var error;
	if((Y % 4 == 0) && ((Y % 100 != 0) || (Y %400 == 0)))
		Leap = true;
	if((D < 1) || (D > 31) || (M < 1) || (M > 12) || (Y < 0)){
	    error=0
		return error;
		}
	if((D > Months[M-1]) && !((M == 2) && (D > 28))){
	    error=1
		return error;
		}
	if(!(Leap) && (M == 2) && (D > 28)){
		error=2;
		return error;
		}
	if((Leap) && (M == 2) && (D > 29)){
	    error=3
		return error;
		}
}
function IsDate(dateForm){
	cDate = dateForm.value;
	dSize = cDate.length;
	sCount= 0;

	/*if (cDate == ""){
		alert("请输入日期！");
		dateForm.select();
		return false ;
	}
         */
	if(cDate=='') return;

	for(var i=0; i < dSize; i++)
		(cDate.substr(i,1) =="/") ? sCount++ : sCount;
	if (sCount !=2){
		alert("输入的日期格式必须是\n ''年/月/日''");
		dateForm.select();
		return(false);
	};
	var tmpY;
	tmpY=cDate.substring(0,cDate.indexOf("/"))
	ySize = tmpY.length
	if(ySize<2 || ySize>4 || ySize == 3){
		alert('您输入的年份有错误 !');
		dateForm.select();
		return false;
	};
	idxBarI = cDate.indexOf("/");
	idxBarII = cDate.lastIndexOf("/");
	strY = cDate.substring(0,idxBarI);
	strM = cDate.substring(idxBarI+1,idxBarII);
	strD = cDate.substring(idxBarII+1,dSize);

	strM = (strM.length < 2 ? '0'+strM : strM);
	strD = (strD.length < 2 ? '0'+strD : strD);
	if(strY.length == 2)
		strY = (strY > 50 ? '19'+strY : '20'+strY);
	dateForm.value = strY+'/'+strM+'/'+strD;
	ok = valDate(strY, strM, strD);
	if (ok==0){
		alert("您输入的年份、月份和日期可能超出了范围 !");
		dateForm.select();
		return false;
	};
	else if (ok==1){
		alert("您输入的月份和日期可能超出了范围 !");
		dateForm.select();
		return false;
	};
	else if (ok==2){
		alert(strY+"年不是闰年，2月只有28号 !");
		dateForm.select();
		return false;
	};
	else if (ok==3){
		alert(strY+"年是闰年，2月有29号 !");
		dateForm.select();
		return false;
	};
	return true
}

function repaint(){
	var tbls=document.all.tags("fieldset");
	var maxTblLength=600;
	for(var i=0;i<tbls.length;i++){
		//if(tbls[i].clientWidth>maxTblLength){
			//rowspan
			//alert(tbls[i].rows.length);
			/*while(tbls[i].clientWidth>maxTblLength){
				var rownum=tbls[i].clientWidth/maxTblLength;
				if(tbls[i].clientWidth % maxTblLength>0)
					rownum=rownum + 1;
			}*/
		//}
	}
}

function printPage(){
	var pageRowNum=30;
	var onepagecontent="";
	var wholepagecontent="";

	//count the rows of 
	var doc_children=document.all.tags("form")[0].childNodes;
	var rownum=0;
	for(var i=0;i<doc_children.length;i++){	
		if(doc_children[i].tagName=="FIELDSET"){			
			//处理fieldset			
			var childOfFieldset=doc_children[i].childNodes;
			var title="";
			if(rownum==0)
				onepagecontent = onepagecontent + "<table width=610 align=center class=mytable>";
			onepagecontent = onepagecontent + "<tr><td class=mytd><fieldset style='width:605'>";
			for(var j=0;j<childOfFieldset.length;j++){				
				if(childOfFieldset[j].tagName=="LEGEND"){					
					if(rownum>pageRowNum){
						//一页
						wholepagecontent = wholepagecontent + onepagecontent + "</table><br><br><br><br><xmp></xmp>";
						rownum=0;
						onepagecontent="<table width=610 align=center class=mytable><tr><td class=mytd>";
					}
					rownum = rownum +1;
					onepagecontent = onepagecontent + childOfFieldset[j].outerHTML;
					title=childOfFieldset[j].outerHTML;
					
				}
				if(childOfFieldset[j].tagName=="TABLE"){
					var tbl=childOfFieldset[j];
					var rows=tbl.all.tags("tr");
					var cells1=rows[0].all.tags("td");
					var startrow=0;
					onepagecontent = onepagecontent + "<table border=0 cellpadding=0 cellspacing=0  width=600 style='border-collapse:collapse;table-layout:fixed'>";
					
					if(tbl.name && tbl.name.substring(0,2)=="m_"){	
						cells1=rows[3].all.tags("td");			
						startrow=3;
						//alert(tbl.name + "---" +startrow + "-----" + rows.length);
						var cells=rows[0].childNodes;
						onepagecontent = onepagecontent + "<tr>";
						for(var m=0;m<cells.length;m++){
							onepagecontent = onepagecontent + "<td>";
							if(cells[m].children.length==0){
								if(cells[m].innerText && cells[m].innerText.length>0)
								{
									onepagecontent = onepagecontent + cells[m].innerText;
								}
								else
								{
									onepagecontent = onepagecontent + "&nbsp;&nbsp;";
								}
							}
							rownum = rownum + 1;
						}
						onepagecontent = onepagecontent + "</tr>";
					}
					//计算rowspan
					var countchars=0,rowspan=1;
					for(var m=0;m<cells1.length;m++){
						if(cells1[m].children.length==0){
							//alert("001" + cells1[m].innerText.length );
							countchars=countchars + cells1[m].innerText.length;
						}else{
							// set text input
							
							if(cells1[m].children[0].tagName=="INPUT" && cells1[m].children[0].type=="text"){
								//alert("002" + cells1[m].children[0].maxLength );
								countchars=countchars + cells1[m].children[0].maxLength;
							}
							
							//处理select
							if(cells1[m].children[0].tagName=="SELECT"){
								var options=cells1[m].children[0].childNodes;
								var maxLengthOfOptions=0;
								for(var n=0;n<options.length;n++){
									if(options[n].innerText && options[n].innerText.length>maxLengthOfOptions){
										maxLengthOfOptions=options[n].innerText.length;
										//alert("003" + options[n].innerText.length);
									}
								}
								countchars=countchars + maxLengthOfOptions;
							}
									
						}						
					}
					/*while(countchars>70){
						rowspan += 1;
						countchars-=70;
						alert("rowspan---" + rowspan);
						
					}*/
					//alert(countchars + "---" + rowspan);
					//set Content
					for(var l=startrow;l<rows.length;l++){
						var cells=rows[l].childNodes;
						if(rownum>pageRowNum){
							onepagecontent=onepagecontent+"</table></fieldset></td></tr>";
							wholepagecontent = wholepagecontent + onepagecontent + "<br></table><xmp></xmp>";
							rownum=0;							
							//重新生成title信息
							onepagecontent = "<table width=610 align=center class=mytable><tr><td class=mytd><fieldset>" + title + "<table border=0 cellpadding=0 cellspacing=0 width=600 style='border-collapse:collapse;table-layout:fixed'>";
						}
						rownum = rownum +1;											
						onepagecontent = onepagecontent + "<tr>";
						for(var m=0;m<cells.length;m++){
							onepagecontent = onepagecontent + "<td >";
							if(cells[m].children.length==0){
								onepagecontent = onepagecontent + cells[m].innerText + "&nbsp;&nbsp;";
							}else{
								//处理文本框
								
								if(cells[m].children[0].tagName=="INPUT" && cells[m].children[0].type=="text"){
									if(cells[m].children[0].value!="")
										onepagecontent = onepagecontent + cells[m].children[0].value;
									else
										onepagecontent = onepagecontent + "&nbsp;&nbsp;";
								}
								
								//处理select
								if(cells[m].children[0].tagName=="SELECT"){
									var options=cells[m].children[0].childNodes;
									if(cells[m].children[0].value=="")
										onepagecontent = onepagecontent + "&nbsp;&nbsp;";
									for(var n=0;n<options.length;n++){
										if(options[n].value==cells[m].children[0].value)
											onepagecontent = onepagecontent + options[n].innerText;
									}
								}
								else
								{
								    onepagecontent = onepagecontent + cells[m].innerText + "&nbsp;&nbsp;";

								}
							}
							onepagecontent = onepagecontent + "</td>";							
						}
						onepagecontent = onepagecontent + "</tr>";
					}					
					onepagecontent = onepagecontent + "</table>";
				}
			}
			onepagecontent = onepagecontent + "</fieldset></td></tr>";
						
		}
	}
	wholepagecontent = wholepagecontent + onepagecontent + "</table><br><br><br><br><xmp></xmp>";	
 	var win=window.open("");
 	win.document.write("<HTML><HEAD><TITLE></TITLE><link href=aa.css rel=stylesheet type=text/css>");
	win.document.write("<script language=\"JavaScript\">");
	win.document.write("function repaint\(\){");
	win.document.write("var tbls=document.all.tags\(\"fieldset\"\);");
	win.document.write("var maxTblLength=600;");
	win.document.write("for\(var i=0;i<tbls.length;i++\){");
	win.document.write("/*   if\(tbls[i].clientWidth>maxTblLength\){");
	win.document.write("alert\(tbls[i].rows.length\);");
	win.document.write("while\(tbls[i].clientWidth>maxTblLength\){var rownum=tbls[i].clientWidth/maxTblLength; if\(tbls[i].clientWidth % maxTblLength>0) rownum=rownum + 1; } }  */    } }");
        win.document.write("</script>");
        win.document.write("</HEAD><body>")
        win.document.write(wholepagecontent);
        win.document.write("<script>repaint();</script></body></html>");
}


function check(str){
	//判断是否汉字
	//74个西文字母
	if(str.match(/[^ -}]/))
		alert("有");else alert("无");
}

function checkthis(obj){
	if(obj.checked)
		obj.value="1";
	else
		obj.value="0";
}
//金定国添加金额类型检查函数2002-03-07
function isFloat(obj){

	if(isNaN(obj.value)){

		alert("输入的金额类型不对！")
		obj.select();
		return false
	}

        return true
}

function compareDate(strDate1,strDate2){
	var pos=strDate1.indexOf("/");
	var year=strDate1.substring(0,pos);
	strDate1=strDate1.substring(pos+1);
	var date1=new Date(strDate1 + "/" + year);

	pos=strDate2.indexOf("/");
	year=strDate2.substring(0,pos);
	strDate2=strDate2.substring(pos+1);
	var date2=new Date(strDate2 + "/" + year);

	if(date1>date2){
		return true;
	}else{
		return false;
	}
}
