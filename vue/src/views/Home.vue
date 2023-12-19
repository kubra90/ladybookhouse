<template>
  <div class="home">
    <!-- <h1>Home</h1> -->
    <!-- <p>You must be authenticated to see this</p> -->
  
      <!-- decide top right items -->
    <!-- <h1>Lady Bookhouse</h1> -->
    <div><header-page/></div>
    <div><book /></div>
  </div>
  
</template>

<script>
import book from "../views/Book.vue"
import headerPage from "../components/HeaderPage.vue"
export default {
  name: "home",
  components: {
    book, headerPage
  }
};
</script>

<style scoped>

* {
  box-sizing: border-box;
  padding: 5px;
}


</style>
