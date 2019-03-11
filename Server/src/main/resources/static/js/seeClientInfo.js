// 添加地址到表格中
function addAddress() {
    // alert("添加地址到表格中");

    var district = document.getElementById("district").value;
    var address = document.getElementById("address").value;

    var tbody = document.getElementById("addressTable");
    var tr = tbody.insertRow(tbody.rows.length);
    var districtTd = tr.insertCell(0);
    districtTd.innerHTML = district;
    var addressTd = tr.insertCell(1);
    addressTd.innerHTML = address;

    document.getElementById("address").value = "";
}

// 修改会员信息
function modifyClientInfo() {
    // alert("修改会员信息");

    var name = localStorage.userId;
    var realName = document.getElementById("realName").value;
    var phone = document.getElementById("phone").value;
    var districts = [];
    var details = [];

    var tbody = document.getElementById("addressTable");
    var len1 = tbody.rows.length;
    for(var i = 0 ; i < len1 ; i++) {
        districts.push(tbody.rows[i].cells[0].innerText);
        details.push(tbody.rows[i].cells[1].innerText);
    }

    /*console.log("name: "+name+"\n"+
        "realName: "+realName+"\n"+
        "phone: "+phone+"\n"+
        "districts: "+JSON.stringify(districts)+"\n"+
        "details: "+JSON.stringify(details));*/

    $.ajax({
        traditional: true,
        async : false,
        type: "POST",
        url: "http://localhost:8080/user/modifyClientInfo",
        //contentType :"application/json;charset=UTF-8",
        contentType :"application/x-www-form-urlencoded",
        data: {
            name:name,
            realName:realName,
            phone:phone,
            districts:districts,
            details:details
        },
        dataType: "text",
        success: function (data) {
            // alert("success");
            // var result = JSON.parse(JSON.stringify(data));
            // alert("data: "+data);
            if(data === "true") {
                alert("个人信息修改成功");
            }else {
                alert("个人信息修改失败");
            }

        },
        error: function(){
            alert("error");
        }
    });

    getClientInfo();
}

// 得到会员信息
function getClientInfo() {
    // alert("得到会员信息");

    var name = localStorage.userId;

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/client/seeClientInfo",
        data:{
            name:name
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            // alert("success");
            var result = JSON.parse(JSON.stringify(data));

            // console.log("result: "+JSON.parse(result));

            var mail = result.mail;
            var ranking = result.ranking;
            var grade = result.grade;
            var balance = result.balance.toFixed(2);
            var realName = result.realName;
            var phone = result.phone;
            var addressList = result.addressList;

            /*console.log("mail: "+mail+"\n"+
                "ranking: "+ranking+"\n"+
                "grade: "+grade+"\n"+
                "balance: "+balance+"\n"+
                "realName: "+realName+"\n"+
                "phone: "+phone+"\n"+
                "addressList: "+JSON.stringify(addressList));*/

            document.getElementById("name").value = name;
            document.getElementById("mail").value = mail;
            document.getElementById("ranking").value = ranking;
            document.getElementById("grade").value = grade;
            document.getElementById("balance").value = balance;
            document.getElementById("realName").value = realName;
            document.getElementById("phone").value = phone;

            // 地址
            var tbody = document.getElementById("addressTable");
            for(var i = tbody.rows.length-1 ; i >= 0 ; i--) {
                tbody.deleteRow(i);
            }

            for(var i = 0 ; i < addressList.length ; i++) {
                var obj = addressList[i];

                var tr = tbody.insertRow(i);
                var districtTd = tr.insertCell(0);
                districtTd.innerHTML = obj.district;
                var addressTd = tr.insertCell(1);
                addressTd.innerHTML = obj.detail;
            }
        },
        error: function(){
            alert("error");
        }
    });

}

// 注销会员账号
function abandonClient() {
    // alert("注销会员账号： "+localStorage.userId);
    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/user/abandon",
        data:{
            name:localStorage.userId
        },
        dataType: "text",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            alert("账号"+localStorage.userId+"注销成功");
            window.location.href = "/index.html";
        },
        error: function(){
            alert("error");
        }
    });
}