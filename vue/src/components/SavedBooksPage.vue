<template>
  <div>
    here is saved books

    <div v-for="book in userSavedBooks(user.username)" :key="book.sku">
      <p>{{ book.author }}</p>
      <p>{{ book.title }}</p>
      <div class="detail">
        <img v-bind:src="book.image" alt="Book image" />
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapState } from "vuex";
export default {
  name: "saved-books-page",
  components: {},
  computed: {
    ...mapGetters([]),
    ...mapState(['user']),
    userSavedBooks(){
        return (username)=> {
            return this.$store.getters.getSavedBooks(username);
        }
    }
  },
};
</script>
<style scoped>
</style>