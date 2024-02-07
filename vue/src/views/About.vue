<template>
  <div>
    <!-- <HeaderPage/> -->
    <about-lady/>
    <!-- <FooterPage class="footer"/> -->
  </div>
</template>

<script>
import AboutLady from "../components/AboutLady.vue"
// import HeaderPage from "../components/HeaderPage.vue"
// import FooterPage from "../components/FooterPage.vue"

export default {
  name: "about",
  components: {
    AboutLady
  }
}
</script>

<style scoped>
/* .footer {
  position:absolute;
  bottom:0;
} */
</style>

