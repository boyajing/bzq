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

    var childActive = null;
    var menuActive = null;
    var lastHighlight = null;
    var active = false;
   
    function getReal(el) 
	{
      	temp = el;
		while ((temp!=null) && (temp.tagName!="TABLE") && (temp.className!="root") && (temp.className!="rootraised") && (temp.className!="rootsunk") && (temp.id!="menuBar")) 
		{
        	if (temp.tagName=="TD") el = temp;

	        temp = temp.parentElement;
      	}
      	return el
    }

    function raiseMenu(el) { el.className="rootraised"; }

    function clearHighlight(el) 
	{
        if (el==null) return;
	  	el.className="root";
    }

    function sinkMenu(el) 
	{
		el.className="rootsunk"; 
	}  

    function getOffsetPos(which,el,tagName) 
	{
    	var pos = 0;
      	while (el.tagName!=tagName) 
		{
        	pos+=el["offset" + which];
        	el = el.offsetParent;
      	}
      	return pos;
    }

    function getRootTable(el) 
	{
    	el = el.offsetParent;
		if (el.tagName=="TR") el = el.offsetParent;
      	return el;
    }

    function getElement(el,tagName) 
	{
    	while ((el!=null) && (el.tagName!=tagName)) el = el.parentElement;
	    return el;
    }

	function processClick() 
	{		
    	var el = getReal(event.srcElement);
      	if ((getRootTable(el).id=="menuBar") && (active)) 
		{        
        	cleanupMenu(menuActive);
	        clearHighlight(menuActive);
			active=false;
			doHighlight(el);
	    }
      	else 
		{
        	if ( el.className=="root" || el.className=="rootraised" || el.className=="rootsunk" )
			{
          		doMenuDown(el);				
			}
        	else 
			{
          		if (el._childItem==null) el._childItem = getChildren(el)
          		if (el._childItem!=null)  return;
          		if ((el.id!="break") && (el.className!="disabled") && (el.className!="clear"))  
				{            		
					cleanupMenu(menuActive);
					clearHighlight(menuActive);
					active=false;
					menuHandler(el);
          		}
        	}
      	}		
    }

    function getChildren(el) 
	{
    	var tList = el.children.tags("TABLE");
		var i = 0;
      	while ((i<tList.length) && (tList[i].tagName!="TABLE")) i++;
      	if (i==tList.length)
		{
        	return null;
		}
      	else return tList[i];
    }

    function doMenuDown(el) 
	{
    	if (el._childItem==null) el._childItem = getChildren(el);
      	if ((el._childItem!=null) && (el.className!="disabled")) 
		{
        	ch = el._childItem;
        	if (ch.style.display=="block") 
			{
          		removeHighlight(ch.active);
          		return;
        	}
			ch.style.display = "block";

	        if (el.className=="root" || el.className=="rootraised" || el.className=="rootsunk") 
			{
          		ch.style.pixelTop = el.offsetHeight + el.offsetTop; 
		        ch.style.pixelLeft = el.offsetLeft;
	  			if (ch.style.pixelWidth==0) 
				{	
					ch.style.pixelWidth = ch.rows[0].offsetWidth+70; 
				}					
				sinkMenu(el);
          		active = true;
          		menuActive = el;
        	} 
			else 
			{
          		childActive = el;
          		ch.style.pixelTop = getOffsetPos("Top",el,"TABLE") -2;
          		ch.style.pixelLeft = el.offsetLeft + el.offsetWidth - 4;
	  			if (ch.style.pixelWidth==0) ch.style.pixelWidth = ch.offsetWidth+42; 
				ch.style.zIndex=1;
			}     
      	}
    }
 
   	function doHighlight(el) 
   	{
		el = getReal(el);
      	if ( el.className=="root" || el.className=="rootraised" || el.className=="rootsunk" ) 
		{
        	if ((menuActive!=null) && (menuActive!=el)) 
			{
          		clearHighlight(menuActive);
        	}
        	if (!active) 
			{
          		raiseMenu(el);
        	}          
        	else sinkMenu(el);

        	if ((active) && (menuActive!=el)) 
			{
          		cleanupMenu(menuActive);
          		doMenuDown(el);
        	}
        	menuActive = el;
		}
      	else 
	  	{
			if(menuActive)		
			{
				if (childActive!=null) if (!childActive.contains(el)) closeMenu(childActive, el);
		
				if (("TD"==el.tagName) && ("clear"!=el.className)) 
				{
					var ch = getRootTable(el);
					if (ch.active!=null) 
					{
						if (ch.active!=el) 
						{
							if (ch.active.className=="disabled")
							{  
								ch.active.className="disabled";
							}
							else ch.active.className="";
						}
					}
					ch.active = el;				
					if ((el.className=="disabled") || (el.id=="break"))
					{ 
						el.className = "disabled";
					}
					else 
					{
						if (el.id!="break") 
						{
							el.className = "highlight";
							lastHighlight = el;
							if (el._childItem==null) el._childItem = getChildren(el);
							if (el._childItem!=null) doMenuDown(el);				  
						}  
					}
				}
			}
		}
    }

    function removeHighlight(el) 
	{
    	if (el!=null)
		{
        	if (el.className=="disabled")
			{
          		el.className="disabled";
			}
        	else  el.className="";
		}
    }

    function cleanupMenu(el) 
	{
    	if (el==null) return;
      	for (var i = 0; i < el.all.length; i++) 
		{
        	var item = el.all[i];
        	if (item.tagName=="TABLE") item.style.display = "";
        	removeHighlight(item.active);
        	item.active=null;
      	}
    }

    function closeMenu(ch, el) 
	{
    	var start = ch;
      	while (ch.className!="root" && ch.className!="rootraised" && ch.className!="rootsunk") 
		{
        	ch = ch.parentElement;
          	if (((!ch.contains(el)) && (ch.className!="root" && ch.className!="rootraised" && ch.className!="rootsunk"))) 
			{
            	start=ch;
          	}
      	}
      	cleanupMenu(start);
    }
 
    function checkMenu() 
	{   
      	if (document.all.menuBar==null) return;
      	if ((!document.all.menuBar.contains(event.srcElement)) && (menuActive!=null)) 
		{
			clearHighlight(menuActive);
			closeMenu(menuActive);
			active = false;
			menuActive=null;			
      	}
    }
	
    function doCheckOut() 
	{
    	var el = event.toElement;
      	if ((!active) && (menuActive!=null) && (!menuActive.contains(el))) 
		{
        	clearHighlight(menuActive);
        	menuActive=null;
      	}
    }
	
	function processKey()	{	}

	document.onclick = checkMenu;
