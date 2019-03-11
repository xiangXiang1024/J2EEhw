// 得到所有的餐厅概要信息
function getCanteens() {
    document.getElementById("canteenList").innerHTML = "";

    // alert("得到所有的餐厅概要信息");

    $.ajax({

        async:false,
        type:"GET",
        url: "http://localhost:8080/user/getAllCanteens",
        data:"",
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success:function (data){
            // alert("result: "+JSON.stringify(data));

            var list = JSON.parse(JSON.stringify(data));
            // alert("list: "+list.length+"  "+list)

            var tbody = document.getElementById("canteenList");
            for(var i = 0 ; i < list.length ; i++) {
                var obj = list[i];

                var td = tbody.insertRow(i);

                var nameTd = td.insertCell(0);
                nameTd.innerText = obj.name;

                var typeTd = td.insertCell(1);
                typeTd.innerText = obj.type;

                var districtTd = td.insertCell(2);
                districtTd.innerText = obj.district;

                var addressTd = td.insertCell(3);
                addressTd.innerText = obj.address;

                var optionTd = td.insertCell(4);
                optionTd.innerHTML = "<a class='btn btn-default' onclick=\"seeCanteen('"+obj.id+"')\">进入餐厅</a>";
            }
        },
        error: function(error){
            alert("error");
        }
    })
}

// 查看餐厅详情
function seeCanteen(canteenId) {
    // alert("查看餐厅详情 id: "+canteenId);

    localStorage.canteen = canteenId;

    window.location.href = "/seeCanteen.html";
}