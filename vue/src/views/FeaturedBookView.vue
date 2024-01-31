<template>
  <div>
    <HeaderPage/>
    <FeaturedBookList/>
    <FooterPage/>
  </div>    
</template>

<script>
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"
import FeaturedBookList from "../components/FeaturedBookList.vue"

export default {
  name: "featured-book-view",
  components: {
    HeaderPage, FooterPage, FeaturedBookList
  }
}
</script>
