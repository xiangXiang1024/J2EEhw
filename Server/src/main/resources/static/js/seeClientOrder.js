// 得到新的订单
function getNewOrders() {
    // alert("得到新的订单");

    if(document.getElementById("historyOrders")) {
        var ele1 = document.getElementById("historyOrders");
        ele1.innerHTML = "";
    }
    if(document.getElementById("newOrders")) {
        var ele2 = document.getElementById("newOrders");
        ele2.innerHTML = "";
    }

    var clientName = localStorage.userId;
    var state = "WAITINGFORORDER";

    // alert("canteen:"+canteenId+"  state:"+state);

    var noNewOrder = true;

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/client/seeOrdersByState",
        data:{
            clientName:clientName,
            state:state
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data) {
            var result = JSON.stringify(data);
            var list = JSON.parse(result);

            // alert("result: " + JSON.stringify(list));

            if (list === null || list === "" || list.length === 0) {  // 无
                /*var element = document.getElementById("newOrders");
                element.innerHTML = "";
                alert("没有新的订单");*/
            } else {
                noNewOrder = false;
                var tbody = document.getElementById("newOrders");
                for(var i = 0 ; i < list.length ; i++) {
                    var obj = list[i];

                    var td = tbody.insertRow(tbody.rows.length);
                    var idTd = td.insertCell(0);
                    idTd.innerText = obj.orderId;
                    var canteenTd = td.insertCell(1);
                    canteenTd.innerText = obj.canteen;
                    var goodsTd = td.insertCell(2);
                    var goodsList = obj.goodsNames;
                    var goodsStr = "";
                    for(var j = 0 ; j < goodsList.length ; j++) {
                        if(j > 0) {
                            goodsStr = goodsStr + "<br />";
                        }
                        goodsStr = goodsStr + goodsList[j];
                    }
                    goodsTd.innerHTML = goodsStr;

                    var numTd = td.insertCell(3);
                    var numsList = obj.goodsNums;
                    var numsStr = "";
                    for(var j = 0 ; j < numsList.length ; j++) {
                        if(j > 0) {
                            numsStr = numsStr + "<br />";
                        }
                        numsStr = numsStr + numsList[j];
                    }
                    numTd.innerHTML = numsStr;
                    var moneyTd = td.insertCell(4);
                    moneyTd.innerText = obj.finalPrice+"元";
                    var addressTd = td.insertCell(5);
                    addressTd.innerText = obj.address;
                    var noteTd = td.insertCell(6);
                    noteTd.innerText = obj.note;
                    var stateTd = td.insertCell(7);
                    stateTd.innerText = obj.state;
                    var optionTd = td.insertCell(8);
                    optionTd.innerHTML = "<a class='btn btn-default' onclick=\"payOrder('"+obj.orderId+"')\">支付订单</a>";
                }
            }
        },
        error: function(){
            alert("error");
        }
    });

    state = "HASPAID";
    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/client/seeOrdersByState",
        data:{
            clientName:clientName,
            state:state
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data) {
            var result = JSON.stringify(data);
            var list = JSON.parse(result);

            // alert("result: " + JSON.stringify(list));

            if (list === null || list === "" || list.length === 0) {  // 无
                /*var element = document.getElementById("newOrders");
                element.innerHTML = "";
                alert("没有新的订单");*/
            } else {
                noNewOrder = false;
                var tbody = document.getElementById("newOrders");
                for(var i = 0 ; i < list.length ; i++) {
                    var obj = list[i];

                    var td = tbody.insertRow(tbody.rows.length);
                    var idTd = td.insertCell(0);
                    idTd.innerText = obj.orderId;
                    var canteenTd = td.insertCell(1);
                    canteenTd.innerHTML = obj.canteen;

                    var goodsTd = td.insertCell(2);
                    var goodsList = obj.goodsNames;
                    var goodsStr = "";
                    for(var j = 0 ; j < goodsList.length ; j++) {
                        if(j > 0) {
                            goodsStr = goodsStr + "<br />";
                        }
                        goodsStr = goodsStr + goodsList[j];
                    }
                    goodsTd.innerHTML = goodsStr;

                    var numTd = td.insertCell(3);
                    var numsList = obj.goodsNums;
                    var numsStr = "";
                    for(var j = 0 ; j < numsList.length ; j++) {
                        if(j > 0) {
                            numsStr = numsStr + "<br />";
                        }
                        numsStr = numsStr + numsList[j];
                    }
                    numTd.innerHTML = numsStr;

                    var moneyTd = td.insertCell(4);
                    moneyTd.innerText = obj.finalPrice+"元";
                    var addressTd = td.insertCell(5);
                    addressTd.innerText = obj.address;
                    var noteTd = td.insertCell(6);
                    noteTd.innerText = obj.note;
                    var stateTd = td.insertCell(7);
                    stateTd.innerText = obj.state;
                    var optionTd = td.insertCell(8);
                    optionTd.innerHTML = "<a class='btn btn-default' onclick=\"cancelOrder('"+obj.orderId+"')\">取消订单</a>";
                }
            }
        },
        error: function(){
            alert("error");
        }
    });

    state = "HASACCEPT";
    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/client/seeOrdersByState",
        data:{
            clientName:clientName,
            state:state
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data) {
            var result = JSON.stringify(data);
            var list = JSON.parse(result);

            // alert("result: " + JSON.stringify(list));

            if (list === null || list === "" || list.length === 0) {  // 无
                /*var element = document.getElementById("newOrders");
                element.innerHTML = "";
                alert("没有新的订单");*/
            } else {
                noNewOrder = false;
                var tbody = document.getElementById("newOrders");
                for(var i = 0 ; i < list.length ; i++) {
                    var obj = list[i];

                    var td = tbody.insertRow(tbody.rows.length);
                    var idTd = td.insertCell(0);
                    idTd.innerText = obj.orderId;
                    var canteenTd = td.insertCell(1);
                    canteenTd.innerHTML = obj.canteen;

                    var goodsTd = td.insertCell(2);
                    var goodsList = obj.goodsNames;
                    var goodsStr = "";
                    for(var j = 0 ; j < goodsList.length ; j++) {
                        if(j > 0) {
                            goodsStr = goodsStr + "<br />";
                        }
                        goodsStr = goodsStr + (goodsList[j]+"");
                    }
                    goodsTd.innerHTML = goodsStr;

                    var numTd = td.insertCell(3);
                    var numsList = obj.goodsNums;
                    var numsStr = "";
                    for(var j = 0 ; j < numsList.length ; j++) {
                        if(j > 0) {
                            numsStr = numsStr + "<br />";
                        }
                        numsStr = numsStr  + numsList[j];
                    }
                    numTd.innerHTML = numsStr;

                    var moneyTd = td.insertCell(4);
                    moneyTd.innerText = obj.finalPrice+"元";
                    var addressTd = td.insertCell(5);
                    addressTd.innerText = obj.address;
                    var noteTd = td.insertCell(6);
                    noteTd.innerText = obj.note;
                    var stateTd = td.insertCell(7);
                    stateTd.innerText = obj.state;
                    var optionTd = td.insertCell(8);
                    optionTd.innerHTML = "<a class='btn btn-default' onclick=\"cancelOrder('"+obj.orderId+"')\">取消订单</a>";
                }
            }
        },
        error: function(){
            alert("error");
        }
    });

    state = "HASDELIVERED";
    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/client/seeOrdersByState",
        data:{
            clientName:clientName,
            state:state
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data) {
            var result = JSON.stringify(data);
            var list = JSON.parse(result);

            // alert("result: " + JSON.stringify(list));

            if (list === null || list === "" || list.length === 0) {  // 无
                /*var element = document.getElementById("newOrders");
                element.innerHTML = "";
                alert("没有新的订单");*/
            } else {
                noNewOrder = false;
                var tbody = document.getElementById("newOrders");
                for(var i = 0 ; i < list.length ; i++) {
                    var obj = list[i];

                    var td = tbody.insertRow(tbody.rows.length);
                    var idTd = td.insertCell(0);
                    idTd.innerText = obj.orderId;
                    var canteenTd = td.insertCell(1);
                    canteenTd.innerHTML = obj.canteen;

                    var goodsTd = td.insertCell(2);
                    var goodsList = obj.goodsNames;
                    var goodsStr = "";
                    for(var j = 0 ; j < goodsList.length ; j++) {
                        if(j > 0) {
                            goodsStr = goodsStr + "<br />";
                        }
                        goodsStr = goodsStr + goodsList[j];
                    }
                    goodsTd.innerHTML = goodsStr;

                    var numTd = td.insertCell(3);
                    var numsList = obj.goodsNums;
                    var numsStr = "";
                    for(var j = 0 ; j < numsList.length ; j++) {
                        if(j > 0) {
                            numsStr = numsStr + "<br />";
                        }
                        numsStr = numsStr + numsList[j];
                    }
                    numTd.innerHTML = numsStr;

                    var moneyTd = td.insertCell(4);
                    moneyTd.innerText = obj.finalPrice+"元";
                    var addressTd = td.insertCell(5);
                    addressTd.innerText = obj.address;
                    var noteTd = td.insertCell(6);
                    noteTd.innerText = obj.note;
                    var stateTd = td.insertCell(7);
                    stateTd.innerText = obj.state;
                    var optionTd = td.insertCell(8);
                    optionTd.innerHTML = "<a class='btn btn-default' onclick=\"receiveOrder('"+obj.orderId+"')\">确认收货</a>";
                }
            }
        },
        error: function(){
            alert("error");
        }
    });

    if(noNewOrder === true) {
        alert("暂无新的订单");
    }
}

