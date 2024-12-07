<template>
  <a-space wrap>
    <div
        :id="`cpu-${i}`"
        class="cpu-load" style="width: 200px; height: 200px; border: 1px solid black;"
        v-for="i in 16">
    </div>
  </a-space>

</template>

<script setup>
import {onMounted, ref} from "vue";
import {getCpuLoadApi} from "@/api/CpuLoadApi";
import * as echarts from 'echarts';
import 'echarts/lib/chart/line';  // 引入线性图表
import 'echarts/lib/component/dataZoom';  // 引入dataZoom组件


// 保存所有初始化的图表
const chartDom = ref([]);

// 页面加载出来，有 div dom 元素才可以
onMounted(() => {
  // 初始化图表
  initChart();
  getCpuData();
});

const initChart = () => {
  for (let i = 1; i <= 16; i++) {
    var dom = document.getElementById("cpu-" + i);
    var myChart = echarts.init(dom);
    chartDom.value.push(myChart)
  }
}


// 16核cpu的所有数据，每一核还是一个数组，这个数组中保存的是每秒的数据
const cpuAllData = ref([])

const getCpuData = async () => {
  let resp = await getCpuLoadApi();
  let data = resp.data;
  for (let i = 0; i < 16; i++) {
    // 把当前请求到的这个cpu的使用率放进自己的数组
    if (!cpuAllData.value[i]) {
      // 第一次要初始化数组
      cpuAllData.value[i] = [];
    }
    // 这里可能会OOM，这个数组最多放60个，删除最老的
    // 放之前对数组元素个数进行判断
    if(cpuAllData.value[i].length > 60){
      let arr = cpuAllData.value[i].slice(-60);  // 删除倒数第60个(即移除了最老元素的数组)
      arr.push(data[i]);
      cpuAllData.value[i] = arr;
    }else {
      cpuAllData.value[i].push(data[i]);
    }
    drawCpuLoad(i + 1, cpuAllData.value[i]);
  }
  await getCpuData();
}


// 1.每个图显示CPU名
// 2.每个图xy轴不显示
// 3.显示为面积
const drawCpuLoad = (cpuIndex, cpuData) => {

  // 初始化实例
  var myChart = chartDom.value[cpuIndex - 1];
  var option;


  option = {
    title: {
      text: 'CPU' + cpuIndex,
      textStyle: {fontSize: 14}
    },
    grid: {left: "0", top: "0", right: "0", bottom: "0"},
    xAxis: {
      show: false,
      type: 'category',
    },
    yAxis: {
      show: false,
      type: 'value',
      min: 0,
      max: 1   // 显示 0~1之间的数据
    },
    series: [
      {
        data: cpuData,
        type: 'line',
        symbol: "none",
        smooth: true,  // 线变化平滑
        areaStyle: {},
      }
    ]
  };

  myChart.setOption(option);
};

</script>

<style scoped>
.cpu-load {
  /* 确保 div 有宽高 */
  width: 500px;
  height: 500px;
}
</style>
