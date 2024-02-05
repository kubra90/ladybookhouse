<template>
  <div>
    here is saved books

    <div v-for="book in userSavedBooks(user.email)" :key="book.sku">
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
        return (email)=> {
            return this.$store.getters.getSavedBooks(email);
        }
    }
  },
};
</script>
<style scoped>
</style>
