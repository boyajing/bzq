function checkDateWithReturn(obj, name, returnFunction){
	if (checkdate(obj, name)){
        if(returnFunction){
            eval(returnFunction+"()");
        }
	}
}
function checkDate(obj, name){
	checkdate(obj, name);
}
