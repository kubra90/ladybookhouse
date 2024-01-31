<template>
  <div>
    <Header-page/>
    <TermsOfSale/>
    <FooterPage/>
  </div>
</template>
<script>
import HeaderPage from '../components/HeaderPage.vue'
import FooterPage from '../components/FooterPage.vue'
import TermsOfSale from '../components/TermsOfSale.vue'

export default {
  name: "terms",
  components: {
    HeaderPage, FooterPage, TermsOfSale
  }
}
</script>
<style scoped>

</style>