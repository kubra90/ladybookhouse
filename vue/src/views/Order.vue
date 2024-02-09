<template>
    <div>
      <HeaderPage/>
      <OrderPage/>
      <FooterPage/>
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