function isSelectAll() {
    var nodes = $("[name = htbh]:checkbox");
    var count = 0;
    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i].checked) {
            count++;
        }
    }
    if (count == nodes.length) {
        return "all";
    }
    return "0";
}
$(function () {
    $(".checkedtr").unbind("click");
    $(".checkedtr").bind("click", function () {
        var node = $(this).find("td input").first();
        if (node.prop("checked")) {
            node.prop("checked", false);
        } else {
            node.prop("checked", true);
        }
        if (isSelectAll() == "all") {
            $("#selectAll").prop("checked", true);
        } else {
            $("#selectAll").prop("checked", false);
        }
    });
    $("#jxzttz").click(function () {
        if ($("input[name='htbh']:checked").length >= 1) {
            $("#f5009jxx").attr("value", $("#f5009jxxselect option:selected").val());
            $("#jixiForm").submit();
        } else {
            alert("至少选择一个");
        }
    });
    $("#lltz").click(function () {
        if ($("input[name='htbh']:checked").length >= 1) {
            $("#f5009jxx").attr("value", $("#f5009jxxselect option:selected").val());
            var path = $("#path").val() + "/jixitz/jixill";
            $('#jixiForm').attr("action", path).submit();
        } else {
            alert("至少选择一个");
        }
    });
    /*提交查询*/
    $("#chaxun").click(function () {
        $('#queryForm').submit();
    });
    /*清空form表单*/
    $("#reset").click(function () {
        $(':input', '#queryForm')
            .not(':button, :submit, :reset, :hidden')
            .val('')
            .removeAttr('checked')
            .removeAttr('selected');
    });
    $("#selectAll").unbind("change");
    $("#selectAll").bind("change", function () {
        if ($("#selectAll").is(':checked')) {
            $("[name = htbh]:checkbox").prop("checked", true);
        } else {
            $("[name = htbh]:checkbox").prop("checked", false);
        }
    });
    $("input[name=htbh]").each(function () {
        $(this).change(function(){
            if (isSelectAll() == "all") {
                $("#selectAll").prop("checked", true);
            } else {
                $("#selectAll").prop("checked", false);
            }
        });
    });
});
