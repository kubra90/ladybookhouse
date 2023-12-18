<template>
    <div id="book">
    <ul>
        <div id="body-container" v-for="book in paginatedBooks" v-bind:key="book.isbn">
          
          <router-link v-bind:to="{
            name: 'detail',
            params: {
                sku: book.sku
            }
          }"><img v-bind:src="book.image"/>
          <VClamp :line-clamp="1" class="book-title">{{ book.title }}</VClamp>
         </router-link>
         <p class="book-author"><b>{{ book.author }}</b></p>
         <!-- <h4 id="book-publisher">{{ book.publisher }}</h4> -->
         <!-- <h4 id="book-media">{{ book.media }}</h4>-->
        
         <div class="book-detail">
            <p>{{ book.publisher }}, {{ book.media }}</p>
         </div>
         <div  class="book-price">
            <p><b>Price:</b> ${{ book.price }}</p>
         </div>
         <router-link v-bind:to="{
            name: 'detail',
            params: {
                sku: book.sku
            }
          }"><p class="read-more">READ MORE</p>
         </router-link>
        </div>
    </ul>
    <button v-if="currentPage > 1" @click="currentPage--">Previous</button>
    <button v-if="currentPage < totalPages" @click="currentPage++">Next</button>
      
    </div>
 </template>
 
 <script>
 import bookService from "../services/BookService";
 import VClamp from 'vue-clamp';
 export default {
     name: "book-list",
     components: {
        VClamp
     },
     
     data(){
         return {
             bookArray:[],
             image: "",
             currentPage : 1,
             booksPerPage: 24,

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
    height: 60%; /* Adjust height as needed */
    object-fit: cover;
}

#book {
    padding: 30px 130px; /* Adds padding to the start and end of the grid container */
}

/* Style for book title */

 .book-title {
  white-space: nowrap; /* Keeps the text in a single line */
  overflow: hidden; /* Ensures that overflow text is hidden */
  text-overflow: ellipsis; /* Adds an ellipsis to indicate text overflow */
  width: 160px; /*Set a width that suits your layout */
  text-transform: uppercase
}


#book .book-author {
    margin: 0; /* Adjust top and bottom margin to reduce white space */
}

.book-detail p, .book-price p {
    margin: 0; /* Remove default margin to reduce white space */
}


 </style>