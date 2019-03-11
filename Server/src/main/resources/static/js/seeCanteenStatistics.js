// 根据条件查询订单
function getStatistics() {
    // alert("getStatistics");
    var element = document.getElementById("mainBody");
    element.innerHTML = "";

    var canteenId = localStorage.userId;
    var start = document.getElementById("start").value;
    var end = document.getElementById("end").value;
    var min = document.getElementById("minMoney").value;
    var max = document.getElementById("maxMoney").value;
    var client = document.getElementById("client").value;

    /*console.log("canteenId: "+canteenId+"\n"
                +"start: "+start+"\n"
                +"end: "+end+"\n"
                +"min: "+min+"\n"
                +"max: "+max+"\n"
                +"client: "+client+"\n"
    );*/

    $.ajax({
        async:false,
        type:"GET",
        url: "http://localhost:8080/canteen/seeStatistics",
        data:{
            canteenId:canteenId,
            start:start,
            end:end,
            min:min,
            max:max,
            client:client
        },
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        //contentType: "application/x-www-form-urlencoded",
        success:function (data){
            // alert("success");
            var result = JSON.stringify(data);
            var list = JSON.parse(result);

            if(list === null || list === "" || list.length === 0) {  // 无
                var element = document.getElementById("mainBody");
                element.innerHTML = "";
                alert("未查询到符合条件的订单");
            }else {
                // isEmpty = false;

                var htmlInfo = "";
                for (var i = 0; i < list.length; i++) {
                    var order = list[i];

                    var orderId = order.orderId;
                    var goodsNames = order.goodsNames;
                    var goodsNums = order.goodsNums;
                    var price = order.price.toFixed(2);
                    var address = order.address;
                    var note = order.note;
                    var state = order.state;
                    var client = order.client;

                    var goodsStr = "";
                    var numStr = "";

                    for (var j = 0; j < goodsNames.length; j++) {
                        var name = goodsNames[i];
                        var num = goodsNums[i];

                        if (j > 0) {
                            goodsStr = goodsStr + "<br />";
                            numStr = numStr + "<br />";
                        }

                        goodsStr = goodsStr + name;
                        numStr = numStr + num;
                    }

                    if (note === null || note === undefined) {
                        note = "无";
                    }

                    htmlInfo = htmlInfo +
                        "<tr>" +
                        "<td>" + orderId + "</td>\n" +
                        "<td>" + client + "</td>\n" +
                        "<td>" + goodsStr + "</td>\n" +
                        "<td>" + numStr + "</td>\n" +
                        "<td>" + price + "元</td>\n" +
                        "<td>" + address + "</td>\n" +
                        "<td>" + note + "</td>\n" +
                        "<td>" + state + "</td>" +
                        "</tr>";
                }

                var element = document.getElementById("mainBody");
                element.innerHTML = htmlInfo;

            }
        },
        error: function(){
            alert("error");
        }
    });
}