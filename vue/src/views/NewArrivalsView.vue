<template>
  <div>
    <Header-page/>  
    <new-arrival-list/>
    <FooterPage />
  </div>    
</template>

<script>
import HeaderPage from "@/components/HeaderPage.vue"
import FooterPage from "@/components/FooterPage.vue"
import NewArrivalList from "@/components/NewArrivalList.vue"

export default {
  name: "new-arrivals-view",
  components: {
    HeaderPage, FooterPage, NewArrivalList
  }
}
</script>

<style>
</style>