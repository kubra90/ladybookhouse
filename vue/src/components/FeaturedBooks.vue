<template>
  <div id="featured-books">
    <h3><strong>Featured Books</strong></h3>
    <button class="btn primary"><router-link :to="{ name: 'featured-book-view' }" class="title-link">See All Featured Books</router-link></button>
    <ul>
      <div id="body-container" v-for="featuredBook in paginatedList" v-bind:key="featuredBook.isbn">
        <router-link v-bind:to="{
          name: 'detail',
          params: {
            sku: featuredBook.sku
          }}">
            <img v-bind:src="featuredBook.image"/>
            <VClamp :line-clamp="1" class="book-title">{{ featuredBook.title }}</VClamp>
         </router-link>
  
         <p class="book-author"><b>{{ featuredBook.author }}</b></p>
  
        <div class="book-detail">
          <p>{{ featuredBook.publisher }}, {{ featuredBook.media }}</p>
        </div>
        <div class="book-price">
          <p><b>Price:</b> ${{ featuredBook.price }}</p>
        </div>
        <router-link v-bind:to="{
          name: 'detail',
          params: {
            sku: featuredBook.sku
          }
          }">
          <p class="read-more">READ MORE</p>
        </router-link>
      </div>
    </ul>
  </div>
</template>

<script>

import VClamp from "vue-clamp"

import {mapState, mapActions} from "vuex"

export default {
    name: "featured-books",

    components: {
      VClamp
    },
  
    computed: {
    
    paginatedList() {
        return this.featuredItems.slice(0, 16);
    },
    ...mapState(["featuredItems"])
  },
  
    methods: {
    ...mapActions(["fetchFeaturedItems"])
  },
  
  created() {
      this.fetchFeaturedItems();
    }
}

</script>

<style scoped>

#featured-books h3 {
  padding-left: 45px;
}

.title-link {
    position: relative;
    display: block;
    font-size: 14px;
    font-weight: bold;
    text-transform: uppercase;
    margin-top: 10px;
    right: 0;
    bottom: 0;
    flex-direction:row;
}


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

#featured-books img {
    width: 70%; /* Adjust width as needed */
    height: 70%; /* Adjust height as needed */
    object-fit: cover;
}

#featured-books {
    /*padding: 30px 130px; Adds padding to the start and end of the grid container*/
   
  padding: 45px 90px; /* Reduced left and right padding */
}


/* Style for book title */

 .book-title {
  white-space: nowrap; /* Keeps the text in a single line */
  overflow: hidden; /* Ensures that overflow text is hidden */
  text-overflow: ellipsis; /* Adds an ellipsis to indicate text overflow */
  width: 160px; /*Set a width that suits your layout */
  text-transform: uppercase
}


#featured-books .book-author {
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
  #featured-books ul {
    grid-template-columns: repeat(2, 1fr); /* 2 books per row for tablets */
    gap: 20px;
  }

  #featured-books img {
    width: 80%; /* Increase width for smaller screens */
    height: auto; /* Maintain aspect ratio */
  }

  #featured-books {
    padding: 20px; /* Reduce padding */
  }

  .book-title {
    width: 120px; /* Adjust width for smaller screens */
    font-size: 0.9em; /* Reduce font size */
  }

  /* Adjust other elements as needed */
}

@media (max-width: 480px) {
  #featured-books ul {
    grid-template-columns: 1fr; /* 1 book per row for mobiles */
  }

  #featured-books img {
    width: 100%; /* Full width on mobile */
  }

  .book-title {
    width: auto; /* Allow title to take full width */
    font-size: 0.8em; /* Further reduce font size */
  }

  /* Additional mobile adjustments */
}

</style>

