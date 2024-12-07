<template>
  <div class="menu-demo">
    <a-menu mode="horizontal" :default-selected-keys="['1']">
      <a-menu-item key="0" :style="{ padding: 0 }" disabled>
        <div
            :style="{
            width: '80px',
            height: '30px',
            borderRadius: '2px',
            background: 'var(--color-fill-3)',
            cursor: 'text',
          }"
        />
      </a-menu-item>
      <a-menu-item key="/" @click="navTo('/')">首页</a-menu-item>
      <a-menu-item key="2" @click="navTo('/cpu')">监控页</a-menu-item>
      <a-menu-item key="3">Cloud Service</a-menu-item>
      <a-menu-item key="4">Cooperation</a-menu-item>
    </a-menu>
  </div>
</template>


<script setup>

import {useRouter} from "vue-router";

const router = useRouter();
const navTo = (url) => {
  router.push(url)
}


</script>


<style scoped>
.menu-demo {
  box-sizing: border-box;
  width: 100%;
  background-color: var(--color-neutral-2);
}
</style>
