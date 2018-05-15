/**
 * Created by Administrator on 2017/4/26.
 */
function show(select) {
    if (select.checked) {
        $(select).parent().find("textarea").attr("readonly", false);
    } else {
        $(select).parent().find("textarea").attr("readonly", true);
        $(select).parent().find("textarea").val("");
    }
}

function fmoney(res) {
    var value=res.value;
    var index=res.value.indexOf('.');/*小数位数*/
    if (value != "") {
        if (isnumber(value) == false) {
            alert("金额格式错误");
            $(res).val("");
        } else if(res.value!=''&&res.value.split(".")[0].length>13){
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


function compnumber(){
    var frdbdwjbqkNumber=$("#frdbdwjbqkNumber").val();
    if(frdbdwjbqkNumber.length!=18){
        alert("身份证号位数应为18位，请重新输入！");
    }
}
function compnumber1(){
    var zrrdbdwjbqkNumber=$("#zrrdbdwjbqkNumber").val();
    if(zrrdbdwjbqkNumber.length!=18){
        alert("身份证号位数应为18位，请重新输入！");
    }
}
function compnumber2(){
    var zrrdbdwjbqkNumbers=$("#zrrdbdwjbqkNumbers").val();
    if(zrrdbdwjbqkNumbers.length!=18){
        alert("身份证号位数应为18位，请重新输入！");
    }
}
//function  compyhzh(){
//    var frdbdwjbqkZh=$("#frdbdwjbqkZh").val();
//    if(frdbdwjbqkZh.length!=16){
//        alert("银行账号位数应为16位，请重新输入！");
//    }
//}

