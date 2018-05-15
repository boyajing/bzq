
function Scroll_Change(){
	document.forms[0].__SCROLLPOS.value = myBody.scrollTop;
}

function sysAlert(info){
	alert("\u7cfb\u7edf\u4fe1\u606f\u63d0\u793a\uff1a"+info+"\u3002");
}

/*\u6b64\u51fd\u6570\u4e3a\u7cfb\u7edf\u7edf\u4e00\u7684\u786e\u8ba4\u5bf9\u8bdd\u6846
 *\u53c2\u6570\u8bf4\u660e\uff1ainfo\u2014\u2014\u786e\u8ba4\u4fe1\u606f

 *\u8fd4\u56de\u503c\uff1atrue(\u786e\u8ba4)\uff1bfalse(\u53d6\u6d88)
 *001-11-21-qp
 *\u66f4\u6539\u7eaa\u5f55\uff1a

 */
 
 function sysConfirm(info){
	return confirm(info+",\u786e\u5b9e\u8981\u7ee7\u7eed?");
}
function compareValue(oldVal,curtVal){
	//\u6bd4\u8f83oldVal\u548ccurtVal
	//if oldVal<curtVal then return false
	//\u9996\u5148\u53bb\u6389 \u91d1\u989d\u4e2d\u7684","
	
	while(oldVal.search(",")!=-1){
		oldVal=oldVal.replace(",","");
	}
	while(curtVal.search(",")!=-1){
		curtVal=curtVal.replace(",","");
	}
	if(parseFloat(oldVal)<parseFloat(curtVal)) return(false);
}
function compareNum(oldFld,curtFld){
	
	//if old<=curt then return false else return true;
	//\u9996\u5148\u53bb\u6389 \u91d1\u989d\u4e2d\u7684","
	var oldVal = oldFld.value;
	var curtVal = curtFld.value;
	oldFld.value = replace_illegal(oldVal);
	if (oldFld.value=="") oldFld.value=0.0;
	curtFld.value = replace_illegal(curtVal);
	if (curtFld.value=="") curtFld.value=0.0;	

	if(parseFloat(oldFld.value)<=parseFloat(curtFld.value)) return(false);
	return true;
}
//\u5305\u62ec\u76f8\u7b49\u548c\u5c0f\u4e8e\uff0c\u5747\u4e3atrue
function compareNum2(oldFld,curtFld){
	
	//if old<=curt then return false else return true;
	//\u9996\u5148\u53bb\u6389 \u91d1\u989d\u4e2d\u7684","
	var oldVal = oldFld.value;
	var curtVal = curtFld.value;
	oldFld.value = replace_illegal(oldVal);
	if (oldFld.value=="") oldFld.value=0.0;
	curtFld.value = replace_illegal(curtVal);
	if (curtFld.value=="") curtFld.value=0.0;	

	if(parseFloat(oldFld.value)<parseFloat(curtFld.value)) return(false);
	return true;
}
function subValue(val1,val2){
	//val1-val2
	while(val1.search(",")!=-1){
		val1=val1.replace(",","");
	}
	while(val2.search(",")!=-1){
		val2=val2.replace(",","");
	}
	return vbFormatNumber(parseFloat(val1)-parseFloat(val2));
} 
/*\u6b64\u51fd\u6570\u5224\u65ad\u8f93\u5165\u5b57\u7b26\u7684\u6700\u5927\u548c\u6700\u5c0f\u957f\u5ea6

 *\u53c2\u6570\u8bf4\u660e\uff1afld\u2014\u2014\u8981\u68c0\u67e5\u7684\u8f93\u5165\u6846\uff1bfldName\u2014\u2014\u8be5\u5b57\u6bb5\u4e2d\u6587\u540d\uff1bmin\u2014\u2014\u6700\u5c0f\u957f\u5ea6\uff1bmax\u2014\u2014\u6700\u5927\u957f\u5ea6

 *\u8fd4\u56de\u503c\uff1afalse(\u9a8c\u8bc1\u672a\u901a\u8fc7)
 *\u51fa\u9519\u6761\u4ef6\uff1a\u8f93\u5165\u5b57\u7b26\u957f\u5ea6\u5c0f\u4e8e\u6700\u5c0f\u957f\u5ea6\u6216\u5927\u4e8e\u6700\u5927\u957f\u5ea6

 *2001-11-21-qp
 *\u66f4\u6539\u7eaa\u5f55\uff1a

 *2001-11-22-qp\uff1asysAlert\u540e\u589e\u52a0\u62ec\u53f7\uff08\u8bed\u6cd5\u9519\u8bef\uff09

 */
 
