
/**
 * \u81ea\u52a8\u626b\u63cf\u8868\u683c\uff0c\u63cf\u7ed8\u5947\u5076\u884c\u7684\u989c\u8272
 * oTable\uff1a\u76ee\u6807\u8868\u683c oddClass\uff1a\u5947\u6570\u884c\u7684css\u6837\u5f0f evenClass\uff1a\u5076\u6570\u884c\u7684css\u6837\u5f0f
 */
function setRowColor(oTable,oddClass,evenClass)
{
    resetTableColor(oTable);
    for(var i=1; i<oTable.rows.length; i++)
    {
        if(i % 2 == 0)
            oTable.rows[i].className = evenClass;
        else
            oTable.rows[i].className = oddClass;
    }
}



/**
 * \u81ea\u52a8\u626b\u63cf\u8868\u683c\uff0c\u63cf\u7ed8\u5947\u5076\u5217\u7684\u989c\u8272
 * oTable\uff1a\u76ee\u6807\u8868\u683c oddClass\uff1a\u5947\u6570\u5217\u7684css\u6837\u5f0f evenClass\uff1a\u5076\u6570\u5217\u7684css\u6837\u5f0f
 */
function setColColor(oTable,oddClass,evenClass)
{
    resetTableColor(oTable);
    for(var i=1; i<oTable.rows.length; i++)
    {   
        for(var j=0; j<oTable.rows[i].cells.length; j++)
        {
            if(j % 2 == 0)
                oTable.rows[i].cells[j].className = evenClass;
            else
                oTable.rows[i].cells[j].className = oddClass;
        }
    }
}



//\u6e05\u7a7a\u6240\u6709tr\u548ctd\u7684\u6837\u5f0f
function resetTableColor(oTable)
{
    for(var i=1; i<oTable.rows.length; i++)
    {   
        oTable.rows[i].className = "";
        for(var j=0; j<oTable.rows[i].cells.length; j++)
            oTable.rows[i].cells[j].className = "";
    }   
}

