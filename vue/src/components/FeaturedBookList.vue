<template>
  <div id="featured-books">
    <ul>
      <book-card v-for="book in paginatedBooks" :book="book" :key="book.isbn" />
    </ul>
    <button v-if="currentPage > 1" @click="currentPage--">Previous</button>
    <button v-if="currentPage < totalPages" @click="currentPage++">Next</button>
  </div>
</template>
  
  
<script>
  
import BookCard from "../components/BookCard.vue"

import {mapState, mapActions} from "vuex"
  
  export default {
    name: "featured-book-list",

    data() {
      return {
        currentPage: 1,
        booksPerPage:24
        }
      }, 
      
    components: {
      BookCard
      },
    
    computed: {
      totalPages() {
        return Math.ceil(this.featuredItems.length / this.booksPerPage)
    },
    
      paginatedBooks() {
        const start =  (this.currentPage - 1) * this.booksPerPag
        const end = start + this.booksPerPage
        return this.featuredItems.slice(start, end)
    },
      ...mapState(["featuredItems"])
  },
    
    methods: {
      ...mapActions(["fetchFeaturedItems"])
    },
    
    created() {
        this.fetchFeaturedItems()
      }
  }
  
  </script>
  
  <style scoped>
  
  #featured-books ul {
      margin-top:50px;
      display: grid;
      grid-template-columns: repeat(4, 1fr); /* 4 books per row */
      gap: 10px;
      list-style-type: none;
      padding-left: 45px;
   
  }
  
  #featured-books li {
      text-align: center;
      
  }
  
  #featured-books {
    padding: 30px 90px; /* Reduced left and right padding */
    /* font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
    font-size: 14px;
    line-height: 1.42857143; */
}
  
/* Responsive adjustments */
@media (max-width: 768px) {
    #featured-books ul {
      grid-template-columns: repeat(2, 1fr); /* 2 books per row for tablets */
      gap: 20px;
    }
   
    #featured-books {
      padding: 20px; /* Reduce padding */
    }
  
  }
   
  @media (max-width: 480px) {
    #featured-books ul {
      grid-template-columns: 1fr; /* 1 book per row for mobiles */
    }
  
  }

</style>
 
  
  
  
  
  
  
  