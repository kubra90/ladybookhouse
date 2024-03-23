<template>
    <div class="summary-page">
      <HeaderPage/>
      <OrderSummary class="summary"/>
      <FooterPage class="footer"/>
    </div>
  </template>
  
  <script>
  import OrderSummary from "@/components/OrderSummary.vue"
  import HeaderPage from "@/components/HeaderPage.vue"
  import FooterPage from "@/components/FooterPage.vue"
  
  export default {
    name: "OrderSummaryView",
    components: {
      OrderSummary, HeaderPage, FooterPage
    }
  }
  </script>
  
  <style scoped>
  .summary {
    flex:1;
  }
  
  .summary-page {
    display:flex;
    flex-direction:column;
    height:100%;
  }
  </style>
  