// 得到历史订单
function getHistoryOrders() {
    // alert("得到历史订单");

    if(document.getElementById("historyOrders")) {
        var ele1 = document.getElementById("historyOrders");
        ele1.innerHTML = "";
    }
    if(document.getElementById("newOrders")) {
        var ele2 = document.getElementById("newOrders");
        ele2.innerHTML = "";
    }


    var clientName = localStorage.userId;

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/client/seeHistoryOrders",
        data:{
            name:clientName
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data) {
            var result = JSON.stringify(data);
            var list = JSON.parse(result);

            // alert("result: " + JSON.stringify(list));

            if (list === null || list === "" || list.length === 0) {  // 无
                var element = document.getElementById("newOrders");
                element.innerHTML = "";
                alert("没有历史订单");
            } else {
                noNewOrder = false;
                var tbody = document.getElementById("historyOrders");
                for(var i = 0 ; i < list.length ; i++) {
                    var obj = list[i];

                    var td = tbody.insertRow(tbody.rows.length);
                    var idTd = td.insertCell(0);
                    idTd.innerText = obj.orderId;
                    var canteenTd = td.insertCell(1);
                    canteenTd.innerText = obj.canteen;
                    var goodsTd = td.insertCell(2);
                    var goodsList = obj.goodsNames;
                    var goodsStr = "";
                    for(var j = 0 ; j < goodsList.length ; j++) {
                        if(j > 0) {
                            goodsStr = goodsStr + "<br />";
                        }
                        goodsStr = goodsStr + goodsList[j];
                    }
                    goodsTd.innerHTML = goodsStr;

                    var numTd = td.insertCell(3);
                    var numsList = obj.goodsNums;
                    var numsStr = "";
                    for(var j = 0 ; j < numsList.length ; j++) {
                        if(j > 0) {
                            numsStr = numsStr + "<br />";
                        }
                        numsStr = numsStr + numsList[j];
                    }
                    numTd.innerHTML = numsStr;
                    var moneyTd = td.insertCell(4);
                    moneyTd.innerText = obj.finalPrice;
                    var addressTd = td.insertCell(5);
                    addressTd.innerText = obj.address;
                    var noteTd = td.insertCell(6);
                    noteTd.innerText = obj.note;
                    var stateTd = td.insertCell(7);
                    stateTd.innerText = obj.state;
                }
            }
        },
        error: function(){
            alert("error");
        }
    })


}

