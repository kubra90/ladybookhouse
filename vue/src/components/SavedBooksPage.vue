<template>
  <div class="saved-books-container">
    <div class="saved-book-nav">

    <router-link v-bind:to="{name: 'account'}" >My Account</router-link>
    <span class="greater-sign">&gt;</span>
    <p> My Bookshelf</p>
    </div>
    <!-- {{ user.email }} -->
    <p class="title"><strong>My Bookshelf</strong></p>
    
    <div class="book-card-container">
    <div v-for="savedBook in savedBooks" :key="savedBook.sku" class="book-card">
      <img v-bind:src="savedBook.image" alt="book image">
      <p>{{ savedBook.title }}</p>
      <p>{{ savedBook.author }}</p>
      <p>{{ savedBook.conditionAsText }}</p>
      <p>{{ savedBook.price }}</p>
    </div>
 
  </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
export default {
  name: "saved-books-page",
  components: {},
  computed: {
    ...mapState(['savedBooks', 'user'])
  },

  methods: {
    ...mapActions(['fetchBookshelf'])
  },
  created() {
  this.fetchBookshelf();
}

};
</script>
<style scoped>

.saved-book-nav {
  display: flex;
  flex-direction:row;
  font-size:11.5px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.saved-books-container{
  display: flex;
  flex-direction: column;
  padding-top: 2rem;
  padding-left:13rem;
  /* margin-top:2rem; */
}

.greater-sign {
  font-size:10px;
  padding:0.3rem;
}

img {
 
  height: auto; /* Maintain aspect ratio */
 
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Add a subtle shadow for depth */
  width: 23.5rem; /* Increase width for smaller screens */
  height: 33.75rem; /* Maintain aspect ratio */

}

.title {
  font-size: 18px;
  padding-top:2rem;
  color:brown;
}

.book-card {
  /* display:flex; */
  /* flex-direction:row; */
  box-sizing: border-box;
  border-style:ridge;
}

.book-card-container{
  flex-direction:row;
  display:flex;
  box-sizing: border-box;
  border-style:hidden;
  width:140rem;
  height:auto;
  
}
</style>
