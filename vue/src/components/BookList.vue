<template>
    <div id="book">
    <ul>
        <li v-for="book in paginatedBooks" v-bind:key="book.isbn">
        <img v-bind:src="book.image"/>
        <router-link
          v-bind:to="{
            name: 'detail',
            params: {
                sku: book.sku
            }
          }">
       
        <p>{{ book.title }}</p>
    </router-link>
        </li>
    </ul>
    <button v-if="currentPage > 1" @click="currentPage--">Previous</button>
    <button v-if="currentPage < totalPages" @click="currentPage++">Next</button>
      
    </div>
 </template>
 
 <script>
 import bookService from "../services/BookService";
 export default {
     name: "book-list",
     data(){
         return {
             bookArray:[],
             image: "",
             currentPage : 1,
             booksPerPage: 24
     }
 },
  computed: {
    totalPages() {
        return Math.ceil(this.bookArray.length / this.booksPerPage);
    },
    paginatedBooks() {
        const start =  (this.currentPage - 1) * this.booksPerPage;
        const end = start + this.booksPerPage;
        return this.bookArray.slice(start, end);
    }
  },
  created() {
         bookService.getBooks()
         .then(response => {
             if(response.status == 200){
                this.bookArray = response.data;
             }
         })
     }
  }
 
 </script>
 
 <style scoped>
#book ul {
    display: grid;
    grid-template-columns: repeat(4, 1fr); /* 4 books per row */
    gap: 10px;
    list-style-type: none;
    padding: 0;
}

#book li {
    text-align: center;
}

#book img {
    width: 70%; /* Adjust width as needed */
    height: auto; /* Adjust height as needed */
    object-fit: cover;
}

#book {
    padding: 30px 130px; /* Adds padding to the start and end of the grid container */
}
 </style>