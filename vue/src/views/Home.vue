<template>
  <div class="home">
    <!-- <h1>Home</h1> -->
    <!-- <p>You must be authenticated to see this</p> -->
  
      <!-- decide top right items -->
    <div><header-page/></div>
    <div><newArrivals/></div>
    <div><featuredBooks/></div>
    <div><footer-page/></div>
  </div>
  
</template>

<script>
// import book from "../views/Book.vue"
// import bookList from "../components/BookList.vue"
import newArrivals from "../components/NewArrivals.vue"
import featuredBooks from "../components/FeaturedBooks.vue"
import headerPage from "../components/HeaderPage.vue"
import footerPage from "../components/FooterPage.vue"
export default {
  name: "home",
  components: {
   headerPage, footerPage, newArrivals, featuredBooks
  }
};
</script>

<style scoped>

* {
  box-sizing: border-box;
  padding: 0px;
}


</style>
