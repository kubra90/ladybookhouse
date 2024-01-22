<template>
  <div>
    <div><header-page/></div>
    <h1>Your account information</h1>
    <div><footer-page/></div>
  </div>
</template>

<script>

import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"

export default {
  name: "my-account",
  components: {
   HeaderPage, FooterPage
  }
}
</script>

<style scoped>

* {
  box-sizing: border-box;
  padding: 0px;
}


</style>