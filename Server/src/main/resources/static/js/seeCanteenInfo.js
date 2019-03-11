// 得到并展示餐厅信息
function getCanteenInfo() {
    var canteenId = localStorage.userId;

    // alert("canteenId: "+canteenId);

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/canteen/getCanteenInfo",
        data:{
            canteenId:canteenId
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            var result = JSON.parse(JSON.stringify(data));

            // alert("result: "+JSON.stringify(result));

            var name = result.name;
            var district = result.district;
            var address = result.address;
            var shippingFee = result.shippingFee;
            var packagingFee = result.packagingFee;
            var type = result.type;

            document.getElementById("canteenId").value = canteenId;
            document.getElementById("name").value = name;
            document.getElementById("district").value = district;
            document.getElementById("address").value = address;
            document.getElementById("shippingFee").value = shippingFee.toFixed(2);
            document.getElementById("packagingFee").value = packagingFee.toFixed(2);
            document.getElementById("type").value = type;

        },
        error: function(){
            alert("error");
        }
    })
}

// 修改餐厅信息
function modifyCanteenInfo() {
    // alert("修改餐厅信息");

    var info = {};
    info.id = localStorage.userId;
    info.name = document.getElementById("name").value;
    info.district = document.getElementById("district").value;
    info.address = document.getElementById("address").value;
    info.shippingFee = document.getElementById("shippingFee").value;
    info.packagingFee  = document.getElementById("packagingFee").value;
    info.type = document.getElementById("type").value;

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/user/modifyCanteenInfo",
        data:{
            id:info.id,
            name:info.name,
            district:info.district,
            address:info.address,
            shippingFee:info.shippingFee,
            packagingFee:info.packagingFee,
            type:info.type
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            var result = JSON.parse(JSON.stringify(data));

            if(result === true) {
                alert("提交成功，等待平台审核");
            }else {
                alert("提交失败");
            }

        },
        error: function(){
            alert("error");
        }
    });

    getCanteenInfo();
}