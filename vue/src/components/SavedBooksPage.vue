<template>
  <div>
    here is saved books
    {{ savedBooks }}
    <!-- {{ user.email }} -->
    <div v-for="savedBook in savedBooks" :key="savedBook.sku">
      <p>{{ savedBook.sku}}</p>
 
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
</style>
