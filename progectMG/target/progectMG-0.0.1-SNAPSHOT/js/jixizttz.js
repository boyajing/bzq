$(function () {
    /*停息*/
    $("#tingxi").click(function () {
        updateJixizt("1");
    });
    /*恢复计息*/
    $("#huifu").click(function () {
        updateJixizt("0");
    });
});

/*修改计息状态*/
function updateJixizt(jixizt) {
    if (checkSelected()) {
        if (checkSxrq()) {
            if (checkDate()) {
                var jixiArray = getArray(jixizt);
                $.ajax({
                    url: $("#path").val()+"/jixitz/jxztUpdate",
                    type: "POST",
                    contentType: 'application/json;charset=utf-8', //设置请求头信息
                    data: JSON.stringify(jixiArray),    //将Json对象序列化成Json字符串，JSON.stringify()原生态方法
                    success: function () {
                        updateJxzt(jixizt);
                        alert("修改成功");
                    },
                    error: function (XMLResponse) {
                        alert("修改失败");
                    }
                });
            }else {
                alert("修改后的日期不能小于原来的日期");
            }
        } else {
            alert("生效日期不能为空");
        }
    } else {
        alert("没有任何选项被选中");
    }
}
/*获得选择的行的合同编号f10htbh、计息项f5009jxx、生效日期f5010sxrq*/
function getArray(jxzt) {
    var array = [];
    $("input[name='f10htbh']").each(function () {
        if ($(this).prop("checked")) {
            var htbh = $(this).val();
            var jxx = $(this).next().val();
            var iddate = $(this).next().next().val();
            var sxrq = $("#" + iddate).val();
            array.push({"f10htbh": htbh, "f5009jxx": jxx, "f5010sxrq": sxrq, "f5009jxzt": jxzt});
        }
    });
    return array;
}
/*将选择的行的计息状态变化（0或者1）*/
function updateJxzt(jxzt) {
    $("input[name='f10htbh']").each(function () {
        if ($(this).prop("checked")) {
            var jixizt="";
            if(jxzt=="1"){
                jixizt="停息";
            }else if(jxzt=="0"){
                jixizt="计息";
            }
            $(this).parents("tr.checkedtr").children("td:last-child").html(jixizt);
        }
    });
}
/*判断是否选中*/
function checkSelected() {
    if ($("input[name='f10htbh']:checked").length >= 1) {
        return true;
    } else {
        return false;
    }
}
/*判断被选中的行，日期是否为空*/
function checkSxrq() {
    var flag = true;
    $("input[name='f10htbh']").each(function () {
        if($(this).prop("checked")) {
            var iddate = $(this).next().next().val();
            var sxrq = $("#" + iddate).val();
            if (sxrq == null || sxrq == "") {
                flag = false;
            }
        }
    });
    return flag;
}
/*判断修改后的日期不能小于修改前的日期*/
function checkDate(){
    var flag=true;
    $("input[name='f10htbh']").each(function () {
        var iddate = $(this).next().next().val();
        var sxrq = $("#" + iddate).val();
        var oldSxrq=$("#" + iddate).next().val();
        if (sxrq<oldSxrq) {
            flag=false;
        }
    });
    return flag;
}
