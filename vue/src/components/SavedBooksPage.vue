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
      <!-- <img v-bind:src="savedBook.image" alt="book image"> -->
      <router-link :to="{
       name: 'detail',
       params: {
         sku: savedBook.sku
           }
     }">
       <img v-bind:src="savedBook.image" alt="book image">
     </router-link> 
      <p><strong>{{ savedBook.title }}</strong></p>
      <p class="author">{{ savedBook.author }}</p>
      
      <p class="price">${{ savedBook.price }}</p>
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
  padding-right:9rem;
  /* margin-top:2rem; */
}

.greater-sign {
  font-size:10px;
  padding:0.2rem;
  padding-left:0.6rem;
}

img {
 
  height: auto; /* Maintain aspect ratio */
  width:100%;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Add a subtle shadow for depth */
  width: 23.5rem; /* Increase width for smaller screens */
  height: 33.75rem; /* Maintain aspect ratio */

}

.title {
  font-size: 18px;
  padding-top:2rem;
  color:brown;
}

.price{
  
  flex:1;
 text-align:end;
 padding-right:1rem;
 flex:1;
  margin-bottom:2rem;
}

.author {
  display:flex;
  flex:5;  /*to fix price at the bottom of the card, think about more appropriate way*/
}

.book-card {
  display:flex;
  flex-direction:column;
  box-sizing: border-box;
  border-style:ridge;
  width: 24rem;
  height:auto;  /*consider setting a min-height */
  /* padding:1rem; */
}

.book-card-container{
  /* flex-direction:row; */
  flex-wrap: wrap;   /*this allows the child elements to wrap within the container   */
  display:flex;
  box-sizing: border-box;
  border-style:hidden;
  width: 100%;
  max-width:140rem;
  height:auto;
  gap:0.5rem;
  
}

@media (min-width: 768px) {
  .book-card-container {
    width: 100%; 
  }
}

@media (min-width: 1024px) {
  .book-card-container {
    width: 100%;
  }
}

@media (min-width: 1200px) {
  .book-card-container {
    width: 100%; /* You can adjust this or keep it at 100% */
    max-width: 180rem; /* adjust this to fit your design needs */
  }
}
</style>
