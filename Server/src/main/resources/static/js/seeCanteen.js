// 确认订单
function confirmOrder() {
    // alert("确认订单");

    var vo = JSON.parse(localStorage.order);

    // console.log(localStorage.order);
    // console.log("goodsIdList:"+JSON.stringify(vo.goodsIdList))

    var result = vo.result;

    if(result === "下单成功") {

        $.ajax({
            async:false,
            type:"POST",
            url: "http://localhost:8080/order/addOrder",
            traditional: true,
            data:{
                canteenId: vo.canteenId,
                goodsIdList: vo.goodsIdList,
                goodsNumList: vo.goodsNumList,
                shippingFee: vo.shippingFee,
                packagingFee: vo.packagingFee,
                offerId: vo.offerId,
                sum: vo.sum,
                initialPrice: vo.initialPrice,
                finalPrice: vo.finalPrice,
                note: vo.note,
                addressId: vo.addressId,
                client: localStorage.userId
            },
            dataType: "text",
            contentType: "application/x-www-form-urlencoded",
            success:function (data){
                // alert("result: "+JSON.stringify(data));
                var orderId = JSON.parse(JSON.stringify(data));
                //localStorage.orderId = orderId;

                /*setTimeout(function (orderId) {
                    alert("order "+orderId+" time out")
                }, 2*60*1000);*/

                //window.setTimeout(timeOutOrder, 4*1000, orderId);

                alert("下单成功，请在2分钟内支付");
                // alert("addOrder success");
                window.location.href = "/seeClientOrder.html";
                //sleep(2*60*1000);
               // timeOutOrder(orderId);
            },
            error: function(error){
                alert("error");
            }
        });
    }else {
        alert(result);
        // alert("in /addOrder")
    }
}

// 2min后检查订单是否支付
/*function timeOutOrder(orderId) {
    alert("timeOutOrder order: "+orderId);
    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/order/timeOutOrder",
        data:{
            orderId: orderId
        },
        dataType: "text",
        contentType: "application/x-www-form-urlencoded",
        success:function (data) {
            alert("订单"+orderId+"超时未支付，已取消");
        },
        error: function(error){
            alert("error");
        }
    });
}*/

// 提交订单
function submitOrder() {
    // alert("提交订单");

    var addressEle = document.getElementById("address");
    var index = addressEle.selectedIndex;
    var addressId = addressEle.options[index].value;

    var goodsNumList = [];
    var basketTable = document.getElementById("basketTable");
    for(var i = 0 ; i < basketTable.rows.length ; i++) {
        goodsNumList.push(parseInt(basketTable.rows[i].cells[1].innerText));
    }

    var ele = document.getElementById("myModal");
    ele.style.visibility="visible";

    // alert("goodsNumList:" +goodsNumList)

    $.ajax({
        async:false,
        type:"POST",
        url: "http://localhost:8080/order/confirmOrder",
        traditional: true,
        data:{
            clientName: localStorage.userId,
            canteenId: localStorage.canteen,
            goodsIdList: JSON.parse(localStorage.basket),
            goodsNumList: goodsNumList,
            offerId: localStorage.offer,
            addressId: addressId,
            note: document.getElementById("note").value
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            // alert("result: "+JSON.stringify(data));
            var vo = JSON.parse(JSON.stringify(data));

            var result = vo.result;
                // alert("填表")
                localStorage.order = JSON.stringify(vo);

                var canteenEle = document.getElementById("canteen");
                canteenEle.value = vo.canteenName;

                var goodsBody = document.getElementById("goodsBody");
                goodsBody.innerHTML = "";
                var goodsNameList = vo.goodsNameList;
                var goodsPriceList = vo.goodsPriceList;
                var goodsNumList = vo.goodsNumList;
                var len = goodsNumList.length;
                for(var i = 0 ; i < len ; i++) {
                    var tr = goodsBody.insertRow(i);

                    var nameTd = tr.insertCell(0);
                    nameTd.innerText = goodsNameList[i];

                    var priceTd = tr.insertCell(1);
                    priceTd.innerText = goodsPriceList[i].toFixed(2)+"元";

                    var numTd = tr.insertCell(2);
                    numTd.innerText = goodsNumList[i];
                }

                var packagingFeeEle = document.getElementById("packagingFee");
                packagingFeeEle.value = vo.packagingFee.toFixed(2);

                var shippingFeeEle = document.getElementById("shippingFee");
                shippingFeeEle.value = vo.shippingFee.toFixed(2);

                var canteenOfferEle = document.getElementById("canteen_offer");
                canteenOfferEle.value = vo.offerName;

                var clientOfferEle = document.getElementById("client_offer");
                var clientRanking = parseInt(vo.clientRanking);
                if(clientRanking === 0) {
                    clientOfferEle.value = "无";
                }else {
                    var discount = (clientRanking*0.02)*100;
                    clientOfferEle.value = discount+"%off会员优惠";
                }

                var finalPriceEle = document.getElementById("finalPrice");
                finalPriceEle.value = vo.finalPrice.toFixed(2);

                var note2Ele = document.getElementById("note2");
                note2Ele.value = vo.note;

                var address2Ele = document.getElementById("address2");
                address2Ele.value = vo.address;
        },
        error: function(error){
            alert("error");
        }
    })

}

