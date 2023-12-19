<template>
  <div class="home">
    <!-- <h1>Home</h1> -->
    <!-- <p>You must be authenticated to see this</p> -->
  
      <!-- decide top right items -->
    <!-- <h1>Lady Bookhouse</h1> -->
    <div><header-page/></div>
    <!-- <div><book /></div> -->
    <div>specific items</div> 
    <div><footer-page/></div>
  </div>
  
</template>

<script>
// import book from "../views/Book.vue"
// import bookList from "../components/BookList.vue"
import headerPage from "../components/HeaderPage.vue"
import footerPage from "../components/FooterPage.vue"
export default {
  name: "home",
  components: {
   headerPage, footerPage
  }
};
</script>

<style scoped>

* {
  box-sizing: border-box;
  padding: 0px;
}


</style>
