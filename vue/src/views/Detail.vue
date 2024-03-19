<template>
  <div class="detail-page">
    <HeaderPage/>
    <BookDetail class="detail"/>
    <FooterPage class="footer"/>
  </div>
</template>

<script>
import BookDetail from "@/components/BookDetail.vue"
import HeaderPage from "@/components/HeaderPage.vue"
import FooterPage from "@/components/FooterPage.vue"

export default {
  name: "detail",
  components: {
    BookDetail, HeaderPage, FooterPage
  }
}
</script>

<style scoped>
.detail {
  flex:1;
}

.detail-page {
  display:flex;
  flex-direction:column;
  height:100%;
}
</style>