/*\u6b64\u51fd\u6570\u5224\u65ad\u8f93\u5165\u5b57\u7b26\u7684\u6700\u5927\u548c\u6700\u5c0f\u957f\u5ea6

 *\u53c2\u6570\u8bf4\u660e\uff1afld\u2014\u2014\u8981\u68c0\u67e5\u7684\u8f93\u5165\u6846\uff1bfldName\u2014\u2014\u8be5\u5b57\u6bb5\u4e2d\u6587\u540d\uff1bmin\u2014\u2014\u6700\u5c0f\u957f\u5ea6\uff1bmax\u2014\u2014\u6700\u5927\u957f\u5ea6

 *\u8fd4\u56de\u503c\uff1afalse(\u9a8c\u8bc1\u672a\u901a\u8fc7)
 *\u51fa\u9519\u6761\u4ef6\uff1a\u8f93\u5165\u5b57\u7b26\u957f\u5ea6\u5c0f\u4e8e\u6700\u5c0f\u957f\u5ea6\u6216\u5927\u4e8e\u6700\u5927\u957f\u5ea6

 *2001-11-21-qp
 *\u66f4\u6539\u7eaa\u5f55\uff1a

 *2001-11-22-qp\uff1asysAlert\u540e\u589e\u52a0\u62ec\u53f7\uff08\u8bed\u6cd5\u9519\u8bef\uff09

 */
 
function checkChar(fld,fldName,min,max){
	if(fld.value.length < min){
		sysAlert("\u57df <"+fldName+"> \u4e2d\u81f3\u5c11\u9700\u8981\u8f93\u5165"+min+"\u4e2a\u5b57\u7b26");
		//fld.focus();
		return(false);
	}
	if(fld.value.length > max){
		sysAlert("\u57df <"+fldName+"> \u4e2d\u6b62\u591a\u53ea\u80fd\u8f93\u5165"+max+"\u4e2a\u5b57\u7b26");
		//fld.focus();
		fld.value = "";
		return(false);
	}
}

/*\u6b64\u51fd\u6570\u5224\u65ad\u7528\u6237\u5bf9\u4e0b\u62c9\u5217\u8868\u6b3e\u662f\u5426\u9009\u62e9\u4e86\u7b2c\u4e00\u9879

 *\u4f7f\u7528\u60c5\u51b5\uff1a\u9650\u5236\u7528\u6237\u5bf9\u4e0b\u62c9\u5217\u8868\u6b3e\u7b2c\u4e00\u9879\u7684\u9009\u62e9
 *\u53c2\u6570\u8bf4\u660e\uff1acomb\u2014\u2014\u8981\u68c0\u67e5\u7684\u4e0b\u62c9\u5217\u8868\u6846\uff1bfldName\u2014\u2014\u8be5\u5217\u8868\u6846\u4e2d\u6587\u540d\uff08\u51fa\u9519\u63d0\u793a\u7528\uff09

 *\u8fd4\u56de\u503c\uff1afalse(\u9a8c\u8bc1\u672a\u901a\u8fc7)
 *\u51fa\u9519\u6761\u4ef6\uff1a\u5f53\u7528\u6237\u9009\u62e9\u8be5\u4e0b\u62c9\u5217\u8868\u6846\u7b2c\u4e00\u9879\u65f6
 *\u5907\u6ce8\uff1a\u8be5\u51fd\u6570\u5e94\u89c6\u9700\u8981\u4f7f\u7528\u3002

 *2001-11-21-qp
 *\u66f4\u6539\u7eaa\u5f55\uff1a

 */
 
