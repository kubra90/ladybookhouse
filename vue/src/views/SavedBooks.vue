<template>
  <div>
    <HeaderPage/>
    <SavedBooksPage/>
    <FooterPage/>
  </div>
</template>
<script>
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"
import SavedBooksPage from "@/components/SavedBooksPage.vue"

export default {
  name: 'saved-books',
  components: {
    HeaderPage, FooterPage, SavedBooksPage
  }
}
</script>
