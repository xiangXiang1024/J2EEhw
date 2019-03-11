// 得到商品详细信息，并填表 commodity
function getGoodsDetailInfo() {
    // alert("getGoodsDetailInfo");

    var canteenId = localStorage.userId;
    $.ajax({
        async : false,
        type: "GET",
        url: "http://localhost:8080/canteen/getCommodityDetails",
        contentType :"application/x-www-form-urlencoded",
        data: {
            canteenId:canteenId
        },
        datatype: "json",
        success: function (data) {
            var result = JSON.parse(JSON.stringify(data));

            // alert("/canteen/getCommodityDetails result:\n"+JSON.stringify(data));

            var tbody = document.getElementById("commodityTable");
            for(var i = tbody.rows.length-1 ; i >= 0 ; i--) {
                tbody.deleteRow(i);
            }

            var len = result.length;
            for(var i = 0; i < len; i++){ //遍历一下json数据
                var obj = result[i];

                var tr = tbody.insertRow(tbody.rows.length);
                var nameTd = tr.insertCell(0);
                nameTd.innerHTML = obj.name;
                var typeTd = tr.insertCell(1);
                typeTd.innerHTML = obj.type;
                var priceTd = tr.insertCell(2);
                priceTd.innerHTML = obj.price;
                var quantityTd = tr.insertCell(3);
                quantityTd.innerHTML = obj.quantity;
                var startTd = tr.insertCell(4);
                startTd.innerHTML = obj.start;
                var endTd = tr.insertCell(5);
                endTd.innerHTML = obj.end;
            }
        },
        error: function(){
            alert("error");
        }

    });
}

// 得到商品概要信息，填到下拉选项中
function getGoodsInfo() {
    // alert("getGoodsInfo");

    var canteenId = localStorage.userId;
    $.ajax({
        async : false,
        type: "GET",
        url: "http://localhost:8080/canteen/getCanteenCommodities",
        contentType :"application/x-www-form-urlencoded",
        data: {
            canteenId:canteenId
        },
        datatype: "json",
        success: function (data) {
            var result = JSON.parse(JSON.stringify(data));

            // alert("/canteen/getCanteenCommodities result:\n"+JSON.stringify(data));

            // alert("删除select节点");
            var obj = document.getElementById("foodChoice2");
            while (obj.firstChild) {
                obj.removeChild(obj.firstChild); //移除已有的节点
            }

            var len = result.length;
            for(var i = 0 ; i < len ; i++) {
                var obj = result[i];
                var id = obj.id;
                var name = obj.name;
                $("#foodChoice2").append("<option value='"+id+"'>"+name+"</option>");
            }

            localStorage.goodsList = result;
        },
        error: function(){
            alert("error");
        }

    });
}

// 得到套餐详情
function getSetMeal() {
    // alert("getSetMeal");
    localStorage.goodsIdlist = "";
    localStorage.goodsNumlist = "";

    var canteenId = localStorage.userId;
    $.ajax({
        async : false,
        type: "GET",
        url: "http://localhost:8080/canteen/getSetMealDetails",
        contentType :"application/x-www-form-urlencoded",
        data: {
            canteenId:canteenId
        },
        datatype: "json",
        success: function (data) {
            var result = JSON.parse(JSON.stringify(data));

            // alert("/canteen/getSetMealDetails result:\n"+JSON.stringify(data));

            var tbody = document.getElementById("setmealtable");
            for(var i = tbody.rows.length-1 ; i >= 0 ; i--) {
                tbody.deleteRow(i);
            }

            var len = result.length;
            for(var i = 0; i < len; i++){ //遍历一下json数据
                var obj = result[i];

                var tr = tbody.insertRow(tbody.rows.length);
                // 套餐名
                var nameTd = tr.insertCell(0);
                nameTd.innerHTML = obj.name;
                // 商品
                var goodsStr = "";
                var goodsList = obj.goodsNames;
                for(var j = 0 ; j < goodsList.length ; j++) {
                    if(j > 0) {
                        goodsStr = goodsStr + "<br />";
                    }
                    var s = goodsList[j];
                    goodsStr = goodsStr + s;
                }
                var goodsTd = tr.insertCell(1);
                goodsTd.innerHTML = goodsStr;

                // 数量
                var numStr = "";
                var numList = obj.goodsNums;
                for(var j = 0 ; j < numList.length ; j++) {
                    if(j > 0) {
                        numStr = numStr + "<br />";
                    }
                    var s = numList[j];
                    numStr = numStr + s;
                }
                var numTd = tr.insertCell(2);
                numTd.innerHTML = numStr;
                // 金额
                var priceTd = tr.insertCell(3);
                priceTd.innerHTML = obj.price.toFixed(2)+"元";
                // 每日限量
                var quantityTd = tr.insertCell(4);
                quantityTd.innerHTML = obj.quantity;
                // 开始时间
                var startTd = tr.insertCell(5);
                startTd.innerHTML = obj.start;
                // 结束时间
                var endTd = tr.insertCell(6);
                endTd.innerHTML = obj.end;
            }
            /*for(var i = 0 ; i < len ; i++) {
                var obj = result[i];
                var id = obj.id;
                var name = obj.name;
                $("#foodChoice2").append("<option value='"+id+"'>"+name+"</option>");
            }*/

        },
        error: function(){
            alert("error");
        }

    });
}