function  checkCombox(comb,fldName){
   if(comb.selectedIndex==0||comb.selectedIndex==-1){
	sysAlert("\u57df <"+fldName+"> \u7b2c\u4e00\u4e2a\u9009\u9879\u4e3a\u65e0\u6548\u9009\u9879\uff0c\u8bf7\u9009\u62e9\u5176\u4ed6\u9009\u9879");
	comb.focus();
	return false ;
   }else{
    return true;
	}
}

/*\u6b64\u51fd\u6570\u9a8c\u8bc1\u8f93\u5165\u6570\u503c\u662f\u5426\u6709\u6548

 *\u53c2\u6570\u8bf4\u660e\uff1afld\u2014\u2014\u8981\u68c0\u67e5\u7684\u8f93\u5165\u6846\uff1bfldName\u2014\u2014\u8be5\u5b57\u6bb5\u4e2d\u6587\u540d\uff08\u51fa\u9519\u63d0\u793a\u7528\uff09
 *\u8fd4\u56de\u503c\uff1afalse(\u9a8c\u8bc1\u672a\u901a\u8fc7)
 *\u51fa\u9519\u6761\u4ef6\uff1a1\u3002\u5b57\u6bb5\u4e3a\u7a7a\uff1b2\u3002\u8f93\u5165\u975e\u6570\u503c\u578b\u5b57\u7b26\uff1b3\u3002\u8f93\u5165\u7684\u6570\u503c\u65e0\u6548\uff1b
 *2001-11-21-qp
 *\u66f4\u6539\u7eaa\u5f55\uff1a

 */
 
function checkNum(fld,fldName){
  if(fld.value==""){
    sysAlert("\u8bf7\u5728\u57df <"+fldName+"> \u4e2d\u8f93\u5165\u503c");
    fld.focus();
    return (false);
  }
  var checkOK = "0123456789-.,";
  var checkStr = fld.value;
  var allValid = true;
  var decPoints = 0;
  var allNum = "";
  for (i = 0;  i < checkStr.length;  i++)
  {
    ch = checkStr.charAt(i);
    for (j = 0;  j < checkOK.length;  j++)
      if (ch == checkOK.charAt(j))
        break;
    if (j == checkOK.length)
    {
      allValid = false;
      break;
    }
    if (ch == ".")
    {
      allNum += ".";
      decPoints++;
    }
    else if (ch != ",")
      allNum += ch;
  }
  if (!allValid)
  {
    sysAlert("\u5728\u57df <"+fldName+"> \u4e2d\uff0c\u53ea\u80fd\u8f93\u5165\u6570\u503c\u578b\u5b57\u7b26");
    fld.focus();
    return (false);
  }

  if (decPoints > 1)
  {
    sysAlert("\u8bf7\u5728\u57df <"+fldName+"> \u4e2d\u8f93\u5165\u4e00\u4e2a\u6709\u6548\u6570\u5b57");
    fld.focus();
    return (false);
  }
 return (true);
 }
 
 //\u6c6a\u9633 \u9a8c\u8bc1\u8f93\u5165\u662f\u5426\u4e3a\u7a7a
 function checkEmpty(fld,fldName){
	if(fld.value==""){
	sysAlert("\u8f93\u5165\u57df <"+fldName+"> \u4e0d\u80fd\u4e3a\u7a7a");
	fld.focus();
	return false;
	}else{
	return true;
	}
 }
//\u5224\u65ad\u662f\u5426\u4e3a\u7a7a 
 function checkEmpty1(fld,fldName){
	if(fld.value==""){
	return false;
	}else{
	return true;
	}
 } 