/*
* \u5220\u9664\u8868\u683c\u884c
*/
function __delRow(tdobj){	
        var curObj = null;	
        var rowNum = 1;		
	var tblid = tdobj.parentElement.parentElement.parentElement;
	rowNum = tblid.rows.length;
	if (rowNum == 1 )
	{
		var currRow = tdobj.parentElement.parentElement;
		for ( var i=0; i<currRow.cells.length; i++ )
		{
			var cellNum = currRow.cells(i).childNodes;
			for (var di=0; di<cellNum.length; di++) 
			{
				var child = cellNum(di);
				if ((child.tagName == "INPUT" )&&(child.type=="text"))
				{
					child.value = "";	
				}
				if ((child.tagName == "LABEL" )||(child.tagName=="label"))
				{
					child.innerHTML = "";	
				}

			}
		}
		return;
	}
	
 	if ( tblid.tagName != "TABLE")
 	{
	   tblid =  tdobj.parentElement.parentElement.parentElement.parentElement;
	}	
	tblid.deleteRow(tdobj.parentElement.parentElement.rowIndex);
}
/*
* \u52a0\u5165\u8868\u683c\u884c
*/
function __addRow(tdobj){	
   //     var rows = null;
        var tObj = null;
        var theadNum = 0;
        var tbodyNum = 0;
        var tfootNum = 0;
        var newrow = null;
 				var tblid = tdobj.parentElement.parentElement.parentElement;
			 	tObj = tdobj.parentElement.parentElement.parentElement;
			
			 	
			 	if ( tblid.tagName != "TABLE")
			 	{
				   tblid =  tdobj.parentElement.parentElement.parentElement.parentElement;
				   
				}
			  
				for (var i=0; i<tblid.childNodes.length; i++) 
				{
					if ( tblid.childNodes(i).tagName == "THEAD" )
					{
						theadNum = tblid.childNodes(i).childNodes.length;
					}
					if (tblid.childNodes(i).tagName == "TBODY") 
					{
						tbodyNum = tblid.childNodes(i).childNodes.length;
					}
					if ( tblid.childNodes(i).tagName == "TFOOT" )
					{
						tfootNum = tblid.childNodes(i).childNodes.length; 	
					}
				}
				
			
			        //	var trows = tblid.rows;
				/* thead */
				
				var rowNum = tdobj.parentElement.parentElement.rowIndex ;
				
				var realRow = rowNum;
			
				if (( rowNum >= theadNum )&&(rowNum < theadNum + tbodyNum ))
				{
					realRow = rowNum - theadNum;
				}
			
				if ( rowNum >= theadNum + tbodyNum )
				{
					realRow = rowNum - theadNum - tbodyNum;
				}
				
				newrow = tblid.rows[rowNum].cloneNode(true);
				var tmpr = tObj.insertRow(realRow+1);
				tmpr.replaceNode(newrow);
				
				for ( var i=0; i<newrow.cells.length; i++ )
				{
					var cellNum = newrow.cells(i).childNodes;
					
					for (var di=0; di<cellNum.length; di++) 
					{
						child = cellNum(di);
						if ((child.tagName == "INPUT" )&&(child.type=="text"))
						{
							child.value = "";	
						}
					}
				}

}
/*function_begin
 *name:_isValueLengthOut
 *tooltip:tooltip
 *prototype:_hasString($obj$,$objName$,$subStr$)
 *comment:\u68c0\u67e5\u8f93\u5165\u57df\u5185\u5bb9\u957f\u5ea6,obj\uff1a\u8f93\u5165\u57df\u5bf9\u8c61\u540d\u79f0, objName\uff1a\u63d0\u793a\u65f6\u7684\u8f93\u5165\u57df\u540d\u79f0, subStr \uff1a\u5141\u8bb8\u6700\u5927\u957f\u5ea6
 *type:public
 *function_end
 */
function _isValueLengthOut(obj,objName,maxLength)
{
    if(obj==null)return false;
    var value = obj.value;
    value = _trim(value);
    if(_getStrLength(value)>maxLength)
    {
        alert(objName+"\u957f\u5ea6\u4e0d\u53ef\u5927\u4e8e"+maxLength);
        obj.focus();
        return false;
    }
    return true;
}
/*function_begin
 *name:_hasString
 *tooltip:tooltip
 *prototype:_hasString($obj$,$objName$,$subStr$)
 *comment:\u68c0\u67e5\u8f93\u5165\u57df\u662f\u5426\u5305\u542b\u67d0\u5b57\u7b26\u4e32,obj\uff1a\u8f93\u5165\u57df\u5bf9\u8c61\u540d\u79f0, objName\uff1a\u63d0\u793a\u65f6\u7684\u8f93\u5165\u57df\u540d\u79f0, subStr \uff1a\u5b50\u5b57\u7b26\u4e32
 *type:public
 *function_end
 */
function _hasString(obj,objName,subStr)
{
    if(obj==null)return false;
    if(subStr==null)return false;
    var value = obj.value;
    value = _trim(value);
    if(value.indexOf(subStr)!=-1)
    {
        if(subStr=="\'")
            alert(objName+"\u4e0d\u53ef\u5305\u542b\u5355\u5f15\u53f7");
        else if(subStr=="\"")
            alert(objName+"\u4e0d\u53ef\u5305\u542b\u53cc\u5f15\u53f7");
        else
            alert(objName+"\u4e0d\u53ef\u5305\u542b\""+subStr+"\"");
        obj.focus();
        return false;
    }
    return true;
}

