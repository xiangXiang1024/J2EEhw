// 获得会员统计信息
function getMonthClientStatistics(month) {
    // alert("获得会员近 "+month+" 月统计信息")

    $.ajax({
        async : false,
        type: "GET",
        url: "http://localhost:8080/admin/getMonthClientStatistics",
        contentType :"application/x-www-form-urlencoded",
        data: {
            month: month
        },
        datatype: "json",
        success: function (data) {
            var result = JSON.parse(JSON.stringify(data));

            // alert("/admin/getMonthClientStatistics result:\n"+JSON.stringify(data));

            // 折线图 会员总数
            var idList = [];
            var dataList = [];
            for(var i = 0 ; i < result.length ; i++) {
                idList.push(result[i].id);
                dataList.push(parseInt(result[i].clientNum));
            }

            var myChart1 = echarts.init(document.getElementById("client_num_line"));
            var option1 = {
                color: ['#cd5c5c','#6495ed','#ff7f50','#ff6347','#87cefa','#32cd32',
                    '#ff69b4','#ba55d3','#ffa500','#40e0d0',
                    '#1e90ff','#7b68ee','#00fa9a','#ffd700',
                    '#6699FF','#ff6666','#3cb371','#b8860b','#30e0e0'],
                title: {
                    text: '会员数量'
                },
                tooltip : {
                    trigger: 'axis'
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : idList
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'会员数量',
                        type:'line',
                        stack: '总量',
                        data:dataList,
                        itemStyle: {
                            normal: {
                                color: '#27727B'
                            }
                        }
                    }
                ]
            };
            myChart1.setOption(option1);

            // 带时间轴的饼图 会员等级占比
            var month1 = result[0];
            var month2 = result[1];
            var month3 = result[2];
            var month4 = result[3];
            var month5 = result[4];
            var month6 = result[5];

            for(var i = 0 ; i < result.length ; i++) {
                data.push(result[i].client0Num);
                data.push(result[i].client1Num);
                data.push(result[i].client2Num);
                data.push(result[i].client3Num);
                data.push(result[i].client4Num);
                data.push(result[i].client5Num);
            }

            var myChart2 = echarts.init(document.getElementById("client_ranking_timepie"));
            var option2 = {
                timeline: {
                    axisType: 'category',
                    autoPlay: false,        //是否选择播放
                    playInterval: 1000,  //播放时间
                    data: idList,
                    label: {
                        formatter: function (s) {
                            return s;
                        }
                    }
                },
                options: [
                {
                    color: ['#cd5c5c','#6495ed','#7b68ee','#ff6347','#87cefa','#32cd32',
                        '#ff69b4','#ba55d3','#ffa500','#40e0d0',
                        '#1e90ff','#7b68ee','#00fa9a','#ffd700','#ff7f50',
                        '#6699FF','#ff6666','#3cb371','#b8860b','#30e0e0'],
                    title : {
                        text: '会员等级',
                    },
                    tooltip : {
                        trigger: 'item',
                        x: 'right',
                        y: 'center',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        data:['0级会员','1级会员','2级会员','3级会员','4级会员', '5级会员']
                    },
                    series : [
                        {
                            name:idList[0],
                            type:'pie',
                            center: ['50%', '45%'],
                            radius: '50%',
                            data:[
                                {value: month1.client0Num,  name:'0级会员'},
                                {value: month1.client1Num,  name:'1级会员'},
                                {value: month1.client2Num,  name:'2级会员'},
                                {value: month1.client3Num,  name:'3级会员'},
                                {value: month1.client4Num, name:'4级会员'},
                                {value: month1.client5Num, name:'5级会员'}
                            ]
                        }
                    ]
                },
                {
                    series : [
                        {
                            name:idList[1],
                            type:'pie',
                            center: ['50%', '45%'],
                            radius: '50%',
                            data:[
                                {value: month2.client0Num,  name:'0级会员'},
                                {value: month2.client1Num,  name:'1级会员'},
                                {value: month2.client2Num,  name:'2级会员'},
                                {value: month2.client3Num,  name:'3级会员'},
                                {value: month2.client4Num, name:'4级会员'},
                                {value: month2.client5Num, name:'5级会员'}
                            ]
                        }
                    ]
                },
                {
                    series : [
                        {
                            name:idList[2],
                            type:'pie',
                            center: ['50%', '45%'],
                            radius: '50%',
                            data:[
                                {value: month3.client0Num,  name:'0级会员'},
                                {value: month3.client1Num,  name:'1级会员'},
                                {value: month3.client2Num,  name:'2级会员'},
                                {value: month3.client3Num,  name:'3级会员'},
                                {value: month3.client4Num, name:'4级会员'},
                                {value: month3.client5Num, name:'5级会员'}
                            ]
                        }
                    ]
                },
                {
                    series : [
                        {
                            name:idList[3],
                            type:'pie',
                            center: ['50%', '45%'],
                            radius: '50%',
                            data:[
                                {value: month4.client0Num,  name:'0级会员'},
                                {value: month4.client1Num,  name:'1级会员'},
                                {value: month4.client2Num,  name:'2级会员'},
                                {value: month4.client3Num,  name:'3级会员'},
                                {value: month4.client4Num, name:'4级会员'},
                                {value: month4.client5Num, name:'5级会员'}
                            ]
                        }
                    ]
                },
                {
                    series : [
                        {
                            name:idList[4],
                            type:'pie',
                            center: ['50%', '45%'],
                            radius: '50%',
                            data:[
                                {value: month5.client0Num,  name:'0级会员'},
                                {value: month5.client1Num,  name:'1级会员'},
                                {value: month5.client2Num,  name:'2级会员'},
                                {value: month5.client3Num,  name:'3级会员'},
                                {value: month5.client4Num, name:'4级会员'},
                                {value: month5.client5Num, name:'5级会员'}
                            ]
                        }
                    ]
                },
                {
                    series : [
                        {
                            name:idList[5],
                            type:'pie',
                            center: ['50%', '45%'],
                            radius: '50%',
                            data:[
                                {value: month6.client0Num,  name:'0级会员'},
                                {value: month6.client1Num,  name:'1级会员'},
                                {value: month6.client2Num,  name:'2级会员'},
                                {value: month6.client3Num,  name:'3级会员'},
                                {value: month6.client4Num, name:'4级会员'},
                                {value: month6.client5Num, name:'5级会员'}
                            ]
                        }
                    ]
                }
                ]
            };
            myChart2.setOption(option2);

        },
        error: function(){
            alert("error");
        }

    });

}

