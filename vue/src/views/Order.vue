<template>
    <div class="order">
      <HeaderPage/>
      <OrderPage class="order-page"/>
      <FooterPage class="footer"/>
    </div>
</template>

<script>
import OrderPage from "@/components/OrderPage.vue";
import HeaderPage from '@/components/HeaderPage.vue';
import FooterPage from "@/components/FooterPage.vue";

export default {
    name: "order",
    components: {
        OrderPage, HeaderPage, FooterPage
    }
}
</script>


<style scoped>
.order {
    display:flex;
    flex-direction: column;
    height:100%;
    margin:0;
}

.order-page {
    flex:1;
}

.footer {
    display:flex;
    bottom:0;
}
</style>