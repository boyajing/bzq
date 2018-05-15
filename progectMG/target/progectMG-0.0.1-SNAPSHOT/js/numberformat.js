var NumberFormat = {
    toCurrency2: function(num){
        var factor = 100;
        num = num.toString().replace(/\$|\,/g,'');
        if(isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num*factor+0.50000000001);
        cents = num%factor;
        num = Math.floor(num/factor).toString();

        if(cents<10)
            cents = "0" + cents;
        return (((sign)?'':'-') + '' + num + '.' + cents);
    },

    toCurrency: function(num){
        var factor = 100;
        num = num.toString().replace(/\$|\,/g,'');
        if(isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num*factor+0.50000000001);
        cents = num%factor;
        num = Math.floor(num/factor).toString();
        if(cents<10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
            num = num.substring(0,num.length-(4*i+3))+','+
                num.substring(num.length-(4*i+3));
        return (((sign)?'':'-') + '' + num + '.' + cents);
    },

    toCurrency3: function(num){
        var factor = 1000000;
        num = num.toString().replace(/\$|\,/g,'');
        if(isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num*factor+0.50000000001);
        cents = num%factor;
        num = Math.floor(num/factor).toString();
        if(cents<10)
            cents = "00000" + cents;
        for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
            num = num.substring(0,num.length-(4*i+3))+','+
                num.substring(num.length-(4*i+3));
        return (((sign)?'':'-') + '' + num + '.' + cents);
    },

    toNumber: function(num){
        return num.toString().replace(/\$|\,/g,'');
    },

    toNumberFloat: function(num){
        num = parseFloat(num.toString().replace(/\$|\,/g,''));
        if(isNaN(num))
            num = parseFloat("0");
        return num;
    },

    formatCurrency: function(obj){
        obj.value = this.toCurrency(obj.value);
        $(obj).valid();
    },

    formatCurrency2: function(obj){
        obj.value = this.toCurrency2(obj.value);
    },

    formatCurrency3: function(obj){
        obj.value = this.toCurrency3(obj.value);
    },

    formatNumber: function(obj){
        obj.value = this.toNumber(obj.value);
        $(obj).valid();
    },

    error: function(){
        alert("error");
    }

}