// 获得餐厅统计信息
function getMonthCanteenStatistics(month) {
    // alert("获得餐厅近 "+month+" 月统计信息");

    $.ajax({
        async : false,
        type: "GET",
        url: "http://localhost:8080/admin/getMonthCanteenStatistics",
        contentType :"application/x-www-form-urlencoded",
        data: {
            month:'6'
        },
        datatype: "json",
        success: function (data) {
            var result = JSON.parse(JSON.stringify(data));

            // alert("/admin/getMonthCanteenStatistics result:\n"+JSON.stringify(data));

            var idList = [];
            var dataList = [];

            for(var i = 0 ; i < result.length ; i++) {
                idList.push(result[i].id);
                dataList.push(parseInt(result[i].canteenNum));
            }

            // console.log("x: "+JSON.stringify(idList));
            // console.log("data: "+JSON.stringify(dataList));

            var myChart = echarts.init(document.getElementById("canteen_num_line"));
            var option = {
                color: ['#ff7f50','#87cefa','#da70d6','#32cd32','#6495ed',
                    '#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0',
                    '#1e90ff','#ff6347','#7b68ee','#00fa9a','#ffd700',
                    '#6699FF','#ff6666','#3cb371','#b8860b','#30e0e0'],
                title: {
                    text: '餐厅数量'
                },
                tooltip : {
                    trigger: 'axis'
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : idList
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'餐厅数量',
                        type:'line',
                        stack: '总量',
                        data:dataList,
                        itemStyle: {
                            normal: {
                                color: '#27727B'
                            }
                        }
                    }
                ]
            };
            myChart.setOption(option);
        },
        error: function(){
            alert("error");
        }

    });
}

// 获得财务统计信息
function getMonthFinanceStatistics(month) {
    // alert("获得财务近 "+month+" 月统计信息");

    $.ajax({
        async : false,
        type: "GET",
        url: "http://localhost:8080/admin/getMonthFinanceStatistics",
        contentType :"application/x-www-form-urlencoded",
        data: {
            month:'6'
        },
        datatype: "json",
        success: function (data) {
            var result = JSON.parse(JSON.stringify(data));

            // alert("/admin/getMonthFinanceStatistics result:\n"+JSON.stringify(data));

            var idList = [];
            var totalSumList = [];
            var canteenSumList = [];
            var platSumList = [];
            /*var orderSumList = [];
            var cancelSumList = [];
            var finishedSumList = [];*/

            for(var i = 0 ; i < result.length ; i++) {
                idList.push(result[i].id);
                totalSumList.push(result[i].totalSum);
                canteenSumList.push(result[i].canteenSum);
                platSumList.push(result[i].platSum);
                /*orderSumList.push(result[i].orderSum);
                cancelSumList.push(result[i].cancelSum);
                finishedSumList.push(result[i].finishedSum);*/
            }

            var myChart = echarts.init(document.getElementById("income_line"));
            var option = {
                color: ['#cd5c5c','#6495ed','#ff7f50','#ff6347','#87cefa','#32cd32'],
                title : {
                    text: 'Yummy！财务情况'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['总收入', '餐厅收入', '平台收入']
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : idList
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        axisLabel : {
                            formatter: '{value} 元'
                        }
                    }
                ],
                series : [
                    {
                        name:'总收入',
                        type:'line',
                        data:totalSumList,
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    },
                    {
                        name:'餐厅收入',
                        type:'line',
                        data: canteenSumList,
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    },
                    {
                        name:'平台收入',
                            type:'line',
                        data: platSumList,
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            };
            myChart.setOption(option);
        },
        error: function(){
            alert("error");
        }
    });
}