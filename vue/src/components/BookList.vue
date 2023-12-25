<template>
    <div id="book">
    <ul>
      <div id="body-container" v-for="book in paginatedBooks" v-bind:key="book.isbn">
        <router-link v-bind:to="{
          name: 'detail',
          params: {
            sku: book.sku
          }}">
            <img v-bind:src="book.image"/>
            <VClamp :line-clamp="1" class="book-title">{{ book.title }}</VClamp>
         </router-link>

         <p class="book-author"><b>{{ book.author }}</b></p>

        <div class="book-detail">
          <p>{{ book.publisher }}, {{ book.media }}</p>
        </div>
        <div class="book-price">
          <p><b>Price:</b> ${{ book.price }}</p>
        </div>
        <router-link v-bind:to="{
          name: 'detail',
          params: {
            sku: book.sku
          }
          }">
          <p class="read-more">READ MORE</p>
        </router-link>
      </div>
    </ul>
    <button v-if="currentPage > 1" @click="currentPage--">Previous</button>
    <button v-if="currentPage < totalPages" @click="currentPage++">Next</button>
  </div>
 </template>
 
 <script>
 import VClamp from 'vue-clamp';
 import { mapState, mapActions } from 'vuex';

 export default {
  name: "book-list",
  components: {
    VClamp
  },
  data(){
    return {
      image: "",
      currentPage : 1,
      booksPerPage: 24,
    }
  },
  computed: {
    totalPages() {
        return Math.ceil(this.books.length / this.booksPerPage);
    },
    paginatedBooks() {
        const start =  (this.currentPage - 1) * this.booksPerPage;
        const end = start + this.booksPerPage;
        return this.books.slice(start, end);
    },
    ...mapState(['books'])
  },
  methods: {
    ...mapActions(['fetchBooks'])
  },
  created() {
      this.fetchBooks();
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

#book img {
    width: 70%; /* Adjust width as needed */
    height: 70%; /* Adjust height as needed */
    object-fit: cover;
}

#book {
    /*padding: 30px 130px; Adds padding to the start and end of the grid container*/
   
  padding: 30px 90px; /* Reduced left and right padding */
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

/* #book ul {
  display: grid;
  grid-template-columns: repeat(4, 1fr); /* 4 books per row 
  gap: 30px;
  list-style-type: none;
  padding: 0;
*/

/* ... existing styles ... */

/* Responsive adjustments */
@media (max-width: 768px) {
  #book ul {
    grid-template-columns: repeat(2, 1fr); /* 2 books per row for tablets */
    gap: 20px;
  }

  #book img {
    width: 80%; /* Increase width for smaller screens */
    height: auto; /* Maintain aspect ratio */
  }

  #book {
    padding: 20px; /* Reduce padding */
  }

  .book-title {
    width: 120px; /* Adjust width for smaller screens */
    font-size: 0.9em; /* Reduce font size */
  }

  /* Adjust other elements as needed */
}

@media (max-width: 480px) {
  #book ul {
    grid-template-columns: 1fr; /* 1 book per row for mobiles */
  }

  #book img {
    width: 100%; /* Full width on mobile */
  }

  .book-title {
    width: auto; /* Allow title to take full width */
    font-size: 0.8em; /* Further reduce font size */
  }

  /* Additional mobile adjustments */
}



 </style>

