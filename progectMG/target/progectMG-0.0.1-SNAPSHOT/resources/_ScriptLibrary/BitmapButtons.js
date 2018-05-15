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

	function BB_getElement(el){temp = el;while((temp!=null)&&(temp.tagName!="TABLE")){if(temp.tagName=="TR")el=temp;temp=temp.parentElement;}return el;} 	
	var lastel=null;    
	function switchon(el){el=BB_getElement(el);lastel=el;}
	function switchoff(el){if(lastel){lastel.style.position="relative";lastel.style.top="0px";lastel.style.left="0px";}}
	function switchpress(el){if(lastel){lastel.style.position="relative";lastel.style.top="1px";lastel.style.left="1px";}}