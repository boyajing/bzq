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

	function erase(pos) { if (pos>=0) this.splice(pos,1); else this.splice(0,this.length); return this; }
	Array.prototype.erase = erase;

	function urlIncQuerystring(str)
	{
		tmp=str.toString(); tmp=tmp.toLowerCase();
		ePos=str.indexOf(".asp"); sPos=str.lastIndexOf("/",ePos);
		if (ePos && sPos) str=tmp.substring(sPos+1, tmp.length);
		return unescape(str);
	}

	function getTFarray()
	{
		if ((top.opener) && (top.opener.top.frames["ConveaFrame"])) tFrame=top.opener.top.frames["ConveaFrame"].frames["topFrame"];
		if ((top.frames) && (top.frames["ConveaFrame"])) tFrame=top.frames["ConveaFrame"].frames["topFrame"];
		if ((window.dialogArguments) && (window.dialogArguments.top.frames["ConveaFrame"])) tFrame=window.dialogArguments.top.frames["ConveaFrame"].frames["topFrame"];		
		if (window.tFrame) return tFrame.popupArray;
		else return false;
	}

	var aWin=new Array();
	function launchWin(w,h,srcURL,sizable, obj, scrollbar)
	{
		aTPWin=getTFarray();

		for (i=0;i<aWin.length;i++)
		{
			if ((aWin[i]) && (!aWin[i].closed))
			{
				winURL=urlIncQuerystring(aWin[i].document.URL);
				newURL=urlIncQuerystring(srcURL);

				if (winURL.indexOf(newURL)>-1) { aWin[i].focus(); return false; }
			}
		}

		if (aTPWin!=null)
		{
			for (i=0;i<aTPWin.length;i++)
			{
				if ((aTPWin[i]) && (!aTPWin[i].closed))
				{
					winURL=urlIncQuerystring(aTPWin[i].document.URL);
					newURL=urlIncQuerystring(srcURL);
	
					if (winURL.indexOf(newURL)>-1) { aTPWin[i].focus(); return false; }
				}
			}
		}	

		if (scrollbar=="") scrollbar=0;
		aPos=aWin.length;
		lPos = (screen.width-w)/2 ;
		tPos = (screen.height-h)/2;
		aWin[aPos] = window.open(srcURL, "", "height="+h+", width="+w+", left="+lPos+", top="+tPos+", toolbar=0, location=0,directories=0,status=0,menuBar=0,scrollBars="+scrollbar+",resizable="+sizable);
		aWin[aPos].opener=obj;				
		aWin[aPos].focus();
		aWin[aPos].owner=document.location.href;
		processBarred(aPos);
		return aWin[aPos];
	}

	function processBarred(pos)
	{
		aTPWin=getTFarray();
		if ((aTPWin!=null) && (aTPWin.length>0))
		{
			for (b=0;b<aTPWin.length;b++) 
			{
				if ((aWin[pos]) && ((!aWin[pos].closed) && (aWin[pos].document.URL.toLowerCase().indexOf(aTPWin[b].toLowerCase())>-1)))
				{
					aTPWin[aTPWin.length]=aWin[pos]; aWin[pos]=null; break;
				}
			}
		}
	}

	function nullOpeners()
	{
		aTPWin=getTFarray();
		if (aTPWin!=null)
		{
			for (i=0;i<aTPWin.length;i++)
			{
				if ((aTPWin[i]) && (!aTPWin[i].closed))
				{
					if (aTPWin[i].owner==document.URL) { aTPWin[i].opener=null; aTPWin[i].owner=null; }
				}
			}
		}
	}

	function closeWins()
	{
		if (aWin.length>0)
		{
			nullOpeners();
			for (a=0;a<aWin.length;a++) 
			{ 
				if ((aWin[a]) && (!aWin[a].closed)) aWin[a].close();
			}
		}
	}