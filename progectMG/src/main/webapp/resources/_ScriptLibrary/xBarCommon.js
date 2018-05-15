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

	function objDown(el, id) {}

	function objUp(el, id) {objOver(el, id);}

	function objOver(el, id) {}

	function objOut(el, id) {}

	function fixsize() 
	{
		var parentframe=parent.document.getElementById("outlookframe");
		newheight=Math.max(parentframe.style.pixelHeight,96);
		heightdiff=OB_Height-newheight;
		step=0-heightdiff;
		OB_Height=newheight;
		visibleAreaHeight=OB_Height-2*0-(j-1)*OB_ButtonHeight;
			
		for(var i=0;i<=items;i++)
		{
			if(document.getElementById("OB_Button"+i)!=null)
			{
				if(document.all["OB_Button"+i].position=="DOWN")
				{
					document.all["OB_Button"+i].style.pixelTop+=step;
					document.all["OB_Folder"+i].style.pixelTop+=step;
				}
				filter = /rect\((\d*)px (\d*)px (\d*)px (\d*)px\)/;			
			}
		}
		folder=currentFolder;
		
		var clipString=document.all["OB_Folder"+folder].style.clip;
		var clip=clipString.match(filter);
		if(clip)
		{
			if(newheight<=96) ClipFolder(folder,parseInt(Math.max(clip[1],2)),visibleAreaWidth,(parseInt(visibleAreaHeight)+step),0);
			else ClipFolder(folder,parseInt(clip[1]),visibleAreaWidth,(parseInt(clip[3])+step),0);
			
			clipString=document.all["OB_Folder"+folder].style.clip;
			clip=clipString.match(filter);
			if(clip)
			{
				clipHeight = clip[3];
					
				for (i = 1; i < j; i++) 
				{
					aMenu = eval("aMenu" + i);
					fixScroll(aMenu);
				}
			}
		}
	}
	
	function FolderClicked(folder)
	{
		if(sliding) return;
		if(folder==currentFolder) return;
		sliding=true;		
		slideCount=visibleAreaHeight;
		slideStep=1;
		countStep=0;
		SlideFolders(folder,document.all["OB_Button"+folder].position=="DOWN");
	}
	
	function SlideFolders(folder,down)
	{
		var step;	
		if(down)
		{
			slideCount-=Math.floor(slideStep);
			if(slideCount<0) slideStep+=slideCount;
			step=Math.floor(slideStep);
			for(var i=2;i<=folder;i++)
				if(document.all["OB_Button"+i].position=="DOWN")
				{
					document.all["OB_Button"+i].style.pixelTop-=step;
					document.all["OB_Folder"+i].style.pixelTop-=step;
				}				
	
				filter = /rect\((\d*)px (\d*)px (\d*)px (\d*)px\)/;
	
			var clipString=document.all["OB_Folder"+folder].style.clip;
			var clip=clipString.match(filter);
			ClipFolder(folder,parseInt(clip[1]),visibleAreaWidth,(parseInt(clip[3])+step),0);
	
			var clipString=document.all["OB_Folder"+currentFolder].style.clip;
			var clip=clipString.match(filter);
			ClipFolder(currentFolder,parseInt(clip[1]),visibleAreaWidth,(parseInt(clip[3])-step),0);
	
			slideStep*=slideSpeed;
			if(slideCount>0) setTimeout("SlideFolders("+folder+",true)",20);
			else		
			{
				for(var k=2;k<=folder;k++)
					document.all["OB_Button"+k].position="UP";
				currentFolder=folder;		
				sliding=false;		
			}
		}
		else		
		{
			slideCount-=Math.floor(slideStep);
			if(slideCount<0) slideStep+=slideCount;
			step=Math.floor(slideStep);
			for(var i=folder+1;i<=items;i++)
				if(document.all["OB_Button"+i].position=="UP")
				{
					document.all["OB_Button"+i].style.pixelTop+=step;
					document.all["OB_Folder"+i].style.pixelTop+=step;
				}
				filter = /rect\((\d*)px (\d*)px (\d*)px (\d*)px\)/;
	
			var clipString=document.all["OB_Folder"+folder].style.clip;
			var clip=clipString.match(filter);
			ClipFolder(folder,parseInt(clip[1]),visibleAreaWidth,(parseInt(clip[3])+step),0);
	
			var clipString=document.all["OB_Folder"+currentFolder].style.clip;
			var clip=clipString.match(filter);
			ClipFolder(currentFolder,parseInt(clip[1]),visibleAreaWidth,(parseInt(clip[3])-step),0);
	
			slideStep*=slideSpeed;
			if(slideCount>0) setTimeout("SlideFolders("+folder+",false)",20);
			else		
			{
				for(var k=folder+1;k<=items;k++) document.all["OB_Button"+k].position="DOWN";
				currentFolder=folder;		
				sliding=false;		
			}
		}
	}
	
	function ClipFolder(folder,top,right,bottom,left)
	{
		document.all["OB_Folder"+folder].style.clip='rect('+Math.max(top,0)+' '+right+' '+Math.max(bottom,0)+' '+left+')';
	}
	
	function Start()
	{
		if(!started)
		{
			ClipFolder(1,0,visibleAreaWidth,visibleAreaHeight,0);
		}		
	}

	function sObjDown(el) 
	{
		var x = document.getElementById(el);
		x.className="xBarSObjDown";
	}
	
	function sObjOver(el, id) 
	{
		var x = document.getElementById(el);
		x.className="xBarSObjOver";
		var img=document.getElementById("img"+id);
		if(img) img.style.filter = "";	
	}
	
	function sObjOut(el, id) {
		var x = document.getElementById(el);
		x.className="xBarSObjOut";
		var img=document.getElementById("img"+id);
		if(img) img.style.filter = "gray()";	
	}
	var selectedItem = null;
	
	function handleOver() {
		var fromEl = getReal(window.event.fromElement, "tagName", "DIV");
		var toEl = getReal(window.event.toElement, "tagName", "DIV");
		if (fromEl == toEl) return;	
		el = toEl;	
		if (el.className == "scrollButton" || el.className == "clsArrow") {
			if (el.className == "clsArrow") el = el.parentElement;
			overscrollButton(el);
		}
	}
	
	function handleOut() {
		var fromEl = getReal(window.event.fromElement, "tagName", "DIV");
		var toEl = getReal(window.event.toElement, "tagName", "DIV");
		if (fromEl == toEl) return;	
		el = fromEl;
		if (el.className == "scrollButton" || el.className == "clsArrow") {
			if (el.className == "clsArrow") el = el.parentElement;
			outscrollButton(el);
		}
	}
	
	function handleDown() {
		el = getReal(window.event.srcElement, "tagName", "DIV");	
		if (el.className == "scrollButton" || el.className == "clsArrow") {
			if (el.className == "clsArrow") el = el.parentElement;
			downscrollButton(el);
			var mark = Math.max(el.id.indexOf("Up"), el.id.indexOf("Down"));
			var type = el.id.substr(mark);
			var menuID = el.id.substring(0,mark);
			eval("scroll" + type + "(" + menuID + ")");
		}
	}
	
	function handleUp() {
		el = getReal(window.event.srcElement, "tagName", "DIV");
		if (el.className == "scrollButton" || el.className == "clsArrow") {
			if (el.className == "clsArrow") el = el.parentElement;
			upscrollButton(el);
			window.clearTimeout(scrolltimer);
		}
	}
	
	function getReal(el, type, value) {
		temp = el;
		while ((temp != null) && (temp.tagName != "DIV")) {
			if (eval("temp." + type) == value) {
				el = temp;
				return el;
			}
			temp = temp.parentElement;
		}
		return el;
	}
	
	function overscrollButton(el) {
		el.style.border = "2px outset";
	}
	
	function outscrollButton(el) {
		el.style.border = "2px outset";
	}
	
	function downscrollButton(el) {
		with (el.style) {
			el.style.border = "2px inset";
		}
	}
	
	function upscrollButton(el) {
		el.style.border = "2px outset";
	}
	
	var scrolltimer;
	var scrollAmount = 60;
	
	if (j-1 == 1) i = 33;
	else if (j-1 == 2) i = 29;
	else if (j-1 == 3) i = 26;
	else if (j-1 == 4) i = 25;
	else i = 24;
	
	scrollSpace = OB_ButtonHeight*(j-1)+i/2;
	
	function scrollDown(el) {
		if (el.offsetHeight > el.parentElement.offsetHeight - scrollSpace) {
			var mt = parseInt(el.style.marginTop);
			mt -= scrollAmount;
			if (mt >= el.parentElement.offsetHeight - el.offsetHeight - scrollSpace) {
				el.style.marginTop = mt;
				scrolltimer = window.setTimeout("scrollDown(" + el.id + ")",75);
			}
			else {
				el.style.marginTop = el.parentElement.offsetHeight - el.offsetHeight - scrollSpace;
			}
		}
		fixScroll(el)
	}
	
	function scrollUp(el) {
		var mt = parseInt(el.style.marginTop);
		mt += scrollAmount;
		if (mt >= 0) {
			el.style.marginTop = 0;
		}
		else {
			el.style.marginTop = mt;
			scrolltimer = window.setTimeout("scrollUp(" + el.id + ")",75);
		}
		fixScroll(el);
	}
	
	function fixScroll(el) {	
		if (el.style.marginTop == "") el.style.margin = "0px";
		mt = parseInt(el.style.marginTop);
		var downButton = eval(el.id + "Down");
		var upButton   = eval(el.id + "Up");
		
		upButton.style.left = el.parentElement.offsetWidth - 19;
		upButton.style.top = 3;
		downButton.style.left = el.parentElement.offsetWidth - 19;
		downButton.style.top = clipHeight - 19;
		upButton.style.display = (mt < 0) ? "block" : "none";
		downButton.style.display = ((mt==el.parentElement.offsetHeight-el.offsetHeight-scrollSpace) || (el.offsetHeight<=Math.max(el.parentElement.offsetHeight,96)-scrollSpace)) ? "none" : "inline";		 
		if (el.offsetHeight < el.parentElement.offsetHeight - scrollSpace) 
		{
			el.style.marginTop = 0;
			upButton.style.display = "none";
		}
	}
	function topPos(el) 
	{
		return doPosLoop(el, "Top");
	}
	
	function leftPos(el) 
	{
		return doPosLoop(el, "Left");
	}
	
	function doPosLoop(el, val) 
	{
		var temp = el;
		var x = eval("temp.offset" + val);
		while ((temp.tagName!="DIV") && (temp.offsetParent.style.position != "absolute")) {
			temp = temp.offsetParent;
			x += eval("temp.offset" + val);
		}
		return x;
	}
	
	var currentFolder; var currentItem; var slideCount; var slideStep;
	var slideSpeed; var width; var visibleAreaHeight; var visibleAreaWidth;
	var FolderClicked; var SlideFolders; var Start; var ClipFolder;
	var sliding; var items; var started; var clipHeight;
	
	function ini()
	{
		currentFolder=1;
		currentItem=null;
		slideCount=0;
		slideStep=1;
		slideSpeed=OB_SlideSpeed;
		width=OB_Width;
		visibleAreaHeight=Math.max(OB_Height,98)-2*0-(j-1)*OB_ButtonHeight;
		visibleAreaWidth=width;
		FolderClicked=FolderClicked;
		SlideFolders=SlideFolders;
		Start=Start;
		ClipFolder=ClipFolder;
		sliding=false;
		items=j-1;
		started=false;
	
		document.all["xBar"].style.visibility="visible";
		if (document.all)
		{
			document.onmouseover = handleOver;
			document.onmouseout = handleOut;
			document.onmousedown = handleDown;
			document.onmouseup = handleUp;
			var filter = /rect\((\d*)px (\d*)px (\d*)px (\d*)px\)/;
			var clipString=document.all["OB_Folder1"].style.clip;
			var clip=clipString.match(filter);
			clipHeight = clip[3];
		}
		
		scrollSpace = OB_ButtonHeight*(j-1)+i/2;
		for (i = 1; i < j; i++) {
			aMenu = eval("aMenu" + i);
			fixScroll(aMenu);
		}

		Start();
		fixsize();		
	}