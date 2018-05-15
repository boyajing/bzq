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

	function ConveaTree(sText, sFileIcon, sContextFunction, iType) 
	{
		this._subItems = [];
		if (iType) this.type=iType; else this.type = -1;
		this.id        = ConveaTreeHandler.getId();
		this.text      = sText || defaultText;
		this.action    = defaultAction;
		this._wasLast  = false; 
		this.open      = (getCookie(this.id.substr(18,this.id.length - 18)) == '0')?false:true;
		this.icon      = sFileIcon;
		this.openIcon  = sFileIcon;
		this.context   = sContextFunction;
		ConveaTreeHandler.behavior =  'classic';
		ConveaTreeHandler.all[this.id] = this;
	}
	
	ConveaTree.prototype.setBehavior = function (sBehavior) {
		ConveaTreeHandler.behavior =  sBehavior;
	};
	
	ConveaTree.prototype.getBehavior = function (sBehavior) {
		return ConveaTreeHandler.behavior;
	};
	
	ConveaTree.prototype.add = function (treeItem) {
		treeItem.parent = this;
		this._subItems[this._subItems.length] = treeItem;
	};
	
	ConveaTree.prototype.toString = function () {
		var str = "<div id=\"" + this.id + "\" ondblclick=\"ConveaTreeHandler.toggle(this);\" class=\"Convea-tree-item\" oncontextmenu=\""+this.context+"\"><nobr>";
		str += "<img width=16 height=16 id=\"" + this.id + "-icon\" src=\"" + ((ConveaTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"ConveaTreeHandler.select(this);\"><a href=\"" + this.action + "\" id=\"" + this.id + "-anchor\" onfocus=\"ConveaTreeHandler.focus(this);\">" + this.text + "</a></div>";
		str += "<div id=\"" + this.id + "-cont\" class=\"Convea-tree-container\" style=\"display: " + ((this.open)?'block':'none') + ";\">";
		for (var i = 0; i < this._subItems.length; i++) {
			str += this._subItems[i].toString(i,this._subItems.length);
		}
		str += "</nobr></div>";	
		return str;
	};
	
	ConveaTree.prototype.getSelected = function () {
		if (selectedObj) { return selectedObj.id; }
		else { return null; }
	}
	
	ConveaTree.prototype.toggle = function () {
		if (this.open) { this.collapse(); }
		else { this.expand(); }
	}
	
	ConveaTree.prototype.select = function () {
		document.getElementById(this.id + '-anchor').focus();
	}
	
	ConveaTree.prototype.focus = function () {
		if (selectedObj) { selectedObj.blur(); }
		selectedObj = this;
		if ((this.openIcon) && (ConveaTreeHandler.behavior != 'classic')) { document.getElementById(this.id + '-icon').src = this.openIcon; }
		document.getElementById(this.id + '-anchor').style.backgroundColor = highlight;
		document.getElementById(this.id + '-anchor').style.color = 'black';
		document.getElementById(this.id + '-anchor').style.border = '1px solid gray';
	}
	
	ConveaTree.prototype.blur = function () {
		if ((this.openIcon) && (ConveaTreeHandler.behavior != 'classic')) { document.getElementById(this.id + '-icon').src = this.icon; }
		document.getElementById(this.id + '-anchor').style.backgroundColor = 'window';
		document.getElementById(this.id + '-anchor').style.color = 'menutext';
		document.getElementById(this.id + '-anchor').style.border = '0px';
	}
	
	ConveaTree.prototype.expand = function () {
		if (ConveaTreeHandler.behavior == 'classic') {
			document.getElementById(this.id + '-icon').src = this.openIcon;
		}
		document.getElementById(this.id + '-cont').style.display = 'block';
		this.open = true;
		setCookie(this.id.substr(18,this.id.length - 18), '1');
	}
	
	ConveaTree.prototype.collapse = function () {
		if (ConveaTreeHandler.behavior == 'classic') {
			document.getElementById(this.id + '-icon').src = this.icon;
		}
		document.getElementById(this.id + '-cont').style.display = 'none';
		this.open = false;
		setCookie(this.id.substr(18,this.id.length - 18), '0');
	}
	
	ConveaTree.prototype.expandAll = function () {
		this.expandChildren();
		this.expand();
	}
	
	ConveaTree.prototype.expandChildren = function () {
		for (var i = 0; i < this._subItems.length; i++) {
			this._subItems[i].expandAll();
		}
	}
	
	ConveaTree.prototype.collapseAll = function () {
		this.collapse();
		this.collapseChildren();
	}
	
	ConveaTree.prototype.collapseChildren = function () {
		for (var i = 0; i < this._subItems.length; i++) {
			this._subItems[i].collapseAll();
		}
	}
	
	function ConveaTreeItem(sText, iType, iRecord, sFileIcon, sContextFunction) 
	{
		this._subItems  = [];
		this._wasLast   = false;
		this.text       = sText || defaultText;
		this.type		= iType;
		this.record	    = iRecord; 
		this.fileicon   = sFileIcon;
		this.action     = defaultAction;
		this.id         = ConveaTreeHandler.getId();
		this.open		= (getCookie(this.id.substr(18,this.id.length - 18)) == '1')?true:false;
		this.highlight  = false;
		this.context	= sContextFunction
		ConveaTreeHandler.all[this.id] = this;
	};
	
	ConveaTreeItem.prototype.add = function (treeItem) {
		treeItem.parent = this;
		this._subItems[this._subItems.length] = treeItem;
	};
	
	ConveaTreeItem.prototype.toggle = function () {
		if (this.open) { this.collapse(); }
		else { this.expand(); }
	}
	
	ConveaTreeItem.prototype.select = function () {
		document.getElementById(this.id + '-anchor').focus();
	}
	
	ConveaTreeItem.prototype.focus = function () {
		if (selectedObj) { selectedObj.blur(); }
		selectedObj = this;
		if ((this.openIcon) && (ConveaTreeHandler.behavior != 'classic')) { document.getElementById(this.id + '-icon').src = this.openIcon; }
		document.getElementById(this.id + '-anchor').style.backgroundColor = highlight;
		document.getElementById(this.id + '-anchor').style.color = 'black';
		document.getElementById(this.id + '-anchor').style.border = '1px solid gray';
	}
	
	ConveaTreeItem.prototype.blur = function () {
		if ((this.openIcon) && (ConveaTreeHandler.behavior != 'classic')) { document.getElementById(this.id + '-icon').src = this.icon; }
		document.getElementById(this.id + '-anchor').style.backgroundColor = 'window';
		document.getElementById(this.id + '-anchor').style.color = 'menutext';
		document.getElementById(this.id + '-anchor').style.border = '0px';
	}
	
	ConveaTreeItem.prototype.expand = function () {
		if (this._subItems.length > 0) { 
			document.getElementById(this.id + '-cont').style.display = 'block';
		}
		if (ConveaTreeHandler.behavior == 'classic') {
			document.getElementById(this.id + '-icon').src = this.openIcon;
		}
		document.getElementById(this.id + '-plus').src = this.minusIcon;
		this.open = true;
		setCookie(this.id.substr(18,this.id.length - 18), '1');
	}
	
	ConveaTreeItem.prototype.collapse = function () {
		if (this._subItems.length > 0) {
			document.getElementById(this.id + '-cont').style.display = 'none';
		}
		if (ConveaTreeHandler.behavior == 'classic') {
			document.getElementById(this.id + '-icon').src = this.icon;
		}
		document.getElementById(this.id + '-plus').src = this.plusIcon;
		this.open = false;
		setCookie(this.id.substr(18,this.id.length - 18), '0');
	}
	
	ConveaTreeItem.prototype.expandAll = function () {
		this.expandChildren();
		this.expand();
	}
	
	ConveaTreeItem.prototype.expandChildren = function () {
		for (var i = 0; i < this._subItems.length; i++) {
			this._subItems[i].expandAll();
		}
	}
	
	ConveaTreeItem.prototype.collapseAll = function () {
		this.collapse();
		this.collapseChildren();
	}
	
	ConveaTreeItem.prototype.collapseChildren = function () {
		for (var i = 0; i < this._subItems.length; i++) {
			this._subItems[i].collapseAll();
		}
	}
	
	ConveaTreeItem.prototype.toString = function (nItem,nItemCount) {
		var foo = this.parent;
		var indent = '';
		if (nItem + 1 == nItemCount) { this.parent._wasLast = true; }
		
		var count=0;
		while (foo.parent) 
		{
			count++;
			foo = foo.parent;
			indent = "<img width=19 height=16 src=\"" + ((foo._wasLast)?blankIcon:iIcon) + "\">" + indent;
		}
	
		this.icon=this.openIcon=this.fileicon;
		
		if (this._subItems.length) { this.folder = 1; }
		if (this.folder) 
		{
			var str = "<div id=\"" + this.id + "\" ondblclick=\"ConveaTreeHandler.toggle(this);\" class=\"Convea-tree-item\" oncontextmenu=\""+this.context+"\"><nobr>";
			str += indent;
			str += "<img width=19 height=16 id=\"" + this.id + "-plus\" src=\"" + ((this.open)?((this.parent._wasLast)?lMinusIcon:tMinusIcon):((this.parent._wasLast)?lPlusIcon:tPlusIcon)) + "\" onclick=\"ConveaTreeHandler.toggle(this);\">"
			str += "<img width=16 height=16 id=\"" + this.id + "-icon\" src=\"" + ((ConveaTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"ConveaTreeHandler.select(this);\"><a href=\"" + this.action + "\" id=\"" + this.id + "-anchor\" onfocus=\"ConveaTreeHandler.focus(this);\">" + this.text + "</a></div>";
			str += "<div id=\"" + this.id + "-cont\" class=\"Convea-tree-container\" style=\"display: " + ((this.open)?'block':'none') + ";\">";
			for (var i = 0; i < this._subItems.length; i++) {
				str += this._subItems[i].toString(i,this._subItems.length);
			}
			str += "</nobr></div>";
		}
		else 
		{
			var str = "<div id=\"" + this.id + "\" class=\"Convea-tree-item\" oncontextmenu=\""+this.context+"\"><nobr>";
			str += indent;
			str += "<img width=19 height=16 id=\"" + this.id + "-plus\" src=\"" + ((this.parent._wasLast)?lIcon:tIcon) + "\">"
			
			if(this.highlight==true) 
			{
				selectedObj = this;
				str += "<img width=16 height=16 id=\"" + this.id + "-icon\" src=\"" + this.icon + "\" onclick=\"ConveaTreeHandler.select(this);\"><a class=\"Convea-tree-item-on\" href=\"" + this.action + "\" id=\"" + this.id + "-anchor\" onfocus=\"ConveaTreeHandler.focus(this);\">" + this.text + "</a></nobr></div>";
			}
			else str += "<img width=16 height=16 id=\"" + this.id + "-icon\" src=\"" + this.icon + "\" onclick=\"ConveaTreeHandler.select(this);\"><a href=\"" + this.action + "\" id=\"" + this.id + "-anchor\" onfocus=\"ConveaTreeHandler.focus(this);\">" + this.text + "</a></nobr></div>";
	
		}
		
		this.plusIcon = ((this.parent._wasLast)?lPlusIcon:tPlusIcon);
		this.minusIcon = ((this.parent._wasLast)?lMinusIcon:tMinusIcon);
		
		return str;
	}
