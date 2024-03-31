<template>
  <div class="home">
    <HeaderPage />
    <NewArrivals />
    <FeaturedBooks />
    <FooterPage />
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
    HeaderPage, NewArrivals, FeaturedBooks, FooterPage
  },
  created() {
    // this.fetchNewArrivals()
    this.$store.dispatch("fetchBooks")
  },
}
</script>

<style scoped>
.home {
  display: flex;
  flex-direction: column;
  height: 100%;
  margin: 0;
}
</style>
