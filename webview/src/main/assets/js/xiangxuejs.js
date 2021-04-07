var xiangxuejs = {};
xiangxuejs.os = {};
xiangxuejs.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
xiangxuejs.os.isAndroid = !xiangxuejs.os.isIOS;
xiangxuejs.callbacks = {};

xiangxuejs.callback = function(callbackName,response){
    var callbackObject = xiangxuejs.callbacks[callbackName];
        if(callbackObject!=undefined){
            if(callbackObject.callback!=undefined){
                var ret = callbackObject.callback(response);
                if(ret===false){
                    return
                }
                delete xiangxuejs.callbacks[callbackName];
            }
        }
}


xiangxuejs.takeNativeAction = function(commandname, parameters){
    console.log("xiangxuejs takenativeaction")
    var request = {};
    request.name = commandname;
    request.param = parameters;
    if(window.xiangxuejs.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.xiangxuewebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.xiangxuewebview.postMessage(JSON.stringify(request))
    }
}
xiangxuejs.takeNativeActionWithCallBack = function(commandname, parameters,callback){
    var callbackName = "native2js_callback"+(new Date()).getTime()+"_"+Math.floor(Math.random()*10000);
    xiangxuejs.callbacks[callbackName] = {callback:callback};
    console.log("xiangxuejs takeNativeActionWithCallBack")
    var request = {};
    request.name = commandname;
    request.param = parameters;
    request.param.callbackName = callbackName;
    if(window.xiangxuejs.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.xiangxuewebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.xiangxuewebview.postMessage(JSON.stringify(request))
    }
}

window.xiangxuejs = xiangxuejs;
