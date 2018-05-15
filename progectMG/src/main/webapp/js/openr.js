function openWin(winJson) {
    var winJson_ = {
        url: winJson.url || '',					
        isPost: winJson.isPost === undefined ? true : winJson.isPost,     			
        isFull: winJson.isFull || false,	     		
        width:  winJson.width || 300,				
        height: winJson.height || 150,				
        isScroll: winJson.isScroll || 'yes',				
        isResizable: winJson.isResizable || 'yes',			
        winName: winJson.winName || ('w'+Math.round(Math.random()*100000))
    };

    try{winJson_.height = parseInt(winJson_.height);}catch(e){}

 

    var wn = winJson_.winName;
    if(wn.length>20){
        wn = wn.substring(0,20);
        winJson_.winName = wn ;
    }

    if(winJson_.isFull){
        winJson_.width  = screen.availWidth;
        winJson_.height = screen.availHeight;
    }
    var l = (screen.availWidth - winJson_.width) / 2;
    var t = (screen.availHeight - winJson_.height) / 2;

   

    var s = 'width=' + winJson_.width + ', height=' + winJson_.height + ', top=' + t + ', left=' + l;
    s += ', toolbar=no, scrollbars='+winJson_.isScroll+', menubar=no, locations=0,location=no, status=no, status=0,resizable='+winJson_.isResizable;
    if(winJson_.isPost){
        if (window.attachEvent){ //ie,360兼容模式（其实是当前系统上装的ie浏览器对应的版本）
            UTFWindowOpen(winJson_.url, winJson_.winName, s, l, t, winJson_.width, winJson_.height, winJson_.isFull);
        }else{
            whir_openWindow(encodeURI(winJson_.url), winJson_.winName, s, l, t, winJson_.width, winJson_.height, winJson_);
        }
    }else{
        window.open(encodeURI(winJson_.url), winJson_.winName, s);
    }
}

function whir_openWindow(url, name, features, l, t, width, height, winJson_) {
    var targeturl = url;
    var newwin = window.open("", name, features);
     
    targeturl = targeturl.replace(/#/,'%23');
    newwin.location = targeturl;
} 


//被openWin方法调用
function UTFWindowOpen(sURL, winName, features, l, t, width, height, isFull) { 
    var oW;
    var contents = "";
    var mainUrl  = ""; 
    if(sURL.indexOf("?") > 0){
        var arrayParams = sURL.split("?");
        var arrayURLParams = arrayParams[1].split("&");
        mainUrl = arrayParams[0];
        for (var i = 0, len=arrayURLParams.length; i < len; i++){
            var sParam = arrayURLParams[i].split("=");
            if ((sParam[0] != "") && (sParam[1] != "")){
                contents += "<input type=\"hidden\" name=\""+sParam[0] +"\" value=\""+sParam[1]+"\"/>";
            }else if (sParam[0] != "" && sParam[1] == ""){
                contents += "<input type=\"hidden\" name=\""+sParam[0] +"\" value=\"\"/>";
            }
        }
    }else{
        mainUrl=sURL;
    }

    oW = window.open('', winName, features);
    oW.document.open();
    oW.document.write('<form name="postform" id="postform" action="'+mainUrl+'" method="post" accept-charset="utf-8">'+contents+'</form><script type="text/javascript">document.getElementById("postform").submit();</script>');
    oW.document.close();
    //$("#postform", oW.document).submit();

    oW.moveTo(l, t);
    oW.resizeTo(width, height);
}