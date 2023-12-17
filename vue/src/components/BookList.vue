<template>
    <div id="book">
    <ul>
        <li v-for="book in bookArray" v-bind:key="book.isbn">
        <img v-bind:src="book.image"/>
        {{ book.title }}
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
    display: flex; /* Aligns li elements horizontally */
    list-style-type: none; /* Removes default list styling */
    padding: 0; /* Removes default padding */
}

#book li {
    margin-right: 40px; /* Adds space between books */
    text-align: center; /* Center aligns the text under the image */
}

#book img {
    width: 100px; /* Fixed width for each book image */
    height: 150px; /* Fixed height for each book image */
    object-fit: cover; /* Ensures images cover the area without being stretched */
}
 </style>