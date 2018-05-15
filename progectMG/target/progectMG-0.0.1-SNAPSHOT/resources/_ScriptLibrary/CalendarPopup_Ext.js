if (!document.getElementById("calendarDiv"))
{
	document.write(getCalendarStyles());
	document.write("<DIV ID=\"calendarDiv\" STYLE=\"position:absolute;visibility:hidden;background-color:white;layer-background-color:white;\"></DIV>");
	var datePattern = 'yyyy/MM/dd';
	var calendar = new CalendarPopup("calendarDiv"); 
	calendar.showNavigationDropdowns();
    var tempReturnEventName;
    var tempReturnEventObject;
}

function showCalendar(formName, fieldName, anchorId){
	calendar.select(document[formName][fieldName], anchorId, datePattern);
}
function showCalendarByTagId(formTagId, fieldTagId, anchorId){
	showCalendar(getNetuiTagName(formTagId), getNetuiTagName(fieldTagId), anchorId);
}
function showCalendarByTagId(formTagId, fieldTagId, anchorId, returnEventName){
    calendar.setReturnFunction('customOnDateReturn');
    tempReturnEventName = returnEventName
    tempReturnEventObject = document[getNetuiTagName(formTagId)][getNetuiTagName(fieldTagId)]
	showCalendar(getNetuiTagName(formTagId), getNetuiTagName(fieldTagId), anchorId);
}
function customOnDateReturn(y,m,d){
    CP_tmpReturnFunction(y,m,d);
    eval(tempReturnEventName+"()");
}
