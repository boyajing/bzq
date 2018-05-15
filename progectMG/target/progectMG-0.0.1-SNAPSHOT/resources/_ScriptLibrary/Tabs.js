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

	function TabChange(tabID, wantcookie, numtabs, newwinheight) 
	{	
  		var tab0=null; var tab1=null; var tab2=null; var tab3=null;	var tab4=null;
				
		if(newwinheight)
		{
			frameWidth = document.body.clientWidth+6;
			frameHeight = document.body.clientHeight;		
			window.resizeTo(frameWidth, newwinheight);
		}
	
	    tab0 = document.getElementById('TabA').style;
	    tab1 = document.getElementById('TabB').style;
	    if(numtabs>2)tab2 = document.getElementById('TabC').style;
	    if(numtabs>3)tab3 = document.getElementById('TabD').style;
		if(numtabs>4)tab4 = document.getElementById('TabE').style;
	
	  	if (tabID == 1) 
		{
			if(wantcookie) SetCookie ("ViewTab", "A");
			tab0.visibility = "visible"; 
			tab1.visibility = "hidden"; 
			if(tab2){tab2.visibility = "hidden"; }
			if(tab3){tab3.visibility = "hidden"; }
			if(tab4){tab4.visibility = "hidden"; }
		}

		if (tabID == 2) 
		{
			if(wantcookie) SetCookie ("ViewTab", "B");
			tab0.visibility = "hidden";	
			tab1.visibility = "visible"; 
			if(tab2){tab2.visibility = "hidden"; }
			if(tab3){tab3.visibility = "hidden"; }
			if(tab4){tab4.visibility = "hidden"; }
		}

		if (tabID == 3) 
		{
			if(wantcookie) SetCookie ("ViewTab", "C");
			tab0.visibility = "hidden";
			tab1.visibility = "hidden";	
			if(tab2){tab2.visibility = "visible"; }
			if(tab3){tab3.visibility = "hidden"; }
			if(tab4){tab4.visibility = "hidden"; }
		}

		if (tabID == 4) 
		{
			if(wantcookie) SetCookie ("ViewTab", "D");
			tab0.visibility = "hidden";	
			tab1.visibility = "hidden";
			if(tab2){tab2.visibility = "hidden"; }
			if(tab3){tab3.visibility = "visible"; }
			if(tab4){tab4.visibility = "hidden"; }
		}
		
		if (tabID == 5) 
		{
			if(wantcookie) SetCookie ("ViewTab", "E");
			tab0.visibility = "hidden";
			tab1.visibility = "hidden";
			if(tab2){tab2.visibility = "hidden"; }
			if(tab3){tab3.visibility = "hidden"; }
			if(tab4){tab4.visibility = "visible"; }
		}
						
		return;
	}