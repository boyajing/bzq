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

	function createButton(el) {	
	
		el.attachEvent("onmouseover",	createButton.overToolBarButton);
		el.attachEvent("onmouseout",	createButton.outToolBarButton);
		el.attachEvent("onmousedown",	createButton.downToolBarButton);
		el.attachEvent("onmouseup",		createButton.upToolBarButton);
		el.attachEvent("onclick",		createButton.clickToolBarButton);
		el.attachEvent("ondblclick",	createButton.clickToolBarButton);
		el.attachEvent("onkeypress",	createButton.keypressToolBarButton);
		el.attachEvent("onkeyup",		createButton.keyupToolBarButton);
		el.attachEvent("onkeydown",		createButton.keydownToolBarButton);
		el.attachEvent("onfocus",		createButton.focusToolBarButton);
		el.attachEvent("onblur",		createButton.blurToolBarButton);
		
		el.className = "toolBarButton";
		
		el.setEnabled	= createButton.setEnabled;
		el.getEnabled	= createButton.getEnabled;
		el.setValue		= createButton.setValue;
		el.getValue		= createButton.getValue;
		el.setToggle	= createButton.setToggle;
		el.getToggle	= createButton.getToggle;
		el.setAlwaysUp	= createButton.setAlwaysUp;
		el.getAlwaysUp	= createButton.getAlwaysUp;
		
		el._enabled		= true;
		el._toggle		= false;
		el._value		= false;
		el._alwaysUp	= false;
		
		return el;
	};
	
	createButton.LEFT = window.moz ? 0 : 1;
		
	createButton.overToolBarButton = function () {
		var toEl = createButton.getParentToolBarButton(window.event.toElement);
		var fromEl = createButton.getParentToolBarButton(window.event.fromElement);
		if (toEl == fromEl || toEl == null) return;
		
		toEl._over = true;
		
		if (!toEl._enabled) return;
		
		createButton.setClassName(toEl);
	};
	
	createButton.outToolBarButton = function () {
		var toEl = createButton.getParentToolBarButton(window.event.toElement);
		var fromEl = createButton.getParentToolBarButton(window.event.fromElement);
		if (toEl == fromEl || fromEl == null) return;
		
		fromEl._over = false;
		fromEl._down = false;
		
		if (!fromEl._enabled) return;	
	
		createButton.setClassName(fromEl);
	};
	
	createButton.downToolBarButton = function () {
		if (window.event.button != createButton.LEFT) return;
		
		var el = createButton.getParentToolBarButton(window.event.srcElement);
		if (el == null) return;
		
		el._down = true;
		
		if (!el._enabled) return;
	
		createButton.setClassName(el);
	};
	
	createButton.upToolBarButton = function () {
		if (window.event.button != createButton.LEFT) return;
		
		var el = createButton.getParentToolBarButton(window.event.srcElement);
		if (el == null) return;
		
		el._down = false;
		
		if (!el._enabled) return;
		
		if (el._toggle)
			el.setValue(!el._value);
		else
			createButton.setClassName(el);
	};
	
	createButton.clickToolBarButton = function () {
		var el = createButton.getParentToolBarButton(window.event.srcElement);
		el.onaction = el.getAttribute("onaction");
		if (el == null || !el._enabled || el.onaction == "" || el.onaction == null) return;
		
		if (typeof el.onaction == "string")
			el.onaction = new Function ("event", el.onaction);
		
		el.onaction(window.event);
	};
	
	createButton.keypressToolBarButton = function () {
		var el = createButton.getParentToolBarButton(window.event.srcElement);
		if (el == null || !el._enabled || window.event.keyCode != 13) return;
		
		el.setValue(!el._value);
		
		if (el.onaction == null) return;
		
		if (typeof el.onaction == "string")
			el.onaction = new Function ("event", el.onaction);
		
		el.onaction(window.event);
	};
	
	createButton.keydownToolBarButton = function () {
		var el = createButton.getParentToolBarButton(window.event.srcElement);
		if (el == null || !el._enabled || window.event.keyCode != 32) return;
		createButton.downToolBarButton();
	};
	
	createButton.keyupToolBarButton = function () {
		var el = createButton.getParentToolBarButton(window.event.srcElement);
		if (el == null || !el._enabled || window.event.keyCode != 32) return;
		createButton.upToolBarButton();
				
		if (el.onaction == null) return;
		
		if (typeof el.onaction == "string")
			el.onaction = new Function ("event", el.onaction);
		
		el.onaction(window.event);
	};
	
	createButton.focusToolBarButton = function () {
		var el = createButton.getParentToolBarButton(window.event.srcElement);
		if (el == null || !el._enabled) return;
		createButton.setClassName(el);
	};
	
	createButton.blurToolBarButton = function () {
		var el = createButton.getParentToolBarButton(window.event.srcElement);
		if (el == null) return;
		
		createButton.setClassName(el)
	};
	
	createButton.getParentToolBarButton = function (el) {
		if (el == null) return null;
		if (/toolBarButton/.test(el.className))
			return el;
		return createButton.getParentToolBarButton(el.parentNode);
	};
	
	createButton.setClassName = function (el) {
		var over = el._over;
		var down = el._down;
		var focused;
		try {
			focused = (el == document.activeElement && el.tabIndex > 0);
		}
		catch (exc) {
			focused = false;
		}
		
		if (!el._enabled) {
			if (el._value)
				el.className = "toolBarButtonActiveDisabled";
			else
				el.className = el._alwaysUp ? "toolBarButtonUpDisabled" : "toolBarButtonDisabled";
		}
		else {
			if (el._value) {
				if (over || down || focused)
					el.className = "toolBarButtonActiveHover";
				else
					el.className = "toolBarButtonActive";
			}
			else {
				if (down)
					el.className = "toolBarButtonActiveHover";
				else if (over || el._alwaysUp || focused)
					el.className = "toolBarButtonHover";
				else
					el.className = "toolBarButton";
			}
		}
	};
	
	createButton.setEnabled = function (b) {
		if (this._enabled != b) {
			this._enabled = b;
			createButton.setClassName(this, false, false);
			if (!window.moz) {
				if (b)
					this.innerHTML = this.firstChild.firstChild.innerHTML;
				else
					this.innerHTML = "<span class='toolBarButtonDisabledContainer'><span class='toolBarButtonDisabledContainer'>" + this.innerHTML + "</span></span>";
			}
		}
	};
	
	createButton.getEnabled = function () {
		return this._enabled;
	};
	
	createButton.setValue = function (v, bDontTriggerOnChange) {
		if (this._toggle && this._value != v) {
			this._value = v;
			createButton.setClassName(this, false, false);
			
			this.onchange = this.getAttribute("onchange");
			
			if (this.onchange == null || this.onchange == "" || bDontTriggerOnChange) return;
			
			if (typeof this.onchange == "string")
				this.onchange = new Function("", this.onchange);
	
			this.onchange();
		}
	};
	
	createButton.getValue = function () {
		return this._value;
	};
	
	createButton.setToggle = function (t) {
		if (this._toggle != t) {
			this._toggle = t;
			if (!t) this.setValue(false);
		}
	};
	
	createButton.getToggle = function () {
		return this._toggle;
	};
	
	createButton.setAlwaysUp = function (up) {
		if (this._alwaysUp != up) {
			this._alwaysUp = up;
			createButton.setClassName(this, false, false);
		}
	};
	
	createButton.getAlwaysUp = function () {
		return this._alwaysUp;
	};
