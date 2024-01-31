<template>
  <div>
    <HeaderPage/>
    <BookDetail/>
    <FooterPage/>
  </div>
</template>

<script>
import BookDetail from "../components/BookDetail.vue"
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"

export default {
  name: "detail",
  components: {
    BookDetail, HeaderPage, FooterPage
  }
}
</script>
