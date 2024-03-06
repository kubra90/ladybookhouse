<template>
<div class="home">
  <HeaderPage/>
  <NewArrivals/>
  <FeaturedBooks/>
  <FooterPage/>
</div>
</template>

<script>
import NewArrivals from "@/components/NewArrivals.vue"
import FeaturedBooks from "@/components/FeaturedBooks.vue"
import HeaderPage from "@/components/HeaderPage.vue"
import FooterPage from "@/components/FooterPage.vue"

export default {
  name: "home",
  components: {
   HeaderPage, NewArrivals, FeaturedBooks,FooterPage
  }
}
</script>

<style scoped>
* {
  box-sizing: border-box;
  padding: 0px;
  font-size:13px;
}


</style>
