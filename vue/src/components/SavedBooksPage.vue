<template>
  <div class="saved-books-container">
    <div class="saved-book-nav">

    <router-link v-bind:to="{name: 'account'}" >My Account</router-link>
    <span class="greater-sign">&gt;</span>
    <p> My Bookshelf</p>
    </div>
    <!-- {{ user.email }} -->
    <div v-for="savedBook in savedBooks" :key="savedBook.sku">
      <p>{{ savedBook.sku}}</p>
      <p>{{ savedBook.title }}</p>
      <p>{{ savedBook.conditionAsText }}</p>
      <p>{{ savedBook.price }}</p>
 
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
</style>
