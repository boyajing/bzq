/**
 * Created by Administrator on 2017/5/16.
 */

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

//校验联系电话，例：手机号15210361234 座机号010-62698081
function sf(obj) {
    var ae = /^1[34578]+\d{9}$/;
    var be=/^([0-9]{3,4}-)?[0-9]{7,8}$/;
    if (!ae.test($(obj).val())&&!be.test($(obj).val())){
        alert("请输入正确的联系电话！");
        $(obj).val("");
    }
}
//校验邮编

function yb(obj) {
    var re = /^\d{6}$/;
    if (!re.test($(obj).val())){
        alert("请输入正确的邮编！");
        $(obj).val("");
    }
}

//校验贷款卡号
function dk(obj) {
    var re = new RegExp("^-?[\\d]*$");
    if (!re.test($(obj).val())){
        alert("只能输入整数数字！");
        $(obj).val("");
    }
    if($(obj).val().length>18){
        alert("请输入小于19位数字！");
        $(obj).val("");
    }
    if($(obj).val()<0){
        alert("不能输入小于0的数字！");
        $(obj).val("");
    }
}

//校验现有职工
function dks(obj) {
    var re = new RegExp("^-?[\\d]*$");
    if (!re.test($(obj).val())){
        alert("只能输入整数数字！");
        $(obj).val("");
    }
    if($(obj).val().length>6){
        alert("请输入小于6位数字！");
        $(obj).val("");
    }
    if($(obj).val()<0){
        alert("不能输入小于0的数字！");
        $(obj).val("");
    }
}

function cardh(){
    var frdbdwjbqkNumber=$("#frdbdwjbqkNumber").val();
    if(frdbdwjbqkNumber.length!=18){
        alert("身份证号位数应为18位，请重新输入！");
    }
}