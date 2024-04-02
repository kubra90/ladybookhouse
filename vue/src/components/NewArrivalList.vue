<template>
  <div id="new-arrivals">
    <ul>
      <book-card v-for="book in paginatedBooks" :book="book" :key="book.isbn" />
    </ul>
    <hr />
    <div class="page-nav">
      <div class="book-number">
        <p>
          Results {{ bookStart }} - {{ bookEnd }} ( of
          {{ newBookArrivals.length }} )
        </p>
      </div>
      <div class="page-detail">
        <p>Page {{ currentPage }} of {{ totalPages }}</p>
      </div>

      <!-- Page buttons -->
      <div class="pagination-buttons">
        <button v-if="currentPage > 1" @click="currentPage--">&lt;</button>
        <span v-if="currentPage > 10" @click="currentPage -= 10">&lt;&lt;</span>
        <span
          id="page-num"
          v-for="page in visiblePages"
          :key="page"
          @click="gotoPage(page)"
          :class="{ active: currentPage === page }"
        >
          {{ page }}
        </span>
        <span
          v-if="currentPage < totalPages && currentPage + 10 <= totalPages"
          @click="currentPage += 10"
          >&gt;&gt;</span
        >
        <button v-if="currentPage < totalPages" @click="currentPage++">
          &gt;
        </button>
      </div>
    </div>
  </div>
</template>
  

<script>
import BookCard from "@/components/BookCard.vue";

import { mapState, mapGetters } from "vuex";

export default {
  name: "new-arrival-list",

  data() {
    return {
      currentPage: 1,
      booksPerPage: 24,
    };
  },

  components: {
    BookCard,
  },

  computed: {
    totalPages() {
      return Math.ceil(this.newBookArrivals.length / this.booksPerPage);
    },

    visiblePages() {
      let startPage = Math.floor((this.currentPage - 1) / 10) * 10 + 1;
      let endPage = startPage + 9;
      endPage = endPage > this.totalPages ? this.totalPages : endPage;

      let pages = [];
      for (let page = startPage; page <= endPage; page++) {
        pages.push(page);
      }
      return pages;
    },

    bookStart() {
      return (this.currentPage - 1) * this.booksPerPage + 1;
    },
    bookEnd() {
      let end = this.currentPage * this.booksPerPage;
      return end > this.newBookArrivals.length
        ? this.newBookArrivals.length
        : end;
    },

    paginatedBooks() {
      console.log(this.newBookArrivals);
      const start = (this.currentPage - 1) * this.booksPerPage;
      const end = start + this.booksPerPage;
      return this.newBookArrivals.slice(start, end);
    },
    ...mapGetters(["newBookArrivals"]),
    // ...mapState(["newArrivals"])
    ...mapState(["books"]),
  },
  // created() {
  
  //   this.$store.dispatch("fetchBooks")
  // },

  methods: {
    // ...mapActions(["fetchNewArrivals"]),
    gotoPage(page) {
      this.currentPage = page;
    },
  },


};
</script>
  
<style scoped>
#new-arrivals ul {
  margin-top: 50px;
  display: grid;
  grid-template-columns: repeat(4, 1fr); /* 4 books per row */
  gap: 10px;
  list-style-type: none;
  padding-left: 45px;
}

#new-arrivals li {
  text-align: center;
}

#new-arrivals {
  padding: 30px 90px;
}

.page-nav {
  display: flex;
  flex-direction: row;
}

.page-detail {
  margin-left: 65rem;
  margin-right: 3rem;
}

.pagination-buttons span {
  padding-right: 0.75rem;
  padding-left: 0.25rem;
  color: brown;
  cursor: pointer;
}

.pagination-buttons button {
  color: #6b3630;
  border: none;
  background-color: orange;
  border-radius: 0.3rem;
  padding-right: 0.5rem;
  font-size: 1.25rem;
  cursor: pointer;
}

/* Responsive adjustments */
@media (max-width: 768px) {
  #new-arrivals ul {
    grid-template-columns: repeat(2, 1fr); /* 2 books per row for tablets */
    gap: 20px;
  }

  #new-arrivals {
    padding: 20px; /* Reduce padding */
  }
}

@media (max-width: 480px) {
  #new-arrivals ul {
    grid-template-columns: 1fr; /* 1 book per row for mobiles */
  }
}
</style>  
