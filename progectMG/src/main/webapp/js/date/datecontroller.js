$(function () {
    $('button').button();
    $('input[id$=date]').attr({onfocus: "this.blur()"});
    //年月日
    $('input[id$=date]').datepicker({
        dateFormat: 'yy-mm-dd',
        //dayNames : ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
        //dayNamesShort : ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
        dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
        monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        monthNamesShort: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
        //appendText : '日历',
        showWeek: false,
        weekHeader: '周',
        firstDay: 0,
        changeMonth: true,
        changeYear: true,
        //yearSuffix : '年',
        showMonthAfterYear: true,
        showButtonPanel: true,
        closeText: '关闭',
        currentText: '今天',
        nextText: '下个月',
        prevText: '上个月',
        yearRange: '1900:2200'
    });
});

//基础平台的年月日
function datepicker(id) {
    $("#" + id).datepicker({
        dateFormat: 'yy-mm-dd',
        //dayNames : ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
        //dayNamesShort : ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
        dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
        monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        monthNamesShort: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
        //appendText : '日历',
        showWeek: false,
        weekHeader: '周',
        firstDay: 0,
        changeMonth: true,
        changeYear: true,
        //yearSuffix : '年',
        showMonthAfterYear: true,
        showButtonPanel: true,
        closeText: '关闭',
        currentText: '今天',
        nextText: '下个月',
        prevText: '上个月',
        yearRange: '1900:2200',
    });
}

