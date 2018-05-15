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

	function startwin(url, width, height)
	{
		LeftPosition = (screen.width) ? (screen.width-width)/2 : 0;
		TopPosition = (screen.height) ? (screen.height-height)/2 : 0;
		win = window.open(url, "", "height="+height+", width="+width+", left="+LeftPosition+", top="+TopPosition+", toolbar=0, location=0,directories=0,status=0,menuBar=0,scrollBars=0,resizable=0");
		win.focus();
	}
	
	function AFL_GetSide(parframe)
	{
		// this is now recursive rather than the old nested approach - al	
		var frame; var out;		
		if(parframe) frame=parframe.parent; else frame=frames.parent;		
		if(frame.name=="convea_leftframe" || frame.name=="convea_rightframe") return(frame); else out=AFL_GetSide(frame);
		return out;
	}

	function loaddoc(loc, nested)
	{
		if (top.opener) return;
	
		var currentFrame=AFL_GetSide(); //nest.name.toLowerCase();
	
		if (currentFrame.name=="convea_leftframe")
		{	
			if (currentFrame.parent.frames["convea_rightframe"])
			{
				top.frames["ConveaFrame"].frames["topFrame"].launchApp(1, loc, 0, 0, 0, 0, 0);
			}
			else 
			{
				top.frames["ConveaFrame"].frames["topFrame"].launchApp(0, loc, 0, 0, 0, 0, 0);	
			}
		}
		else if (currentFrame.name=="convea_rightframe")
		{
			top.frames["ConveaFrame"].frames["topFrame"].launchApp(0, loc, 0, 0, 0, 0, 0);
		}
		else frames.parent.document.location.replace(loc);
	}