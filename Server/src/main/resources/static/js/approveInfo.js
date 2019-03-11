// 审批通过
function pass() {
    // alert("审批通过");

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/admin/approveModifyInfo",
        data:{
            id: localStorage.infoId,
            result: "PASS",
            comment: document.getElementById("comment").value
        },
        dataType: "text",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            // alert("pass success");
        },
        error: function(error){
            alert(error);
        }
    });

    getNextModifyInfo();
}

// 审批不通过
function fail() {
    // alert("审批不通过");

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/admin/approveModifyInfo",
        data:{
            id: localStorage.infoId,
            result: "FAIL",
            comment: document.getElementById("comment").value
        },
        dataType: "text",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            // alert("pass success");
        },
        error: function(error){
            alert(error);
        }
    });

    getNextModifyInfo();
}

// 获得第一条待审批的修改信息
function getModifyInfo() {
    // alert("获得第一条待审批的修改信息")

    $.ajax({

        async:false,
        type:"GET",
        url: "http://localhost:8080/admin/getModifyInfo",
        data:"",
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            // alert("result: "+JSON.stringify(data));
            // console.log("result: "+JSON.stringify(data));

            var obj = JSON.parse(JSON.stringify(data));
            fillForm(obj);
        },
        error: function(error){
            alert("没有需要审批的信息");
            emptyForm();
        }
    })
}

// 获得下一条待审批的修改信息
function getNextModifyInfo() {
    // alert("获得下一条待审批的修改信息");

    $.ajax({

        async:false,
        type:"GET",
        url: "http://localhost:8080/admin/getNextModifyInfo",
        data:{
            id: localStorage.infoId
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            // alert("result: "+JSON.stringify(data));
            // console.log("result: "+JSON.stringify(data));

            var obj = JSON.parse(JSON.stringify(data));
            fillForm(obj);
        },
        error: function(error){
            alert("没有需要审批的信息");
            emptyForm();
        }
    })
}

// 填表
function fillForm(obj) {

    // console.log("obj: "+JSON.stringify(obj));

    // alert("填表");

    var idEle = document.getElementById("canteenId");
    idEle.innerText = "餐厅编号："+obj.canteenId;

    var tbody = document.getElementById("info");
    tbody.innerHTML = "";

    var nameTr = tbody.insertRow(0);
    var td1 = nameTr.insertCell(0);
    td1.innerText = "餐厅名";
    var preTd = nameTr.insertCell(1);
    preTd.innerText = obj.preName;
    var td = nameTr.insertCell(2);
    td.innerText = obj.name;

    var nameTr = tbody.insertRow(1);
    var td1 = nameTr.insertCell(0);
    td1.innerText = "类型";
    var preTd = nameTr.insertCell(1);
    preTd.innerText = obj.preType;
    var td = nameTr.insertCell(2);
    td.innerText = obj.type;

    var nameTr = tbody.insertRow(2);
    var td1 = nameTr.insertCell(0);
    td1.innerText = "街道";
    var preTd = nameTr.insertCell(1);
    preTd.innerText = obj.preDistrict;
    var td = nameTr.insertCell(2);
    td.innerText = obj.district;

    var nameTr = tbody.insertRow(3);
    var td1 = nameTr.insertCell(0);
    td1.innerText = "地址";
    var preTd = nameTr.insertCell(1);
    preTd.innerText = obj.preAddressDetail;
    var td = nameTr.insertCell(2);
    td.innerText = obj.addressDetail;

    var commentEle = document.getElementById("comment");
    commentEle.innerText = "";

    localStorage.infoId = obj.id;
}

// 清空表单
function emptyForm() {
    // alert("清空表单");

    var idEle = document.getElementById("canteenId");
    idEle.innerText = "餐厅编号：";

    var tbody = document.getElementById("info");
    tbody.innerHTML = "";

    var nameTr = tbody.insertRow(0);
    var td1 = nameTr.insertCell(0);
    td1.innerText = "餐厅名";
    var preTd = nameTr.insertCell(1);
    preTd.innerText = "";
    var td = nameTr.insertCell(2);
    td.innerText = "";

    var nameTr = tbody.insertRow(1);
    var td1 = nameTr.insertCell(0);
    td1.innerText = "类型";
    var preTd = nameTr.insertCell(1);
    preTd.innerText = "";
    var td = nameTr.insertCell(2);
    td.innerText = "";

    var nameTr = tbody.insertRow(2);
    var td1 = nameTr.insertCell(0);
    td1.innerText = "街道";
    var preTd = nameTr.insertCell(1);
    preTd.innerText = "";
    var td = nameTr.insertCell(2);
    td.innerText = "";

    var nameTr = tbody.insertRow(3);
    var td1 = nameTr.insertCell(0);
    td1.innerText = "地址";
    var preTd = nameTr.insertCell(1);
    preTd.innerText = "";
    var td = nameTr.insertCell(2);
    td.innerText = "";

    var commentEle = document.getElementById("comment");
    commentEle.value = "";

    localStorage.infoId = "";
}