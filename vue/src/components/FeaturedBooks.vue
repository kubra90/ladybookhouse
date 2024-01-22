<template>
  <div id="featured-books">
    <div class="title-link">
      <h3><strong>Featured Books</strong></h3>
      <router-link :to="{ name: 'featured-book-view' }" class="link">See All Featured Books</router-link>
    </div>
    <ul>
     <book-card v-for="book in paginatedList" :book="book" :key="book.isbn" />
    </ul>
  </div>
</template>   


<script>

import BookCard from "../components/BookCard.vue"

import {mapState, mapActions} from "vuex"

export default {
    name: "featured-books",

    components: {
      BookCard
    },
  
    computed: {
    
    paginatedList() {
        return this.featuredItems.slice(0, 16)
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

.title-link h3 {
  padding-left: 45px;
  color: rgb(232, 89, 49);
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
  padding: 45px 90px;

}

/* Responsive adjustments */
@media (max-width: 768px) {
  #featured-books ul {
    grid-template-columns: repeat(2, 1fr); 
    gap: 20px;
  }

  #featured-books {
    padding: 20px;
  }
 
}

@media (max-width: 480px) {
  #featured-books ul {
    grid-template-columns: 1fr; /* 1 book per row for mobiles */
  }
}

</style>



