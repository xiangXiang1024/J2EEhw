// 查看餐厅的新订单
function getNewOrders() {
    if(document.getElementById("historyOrders")) {
        var ele = document.getElementById("historyOrders");
        ele.innerHTML = "";
    }
    if(document.getElementById("newOrders")) {
        var ele = document.getElementById("newOrders");
        ele.innerHTML = "";
    }

    var canteenId = localStorage.userId;

    // alert("canteen:"+canteenId+"  state: "+"HASPAID");

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/canteen/seeOrder",
        data:{
            canteenId:canteenId,
            state:"HASPAID"
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            var result = JSON.stringify(data);
            var list = JSON.parse(result);

            // alert("HASPAID orders: "+JSON.stringify(list));

            if(list === null || list === "" || list.length === 0) {  // 无
            }else {
                var htmlInfo = "";

                for (var i=0 ; i<list.length ; i++) {
                    var order = list[i];

                    var orderId = order.orderId;
                    var goodsNames = order.goodsNames;
                    var goodsNums = order.goodsNums;

                    // alert("goodsNames： "+JSON.stringify(goodsNames))
                    // alert("goodsNums： "+JSON.stringify(goodsNums))

                    var price = order.price.toFixed(2);
                    var address = order.address;
                    var note = order.note;

                    var goodsStr = "";
                    var numStr = "";

                    // alert("goodsNames.length: "+goodsNames.length)

                    for(var j=0 ; j<goodsNames.length ; j++) {
                        var name = goodsNames[j];
                        var num = goodsNums[j];

                        if(j>0) {
                            goodsStr = goodsStr + "<br />";
                            numStr = numStr + "<br />";
                        }

                        goodsStr = goodsStr + name;
                        numStr = numStr + num;
                        // alert("j: "+j)
                        // alert("name: "+name)
                        // alert("num: "+num)
                        // alert("goodsStr1: "+goodsStr)
                        // alert("numStr1: "+numStr)
                    }

                    // alert("goodsStr2: "+goodsStr)
                    // alert("numStr2: "+numStr)

                    if(note === null || note === undefined) {
                        note = "无";
                    }

                    htmlInfo = htmlInfo+
                        "<tr>"+
                        "<td>"+orderId+"</td>\n" +
                        "<td>"+goodsStr+"</td>\n" +
                        "<td>"+numStr+"</td>\n" +
                        "<td>"+price+"元</td>\n" +
                        "<td>"+address+"</td>\n" +
                        "<td>"+note+"</td>\n" +
                        "<td>" +
                            "<a onclick=\"acceptOrder('"+orderId+"')\">接单</a>"+
                        "</td>"+
                        "</tr>";
                }

                var element = document.getElementById("newOrders");
                element.innerHTML = htmlInfo;
            }
        },
        error: function(){
            alert("error");
        }
    });

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/canteen/seeOrder",
        data:{
            canteenId:canteenId,
            state:"HASACCEPT"
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            var result = JSON.stringify(data);
            var list = JSON.parse(result);

            // alert("待发货的订单： "+JSON.stringify(list));

            if(list === null || list === "" || list.length === 0) {  // 无
            }else {
                var element = document.getElementById("newOrders");
                var htmlInfo = element.innerHTML;

                for (var i=0 ; i<list.length ; i++) {
                    var order = list[i];

                    var orderId = order.orderId;
                    var goodsNames = order.goodsNames;
                    var goodsNums = order.goodsNums;
                    var price = order.price.toFixed(2);
                    var address = order.address;
                    var note = order.note;

                    var goodsStr = "";
                    var numStr = "";

                    for(var j=0 ; j<goodsNames.length ; j++) {
                        var name = goodsNames[j];
                        var num = goodsNums[j];

                        if(j>0) {
                            goodsStr = goodsStr + "<br />";
                            numStr = numStr + "<br />";
                        }

                        goodsStr = goodsStr + name;
                        numStr = numStr + num;
                    }

                    if(note === null || note === undefined) {
                        note = "无";
                    }

                    htmlInfo = htmlInfo+
                        "<tr>"+
                        "<td>"+orderId+"</td>\n" +
                        "<td>"+goodsStr+"</td>\n" +
                        "<td>"+numStr+"</td>\n" +
                        "<td>"+price+"元</td>\n" +
                        "<td>"+address+"</td>\n" +
                        "<td>"+note+"</td>\n" +
                        "<td>" +
                        "<a href=\"#\" onclick=\"deliverOrder('"+orderId+"')\">发货</a>"+
                        "</td>"+
                        "</tr>";
                }

                var element = document.getElementById("newOrders");
                element.innerHTML = htmlInfo;
            }
        },
        error: function(){
            alert("error");
        }
    });

    var element = document.getElementById("newOrders");
    if(element.innerHTML === "") {
        alert("暂无新的订单");
    }
}