/*
\u9648\u8def\u51dd:
checkDate\uff1a\u65f6\u95f4\u8f93\u5165\u57df\u7684\u5408\u6cd5\u6027\u6821\u9a8c

fld:\u65f6\u95f4\u8f93\u5165\u57df\u7684\u5bf9\u8c61,
fldName:\u65f6\u95f4\u8f93\u5165\u57df\u7684\u542b\u4e49
*/
function checkDate(fld,fldName)
{

	var DateStr;
	DateStr=JSCDate(fld.value);
	if(DateStr=="false"|| DateStr=="NaN")
	{
	alert("\u8bf7\u5728\u57df<"+fldName+">\u4e2d\u8f93\u5165\u65e5\u671f\uff0c\u5982:2000-01-01");
	fld.focus();
	return false;
	}else{
	return true;
	}

}
 
 //\u6c6a\u9633 \u9a8c\u8bc1\u6570\u5b57\u8f93\u5165\uff0c\u4f46\u4e0d\u68c0\u9a8c\u8f93\u5165\u57df\u7a7a

 function checkNum1(fld,fldName){
  if(fld.value==""){
    return (false);
  }
  var checkOK = "0123456789-.,";
  var checkStr = fld.value;
  var allValid = true;
  var decPoints = 0;
  var allNum = "";
  for (i = 0;  i < checkStr.length;  i++)
  {
    ch = checkStr.charAt(i);
    for (j = 0;  j < checkOK.length;  j++)
      if (ch == checkOK.charAt(j))
        break;
    if (j == checkOK.length)
    {
      allValid = false;
      break;
    }
    if (ch == ".")
    {
      allNum += ".";
      decPoints++;
    }
    else if (ch != ",")
      allNum += ch;
  }
  if (!allValid)
  {
    sysAlert("\u5728\u57df <"+fldName+"> \u4e2d\uff0c\u53ea\u80fd\u8f93\u5165\u6570\u503c\u578b\u5b57\u7b26");
    fld.focus();
    return (false);
  }

  if (decPoints > 1)
  {
    sysAlert("\u8bf7\u5728\u57df <"+fldName+"> \u4e2d\u8f93\u5165\u4e00\u4e2a\u6709\u6548\u6570\u5b57");
    fld.focus();
    return (false);
  }
  return true;
 }
 
 
function replace_illegal(s1){
      var r;
      var reg= /[^0123456789.-]/g ;
      if(s1==null)s1="";
      r=s1.replace(reg, "");
      return r;
}
 
 // mirage \u66ff\u6362\u975e\u6cd5\u5b57\u7b26\u5e76\u9a8c\u8bc1\u6570\u5b57\u683c\u5f0f\uff0c\u4fdd\u8bc1\u6b63\u786e\u6027

