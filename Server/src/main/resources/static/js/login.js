function login() {
    var id = document.getElementById("id").value;
    var password = document.getElementById("password").value;

    // alert(id + " " + password);
    localStorage.clear();

    $.ajax({
        async:false,
        type:"POST",
        url: "http://localhost:8080/user/login",
        data:{
            id:id,
            password:password
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            var result = JSON.parse(JSON.stringify(data));
            //var loginResult = result.result;
            console.log("result:"+result.result);
            console.log("userId:"+result.userId);
            console.log("userType:"+result.userType);
            //
            // alert("result:"+result.result);
            // alert("userId:"+result.userId);
            // alert("userType:"+result.userType);

            var loginResult = result.result;
            if(loginResult === "PASS") {
                var type = result.userType;

                localStorage.userId = result.userId;
                // localStorage.userType = type;
                // localStorage.hasLogin = true;

                if(type === "ADMIN") {
                    // alert("管理员登录");
                    window.location.href = "/approveInfo.html";
                }else if(type === "CLIENT") {
                    // alert("会员登录");
                    window.location.href = "/seeClientOrder.html";
                }else if(type === "CANTEEN") {
                    // alert("餐厅登录");
                    window.location.href = "/seeCanteenOrder.html";
                }

            }else {
                alert(result.loginResult);
            }
        },
        error: function(){
            alert("error");
        }
    })
}