// 查看餐厅的历史订单
function getHistoryOrders() {
    var ele = document.getElementById("newOrders");
    ele.innerHTML = "";

    var canteenId = localStorage.userId;
    var isEmpty = true;

    // alert("canteen:"+canteenId);

    var htmlInfo = "";

    /*$.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/canteen/seeOrder",
        data:{
            canteenId:canteenId,
            state:"HASACCEPT"
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            var result = JSON.stringify(data);
            var list = JSON.parse(result);

            if(list === null || list === "" || list.length === 0) {  // 无
            }else {
                isEmpty = false;

                for (var i=0 ; i<list.length ; i++) {
                    var order = list[i];

                    var orderId = order.orderId;
                    var goodsNames = order.goodsNames;
                    var goodsNums = order.goodsNums;
                    var price = order.price.toFixed(2);
                    var address = order.address;
                    var note = order.note;
                    var state = order.state;

                    var goodsStr = "";
                    var numStr = "";

                    for(var j=0 ; j<goodsNames.length ; j++) {
                        var name = goodsNames[i];
                        var num = goodsNums[i];

                        if(j>0) {
                            goodsStr = goodsStr + "<br />";
                            numStr = numStr + "<br />";
                        }

                        goodsStr = goodsStr + name;
                        numStr = numStr + num;
                    }

                    if(note === null || note === undefined) {
                        note = "无";
                    }

                    htmlInfo = htmlInfo+
                        "<tr>"+
                        "<td>"+orderId+"</td>\n" +
                        "<td>"+goodsStr+"</td>\n" +
                        "<td>"+numStr+"</td>\n" +
                        "<td>"+price+"元</td>\n" +
                        "<td>"+address+"</td>\n" +
                        "<td>"+note+"</td>\n" +
                        "<td>"+state+"</td>"+
                        "</tr>";
                }


            }
        },
        error: function(){
            alert("error");
        }
    });*/

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/canteen/seeOrder",
        data:{
            canteenId:canteenId,
            state:"HASDELIVERED"
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            var result = JSON.stringify(data);
            var list = JSON.parse(result);

            if(list === null || list === "" || list.length === 0) {  // 无
            }else {
                isEmpty = false;

                for (var i=0 ; i<list.length ; i++) {
                    var order = list[i];

                    var orderId = order.orderId;
                    var goodsNames = order.goodsNames;
                    var goodsNums = order.goodsNums;
                    var price = order.price.toFixed(2);
                    var address = order.address;
                    var note = order.note;
                    var state = order.state;

                    var goodsStr = "";
                    var numStr = "";

                    for(var j=0 ; j<goodsNames.length ; j++) {
                        var name = goodsNames[i];
                        var num = goodsNums[i];

                        if(j>0) {
                            goodsStr = goodsStr + "<br />";
                            numStr = numStr + "<br />";
                        }

                        goodsStr = goodsStr + name;
                        numStr = numStr + num;
                    }

                    if(note === null || note === undefined) {
                        note = "无";
                    }

                    htmlInfo = htmlInfo+
                        "<tr>"+
                        "<td>"+orderId+"</td>\n" +
                        "<td>"+goodsStr+"</td>\n" +
                        "<td>"+numStr+"</td>\n" +
                        "<td>"+price+"元</td>\n" +
                        "<td>"+address+"</td>\n" +
                        "<td>"+note+"</td>\n" +
                        "<td>"+state+"</td>"+
                        "</tr>";
                }
            }
        },
        error: function(){
            alert("error");
        }
    });

    if(isEmpty === true) {
        alert("没有历史订单");
    }else {
        var element = document.getElementById("historyOrders");
        element.innerHTML = htmlInfo;
    }
}

// 餐厅接单
function acceptOrder(orderId) {
    // alert("order:"+orderId);

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/order/acceptOrder",
        data:{
            orderId:orderId
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            var result = JSON.parse(JSON.stringify(data));

            if(result === true) {
                alert("接单成功");
            }else {
                alert("接单失败");
            }
        },
        error: function(){
            alert("error");
        }
    });

    getNewOrders();
}

// 商家发货
function deliverOrder(orderId) {
    // alert("商家发货 "+orderId)

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/order/deliverOrder",
        data:{
            orderId:orderId
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            var result = JSON.parse(JSON.stringify(data));

            if(result === true) {
                alert("发货成功");
            }else {
                alert("发货失败");
            }
        },
        error: function(){
            alert("error");
        }
    });

    getNewOrders();
}