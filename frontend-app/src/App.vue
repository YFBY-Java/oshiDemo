<script setup>


import NavBar from './views/NavBar.vue';
</script>

<template>
  <div class="layout-demo">
    <a-layout style="height: 100vh;">
      <a-layout-header>
        <NavBar/>
      </a-layout-header>
      <a-layout-content>
        <RouterView/>
      </a-layout-content>
      <a-layout-footer>Footer</a-layout-footer>
    </a-layout>
  </div>

</template>

<style scoped>
.layout-demo :deep(.arco-layout-header),
.layout-demo :deep(.arco-layout-footer),
.layout-demo :deep(.arco-layout-sider-children),
.layout-demo :deep(.arco-layout-content) {
  display: flex;
  flex-direction: column;
  justify-content: center;
  font-size: 16px;
  font-stretch: condensed;
  text-align: center;
}


.layout-demo :deep(.arco-layout-header),
.layout-demo :deep(.arco-layout-footer) {
  height: 64px;
}

.layout-demo :deep(.arco-layout-sider) {
  width: 206px;
}


</style>
