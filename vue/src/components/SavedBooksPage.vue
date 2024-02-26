<template>
  <div>
    <div class="saved-book-nav">

    <router-link v-bind:to="{name: 'account'}" >My Account</router-link>
    <span>&gt;</span>
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
}
</style>
