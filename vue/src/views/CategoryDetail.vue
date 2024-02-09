<template>
  <div>
    <header-page />
    <div id="categories">
      <ul>
        <book-card v-for="book in paginatedBooks" :book="book" :key="book.isbn" />
      </ul>
      <button v-if="currentPage > 1" @click="currentPage--">Previous</button>
      <button v-if="currentPage < totalPages" @click="currentPage++">Next</button>
    </div>
    <footer-page />
  </div>
</template>
   
<script>

import { mapActions, mapGetters } from "vuex"
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"
import BookCard from "../components/BookCard.vue"

export default {
  name: "category-detail",

  data() {
    return {
      currentPage: 1,
      booksPerPage: 24
    }
  },

  components: { HeaderPage, FooterPage, BookCard },

  computed: {
    ...mapGetters(["filteredBooksByCateg"]),
    totalPages() {
      return Math.ceil(this.filteredBooksByCateg(this.$route.params.category).length / this.booksPerPage)
    },
    paginatedBooks() {
      const start = (this.currentPage - 1) * this.booksPerPage
      const end = start + this.booksPerPage
      return this.filteredBooksByCateg(this.$route.params.category).slice(start, end)
    }
  },
  methods: {
    ...mapActions(["fetchBooks"])
  },

  created() {
    this.fetchBooks()
  }
}

</script>

<style scoped>
#categories ul {
  margin-top: 50px;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  /* 4 books per row */
  gap: 10px;
  list-style-type: none;
  padding-left: 45px;
}

#categories li {
  text-align: center;
}

#categories {
  padding: 30px 90px;
}

@media (max-width: 768px) {
  #categories ul {
    grid-template-columns: repeat(2, 1fr);
    /* 2 books per row for tablets */
    gap: 20px;
  }

  #categories {
    padding: 20px;
    /* Reduce padding */
  }
}

@media (max-width: 480px) {
  #categories ul {
    grid-template-columns: 1fr;
    /* 1 book per row for mobiles */
  }
}
</style>