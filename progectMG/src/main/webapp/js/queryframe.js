$(function () {
    $("form").attr("style", "margin:0 0 10px");
    $("input").attr("style", "margin: 0px");
    $("form input").attr("class", "input-medium");
    $("form select").attr("class", "input-medium");
    $("form:has(table)").find('input').removeClass("input-medium");
    $("form:has(table)").find('select').removeClass("input-medium");
    $("button").attr("class", "btn");
    $("body").append("<div id='showDiv' style='display:none;text-align:center;height:20px;width:auto;margin-top:-30px;position: absolute; background-color: white; border: 1px solid black;'></div>");
});
$(function () {
    $("#resultTab tbody tr").attr({
        onclick: "select(this)",
        style: "cursor:pointer"
    }).find("input[name=radio]").attr({
        onclick: "event.cancelBubble=true"
    });
    $("#CzresultTab tbody tr").attr("onclick", "DoSelect(this)");
    $("#CzresultTab tbody tr").attr("style", "cursor:pointer");
    $("#CzresultTab tbody tr").find("input[name=radio]").attr("onclick", "event.cancelBubble=true");

    $("#resTab tbody tr").attr("onclick", "DoSelect(this)");
    $("#resTab tbody tr").attr("style", "cursor:pointer");
    $("#resTab tbody tr").find("input[name=radio]").attr("onclick", "event.cancelBubble=true");
});
/*金额类型加逗号*/
/*function fmoney(value, name) {
 if (value != "") {
 if (isnumber(value) == false) {
 alert("金额格式错误");
 document.getElementsByName(name)[0].value = "";
 } else {
 n = 2;
 value = parseFloat((value + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
 var l = value.split(".")[0].split("").reverse(),
 r = value.split(".")[1];
 t = "";
 for (i = 0; i < l.length; i++) {
 t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
 }
 document.getElementsByName(name)[0].value = t.split("").reverse().join("") + "." + r;
 return t.split("").reverse().join("") + "." + r;
 }
 }
 var a=document.getElementsByName(name)[0];
 outHide(a);
 }
 /!*金额类型去掉逗号*!/
 function rmoney(name) {
 s = document.getElementsByName(name)[0].value;
 if (s.indexOf(",") >= 0) {
 document.getElementsByName(name)[0].value = s.replace(/[^\d\.-]/g, "");
 return s.replace(/[^\d\.-]/g, "");
 }
 var a=document.getElementsByName(name)[0];
 overShow(a);
 }*/
