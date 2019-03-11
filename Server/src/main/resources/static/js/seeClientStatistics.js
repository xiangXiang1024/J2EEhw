// 查询会员的所有订单
function getStatistics() {
    // alert("查询会员的所有订单");

    var element = document.getElementById("mainBody");
    element.innerHTML = "";

    var client = localStorage.userId;
    var start = document.getElementById("start").value;
    var end = document.getElementById("end").value;
    var min = document.getElementById("minMoney").value;
    var max = document.getElementById("maxMoney").value;
    var canteen = document.getElementById("canteen").value;

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
        url: "http://localhost:8080/client/seeStatistics",
        data:{
            client:client,
            start:start,
            end:end,
            min:min,
            max:max,
            canteen:canteen
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

                // alert("result: "+result);

                var tbody = document.getElementById("mainBody");
                for(var i = 0 ; i < list.length ; i++) {
                    var obj = list[i];
                    var td = tbody.insertRow(i);

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
    });
}