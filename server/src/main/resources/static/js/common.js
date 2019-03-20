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