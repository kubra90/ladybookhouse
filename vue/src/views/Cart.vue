<template>
  <div class="cart-container">
    <HeaderPage/>
    <ShoppingCart class="cart"/>
    <FooterPage class="footer"/>
  </div>
</template>

<script>
import ShoppingCart from "../components/ShoppingCart.vue"
import HeaderPage from "@/components/HeaderPage.vue"
import FooterPage from "@/components/FooterPage.vue"

export default {
  name: "cart",
  components: {
    ShoppingCart, HeaderPage, FooterPage
  }
}
</script>

<style scoped>
.cart-container{
  display:flex;
  flex-direction: column;
  height:100%;
  margin:0;
}


.cart{
  flex:1;
}
</style>