// 添加商品
function addCommodity() {
    // alert("addCommodity")

    var canteenId = localStorage.userId;
    var name = document.getElementById("name").value;
    var price = document.getElementById("price").value;
    var type = document.getElementById("type").value;
    var description = document.getElementById("description").value;
    var quantity = document.getElementById("quantity").value;
    var start = document.getElementById("start").value;
    var end = document.getElementById("end").value;
/*
    alert("commodity:\n"+
        "   canteenId: "+canteenId+"\n"+
        "   name: "+name+"\n"+
        "   price: "+price+"\n"+
        "   type: "+type+"\n"+
        "   description: "+description+"\n"+
        "   quantity: "+quantity+"\n"+
        "   start: "+start+"\n"+
        "   end: "+end+"\n");*/

    var commodityVO = {
        canteenId:canteenId,
        name:name,
        price:price,
        type:type,
        description:description,
        quantity:quantity,
        start:start,
        end:end
    };

    $.ajax({
        async : false,
        type: "POST",
        url: "http://localhost:8080/canteen/addGoods",
        contentType :"application/json",
        data: JSON.stringify(commodityVO),
        datatype: "json",
        success: function (data) {
            var result = JSON.parse(JSON.stringify(data));

            if(result === "成功") {
                alert("商品添加成功");

                document.getElementById("name").value = "";
                document.getElementById("price").value = "";
                document.getElementById("type").value = "";
                document.getElementById("description").value = "";
                document.getElementById("quantity").value = "";
                document.getElementById("start").value = "";
                document.getElementById("end").value = "";

            }else {
                alert(result);
            }

        },
        error: function(){
            alert("error");
        }
    });
    getGoodsDetailInfo();

}

// 向套餐中添加商品
function addSetMealCommodity() {
    // alert("addSetMealCommodity");

    // console.log("localStorage.goodsIdlist: "+localStorage.goodsIdlist);
    // console.log("localStorage.goodsNumlist: "+localStorage.goodsNumlist);

    var goodsIdlist;
    if(localStorage.goodsIdlist === null || localStorage.goodsIdlist === undefined || localStorage.goodsIdlist === "") {
        goodsIdlist = [];
    }else {
        goodsIdlist = JSON.parse(localStorage.goodsIdlist);
    }
    var goodsNumlist;
    if(localStorage.goodsNumlist === null || localStorage.goodsNumlist === undefined || localStorage.goodsNumlist === "") {
        goodsNumlist = [];
    }else {
        goodsNumlist = JSON.parse(localStorage.goodsNumlist);
    }

    // console.log("type of goodsIdlist: "+typeof(goodsIdlist));
    // console.log("type of goodsNumlist: "+typeof(goodsNumlist));

    // alert("list: "+JSON.stringify(list));

    var obj = document.getElementById("foodChoice2"); //定位id
    var index = obj.selectedIndex; // 选中索引
    var name = obj.options[index].text; // 选中文本
    var id = obj.options[index].value; // 选中值

    // alert("index: "+index+"  name:"+name +"  id:"+id);

    var quantity = document.getElementById("num2").value;

    // 表格显示
    var tbody = document.getElementById("goods2");
    var tr = tbody.insertRow(tbody.rows.length);
    var nameTd = tr.insertCell(0);
    nameTd.innerHTML = name;
    var quantityTd = tr.insertCell(1);
    quantityTd.innerHTML = quantity;

    // 存储
    goodsIdlist.push(id);
    goodsNumlist.push(quantity);

    // alert("goodsIdlist: "+goodsIdlist+"\ngoodsNumlist: "+goodsNumlist);


    // alert("goods: "+JSON.stringify(goods));

    // alert("list.length = "+list.length);

    localStorage.goodsIdlist = JSON.stringify(goodsIdlist);
    localStorage.goodsNumlist = JSON.stringify(goodsNumlist);

    // console.log("localStorage.goodsIdlist: "+localStorage.goodsIdlist);
    // console.log("localStorage.goodsNumlist: "+localStorage.goodsNumlist);

    // alert(JSON.stringify(localStorage.commodityList));
}

