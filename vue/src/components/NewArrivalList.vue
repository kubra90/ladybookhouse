<template>
  <div  class="container my-5">
    <div class="row mt-5 px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5 px-xxl-0 mx-xxl-0">
      <book-card 
        v-for="book in paginatedBooks" 
        :book="book" 
        :key="book.isbn"
        class="col-md-3 col-sm-6 mb-4"
      />
   
    <hr />

    <nav aria-label="Page navigation example">
          <div class="d-flex justify-content-between mt-4">
            <!-- left part about results -->
            <div class="">
              Results {{ bookStart }} - {{ bookEnd }} (of
              {{ this.newBookArrivals.length }})
             
              <!-- add small x icon  -->
            </div>
            <div class="pagination justify-content-end">
              <p>Page {{ currentPage }} of {{ totalPages }}</p>
              <ul class="pagination">
                <li class="page-item">
                  <a
                    class="page-link"
                    v-if="currentPage > 1"
                    @click="goToPage(currentPage - 1)"
                   
                    >&lt;</a
                  >
                </li>
                <!-- less 10 -->
                <li class="page-item">
                  <a
                    class="page-link"
                    v-if="currentPage > 10"
                    @click="currentPage -= 10"
                    >&lt;&lt;</a
                  >
                </li>
                <!-- Dynamically generated page numbers here -->
                <li
                  class="page-item"
                  v-for="n in paginationNumbers"
                  :key="n"
                  :class="{ active: n === currentPage }"
                >
                  <a class="page-link" @click="goToPage(n)">{{ n }}</a>
                </li>
                <!-- if there is items more than 10 -->
                <li class="page-item">
                  <a
                    class="page-link"
                    v-if="
                      currentPage < totalPages && currentPage + 10 <= totalPages
                    "
                    @click="currentPage += 10"
                    >&gt;&gt;</a
                  >
                </li>
                <!-- till here -->
                <li class="page-item">
                  <a
                    class="page-link"
            
                    @click="goToPage(currentPage + 1)"
                    >&gt;</a
                  >
                </li>
              </ul>
            </div>
          
            
          </div>
        </nav>
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

    // visiblePages() {
    //   let startPage = Math.floor((this.currentPage - 1) / 10) * 10 + 1;
    //   let endPage = startPage + 9;
    //   endPage = endPage > this.totalPages ? this.totalPages : endPage;

    //   let pages = [];
    //   for (let page = startPage; page <= endPage; page++) {
    //     pages.push(page);
    //   }
    //   return pages;
    // },
    paginationNumbers() {
      let startPage = Math.floor((this.currentPage - 1) / 10) * 10 + 1;
      let endPage = Math.min(startPage + 9, this.totalPages);
      return Array.from(
        { length: endPage - startPage + 1 },
        (v, k) => k + startPage
      );
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
    ...mapState(["books"]),
  },
  // created() {
  
  //   this.$store.dispatch("fetchBooks")
  // },

  methods: {
    goToPage(page) {
      this.currentPage = page;
    },
  }
};
</script>

<style scoped>
.pagination .page-link {
  border: none; /* Removes the border */
  background: none; /* Removes the background color */
  color: black; /* Bootstrap blue for link color, adjust as necessary */
  margin-top: 0.2em;
  margin-bottom: 0;
  padding-top: 0;
  padding-bottom: 0;
  margin-right: 0;
  padding-right: 0;
  font-size: 12px;
}

.pagination .page-item.active .page-link {
  background-color: none; /* Bootstrap blue for active page, adjust if needed */
  color: orange;
}

.pagination .page-link:hover {
  background-color: none; /* Light grey background on hover, optional */
}
</style>

