
import FooterPage from './components/FooterPage.vue';
<!-- <template>
  <div id="app">
    <router-view />
  </div>
</template>

<style scoped>
  #app {
    min-height:100vh;
    position: relative;
  }
</style> -->

<template>
  <div id="app" class="flex-container">
    <router-view class="flex-content" />
    <!-- <FooterPage /> -->
  </div>
</template>

<style scoped>
 #app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  /* position:relative; */
} 

.flex-content {
  flex:2;
} 
</style>

