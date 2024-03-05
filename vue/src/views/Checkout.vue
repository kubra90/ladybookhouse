<template>
  <div class="checkout-container">
    <HeaderPage/>
    <CheckoutPage class="checkout-page"/>
    <FooterPage class="footer"/>
  </div>
</template>

<script>
import CheckoutPage from "@/components/CheckoutPage.vue"
import HeaderPage from "@/components/HeaderPage.vue"
import FooterPage from "@/components/FooterPage.vue"

export default {
  name: "checkout",
  components: {
    CheckoutPage, HeaderPage, FooterPage
  }
}
</script>

<style scoped>

.checkout-container {
  display:flex;
  flex-direction:column;
  height: 100%;
  margin:0;
}

.checkout-page {
  flex:1;
}

.footer {
  display:flex;
  bottom:0;
}
</style>
