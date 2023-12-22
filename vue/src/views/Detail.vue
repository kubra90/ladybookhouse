<template>
    <div>
       <header-page/>
      <book-detail/>
      <footer-page/>
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

<style scoped>

</style>