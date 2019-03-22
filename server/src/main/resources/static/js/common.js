var apiPreFix = "/api/v1.0";
/**
 * 检查请求是否成功，未成功检查是否未登录
 * @param response
 */
function checkLogin(response){
    if(!response.success){
        alert(response.errMsg);
        if(response.code === 5000){
            window.parent.location = "/login.html"
        }
        return false;
    }
    return true;
}

/**
 * 获取url参数
 * @param name
 * @returns {*}
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}