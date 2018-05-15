$(function () {
    $("#saveLl").click(function () {
        if (checkDate()) {
            if (checkHl()) {
                if (checkSxrq()) {
                    var d5010lls = getArray();
                    $.ajax({
                        url: $("#path").val() + "/jixitz/jxllUpdate",
                        type: "POST",
                        contentType: 'application/json;charset=utf-8', //设置请求头信息
                        data: $.toJSON(d5010lls),    //将Json对象序列化成Json字符串，JSON.stringify()原生态方法
                        success: function () {
                            alert("操作成功");
                            $("#refreshForm").submit();
                        },
                        error: function () {
                            alert("操作失败");
                        }
                    });
                } else {
                    alert(Jixi.message);
                }
            } else {
                alert(Jixi.message);
            }
        } else {
            alert(Jixi.message);
        }
    });
});
function getArray() {
    var d5010lls = [];
    $("tr.checkedtr").each(function () {
        var f5010htbh = $.trim($(this).children("td[name=f5010htbh]").html());
        var f5010sxrq = $.trim($(this).find("input[name=f5010sxrq]").val());
        var f5010jxx = $.trim($(this).find("input[name=f5010jxx]").val());
        var f5010ll = $.trim($(this).find("input[name=f5010ll]").val());
        var f5010jsr = $.trim($(this).children("td[name=f5010jsr]").html());
        var f5008scjxr = $.trim($(this).find("input[name=f5008scjxr]").val());
        var f5010jxts = $.trim($(this).find("select[name=f5010jxts]").val());
        var isUpdateRq='0';
        if (f5010sxrq == null || f5010sxrq == "") {
            f5010sxrq = f5010jsr;
            isUpdateRq='1';
        }
        d5010lls.push({
            f5010htbh: f5010htbh,
            f5010sxrq: f5010sxrq,
            f5010jxx: f5010jxx,
            f5010ll: f5010ll,
            f5010jsr: f5010jsr,
            f5008scjxr: f5008scjxr,
            f5010jxts: f5010jxts,
            isUpdateRq:isUpdateRq
        });
    });
    return d5010lls;
}

/*
 * 判断相同合同编号、不同计息项的日期是否相同
 * */
function checkDate() {
    var arr1 = getArray();
    for (var i = 0; i < arr1.length; i++) {
        if (arr1[i].f5010sxrq < arr1[i].f5010jsr) {
            Jixi.message = "调整后日期不能小于调整前日期";
            return false;
        }
    }
    return true;
}
/**
 * 判断汇率是否是数字
 */
function checkHl() {
    var flag = true;
    $("input[name=f5010ll]").each(function () {
        if (/^\d+(\.\d+)?$/.test($(this).val()) == false) {
            Jixi.message = "利率包含非法字符";
            flag = false;
        }
    });
    return flag;
}
var Jixi = {
    message: '',
    count: ''
}
/*
 * 判断生效日期是否大于最大计息日
 * */
function checkSxrq() {
    var flag = true;
    var d5010lls = getArray();
    for (var i = 0; i < d5010lls.length; i++) {
        if (d5010lls[i].isUpdateRq=='0')
            if (d5010lls[i].f5010sxrq < d5010lls[i].f5008scjxr) {
                Jixi.message = "调整后利率生效日期小于计息日期【" + d5010lls[i].f5008scjxr + "】，请重新调整！";
                flag = false;
            }
    }
    return flag;
}