function replaceCheckNum(obj,objName)
{
	if(obj==null)return false;
	var value = obj.value;
	obj.value = replace_illegal(value);
	if (obj.value=="") obj.value=0.0;

	if(checkNum(obj,objName))
	{
		
	//	obj.focus();
		return false;
	}
			    
	obj.value = vbFormatNumber(obj.value);
	return true;
}
 
 //\u6c6a\u9633 \u5224\u65ad\u8f93\u5165\u57df\u662f\u5426\u662f\u6570\u5b57\uff0c\u4e0d\u683c\u5f0f\u5316

 function exitNumber(fld){
	if(checkNum(fld,"\u5f53\u524d\u8f93\u5165\u6846")==false){
		fld.value="0";
		return;
	}
 }
 
 //\u6c6a\u9633 \u5224\u65ad\u6570\u5b57\u548c\u8f93\u5165\u57df\u7684\u957f\u5ea6

 function checknumandlen(fld,fldName,min,max){
	if(checkNum1(fld,fldName)!=false){
		checkChar(fld,fldName,min,max);
	}
	fld.blur;
 }
 
 //\u6c6a\u9633 \u5224\u65ad\u4e24\u4e2a\u8f93\u5165\u57df\u6570\u503c\u5927\u5c0f

 function checklarge(fld1,fld2){
    while(fld1.value.search(",")!=-1){
		fld1.value=fld1.value.replace(",","");
	}
	while(fld2.value.search(",")!=-1){
		fld2.value=fld2.value.replace(",","");
	}
	if(parseFloat(fld1.value)<parseFloat(fld2.value)){
		sysAlert("\u8f93\u5165\u7684\u5ba2\u6237\u6388\u4fe1\u989d\u5ea6\u5927\u4e8e\u603b\u989d");
		fld2.value = "0.00";
		fld2.focus();
		return;
	}
 }
 
 //\u8ba1\u7b97\u4e24\u4e2a\u8f93\u5165\u57df\u7684\u548c

 function addValue(val1,val2){
	//val1+val2
	while(val1.search(",")!=-1){
		val1=val1.replace(",","");
	}
	while(val2.search(",")!=-1){
		val2=val2.replace(",","");
	}
	return vbFormatNumber(parseFloat(val1)+parseFloat(val2));
}
 
 //\u6c6a\u9633 \u68c0\u67e5\u6570\u5b57\u8f93\u5165\u57df\u5e76\u8ba1\u7b97\u6388\u4fe1\u989d\u5ea6\u603b\u989d
 function checkNumandSum(fld){
	if(checkNum(fld,"\u5f53\u524d\u8f93\u5165\u6846")==false){
		fld.value="0.00";
		return;
	}
	fld.value=vbFormatNumber(fld.value);
	
	document.khsxed.zezg.value = "0.00";
	document.khsxed.zekh.value = "0.00";
	
	document.khsxed.zezg.value = addValue(document.khsxed.zezg.value,document.khsxed.lddqzg.value);
	document.khsxed.zekh.value = addValue(document.khsxed.zekh.value,document.khsxed.lddqkh.value);
	
	document.khsxed.zezg.value = addValue(document.khsxed.zezg.value,document.khsxed.ldzqzg.value);
	document.khsxed.zekh.value = addValue(document.khsxed.zekh.value,document.khsxed.ldzqkh.value);
	
	document.khsxed.zezg.value = addValue(document.khsxed.zezg.value,document.khsxed.xmdqzg.value);
	document.khsxed.zekh.value = addValue(document.khsxed.zekh.value,document.khsxed.xmdqkh.value);
	
	document.khsxed.zezg.value = addValue(document.khsxed.zezg.value,document.khsxed.xmzcqzg.value);
	document.khsxed.zekh.value = addValue(document.khsxed.zekh.value,document.khsxed.xmzcqkh.value);
	
	document.khsxed.zezg.value = addValue(document.khsxed.zezg.value,document.khsxed.rzzg.value);
	document.khsxed.zekh.value = addValue(document.khsxed.zekh.value,document.khsxed.rzkh.value);
	
	document.khsxed.zezg.value = addValue(document.khsxed.zezg.value,document.khsxed.frzzg.value);
	document.khsxed.zekh.value = addValue(document.khsxed.zekh.value,document.khsxed.frzkh.value);
	
	document.khsxed.zezg.value = addValue(document.khsxed.zezg.value,document.khsxed.yhzg.value);
	document.khsxed.zekh.value = addValue(document.khsxed.zekh.value,document.khsxed.yhkh.value);
	
	document.khsxed.zezg.value = addValue(document.khsxed.zezg.value,document.khsxed.syzg.value);
	document.khsxed.zekh.value = addValue(document.khsxed.zekh.value,document.khsxed.sykh.value);
	
	document.khsxed.zezg.value = addValue(document.khsxed.zezg.value,document.khsxed.qtzg.value);
	document.khsxed.zekh.value = addValue(document.khsxed.zekh.value,document.khsxed.qtkh.value);	
 }
 
 //\u68c0\u67e5\u8f93\u5165\u9884\u662f\u5426\u662f\u6570\u5b57\u5e76\u68c0\u67e5\u957f\u5ea6

function exitNumberTextL(thisText,fldName,max){
	if(checkNum(thisText,"\u5f53\u524d\u8f93\u5165\u6846")==false){
		thisText.value="0.00";
		return;
	}
	if(thisText.value.length > max){
		sysAlert("\u57df <"+fldName+"> \u4e2d\u6b62\u591a\u53ea\u80fd\u8f93\u5165"+max+"\u4f4d\u6570\u5b57");
		thisText.value = "0.00";
	}else{
		thisText.value=vbFormatNumber(thisText.value);
	}
} 

//\u6c6a\u9633
function checkHTBH(thisText,jgbm){
	if(thisText.value.length<7){
		sysAlert("\u5408\u540c\u53f7\u4f4d\u6570\u4e0d\u591f");
	}else {
		var jgbm1 = thisText.value;
		if(jgbm1.substring(4,7)!=jgbm){
			sysAlert("\u5408\u540c\u53f7\u4e2d\u673a\u6784\u7f16\u7801\u4e0d\u5bf9");
		}
	}
}
  /*********************************************************************
\u79e6\u9e4f\u2014\u2014\u6587\u672c\u6846\u5931\u53bb\u7126\u70b9\u65f6\u68c0\u67e5\u5176\u662f\u5426\u4e3a\u6709\u6548\u6570\u5b57\uff0c\u662f\u5219\u5c06\u5176\u683c\u5f0f\u5316\uff0c\u5426\u5219\u7f6e\u4e3a"0.00
 *********************************************************************/