function fmoney(res) {
    var value=res.value;
    var index=res.value.indexOf('.');/*小数位数*/
    if (value != "") {
        if (isnumber(value) == false) {
            alert("金额格式错误");
            $(res).val("");
        } else if(res.value!=''&&res.value.split(".")[0].length>12){
            alert("金额太大");
            $(res).val("");
        }else if(index!=-1&&(res.value.length-index-1)>2){
            alert("金额小数位太多");
            $(res).val("");
        }else {
            n = 2;
            value = parseFloat((value + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
            var l = value.split(".")[0].split("").reverse(),
                r = value.split(".")[1];
            t = "";
            for (i = 0; i < l.length; i++) {
                t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
            }
            $(res).val(t.split("").reverse().join("") + "." + r);
        }
    }
    outHide($(res));
}
/*金额类型去掉逗号*/
function rmoney(res) {
    s = res.value;
    if (s.indexOf(",") >= 0) {
        $(res).val(s.replace(/[^\d\.-]/g, ""));
    }
    overShow($(res));
}

/*金额类型去掉逗号，不显示汉字*/
function rmoneys(res) {
    s = res.value;
    if (s.indexOf(",") >= 0) {
        $(res).val(s.replace(/[^\d\.-]/g, ""));
    }
}


/*下拉框的联动选择*/
function select(e) {
    var temp = $(e).find("input[name=radio]");
    var bool = temp.prop("checked");
    if (bool) {
        $(e).find("input[name=radio]").prop({checked: false});
    }
    if (!bool) {
        $(e).find("input[name=radio]").prop({checked: true});
    }
}
/*使div伸缩*/
function elastic(targetid) {
    var d = document.getElementById(targetid);
    if (d.style.display !== "block") {
        d.style.display = "block";
        return;
    }
    if (d.style.display !== "none") {
        d.style.display = "none";
        return;
    }
}

/*使div伸缩 zhujianhu 为待办已办做的查询条件，点击查询条件之后清空数据*/
function elastictask(targetid) {
    $("#queryclick").val("");
    $("#queryname").val("");
    var d = document.getElementById(targetid);
    if (d.style.display !== "block") {
        d.style.display = "block";
        return;
    }
    if (d.style.display !== "none") {
        d.style.display = "none";
        return;
    }
}

/*使div伸缩 zhujianhu 为审批中的项目做的查询条件，点击查询条件之后清空数据*/
function elastictaskRun(targetid) {
    $("#queryclick").val("");
    $("#queryname").val("");
    $("#productName").val("");
    $("#parent").val("");
    $("#taskName").val("");
    $("#userID").val("");
    var d = document.getElementById(targetid);
    if (d.style.display !== "block") {
        d.style.display = "block";
        return;
    }
    if (d.style.display !== "none") {
        d.style.display = "none";
        return;
    }
}
/*添加行*/
function addRow(id) {
    var trlist = $("#" + id).find("table tr");
    var tr = trlist.eq(trlist.length - 1);
    tr.after(tr.clone());
    $("#" + id).find("table tr").last().find("input").each(function (i, o) {
        o.value = "";
    });
}
/*删除行*/
function delRow(id, index, minNum) {
    if (index > 0) {
        var trlist = $("#" + id).find("table tr");
        var obj = trlist.get(index + 1);
        if (trlist.length > minNum) {
            $(obj).remove();
        }
    }
}
/*去除数组内重复的项*/
function arrUnique(array) {
    var temp = [];
    $.each(array, function (i, o) {
        if ($.inArray(o, temp) == -1) {
            temp.push(o);
        }
    });
    return temp;
}
/*隐藏给定id的div*/
function display(id) {
    $("#" + id + " " + "input").each(function (i, o) {
        $("#" + o.value).attr({style: "display:none;"});
        $("#" + o.value + " " + "input").attr({disabled: true});
    });
    $("#" + id + " " + "select").each(function (i, o) {
        $("#" + o.value).attr({style: "display:none;"});
        $("#" + o.value + " " + "select").attr({disabled: true});
    });
}
/*初始化只有4列的表格样式*/
function initTable(id, index) {
    if (id == null) {
        $("table").each(function (i, o) {
            var thObj = $(o).find("tr:eq(" + index + ") td");
            if (thObj.length == 4) {
                thObj.eq(0).css({width: "20%"});
                thObj.eq(1).css({width: "30%"});
                thObj.eq(2).css({width: "20%"});
                thObj.eq(3).css({width: "30%"});
            }
        });
    }
}
/*字符串转双精度数字*/
function fmtNum(divId, inputName) {
    var temp = $("#" + divId + " " + "input[name=" + inputName + "]");
    var sum = parseFloat(0);
    temp.each(function (i, o) {
        var object = $(o).val().replace(/,/g, '');
        sum = sum + parseFloat(object == "" ? parseFloat(0) : object);
    });
    return sum;
}
/*cook*/
function SetRadioId(o, i, key,path) {
    if (o[0].checked) {

        AddCookie(i, key,path);
    }
    else {
        RemoveCookie(i, key,path);
    }
}
function SetCookie(name, value,path) {
    document.cookie = name + "=" + escape(value) + ";path="+path+"/"
}
function GetCookie(name) {
    if (!Array.prototype.indexOf) {
        Array.prototype.indexOf = function (elt /*, from*/) {
            var len = this.length >>> 0;

            var from = Number(arguments[1]) || 0;
            from = (from < 0)
                ? Math.ceil(from)
                : Math.floor(from);
            if (from < 0)
                from += len;

            for (; from < len; from++) {
                if (from in this &&
                    this[from] === elt)
                    return from;
            }
            return -1;
        };
    }
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(name + "=");
        if (c_start != -1) {
            c_start = c_start + name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) c_end = document.cookie.length;
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";
}
function AddCookie(i, key,path) {
    if (!Array.prototype.indexOf) {
        Array.prototype.indexOf = function (elt /*, from*/) {
            var len = this.length >>> 0;
            var from = Number(arguments[1]) || 0;
            from = (from < 0)
                ? Math.ceil(from)
                : Math.floor(from);
            if (from < 0)
                from += len;

            for (; from < len; from++) {
                if (from in this &&
                    this[from] === elt)
                    return from;
            }
            return -1;
        };
    }
    d = GetCookie(key);
    if (d == "") d = "&";
    if (d.indexOf("&" + i + "&") == -1) {
        d += i + "&";
        SetCookie(key, d,path);
    }
}

function RemoveCookie(i, key,path) {
    d = GetCookie(key);
    var reg = new RegExp("\\&" + i + "\\&");
    if (reg.test(d)) {
        d = d.replace(reg, "&");
        SetCookie(key, d,path);
    }
}

/*******************************************************************************
 * 是否数字（包括小数）
 *
 * @author 李然
 ******************************************************************************/
function isnumber(num) {
    num = num.replace(/,/g, "");
    //var re = new RegExp("^-?[\\d]*(\\.?[\\d]{1,2})?$");
    var re = new RegExp("^-?[\\d]*(\\.?[\\d]*)?$");
    if (re.test(num))
        return (!isNaN(parseFloat(num)));
    else
        return false;
}

function sfnumber(obj) {
    var re = new RegExp("^-?[\\d]*(\\.?[\\d]*)?$");
    if (!re.test($(obj).val())){
        alert("只能输入数字！");
        $(obj).val("");
        $(obj).focus();}
}
/*******************************************************************************
 * 是否整数
 *
 * @author 李然
 ******************************************************************************/
function isinteger(num) {
    var re = new RegExp("^-?[\\d]*$");
    if (re.test(num))
        return (!isNaN(parseInt(num)));
    else
        return false;
}

/** 非必填 整数验证 **/
function isIntnum(id) {
    var value = $.trim($("#" + id).val());
    if (value != "") {
        if (isnumber(value) == false) {
            alert("输入错误,请重新输入!");
            $("#" + id).val("");
            $("#" + id).focus();
            return false;
        }
        if (parseFloat(value) > 1 || parseFloat(value) <= 0) {
            alert("输入错误,利率只能输入0-1的数值!");
            $("#" + id).val("");
            $("#" + id).focus();
            return false;
        }
        if (value.length > 7) {
            alert("利率只能精确到小数点后6位!");
            $("#" + id).val("");
            $("#" + id).focus();
            return false;
        }
    }
    return true;
}
//收益率
function sylnumber(obj) {
    var value = $(obj).val();
    if (value != "") {
        if (isnumber(value) == false) {
            alert("输入错误,请重新输入!");
            $(obj).val("");
            $(obj).focus();
            return false;
        }
        if (parseFloat(value) >= 100 || parseFloat(value) <= 0) {
            alert("输入错误,利率只能输入0-100之间的数值!");
            $(obj).val("");
            $("#" + id).focus();
            return false;
        }
        if (value.length > 7) {
            alert("利率只能精确到小数点后6位!");
            $(obj).val("");
            $(obj).focus();
            return false;
        }
    }
    return true;
}

//格式话金额
function fmoneys(s, n)//将数字转换成逗号分隔的样式,保留两位小数s:value,n:小数位数
{
    s = getVal(s);
    s = Number(s).toFixed(n);
    s = s + "";
    var s1 = s.split(".")[0];
    var s2 = s.split(".")[1];
    var re = /(\d{1,3})(?=(\d{3})+(?:$|\D))/g;///(-?\d+)(\d{3})/
    s1 = s1.replace(re, "$1,");
    if(s1==-0)
        s1=0;
    return s1 + "." + s2;

}
function getVal(num) {
    return num.toString().replace(/,/g, "");
}
/**
 * 格式化金额域
 */
function formatMoney() {
    var moneyEl = $("input[type!=hidden][name!=khbh][name!=hskhbh][name!=hskhmc][name!=falcid][name!=bzm][name!=jgdm][name!=hsfkdw][name!=jydsmc][type!=radio][name!=famc][name!=djbh][name!=khmc][name!=yinhangkahao][name!=xmfzhs][name!=khfzhs][name!=xjllxm][name!=dzybh][name!=dhszclx]");
    moneyEl.each(function () {
        if (isnumber($(this).val()) == true) {
            var m = fmoneys($(this).val(), 2);
            $(this).val(m);
        }
    })
    moneyEl.blur(function () {
        if ($(this).val() != "") {
            if (isnumber($(this).val()) == true) {
                if ($(this).val()) {
                    if ($(this).attr("id") != undefined) {
                        if (!$(this).attr("id").contains("date")) {
                            var m = fmoneys($(this).val(), 2);
                            $(this).val(m);
                        }
                    } else {
                        var m = fmoneys($(this).val(), 2);
                        $(this).val(m);
                    }
                } else {
                    alert("金额格式错误");
                    $(this).val("");
                }
            }
        }
        outHide($(this));

    });
    moneyEl.focus(function () {
        if (isnumber($(this).val()) == true) {
            var m = $(this).val().replace(/,/g, "");
            $(this).val(m);
            overShow($(this));

        }
    });
}

function replaceMoney() {
    var moneyEl = $("input");
    moneyEl.each(function () {
        if (isnumber($(this).val()) == true) {
            var m = $(this).val().replace(/,/g, "");
            $(this).val(m);
        }
    });
}
function tdformatMoney() {
    $("td").text(function (i, v) {
        s = getVal(v);
        if (isnumber(s) == true) {

            s = Number(s).toFixed(2);
            s = s + "";
            var s1 = s.split(".")[0];
            var s2 = s.split(".")[1];
            var re = /(\d{1,3})(?=(\d{3})+(?:$|\D))/g;///(-?\d+)(\d{3})/
            s1 = s1.replace(re, "$1,");
            return s1 + "." + s2;
        }
    });
}
var digitUppercase = function (num) {
    var strOutput = "";
    var strUnit = '万仟佰拾亿仟佰拾万仟佰拾元角分';
    num += "00";
    var intPos = num.indexOf('.');
    if (intPos >= 0)
        num = num.substring(0, intPos) + num.substr(intPos + 1, 2);
    strUnit = strUnit.substr(strUnit.length - num.length);
    for (var i=0; i < num.length; i++)
        strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(num.substr(i,1),1) + strUnit.substr(i,1);
    return strOutput.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元').replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元");
};

function overShow(res) {
    if($(res).val()!=""){
        var offset = $(res).offset();
        $("#showDiv").css('left',offset.left+'px').css('top',offset.top+'px').fadeIn();
        $("#showDiv").css('display','black');
        $("#showDiv").html(digitUppercase($(res).val().replace(/,/g, "")));
    }
}

function outHide(res) {
    $("#showDiv").css('display','none');
    $("#showDiv").html = ('');

}

function bili(res) {
    if ($(res).val() != '') {
        if (isnumber($(res).val()) == true) {
            var cgbl = parseFloat($(res).val());
            if (cgbl <0 || cgbl > 100) {
                //alert("只能输入大于0小于100的值");
                alert("只能输入大于等于0小于等于100的值");
                $(res).val("");
                $(res).focus();
            }
        } else {
            alert("请输入正确数字格式");
            $(res).val("");
            $(res).focus();
        }
    }
}

/**
 * 正整数
 */
function integers(obj) {
    if ($(obj).val() != '') {
        nubmer = obj.value;
        if (isNaN(nubmer) || nubmer <= 0 || !(/^\d+$/.test(nubmer))) {
            {
                alert("请输入正确的数值,只允许输入整数!");
                $(obj).val("");
                $(obj).focus();
            }
        }
       
    }
}


//给必输项添加标识
function verify(temp){
    var a = new Array();
    a = temp.split(",");
    for(i= 0;i<a.length;i++){
        $("[name="+a[i]+"]").parent().append("<span style='color:red' class='affirmatively'>*</span>")
    }
}

//必输项校验
function inevit(){
    var a = "1";
    $(".affirmatively").each(function() {
        if($(this).prev().val()==null||$(this).prev().val()=="")
        {
            alert($(this).prev().parent().prev().text()+"不能为空");
            a = "0";
            return false;
        }
    });
    return a;
}