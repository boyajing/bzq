
function vbFormatDate(num)
	if len(num)=1 then
		vbFormatDate="0" & num
	else
		vbFormatDate=num
	end if
end function
function vbFormatNumber(num)
	vbFormatNumber=FormatNumber(num,2,-1,0,0)
end function
function vbFormatDNumber(num)
	vbFormatDNumber=FormatNumber(num,3,-1)
end function
function vbFormatINumber(num)
	vbFormatINumber=FormatNumber(num,0,-1)
end function
function vbFormatNumber(num,num1)
	vbFormatNumber=FormatNumber(num,4,-1,0,0)
end function