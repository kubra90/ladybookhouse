<template>
<div class="home">
  <HeaderPage/>
  <NewArrivals/>
  <FeaturedBooks/>
  <FooterPage/>
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
  position: relative;
}
</style>
