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

	function getCookie(NameOfCookie)
	{
		if (document.cookie.length > 0) 
		{              
			begin = document.cookie.indexOf(NameOfCookie+"=");       
			if (begin != -1) 
			{           
				begin += NameOfCookie.length+1;       
				end = document.cookie.indexOf(";", begin);
				if (end == -1) end = document.cookie.length;
				return unescape(document.cookie.substring(begin, end));
			} 
		}
		return null;
	}
	
	function setCookie(NameOfCookie, value, expiredays) 
	{
		var ExpireDate = new Date ();
		ExpireDate.setTime(ExpireDate.getTime() + (expiredays * 24 * 3600 * 1000));
		document.cookie = NameOfCookie + "=" + escape(value) + ((expiredays == null) ? "" : "; expires=" + ExpireDate.toGMTString());
	}
	
	function setSessionCookie(NameOfCookie, value) { document.cookie = NameOfCookie + "=" + escape(value) }
	
	function getRawCookie(NameOfCookie)
	{
		if (document.cookie.length > 0) 
		{              
			begin = document.cookie.indexOf(NameOfCookie+"=");       
			if (begin != -1) 
			{           
				begin += NameOfCookie.length+1;       
				end = document.cookie.indexOf(";", begin);
				if (end == -1) end = document.cookie.length;
				return document.cookie.substring(begin, end);
			} 
		}
		return null;
	}
	
	function setRawCookie(NameOfCookie, value, expiredays) 
	{
		var ExpireDate = new Date ();
		ExpireDate.setTime(ExpireDate.getTime() + (expiredays * 24 * 3600 * 1000));
		document.cookie = NameOfCookie + "=" + value + ((expiredays == null) ? "" : "; expires=" + ExpireDate.toGMTString());
	}
	
	function setRawSessionCookie(NameOfCookie, value) { document.cookie = NameOfCookie + "=" + value }
	
	function delCookie (NameOfCookie) {	if (getCookie(NameOfCookie)) document.cookie = NameOfCookie + "=" + "; expires=Thu, 01-Jan-70 00:00:01 GMT"; }
