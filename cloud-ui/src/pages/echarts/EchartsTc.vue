<template>
    <div :id="uuid" :style="style">

    </div>
</template>

<script>
    import * as echarts from 'echarts';
    import {tc_pageQuery,tc_queryDetail} from '@/api/tc/tc'

    const idGen = ()=>{
        return new Date().getTime()
    }

    export default {
        data() {
            return {
                uuid:null,
                myChart:null,
                options:{},
                data:{},
                queryDTO:{
                    pageNo:1,
                    pageSize:100,
                },

            }
        },
        created(){
            this.uuid = idGen()
        },
        watch:{
            width(){
                if (this.myChart){
                    this.myChart.resize({
                        animation:{
                            duration:500
                        }
                    })
                }
            },
            options(){
                if (this.myChart){
                    this.myChart.setOption(this.options,{
                        notMerge:true
                    })
                }
            }
        },
        computed:{
            style(){
                return {
                    height: this.height,
                    width: this.width
                }
            }
        },
        props:{
            height:{
                type:String,
                default:'400px',
            },
            width:{
                type: String,
                default: '800px',
            }
        },

        mounted(){
            this.getTcData()
            this.$nextTick(()=>{
                this.getCharts()
            })
        },
        methods:{

            getTcData(){
                tc_pageQuery(this.queryDTO).then(
                    response=>{
                        if (!response.data.success) {
                            alert(response.data.errorMsg)
                        }
                        this.data = response.data.data;
                        let result = this.data.list
                        let xAxisData = result.map((item,index) => item.id)
                        let one = result.map((item,index) => item.one)
                        let two = result.map((item,index) => item.two)
                        let three = result.map((item,index) => item.three)
                        let four = result.map((item,index) => item.four)
                        let five = result.map((item,index) => item.five)
                        let six = result.map((item,index) => item.six)
                        let seven = result.map((item,index) => item.seven)

                        this.getCharts(xAxisData,one,two,three,four,five,six,seven)
                    },
                    error => {
                        alert(error.errorMsg)
                    }
                )
            },

            getCharts(xAxisData,one,two,three,four,five,six,seven){
                console.log(xAxisData)
                let options = {
                    title: {
                        text: 'Stacked Line'
                    },
                    // 触发类型  'item'图形触发：散点图，饼图等无类目轴的图表中使用；
                    // 'axis'坐标轴触发；'none'：什么都不触发。
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['第一位', '第二位', '第三位', '第四位', '第五位','蓝一','蓝二']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: xAxisData
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name: '第一位',
                            type: 'line',
                            stack: 'Total',
                            data: one
                        },
                        {
                            name: '第二位',
                            type: 'line',
                            stack: 'Total',
                            data: two
                        },
                        {
                            name: '第三位',
                            type: 'line',
                            stack: 'Total',
                            data: three
                        },
                        {
                            name: '第四位',
                            type: 'line',
                            stack: 'Total',
                            data: four
                        },
                        {
                            name: '第五位',
                            type: 'line',
                            stack: 'Total',
                            data: five
                        },
                        {
                            name: '蓝一',
                            type: 'line',
                            stack: 'Total',
                            data: six
                        },
                        {
                            name: '蓝二',
                            type: 'line',
                            stack: 'Total',
                            data: seven
                        }
                    ]
                };
                this.myChart = echarts.init(document.getElementById(this.uuid));
                this.myChart.setOption(options);
            },
        }
    }

</script>