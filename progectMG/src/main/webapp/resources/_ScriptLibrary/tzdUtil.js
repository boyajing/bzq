/*****************************************************************
 * tzdUtil.js                                           
 * Create Date: 2008/08/13
 * Creator: mengzx
 * 
 ****************************************************************/
 
 
 //验证是否数字，格式化为#,##0.00的格式
function is_float(obj){
 var value = obj.value;
 value=NumberFormat.toNumber(value);
var pattern=/^-?([0-9]+|[0-9]+[.]{0,1}[0-9]+)$/;

 if(value !="" && !pattern.test(value)){
    alert("请输入数字！");
    obj.focus();
    obj.value="";
    event.returnValue=false;
 }
  NumberFormat.formatCurrency(obj);
}

 //验证是否数字，格式化为#,##0.000000的格式
 function is_HL(obj){
 var value = obj.value;
 value=NumberFormat.toNumber(value);
 var pattern=/^-?([0-9]+|[0-9]+[.]{0,1}[0-9]+)$/;

 if(value !="" && !pattern.test(value)){
    alert("请输入数字！");
    obj.focus();
    obj.value="";
    event.returnValue=false;
 }
  NumberFormat.formatHL(obj);
 }

 //验证是否整数
function is_int(obj){

 var value = obj.value;
 value=NumberFormat.toNumber(value);
var pattern=/^[0-9]+$/;
 if(value !="" && !pattern.test(value)){
    alert("请输入整数！");
    obj.focus();
    obj.value="0";
    event.returnValue=false;
 }
}

//将字符串转为float
function parseStringToFloat(str){
   if(str=="")
    return parseFloat("0");
   else
    return parseFloat(NumberFormat.toNumber(str)); 
}


/**************************利用js模拟HashMap*****************************/
//模拟HashMap
function JMap(){
  this.keyArr = new Array();
  this.valueArr = new Array();
  this.length = 0;
}

JMap.prototype.put = function (key, value)
{
     key = key.toString().replace(/^\s+|\s+$/g,"");
	 var len = this.containsKey(key);
	 if(len==-1){
	 this.keyArr[this.length] = key;
	 this.valueArr[this.length] = value;
     this.length ++;
	 }else{
	   this.valueArr[len] = value;
	 }
}

JMap.prototype.containsKey = function (key){
     
	var len = -1;
    for(var i=0;i<this.length;i++){
	  
	  if(key==this.keyArr[i]){
		len = i;
		break;
	  }
	}
	
	return len;
}

JMap.prototype.get = function (key)
{
    var the_value = "";
    for(var i=0;i<this.length;i++){

	  if(key==this.keyArr[i]){
		the_value = this.valueArr[i];
		break;
	  }
   }
   return the_value;
}

/*****************************结束**************************************/


/**以下函数为7010\7015\7011\7012\7013所用*
*/
//回调函数，根据dwr调用返回的结果设置新的汇率
function setNewHl(data){

  var bzmMap = new JMap();
  for(var bzm in data){
     var hl = data[bzm];
     bzmMap.put(bzm.toString(),hl.toString());
   }
   
    var bzms = document.getElementsByName("bzm");
    var hls = document.getElementsByName("hl");
     if(bzms){
         for(var i=0;i<bzms.length;i++){
             if(bzms[i].value!="1"){
              var the_hl = bzmMap.get(bzms[i].value);
               if(the_hl!=""){
                hls[i].value=the_hl;
                }
           }
         }  
    }

}





/***/
