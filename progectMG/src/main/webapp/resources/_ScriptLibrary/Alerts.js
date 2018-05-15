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

	function eventHandle()
	{
		var eObj=window.event.srcElement;
		if (eObj.id==objName)
		{
			window.event.returnValue=false;
			if (waitingWin!=null) waitingWin.close();
			eObj.detachEvent(dEventName, eventHandle)
		}
	}

	var sDialogReturn
	function ConveaAlert(apipath, icon, msg, w, h, btns, snd, rtn)
	{
		var docTitle=escape(document.title); sDialogReturn="";

		if (w==0) w=380; if (h==0) h=200; width=w; height=h;

		lPos = (screen.width)/2 -(width/2);
		tPos = (screen.height)/2 -(height/2);

		var AlertModel=window.showModalDialog(apipath+"?icon="+icon+"&Msg="+msg+"&btns="+btns+"&snd="+snd+"&appTitle="+docTitle,window,"dialogLeft:"+lPos+";dialogTop:"+tPos+"; dialogHeight: "+height +"px; dialogWidth: "+width+"px; edge: Raised; center: Yes; help: no; resizable: no; status: no;");

		if (!AlertModel) { if (sDialogReturn && rtn) return sDialogReturn; }
	}

	var waitingWin;	
	var objName;
	var dEventName;

	function waitingDlg(apipath, dlgType, msg, w, h, sCloseEvent, objID)
	{
		var docTitle=document.title;
		objName=objID; dEventName=sCloseEvent;
		obj=document.getElementById(objID);
		if (obj!=null) obj.attachEvent(sCloseEvent, eventHandle);
		if (w==0) w=380; if (h==0) h=200; width=w; height=h;

		lPos = (screen.width)/2 -(width/2);
		tPos = (screen.height)/2 -(height/2);

		return waitingWin=window.showModelessDialog(apipath+"?Msg="+msg+"&appTitle="+docTitle+"&dlgType="+dlgType,window,"dialogLeft:"+lPos+";dialogTop:"+tPos+"; dialogHeight: "+height +"px; dialogWidth: "+width+"px; edge: Raised; center: Yes; help: no; resizable: no; status: no;");
	}

	function closeWait()
	{
		if (waitingWin!=null) waitingWin.close();
	}