// 选择优惠
function chooseOffer(eleId) {
    // alert("选择优惠");

    var ele = document.getElementById(eleId);
    var offer = localStorage.offer;
    // alert("ele.value: "+ele.value);

    if(ele.value === offer){    //如果点击的对象原来是选中的，取消选中
        // alert("点击的对象原来是选中的，取消选中");
        ele.checked = false;
        offer = null;
    } else{
        offer = ele.value;
        ele.checked = true;
    }

    localStorage.offer = offer;
}

// 得到商品概要信息
function getGoodsInfo() {
    // alert("得到商品概要信息");

    var canteen = localStorage.canteen;
    // alert("canteen: "+canteen);

    $.ajax({

        async:false,
        type:"GET",
        url: "http://localhost:8080/order/seeGooes",
        data:{
            canteen: canteen
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            // alert("result: "+JSON.stringify(data));

            var list = JSON.parse(JSON.stringify(data));
            // alert("list: "+list.length+"  "+list);


            var tbody = document.getElementById("goodsTable");
            tbody.innerHTML = "";


            for(var i = 0 ; i < list.length ; i++) {
                var obj = list[i];

                var td = tbody.insertRow(i);

                var nameTd = td.insertCell(0);
                nameTd.innerText = obj.name;

                var descriptionTd = td.insertCell(1);
                descriptionTd.innerText = obj.description;

                var priceTd = td.insertCell(2);
                priceTd.innerText = obj.price.toFixed(2);

                var goodsTypeTd = td.insertCell(3);
                goodsTypeTd.innerText = obj.goodsType;

                var quantityTd = td.insertCell(4);
                quantityTd.innerText = obj.quantity;

                var optionTd = td.insertCell(5);
                optionTd.innerHTML = "<a class='btn btn-default' onclick=\"addGoods('"+obj.id+"','"+obj.name+"','"+obj.price+"')\">添加商品</a>";


            }
        },
        error: function(error){
            alert("error");
        }
    })

}

// 添加商品到购物车
function addGoods(goodId, goodName, goodPrice) {
    // alert("添加商品到购物车: "+goodId+", "+goodName+", "+goodPrice);
    var basket = JSON.parse(localStorage.basket);
    for(var i = 0 ; i < basket.length ; i++) {
        if(basket[i] === goodId) {
            plusGoodNum(i);
            return;
        }
    }

    var tbody = document.getElementById("basketTable");

    var td = tbody.insertRow(tbody.rows.length);

    var nameTd = td.insertCell(0);
    nameTd.innerText = goodName;

    var numTd = td.insertCell(1);
    numTd.innerText = 1+"";

    var priceTd = td.insertCell(2);
    priceTd.innerText = parseFloat(goodPrice).toFixed(2)*parseInt(numTd.innerText)+"";



    var optionTd = td.insertCell(3);
    optionTd.innerHTML = "<a class='btn btn-default btn-xs glyphicon glyphicon-plus' onclick=\"plusGoodNum('"+(tbody.rows.length-1)+"')\"></a>"
                            + "<a class='btn btn-default btn-xs glyphicon glyphicon-minus' style='margin-left: 5%' onclick=\"minusGoodNum('"+(tbody.rows.length-1)+"')\"></a>"
                            +"<a class='btn btn-default btn-xs glyphicon glyphicon-remove' style='margin-left: 5%' onclick=\"removeGoods('"+(tbody.rows.length-1)+"')\"></a>";

    var basketGoods = JSON.parse(localStorage.basket);
    basketGoods.push(goodId);
    localStorage.basket = JSON.stringify(basketGoods);

}

