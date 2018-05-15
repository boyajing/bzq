function mergeRowsCell(tableId, col, start) {
    this.box = document.getElementById(tableId); //����
    this.col = col || 0; //��Ҫ�ϲ�����
    this.start = start || 0; //��ʼ��
    this.rowsLength = this.box.rows.length;
    this.maxLenth = 0;
    this.runSum = 0;
    this.init();
}
//ִ���е�Ԫ��ϲ���,��Ӧ�������Ѿ��仯,�轨��MAP�����Ӧ�ϲ�
mergeRowsCell.prototype.init = function() {
    var rowsLen = this.rowsLength;
    var colsMap = [];
    var map = [];

    for (var i = 0; i < rowsLen; i++) {
        colsMap.push(this.box.rows[i].cells.length);
    }

    var maxLenth = this.maxLenth = Math.max.apply(Math, colsMap);

    //��ʼ��ʱ��ÿ����Ԫ���Ǵ��ڵ�,ͳһ����ԭʼ��ʶ,����������ô�仯,�����������ҵ���Ӧ����һ�е�Ԫ��    
    for (var x = 0; x < rowsLen; x++) {
        for (var y = 0; y < maxLenth; y++) {
            this.box.rows[x].cells[y].setAttribute('data-mark', x + '.' + y);
        }
    }

};
//��ȡ��Ԫ���ʶ
//@param {element} ele
mergeRowsCell.prototype.getCellMark = function(ele) {
    var markArr = [];

    if (ele) {
        markArr[0] = ele.getAttribute('data-mark').split('.')[0] - 0;
        markArr[1] = ele.getAttribute('data-mark').split('.')[1] - 0;
    } else {
        console.warn('û���ҵ���Ӧ�ڵ�');
    }

    return markArr;
};
//���ҽڵ�
//@param {Number} row
//@param {Number} col
mergeRowsCell.prototype.findNode = function(row, col) {
    return this.box.querySelector('th[data-mark="' + (row + '.' + col) + '"]') || this.box.querySelector('td[data-mark="' + (row + '.' + col) + '"]');
};
//�ϲ���Ԫ��
//@param {Number} start
//@param {Number} col
mergeRowsCell.prototype.merge = function(start, col) {
    var rowSpan,
        start = start || this.start,
        col = col || this.col,
        now, next, temp;

    now = this.findNode(start, col);
    rowSpan = now ? now.rowSpan : 1;
    next = this.findNode(start + rowSpan, col);

    while (start < this.rowsLength) {
        if (next && now.innerHTML == next.innerHTML) {
            now.rowSpan += 1;
            next.parentNode.removeChild(next);
            start = this.getCellMark(now)[0] + now.rowSpan;
            next = this.findNode(start, col);
        } else {
            start = this.getCellMark(next)[0];
            if (!start) {
                return ;
            }
            now = this.findNode(start, col); //next -> now,��������..
            next = this.findNode(this.getCellMark(now)[0] + now.rowSpan, col);

        }
        // console.log(this.runSum);
        this.runSum++;
    }
};
