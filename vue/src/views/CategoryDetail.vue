<template>
 <div>
 <header-page />
 <ul>
  <div v-for="book in filteredBooksByCateg(this.$route.params.category)" :key="book.isbn">{{ book.author }} {{ book.category }}</div>
 </ul>
 <footer-page />
 </div>
</template>
   
<script>
   
import {mapActions, mapGetters} from "vuex"
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"

export default {
  name: "category-detail",

  components: {HeaderPage, FooterPage},

  computed: {
    ...mapGetters(["filteredBooksByCateg"])
  },
  methods: {
    ...mapActions(["fetchBooks"])
  },

  created() {
    this.fetchBooks()
  }
}
   
</script>