// 添加套餐
function addSetMeal() {
    // alert("add set meal");

    var name = document.getElementById("name2").value;
    var description = document.getElementById("description2").value;
    var canteenId = localStorage.userId;
    var price = document.getElementById("price2").value;
    var quantity = document.getElementById("quantity2").value;
    var start = document.getElementById("start2").value;
    var end = document.getElementById("end2").value;
    var goodsIdlist = JSON.parse(localStorage.goodsIdlist);
    var goodsNumlist = JSON.parse(localStorage.goodsNumlist);

    /*var setMealsBriefVO = {
        name:name,
        description:description,
        canteenId:canteenId,
        goodsList:goodsList,
        price:price,
        quantity:quantity,
        start:start,
        end:end
    };*/

    //alert("setMealsBriefVO: \n"+JSON.stringify(setMealsBriefVO));
    //console.log("setMealsBriefVO: \n"+JSON.stringify(setMealsBriefVO));

    // console.log("{\n" +
    //     "            name:"+name+",\n" +
    //     "            description:"+description+",\n" +
    //     "            canteenId:"+canteenId+",\n" +
    //     "            price:"+price+",\n" +
    //     "            quantity:"+quantity+",\n" +
    //     "            start:"+start+",\n" +
    //     "            end:"+end+",\n" +
    //     "            goodsIdlist:"+JSON.stringify(goodsIdlist)+",\n" +
    //     "            goodsNumlist:"+JSON.stringify(goodsNumlist)+"\n" +
    //     "        }");

    $.ajax({
        traditional: true,
        async : false,
        type: "POST",
        url: "http://localhost:8080/canteen/addSetMeal",
        //contentType :"application/json;charset=UTF-8",
        contentType :"application/x-www-form-urlencoded",
        data: {
            name:name,
            description:description,
            canteenId:canteenId,
            price:price,
            quantity:quantity,
            start:start,
            end:end,
            goodsIdlist: goodsIdlist,
            goodsNumlist: goodsNumlist
        },
        dataType: "text",
        success: function (data) {
            // alert("success");
            // var result = JSON.parse(JSON.stringify(data));
            // alert("data: "+data);

            if(data === "成功") {
                alert("套餐添加成功");

                document.getElementById("name2").value = "";
                document.getElementById("price2").value = "";
                document.getElementById("description2").value = "";
                document.getElementById("quantity2").value = "";
                document.getElementById("start2").value = "";
                document.getElementById("end2").value = "";
            }else {
                alert(data);
            }

        },
        error: function(){
            alert("error");
        }
    });

    localStorage.goodsIdlist = "";
    localStorage.goodsNumlist = "";

    getSetMeal();
}

// 查询商家优惠信息
function getOffers() {
    // alert("查询商家优惠信息");

    var canteenId = localStorage.userId;
    $.ajax({
        async : false,
        type: "GET",
        url: "http://localhost:8080/canteen/getOfferInfos",
        contentType :"application/x-www-form-urlencoded",
        data: {
            canteenId:canteenId
        },
        datatype: "json",
        success: function (data) {
            var result = JSON.parse(JSON.stringify(data));

            // alert("/canteen/getOfferInfos result:\n"+JSON.stringify(data));

            var tbody = document.getElementById("offerTable");
            for(var i = tbody.rows.length-1 ; i >= 0 ; i--) {
                tbody.deleteRow(i);
            }

            var len = result.length;
            for(var i = 0; i < len; i++){ //遍历一下json数据
                var obj = result[i];

                var tr = tbody.insertRow(tbody.rows.length);
                // 描述
                var nameTd = tr.insertCell(0);
                nameTd.innerHTML = obj.description;
                // 开始时间
                var startTd = tr.insertCell(1);
                startTd.innerHTML = obj.start;
                // 结束时间
                var endTd = tr.insertCell(2);
                endTd.innerHTML = obj.end;
            }
        },
        error: function(){
            alert("error");
        }
    });
}

// 添加优惠
function addOffer() {
    // alert("添加优惠");

    var canteenId = localStorage.userId;
    var start = document.getElementById("start3").value;
    var end = document.getElementById("end3").value;
    var base = document.getElementById("base3").value;
    var discount = document.getElementById("discount3").value;


    $.ajax({
        async : false,
        type: "GET",
        url: "http://localhost:8080/canteen/addOffer",
        contentType :"application/x-www-form-urlencoded",
        data: {
            canteenId:canteenId,
            start:start,
            end:end,
            base:base,
            discount:discount
        },
        datatype: "json",
        success: function (data) {
            var result = JSON.parse(JSON.stringify(data));

            // alert("/canteen/addOffer result:\n"+JSON.stringify(data));

            if(result === "成功") {
                alert("优惠添加成功");
            }else {
                alert(result);
            }
        },
        error: function(){
            alert("error");
        }
    });

    getOffers();
}

// 判断是否可以发布信息
function canAddInfo() {
    //alert("判断是否可以发布信息");

    var canteen = localStorage.userId;
    $.ajax({
        async : false,
        type: "GET",
        url: "http://localhost:8080/canteen/canAddInfo",
        contentType :"application/x-www-form-urlencoded",
        data: {
            canteen:canteen
        },
        datatype: "text",
        success: function (data) {
            //alert("data: "+data);

            if(data === "false" || data === false) {
                alert("您的信息尚未通过审核，无法发布信息");
                window.location.href("/seeCanteenGoods.html");
            }
        },
        error: function(){
            alert("error");
        }

    });
}