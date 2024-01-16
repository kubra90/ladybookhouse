<template>
    <div>
       <header-page/>
      <checkout-page/>
      <footer-page/>
    </div>
</template>

<script>
import CheckoutPage from "../components/CheckoutPage.vue"
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"
export default {
    name: "checkout",
    components: {
        CheckoutPage, HeaderPage, FooterPage
    }
}
</script>

<style scoped>

</style>