function exitNumberText(thisText){
	if(checkNum(thisText,"\u5f53\u524d\u8f93\u5165\u6846")==false){
		thisText.value="0.00";
		return;
	}
	thisText.value=vbFormatNumber(thisText.value);
 }
 function exitINumberText(thisText){
	if(checkNum(thisText,"\u5f53\u524d\u8f93\u5165\u6846")==false){
		thisText.value="0";
		return;
	}
	thisText.value=vbFormatINumber(thisText.value);
 }
function exitDNumberText(thisText){
	if(checkNum(thisText,"\u5f53\u524d\u8f93\u5165\u6846")==false){
		thisText.value="0.000";
		return;
	}
	thisText.value=vbFormatDNumber(thisText.value);
 }
 
 /////////////////////////////////////////////////////////////////////
 ///\u79e6\u9e4f\u2014\u2014\u65f6\u95f4\u63a7\u4ef6

 /*
 function PopupPicker(ctl,w,h,strApplicationPath)
    {
        var PopupWindow=null;
        var strUrl;
        strUrl = strApplicationPath + "/Page/ht/Modules/DatePicker.aspx?Ctl=" + ctl;
        settings="width="+w+",height="+h+",location=no,directories=no,menubar=no,toolbar=no,status=no,scrollbars=no,resizable=no,dependent=no";
        PopupWindow=window.open(strUrl,"DatePicker",settings);
        PopupWindow.focus();
    }
*/
/*
checkDateBox\uff1a\u65f6\u95f4\u8f93\u5165\u57df\u7684\u5408\u6cd5\u6027\u6821\u9a8c

ctrlBox:\u65f6\u95f4\u8f93\u5165\u57df\u7684\u5bf9\u8c61,
fldName:\u65f6\u95f4\u8f93\u5165\u57df\u7684\u542b\u4e49
*/
function checkDateText(ctrlBox)
{

	var DateStr;
	DateStr=JSCDate(document.forms(0).item(ctrlBox).value);
	if(DateStr=="false"|| DateStr=="NaN")
	{
	alert("\u8bf7\u5728\u57df\u4e2d\u8f93\u5165\u65e5\u671f\uff0c\u5982:2003-1-1");
	document.forms(0).item(ctrlBox).focus();
	return false;
	}
	else
	{
	document.forms(0).item(ctrlBox).value=DateStr;
	return true;
	}
}
/*
	\u68c0\u9a8c\u65f6\u95f4\u7684\u5408\u6cd5\u6027\uff0c\u8fd4\u56de\u5408\u6cd5\u7684\u65f6\u95f4

*/
function JSCDate(str)
{
	var sDate,sDate2="";
	var oDate;
	var ArrCompart="\u5e74\u6708\u65e5/.-";
	var ArrNum="0123456789";
	var ArrMonthDays = new Array(0,31,28,31,30,31,30,31,31,30,31,30,31);
	var ArrDate = new Array();
	var int1,int2,int3;
	var char1,char2;
	var flag1,flag2,IsRN;
	var e="\u8f93\u5165\u65f6\u95f4\u9519\u8bef";
	if(str.length==0)
	{
		return "false";
	}
	for(int1=0;int1<str.length;int1++)
	{
		char1=str.charAt(int1);
		flag1=false;
		for(int2=0;int2<ArrNum.length;int2++)
		{
			char2=ArrNum.charAt(int2);
			if(char1==char2)
			{
				sDate2=sDate2+char1;
				flag1=true;
				break;
			}
		}
		if(!flag1)
		{
				for(int3=0;int3<ArrCompart.length;int3++)
				{
					flag2=false;
					if(char1 == ArrCompart.charAt(int3))
					{
						sDate2=sDate2 + "-" ;
						flag2=true;
						break;
					}
				}
				if(!flag2)
				{
					return "false";
				}
		}
	}
	try
	{	
		ArrDate = sDate2.split("-");
		int1 = parseInt(ArrDate[0]);//\u5e74

		// err: parseInt("09") = 0
		if( ArrDate[1].charAt(0) == "0"){
			ArrDate[1] = ArrDate[1].charAt(1);
		} 
		//
		int2 = parseInt(ArrDate[1]);//\u6708

		int3 = parseInt(ArrDate[2]);//\u65e5

		
		
		if((int1>9999) || (int2>12) || (int1<1900) || (int2<0) || (int3<0))
		{
			return "false";
		}
		if(isNaN(int1) || isNaN(int2) || isNaN(int3))
		{
			return "false";
		}
		if(((int1 % 4)==0) && ((int1 % 100 )!=0) || (int1 %400 )==0)
		{
			//alert("\u6539\u5e74\u65f6\u95f0\u5e74");
			IsRN=true;
		}
		if((IsRN==true) && (int2==2))
		{
			if(int3>29)
			{
				return "false";
			}
		}
		else
		{
			if(int3>parseInt(ArrMonthDays[int2]))
			{
				return "false";
			}
		}
		//sDate=Date.parse(sDate2);
		//oDate = new Date(sDate);
		//sDate=oDate.getFullYear() + "/" +  (oDate.getMonth() + 1) + "/" + oDate.getDay() ;
		sDate= int1 + "-" + int2 + "-" + int3 ;
		return sDate;
	}
	catch(e)
	{
		return "false"
	}
}
/*
	\u9648\u8def\u51dd\uff1a\u6bd4\u8f83\u65f6\u95f4\u7684\u5927\u5c0f\uff0cif(oldDate<=curtDate) then true, else false
*/
function compareDate(oldDate,oldName,curtDate,curtName)
{
	var ArrDate = new Array();
	var sDate1,sDate2;
	var int1,int2,int3;
	var cint1,cint2,cint3;
	sDate1 = JSCDate(oldDate.value);
	sDate2 = JSCDate(curtDate.value);
	ArrDate = sDate1.split("-");
	int1 = parseInt(ArrDate[0]);//\u5e74

	int2 = parseInt(ArrDate[1]);//\u6708

	int3 = parseInt(ArrDate[2]);//\u65e5

	ArrDate = sDate2.split("-");
	cint1 = parseInt(ArrDate[0]);//\u5e74

	cint2 = parseInt(ArrDate[1]);//\u6708

	cint3 = parseInt(ArrDate[2]);//\u65e5

	if(cint1 > int1) {
		return true;
	 }
	 if(cint1 == int1 && cint2>int2 ){
	 	return true;
	 }
	 if(cint1 == int1 && cint2 == int2 && cint3 >= int3){
	 	return true;
	 }	
	 sysAlert("\u57df <"+oldName+"> \u5fc5\u987b\u5c0f\u4e8e\u57df <"+curtName+">");
	 return false; 	

}
/*
	\u9648\u8def\u51dd\uff1a\u6bd4\u8f83\u65f6\u95f4\u7684\u5927\u5c0f\uff0c\u4f46\u4e0d\u63a2\u51fa\u63d0\u793a\u7a97\u53e3\u3002if(oldDate<=curtDate) then true, else false
*/
function compareDate2(oldDate,oldName,curtDate,curtName)
{
	var ArrDate = new Array();
	var sDate1,sDate2;
	var int1,int2,int3;
	var cint1,cint2,cint3;
	sDate1 = JSCDate(oldDate.value);
	sDate2 = JSCDate(curtDate.value);
	ArrDate = sDate1.split("-");
	int1 = parseInt(ArrDate[0]);//\u5e74

	int2 = parseInt(ArrDate[1]);//\u6708

	int3 = parseInt(ArrDate[2]);//\u65e5

	ArrDate = sDate2.split("-");
	cint1 = parseInt(ArrDate[0]);//\u5e74

	cint2 = parseInt(ArrDate[1]);//\u6708

	cint3 = parseInt(ArrDate[2]);//\u65e5

	if(cint1 > int1) {
		return true;
	 }
	 if(cint1 == int1 && cint2>int2 ){
	 	return true;
	 }
	 if(cint1 == int1 && cint2 == int2 && cint3 >= int3){
	 	return true;
	 }	
	 return false; 	

}

//-->
