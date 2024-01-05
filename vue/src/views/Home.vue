<template>
  <div class="home">
    <!-- <h1>Home</h1> -->
    <!-- <p>You must be authenticated to see this</p> -->
  
      <!-- decide top right items -->
    <div><header-page/></div>
    <div><new-arrivals/></div>
    <div><featured-books/></div>
    <div><footer-page/></div>
  </div>
  
</template>

<script>

import NewArrivals from "../components/NewArrivals.vue"
import FeaturedBooks from "../components/FeaturedBooks.vue"
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"

export default {
  name: "home",
  components: {
   HeaderPage, FooterPage, NewArrivals, FeaturedBooks
  }
}
</script>

<style scoped>

* {
  box-sizing: border-box;
  padding: 0px;
}


</style>