// 支付订单
function payOrder(orderId) {
    // alert("支付订单： "+orderId);

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/order/payOrder",
        data:{
            orderId:orderId
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data) {
            var result = JSON.parse(JSON.stringify(data));

            // alert("result: " + result);

            if(result === true) {
                alert("支付成功");
            }else {
                alert("支付失败");
            }
        },
        error: function(){
            alert("error");
        }
    });

    getNewOrders();
}

// 取消订单
function cancelOrder(orderId) {
    // alert("取消订单： "+orderId);

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/order/cancelOrder",
        data:{
            orderId:orderId
        },
        dataType: "text",
        contentType: "application/x-www-form-urlencoded",
        success:function (data) {
            //var result = JSON.parse(JSON.stringify(data));

            // alert("result: " + result);

            alert(data);
        },
        error: function(){
            alert("error");
        }
    });

    getNewOrders();
}

// 确认收货
function receiveOrder(orderId) {
    // alert("确认收货： "+orderId);

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/order/receiveOrder",
        data:{
            orderId:orderId
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data) {
            var result = JSON.parse(JSON.stringify(data));

            // alert("result: " + result);

            if(result === true) {
                alert("确认收货成功");
            }else {
                alert("操作失败");
            }
        },
        error: function(){
            alert("error");
        }
    });

    getNewOrders();
}