<template>
  <div>
    here is saved books

    <div v-for="(book, index) in savedBooks" :key="index">
      here is the saved books
      <p>{{ book.author }}</p>
      <p>{{ book.title }}</p>
      <div class="detail">
        <img v-bind:src="book.image" alt="Book image" />
      </div>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "saved-books-page",
  components: {},
  computed: {
    ...mapState(["savedBooks"]),
  },
};
</script>
<style scoped>
</style>