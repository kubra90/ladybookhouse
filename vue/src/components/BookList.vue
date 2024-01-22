<template>
  <div id="book">
    <ul>
      <book-card v-for="book in paginatedBooks" :book="book" :key="book.isbn" />
    </ul>
    <button v-if="currentPage > 1" @click="currentPage--">Previous</button>
    <button v-if="currentPage < totalPages" @click="currentPage++">Next</button>
  </div>
 </template>
 
 <script>
 import BookCard from "../components/BookCard.vue"
 
 import { mapState, mapActions } from "vuex"

 export default {
  name: "book-list",
  components: {
    BookCard
  },
  data(){
    return {
      currentPage : 1,
      booksPerPage: 24,
    }
  },
  computed: {
    totalPages() {
        return Math.ceil(this.books.length / this.booksPerPage)
    },
    paginatedBooks() {
        const start =  (this.currentPage - 1) * this.booksPerPage
        const end = start + this.booksPerPage
        return this.books.slice(start, end)
    },
    ...mapState(["books"])
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
#book ul {
    margin-top:50px;
    display: grid;
    grid-template-columns: repeat(4, 1fr); /* 4 books per row */
    gap: 10px;
    list-style-type: none;
 
}

#book li {
    text-align: center;
    
}

#book {
    /*padding: 30px 130px; Adds padding to the start and end of the grid container*/
   
  padding: 30px 90px; /* Reduced left and right padding */
}

/* Responsive adjustments */
@media (max-width: 768px) {
  #book ul {
    grid-template-columns: repeat(2, 1fr); /* 2 books per row for tablets */
    gap: 20px;
  }

  #book {
    padding: 20px; /* Reduce padding */
  }
}

@media (max-width: 480px) {
  #book ul {
    grid-template-columns: 1fr; /* 1 book per row for mobiles */
  }
}

</style>

 

