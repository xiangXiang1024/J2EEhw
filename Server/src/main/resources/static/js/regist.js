// 会员注册
function clientRegist() {
    //alert("会员注册");

    var name = document.getElementById("name").value;
    var password = document.getElementById("password").value;
    var e_mail = document.getElementById("e_mail").value;
    var phone = document.getElementById("phone").value;
    var realName = document.getElementById("realName").value;
    var verificationCode = document.getElementById("code").value;

    var info = {};
    info.name = name;
    info.password = password;
    info.e_mail = e_mail;
    info.phone = phone;
    info.realName = realName;
    info.verificationCode = verificationCode;

    // alert(JSON.stringify(info));

    $.ajax({
        async:false,
        type:"POST",
        url: "http://localhost:8080/user/registerClient",
        data:JSON.stringify(info),
        dataType: "text",
        contentType: "application/json;charset=utf-8",
        success:function (data){
            // alert("success");
            var result = JSON.parse(JSON.stringify(data));
            //var loginResult = result.result;
            // alert("result:"+result);

            if(result === "注册成功") {
                alert("会员 "+name+" 注册成功");
                window.location.href = "/login.html";
            }else {
                alert(result);
            }
        },
        error: function(error){
             console.log("error");
            console.log(error);
            alert("error");
        }
    })
}

// 发送邮件
function sendEmail() {
    var name = document.getElementById("name").value;
    // var password = document.getElementById("password").value;
    var e_mail = document.getElementById("e_mail").value;
    // var phone = document.getElementById("phone").value;
    // var realName = document.getElementById("realName").value;
    // var verificationCode = document.getElementById("code").value;

    // alert("in the js fuc sendEmail()")
    // alert("name:"+name+"  e_mail:"+e_mail);

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/user/sendEmail",
        data:{
            name:name,
            mail:e_mail
        },
        dataType: "text",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            var result = JSON.parse(JSON.stringify(data));
            //var loginResult = result.result;
            console.log("result:"+result);

            var loginResult = result.result;
            if(loginResult === "成功") {
                alert("验证邮件已发送，请在5分钟内完成验证。");
            }else {
                alert(result);
            }/*

            document.getElementById("name").value = name;
            document.getElementById("password").value = password;
            document.getElementById("e_mail").value = e_mail;
            document.getElementById("phone").value = phone;
            document.getElementById("realName").value = realName;
            document.getElementById("code").value = verificationCode;*/

        },
        error: function(){
            alert("error");
        }
    })


}

// 餐厅注册
function canteenRegist() {
    // alert("餐厅注册");

    var name = document.getElementById("name2").value;
    var password = document.getElementById("password2").value;
    var type = document.getElementById("type2").value;
    var district = document.getElementById("district2").value;
    var addressDetail = document.getElementById("address2").value;
    var shippingFee = document.getElementById("shippingFee2").value;
    var packagingFee = document.getElementById("packagingFee2").value;
/*

    alert("name:"+name+"\n"+
        "password:"+password+"\n"+
        "type:"+type+"\n"+
        "district:"+district+"\n"+
        "addressDetail:"+addressDetail+"\n"+
        "shippingFee:"+shippingFee+"\n"+
        "packagingFee:"+packagingFee+"\n");*/

    $.ajax({
        async:false,
        type:"POST",
        url: "http://localhost:8080/user/registerCanteen",
        data:{
            name:name,
            password:password,
            type:type,
            district:district,
            addressDetail:addressDetail,
            shippingFee:shippingFee,
            packagingFee:packagingFee
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            var result = JSON.parse(JSON.stringify(data));
            //var loginResult = result.result;
            console.log("result:"+result.resultStr);
            console.log("id:"+result.id);

            //alert("result:"+result.resultStr);
            //alert("id:"+result.id);

            var registResult = result.resultStr;
            if(registResult === "注册成功") {
                var canteenId = result.id;
                alert("注册成功!\n您的餐厅编号是"+canteenId+"。");
                window.location.href = "/login.html";
                // return;
            }else {
                alert("注册失败，"+registResult);
            }
        },
        error: function(){
            alert("error");
        }
    })
}
/*

function test() {
    var name = document.getElementById("name").value;
    var password = document.getElementById("password").value;
    var e_mail = document.getElementById("e_mail").value;
    var phone = document.getElementById("phone").value;
    var realName = document.getElementById("realName").value;
    var verificationCode = document.getElementById("code").value;


    alert("test");
    return false;

    /!*document.getElementById("name").value = name;
    document.getElementById("password").value = password;
    document.getElementById("e_mail").value = e_mail;
    document.getElementById("phone").value = phone;
    document.getElementById("realName").value = realName;
    document.getElementById("code").value = verificationCode;*!/

    //alert("test2")
}*/
