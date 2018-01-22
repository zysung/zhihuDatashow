var mychart11 = echarts.init(document.getElementById("locationTop10"));
var geoCoordMap = {
    "北京":[116.46,39.92],
    "上海":[121.48,31.22],
    "广州":[113.23,23.16],
    "深圳":[114.07,22.62],
    "苏州":[120.62,31.32],
    "西安":[108.95,34.27],
    "南京":[118.78,32.04],
    "杭州":[120.19,30.26],
    "长沙":[113,28.21],
    "武汉":[114.31,30.52],
    "成都":[104.06,30.67],
    "天津": [117.2, 39.13],
    "厦门":[118.1,24.46],
    "香港":[114.19,22.28],
    "重庆": [106.54,29.59]
};

var data = [
    {name: "北京", value: 81404},
    {name:"上海",value:51274},
    {name:"杭州",value:19135},
    {name:"成都",value:13348},
    {name:"深圳",value:23517},
    {name:"广州",value:21289},
    {name:"南京",value:11682},
    {name:"武汉",value:10206},
    {name:"西安",value:7423},
    {name:"重庆",value:5850},
    {name:"天津",value:5296},
    {name:"厦门",value:4259},
    {name:"苏州",value:4311},
    {name:"香港",value:4195},
    {name:"长沙",value:4119}
];
var convertData = function(data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if (geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value)
            });
        }
    }
    return res;
};

var convertedData = [
    convertData(data),
    convertData(data.sort(function(a, b) {
        return b.value - a.value;
    }).slice(0, 6))
];
data.sort(function(a,b){
    return    a.value-b.value;
});

var categoryData = [];
var barData = [];
//   var maxBar = 30;
var sum = 0;
var count = data.length;
for(var i=0;i<data.length;i++){
    categoryData.push(data[i].name);
    barData.push(data[i].value);
    sum+=data[i].value;
}
console.log(categoryData);
console.log(sum+"   "+count);
option11 = {
    backgroundColor: '#404a59',
    animation: true,
    animationDuration: 1000,
    animationEasing: 'cubicInOut',
    animationDurationUpdate: 1000,
    animationEasingUpdate: 'cubicInOut',
    title: [{
        text: '知乎用户居住地分布',
        left: 'center',
        textStyle: {
            color: '#fff'
        }
    }],
    brush: {
        outOfBrush: {
            color: '#abc'
        },
        brushStyle: {
            borderWidth: 2,
            color: 'rgba(0,0,0,0.2)',
            borderColor: 'rgba(0,0,0,0.5)'
        },
        seriesIndex: [0, 1],
        throttleType: 'debounce',
        throttleDelay: 300,
        geoIndex: 0
    },
    geo: {
        map: 'china',
        left: '10',
        right: '35%',
        center: [117.98561551896913, 31.205000490896193],
        zoom: 1.5,
        label: {
            emphasis: {
                show: false
            }
        },
        roam: true,
        itemStyle: {
            normal: {
                areaColor: '#323c48',
                borderColor: '#111'
            },
            emphasis: {
                areaColor: '#2a333d'
            }
        }
    },
    tooltip: {
        trigger: 'item'
    },
    grid: {
        right: 40,
        top: 100,
        bottom: 40,
        width: '30%'
    },
    xAxis: {
        type: 'value',
        scale: true,
        position: 'top',
        boundaryGap: false,
        splitLine: {
            show: false
        },
        axisLine: {
            show: false
        },
        axisTick: {
            show: false
        },
        axisLabel: {
            margin: 2,
            textStyle: {
                color: '#aaa'
            }
        }
    },
    yAxis: {
        type: 'category',
        name: 'TOP 15',
        nameGap: 16,
        axisLine: {
            show: true,
            lineStyle: {
                color: '#ddd'
            }
        },
        axisTick: {
            show: false,
            lineStyle: {
                color: '#ddd'
            }
        },
        axisLabel: {
            interval: 0,
            textStyle: {
                color: '#ddd'
            }

        },
        data: categoryData
    },
    series: [{
        name: '人数',
        type: 'scatter',
        coordinateSystem: 'geo',
        data: convertedData[0],
        symbolSize: function(val) {
            return Math.max(val[2] / 1500, 8);
        },
        label: {
            normal: {
                formatter: '{b}',
                position: 'right',
                show: false
            },
            emphasis: {
                show: true
            }
        },

        itemStyle: {
            normal: {
                color: '#a41f10',
                position: 'right',
                show: true
            }
        }
    }, {
        //  name: 'Top 5',
        type: 'effectScatter',
        coordinateSystem: 'geo',
        data: convertedData[0],
        symbolSize: function(val) {
            return Math.max(val[2] / 1500, 8);
        },
        showEffectOn: 'emphasis',
        rippleEffect: {
            brushType: 'stroke'
        },
        hoverAnimation: true,
        label: {
            normal: {
                formatter: '{b}',
                position: 'right',
                show: true
            }
        },
        itemStyle: {
            normal: {
                color: '#c23531',
                shadowBlur: 10,
                shadowColor: '#333'
            }
        },
        zlevel: 1
    }, {
        id: 'bar',
        zlevel: 2,
        type: 'bar',
        symbol: 'none',
        itemStyle: {
            normal: {
                label : {show: true, position: 'Right',textStyle:{color:'#ffffff'}},
                color: '#a41f10'
            }
        },

        data: data
    }]
};


mychart11.setOption(option11);