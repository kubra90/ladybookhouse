<template>
    <div>
       <header-page/>
      <shopping-cart/>
      <footer-page/>
    </div>
</template>

<script>
import ShoppingCart from "../components/ShoppingCart.vue"
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"
export default {
    name: "cart",
    components: {
        ShoppingCart, HeaderPage, FooterPage
    }
}
</script>

<style scoped>

</style>