// 购物车商品数量+1
function plusGoodNum(id) {
    // alert("购物车商品数量+1 id："+id)
    var tbody = document.getElementById("basketTable");
    var td = tbody.rows[id];

    var num = parseInt(td.cells[1].innerText);
    var price = parseFloat(td.cells[2].innerText) / num;

    td.cells[1].innerText = num+1+"";
    td.cells[2].innerText = price*(num+1)+"";

}

// 购物车商品数量-1
function minusGoodNum(id) {
    // alert("购物车商品数量+1 id："+id)
    var tbody = document.getElementById("basketTable");
    var td = tbody.rows[id];

    var num = parseInt(td.cells[1].innerText);

    if(num === 1) {
        removeGoods(id);
        return;
    }

    var price = parseFloat(td.cells[2].innerText) / num;

    td.cells[1].innerText = num-1+"";
    td.cells[2].innerText = price*(num-1)+"";
}

// 移除购物车商品
function removeGoods(id) {
    id = parseInt(id);
    // alert("移除购物车商品 id："+id);

    var list = [];
    var tbody = document.getElementById("basketTable");
    // alert("tbody.rows.length: "+tbody.rows.length)

    if(tbody.rows.length === 1) {
        tbody.innerHTML = "";
        alert("购物车为空");
        localStorage.basket = JSON.stringify([]);
        return;
    }

    for(var i = 0 ; i < id ; i++) {
        var td = tbody.rows[i];
        var obj = [];
        for(var j = 0 ; j < td.cells.length ; j++) {
            obj.push(td.cells[j].innerText);
        }
        list.push(obj);
    }
    // alert("list: "+JSON.stringify(list))

    for(var i = id+1 ; i < tbody.rows.length ; i++) {
        var td = tbody.rows[i];
        var obj = [];
        for(var j = 0 ; j < td.cells.length ; j++) {
            obj.push(td.cells[j].innerText);
        }
        list.push(obj);
    }
    // alert("list: "+JSON.stringify(list))

    tbody.innerHTML = "";

    for(var i = 0 ; i < list.length ; i++) {
        var obj = list[i];
        var td = tbody.insertRow(i);
        for(var j = 0 ; j < obj.length-1 ; j++) {
            var o = td.insertCell(j);
            o.innerText = obj[j];
        }
        var o = td.insertCell(obj.length-1);
        o.innerHTML = "<a class='btn btn-default btn-xs glyphicon glyphicon-plus' onclick=\"plusGoodNum('"+i+"')\"></a>"
            +"<a class='btn btn-default btn-xs glyphicon glyphicon-minus' style='margin-left: 5%' onclick=\"minusGoodNum('"+i+"')\"></a>"
            +"<a class='btn btn-default btn-xs glyphicon glyphicon-remove' style='margin-left: 5%' onclick=\"removeGoods('"+i+"')\"></a>";
    }

    var basket = JSON.parse(localStorage.basket);
    basket.splice(id, 1);
    // alert("basket: "+JSON.stringify(basket));
    localStorage.basket = JSON.stringify(basket);
}

// TODO 获得地址
function getAddressList() {
    // alert("获得地址");
    // alert("user： "+localStorage.userId);

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/client/getAddress",
        data:{
            client: localStorage.userId
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            // alert("result: "+JSON.stringify(data));

            var list = JSON.parse(JSON.stringify(data));
            // alert("list: "+list.length+"  "+list);

            if(list.length === 0) {
                // alert("您尚未设置送餐地址，请前往设置");
                window.location.href("/seeClientInfo.html");
                return;
            }

            var ele = document.getElementById("address");
            for(var i = 0 ; i < list.length ; i++) {
               var address = list[i];
               ele.options.add(new Option(address.district+" "+address.detail, address.id));
            }
        },
        error: function(error){
            alert("error");
        }
    })

}

// 获得优惠信息
function getOfferList() {
    // alert("获得优惠信息");
    // alert("localStorage.canteen: "+localStorage.canteen)

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/canteen/getOfferInfos",
        data:{
            canteenId: localStorage.canteen
        },
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            // alert("result: "+JSON.stringify(data));

            var list = JSON.parse(JSON.stringify(data));

            var ele = document.getElementById("offer");

            for(var i = 0 ; i < list.length ; i++) {
                var obj = list[i];

                var id = obj.id;
                var description = obj.description;

                var input = document.createElement("input");
                input.type = "radio";
                input.name = "offerChoice";
                input.value = id;
                input.id = id;
                input.onclick =  function() {
                    chooseOffer(id);
                };
                ele.appendChild(input);
                ele.append(description);

            }

        },
        error: function(error){
            alert(error);
        }
    });
}