/*
	\u529f\u80fd\uff1a\u68c0\u67e5\u662f\u5426\u6709\u590d\u9009\u6846\u88ab\u9009\u4e2d
	\u53c2\u6570\uff1adoc \u6587\u6863\u5bf9\u8c61
*/
function isAnyItemChecked(doc){
	var inputs = doc.getElementsByTagName("input");
	for(i=0;i<inputs.length;i++){
		if(inputs.item(i).type.toLowerCase()!="checkbox")
			continue;
		if(inputs.item(i).checked)
			return true;
	}
	return false;
}
/*
 *\u53c2\u6570:email\u586b\u5199\u680f
 *\u529f\u80fd:\u68c0\u67e5\u7535\u5b50\u90ae\u4ef6\u7684\u5408\u6cd5\u6027
 */
function checkEmail(emails){
    if (emails.value.length == 0)
        return true;
        
	var emailPattern = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if(emails.value !=null || emails.value !="" ){
		if (emailPattern.test(emails.value)==false)
		{
			alert("\u975e\u6cd5\u7684email\u5730\u5740!");
			return false;
		}
	}
	return true;
}

/*
 *\u51fd\u6570\u540d:checknum
 *\u53c2\u6570:fdname(\u57df\u5bf9\u8c61),fdts(\u57df\u7684lable)
 *\u529f\u80fd:\u6570\u503c\u68c0\u67e5

function checknum(fdname,fdts)
{
	var obj = document.all.item(fdname);
	if(obj==null || obj.value==null || obj.value.length<1 || obj.type=="select-one" || obj.type=="select-multiple" || obj.type=="textarea") 
		return true;
	var tmp = obj.value;
	var  re = /,/g;
	tmp=tmp.replace(re,'');
	if(isNaN(tmp))
	{
		alert(fdts+"\u5fc5\u987b\u662f\u6570\u5b57");
		obj.select();
		return false;
	}
	return true;
}
*/

/*
 *\u51fd\u6570\u540d:checkmust
 *\u53c2\u6570:fdname(\u57df\u5bf9\u8c61),fdts(\u57df\u7684lable)
 *\u529f\u80fd:\u5fc5\u586b\u9879\u68c0\u67e5
 */
function checkmust(fdname,fdts)
{
	var obj = document.all.item(fdname);
	if(obj==null) return true;
	if(obj.value==null || trim(obj.value).length<1)
	{
		alert("\u8bf7\u8f93\u5165"+fdts);
		obj.focus();
		return false;
	}
	return true;
}

/*
 *\u51fd\u6570\u540d:trim
 *\u53c2\u6570:
 *\u529f\u80fd:\u53bb\u6389\u591a\u4f59\u7a7a\u683c
 */
function trim(str)
{
	var start = false;
	var str_tmp;
	var idx = 0;
	for(idx=0;idx<str.length;idx++)
	{
		str_tmp = str.charAt(idx);
		if(str_tmp!=" ")
		{
			break;
		}
	}
	str = str.substring(idx);
	for(idx=str.length;idx>=0;idx--)
	{
		str_tmp = str.charAt(idx);
		if(str_tmp!=" ")
		{
			break;
		}
	}
	str = str.substring(0,idx);
	return str;
}

/*
 *\u51fd\u6570\u540d:checkdate
 *\u51fd\u6570\u529f\u80fd:\u68c0\u67e5\u65e5\u671f\u8f93\u5165\u7684\u683c\u5f0f\uff0c
 *\u6b63\u786e\u683c\u5f0f\u5982\uff1a2003-06-05,03-06-05,03-06-05,03-6-5
 *\u53c2\u6570:dateForm(\u68c0\u67e5\u7684\u5bf9\u8c61),fdts(\u88ab\u68c0\u67e5\u57df\u7684lable)
 */
