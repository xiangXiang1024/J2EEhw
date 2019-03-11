// 获得已登录的用户名
function getUserName() {
    return localStorage.userId;
}

// 用户登出
function logout() {
    localStorage.userId = "";
    localStorage.userType = "";
    localStorage.hasLogin = false;
    window.location.href = "/index.html";
}