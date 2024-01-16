<template>
  <div id="new-arrivals">
    <div class="title-link">
        <div><h3><strong>New Arrivals</strong></h3></div>
        <div><router-link :to="{ name: 'new-arrivals-view' }" class="link">See All New Arrivals</router-link></div></div>
    <ul>
      <div id="body-container" v-for="newArrival in paginatedList" v-bind:key="newArrival.isbn">
        <router-link v-bind:to="{
          name: 'detail',
          params: {
            sku: newArrival.sku
          }}">
            <img v-bind:src="newArrival.image"/>
            <VClamp :line-clamp="1" class="book-title">{{ newArrival.title }}</VClamp>
         </router-link>
  
         <p class="book-author"><b>{{ newArrival.author }}</b></p>
  
        <div class="book-detail">
          <p>{{ newArrival.publisher }}, {{ newArrival.media }}</p>
        </div>
        <div class="book-price">
          <p><b>Price:</b> ${{ newArrival.price }}</p>
        </div>
        <router-link v-bind:to="{
          name: 'detail',
          params: {
            sku: newArrival.sku
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
    name: "new-arrivals",

    components: {
      VClamp
    },
  
  computed: {
    
    paginatedList() {
        return this.newArrivals.slice(0, 4);
    },
    ...mapState(["newArrivals"])
  },
  
  methods: {
    ...mapActions(["fetchNewArrivals"])
  },
  
  created() {
      this.fetchNewArrivals();
    }
}

</script>

<style scoped>

#new-arrivals h3 {
  padding-left: 45px;
  color: rgb(232, 89, 49);
  /* text-transform: uppercase; */
}

.title-link {
  display: flex;
  justify-content: space-between;
}

.link {
    position: relative;
    display: block;
    font-size: 14px;
    font-weight: bold;
    text-transform: uppercase;
    flex-direction:row;
    margin-top: 20px;
    margin-bottom: 10px;
    padding-right: 100px;
    color: rgb(232, 89, 49);
}


#new-arrivals ul {
    margin-top:50px;
    display: grid;
    grid-template-columns: repeat(4, 1fr); /* 4 books per row */
    gap: 10px;
    list-style-type: none;
    padding-left: 45px;
 
}

#new-arrivals li {
    text-align: center;
    
}

#new-arrivals img {
    width: 70%; /* Adjust width as needed */
    height: 70%; /* Adjust height as needed */
    object-fit: cover;
}

#new-arrivals {
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


#new-arrivals .book-author {
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
  #new-arrivals ul {
    grid-template-columns: repeat(2, 1fr); /* 2 books per row for tablets */
    gap: 20px;
  }

  #new-arrivals img {
    width: 80%; /* Increase width for smaller screens */
    height: auto; /* Maintain aspect ratio */
  }

  #new-arrivals {
    padding: 20px; /* Reduce padding */
  }

  .book-title {
    width: 120px; /* Adjust width for smaller screens */
    font-size: 0.9em; /* Reduce font size */
  }

  /* Adjust other elements as needed */
}

@media (max-width: 480px) {
  #new-arrivals ul {
    grid-template-columns: 1fr; /* 1 book per row for mobiles */
  }

  #new-arrivals img {
    width: 100%; /* Full width on mobile */
  }

  .book-title {
    width: auto; /* Allow title to take full width */
    font-size: 0.8em; /* Further reduce font size */
  }

  /* Additional mobile adjustments */
}

</style>