function checkdate(dateForm,fdts)
{
	//var dateForm=document.all.item(ButtonName);
	if(dateForm == null) return true;
		cDate = dateForm.value;
	dSize = cDate.length;
	sCount= 0;
	/*
	if (cDate == ""){
		alert("\u8bf7\u8f93\u5165\u65e5\u671f\uff01"+fdts);
		dateForm.select();
		return false ;
	}
    */   
	if(cDate=='') return true;

	for(var i=0; i < dSize; i++)
		(cDate.substr(i,1) =="/") ? sCount++ : sCount;
	if (sCount !=2){
		alert(fdts+"\u57df,\u8f93\u5165\u7684\u65e5\u671f\u683c\u5f0f\u5fc5\u987b\u662f\n ''\u5e74/\u6708/\u65e5''");
		dateForm.select();
		return(false);
	};
	var tmpY;
	tmpY=cDate.substring(0,cDate.indexOf("/"))
	ySize = tmpY.length
	if(ySize<2 || ySize>4 || ySize == 3){
		alert(fdts+'\u60a8\u8f93\u5165\u7684\u5e74\u4efd\u6709\u9519\u8bef !');
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
	//alert(dataForm.value);
	if (ok==0){
		alert(fdts+"\u60a8\u8f93\u5165\u7684\u5e74\u4efd\u3001\u6708\u4efd\u548c\u65e5\u671f\u53ef\u80fd\u8d85\u51fa\u4e86\u8303\u56f4 !");
		dateForm.select();
		return false;
	};
	else if (ok==1){
		alert(fdts+"\u60a8\u8f93\u5165\u7684\u6708\u4efd\u548c\u65e5\u671f\u53ef\u80fd\u8d85\u51fa\u4e86\u8303\u56f4 !");
		dateForm.select();
		return false;
	};
	else if (ok==2){
		alert(fdts+strY+"\u5e74\u4e0d\u662f\u95f0\u5e74\uff0c2\u6708\u53ea\u670928\u53f7 !");
		dateForm.select();
		return false;
	};
	else if (ok==3){
		alert(fdts+strY+"\u5e74\u662f\u95f0\u5e74\uff0c2\u6708\u670929\u53f7 !");
		dateForm.select();
		return false;
	};
	return true
}

/*
	\u89e3\u6790\u8f93\u5165\u7684\u6570\u5b57
*/
function parseDouble(arg)
{
	try
	{
		var re =/,/g;
		arg=arg.replace(re,'');
		return parseFloat(arg);
	}catch(e)
	{
		return 0;
	}
}

/*
 *\u53c2\u6570:Y(\u5e74\u4efd),M(\u6708\u4efd),D(\u65e5)
 *\u529f\u80fd:\u65e5\u671f\u51fd\u6570\u7684\u5b50\u51fd\u6570,checkdate(dateForm,fdts)\u8c03\u7528
 */
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

/*\u51fd\u6570\u540d:changeDate
 *\u53c2\u6570	:
 * 		String: objName		\u4e8b\u4ef6\u89e6\u53d1\u7684\u5bf9\u8c61\u540d
 *		String: fdts		\u4e8b\u4ef6\u89e6\u53d1\u7684\u5bf9\u8c61\u7684label
 *\u529f\u80fd	:\u8f93\u5165:20030303
 *		 \u8fd4\u56de:2003-03-03
 */
function changedate(obj,fdts) {
	event.result = false;
	//var obj = document.all.item(objName);
	if(obj == null) return;
	var objvalue = obj.value;
	var len = objvalue.length;
	var kcode = event.keyCode
	try{
		if(kcode == 13 || kcode ==191 || kcode == 8 || kcode == 46 || kcode == 9 || kcode ==144 || kcode == 106 || (kcode>36 && kcode <41))
			return true;
		if((kcode > 47 && kcode < 60 ) || (kcode > 95 && kcode < 106 )){
			if(len > 9){
				obj.value = objvalue.substring(0,9);
			}
			//if((len + 1) == 9)
			//	attachEvent("onblur",checkdate(objName,fdts));
			if(len == 4 || len ==7 )
				obj.value = objvalue +"/";
		}else{
			obj.value = "";
			return false;
		}
	}catch (e){
		return true;
	}		
}

/* 
	e.g: 2003-03-10 to 20030310
*/
function parseDate(str){
	re = /-/g;
	return str.replace(re, "");
}
/*
	e.g: 20030310 to 2003-03-10
*/
function formatDate(str){
	var year, month, day;
	if(str.length==8){
		year = str.substr(0, 4);
		month = str.substr(4, 2);
		day = str.substr(6, 2);
		result = "" + year + "-" + month + "-" + day;
		return result;
	}else
		return str;
}
function selectAll(el){
	var inputs = document.all.tags("input");
	
	for(i=0;i<inputs.length;i++){
	if((inputs[i].type.toLowerCase()=="checkbox")&&(inputs[i].name!="select"))
		inputs[i].checked = el.checked;
	}	
}

function previous(){
	if (document.forms("main").elements("currentPage").value > 1)
		document.forms("main").elements("currentPage").value--;
	document.forms("main").elements("status").value = "0";
	document.forms("main").submit();
}
function previouspage(){
	if (document.forms("main").elements("currentPage").value > 1)
		document.forms("main").elements("currentPage").value--;
	
	document.forms("main").submit();
}
function next(pagesCount){
	if (document.forms("main").elements("currentPage").value < pagesCount)
		document.forms("main").elements("currentPage").value ++;
	document.forms("main").elements("status").value = "0";
	document.forms("main").submit();
}
function nextpage(pagesCount){
	if (document.forms("main").elements("currentPage").value < pagesCount)
		document.forms("main").elements("currentPage").value ++;
	
	document.forms("main").submit();
}

function unHighLight(el) 
{	
	el.style.background = "";
	el.style.color = "windowtext";	
	el.style.cursor = "default";
}		
function highLight(el, background) 
{
	el.style.background = background;
	el.style.color = "black";
	el.style.fontWeight ="normal";
	el.style.borderLeft="1px black solid";
	el.style.cursor = "hand";
}

function fResize(arg)
{
	if (loadedfully)
	{
		arg = (arg==-1)?100:arg;
		var w = document.body.clientWidth; 
		var h = document.body.clientHeight;
		
		var iBodyHeight=h-arg;
		var iBodyWidth=(w);

		if (document.getElementById("divFooter"))
		{
			document.getElementById("divFooter").style.display="block";
			
			if (h>0)
			{
				document.getElementById("divBody").style.height=iBodyHeight;
				document.getElementById("divBody").style.width=iBodyWidth;
			}
		}
	}
}

/*function_begin
 *name:_isValueNumber
 *tooltip:tooltip
 *prototype:__isValueNumber($obj$,$objName$)
 *comment:\u68c0\u67e5\u8f93\u5165\u57df\u5185\u5bb9\u662f\u5426\u4e3a\u6570\u5b57\u5e76\u7b26\u5408\u8981\u6c42,obj\uff1a\u8f93\u5165\u57df\u5bf9\u8c61\u540d\u79f0, objName\uff1a\u63d0\u793a\u65f6\u7684\u8f93\u5165\u57df\u540d\u79f0\uff0c
 *        lenNum\uff1a\u6700\u5927\u957f\u5ea6,preciseNum\uff1a\u6700\u5927\u7cbe\u5ea6,nullable\uff1a\u662f\u5426\u53ef\u4ee5\u4e3a\u7a7a
 *type:public
 *function_end
 */
function _isValidNumber(obj,objName,lenNum,preciseNum,nullable)
{
	if(obj==null)	
	   return false;
		
	var val = NumberFormat.toNumber(obj.value);
	val=_trim(val);
	if( val	== null	||val=="" ) {
		if ( nullable	)	return true;
		alert(objName+"\u5fc5\u987b\u8f93\u5165");
		obj.focus();
		return false;
	}

	var  parterList = val.split(".");	
	if (parterList[0].length > (lenNum - preciseNum) ) {
		alert(objName+"\u6574\u6570\u4f4d\u957f\u5ea6\u4e0d\u80fd\u5927\u4e8e" + (lenNum - preciseNum) );
		obj.focus();
		return false;
	}
	if (parterList.length > 1){
		if (parterList[1].length	>	preciseNum){
			alert(objName+"\u5c0f\u6570\u4f4d\u6570\u4e0d\u80fd\u5927\u4e8e" + preciseNum );
			obj.focus();
			return false;
		}
	}
	
	if(isNaNS(val,1)) {
		alert(objName+"\u5fc5\u987b\u4e3a\u6570\u5b57");
		obj.focus();
		return false;
	}
	
	return true;
}

/*function_begin
 *name:_lTrim
 *tooltip:tooltip
 *prototype:_lTrim($trans1$)
 *comment:\u538b\u7f29\u5de6\u8fb9\u7684\u7a7a\u683c
 *type:public
 *function_end
 */
function _lTrim(s) {
	return (typeof(s) != "string") ? null : s.replace(/^ +/, "");
}


/*function_begin
 *name:_rTrim
 *tooltip:tooltip
 *prototype:_rTrim($trans1$)
 *comment:\u538b\u7f29\u53f3\u8fb9\u7684\u7a7a\u683c
 *type:public
 *function_end
 */
// remove trailing spaces
function _rTrim(s) {
	return (typeof(s) != "string") ? null : s.replace(/ +$/, "");
}

/*function_begin
 *name:_trim
 *tooltip:tooltip
 *prototype:_trim($s1$)
 *comment:\u53bb\u6389\u5b57\u7b26\u4e32\u7684\u7a7a\u683c
 *type:public
 *function_end
 */
function _trim(s1){
      var r;
      if(s1==null)s1="";
      r=_lTrim(s1);
      r=_rTrim(r);
      return r;
}

function isNaNS(value,flag)
{
	var valueStr="";
	if (flag == 0 )
	{
		valueStr = value.toString(10);
	}
	else
	{
		valueStr = value;
	}

	var NumStrArray = valueStr.split(".");
	
	if(NumStrArray.length>2)
	{
		return true;
	}else 
	{
		for ( var i=0; i<NumStrArray.length; i++ )
		{
			if( isIntN(NumStrArray[i], flag))
			{
				return true;
			}
		}
	}
	return false;
}

function isIntN(value,flag)
{
	var valueStr="";
	if (flag == 0 )
	{
		valueStr = value.toString(10);
	}
	else
	{
		valueStr = value;
	}

	

	var blength = valueStr.length;

	var floatNum = parseFloat(valueStr);

	if ( isNaN(floatNum)== true )
	{
		return true;
	}
	
	var numLength = floatNum.toString(10).length;
		
	if ( numLength == blength )
		return false;
	else
	{
		var zero = valueStr.substr(0,blength-numLength);
		var tail = valueStr.substr(blength-numLength, numLength);


		if (( parseFloat(zero) == 0 )&&(floatNum.toString(10)==tail))
		{
		   return false;
		}
		else
		   return true;
	}
}



    var getFFVersion=navigator.userAgent.substring(navigator.userAgent.indexOf("Firefox")).split("/")[1]
    //extra height in px to add to iframe in FireFox 1.0+ browsers
    var FFextraHeight=getFFVersion>=0.1? 16 : 0 
            
    function dyniframesize(iframename) {
      var pTar = null;
      if (document.getElementById){
        pTar = document.getElementById(iframename);
      }
      else{
        eval('pTar = ' + iframename + ';');
      }
      if (pTar && !window.opera){
        //begin resizing iframe
        pTar.style.display="block"
        
        if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight){
          //ns6 syntax
          pTar.height = pTar.contentDocument.body.offsetHeight+FFextraHeight; 
        }
        else if (pTar.Document && pTar.Document.body.scrollHeight){
          //ie5+ syntax
          pTar.height = pTar.Document.body.scrollHeight;
        }
      }
    }
