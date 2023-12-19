<template>
    <div class="book-container">
      <div class="book-details">
        <h1>{{ book.title }} {{ book.sku }}</h1>
        <p><strong>Author:</strong> {{ book.author }}</p>
        <p><strong>Notes:</strong> {{ book.notes }}</p>
        <!-- Other details here -->
      </div>
      <div class="book-image">
        <img v-bind:src="book.image" alt="Book Image"/>
      </div>
      <div class="book-actions">
        <button @click="saveBook">Save the Book</button>
        <button @click="addToCart">Add to Cart</button>
      </div>
    </div>
  </template>
  

<script>
import bookService from '../services/BookService.js';

export default {
    name: "book-detail",

    data() {
        return {
            book: {},
        }
    },
    created() {
        const {sku} = this.$route.params;
    // console.log("Received SKU:", this.sku);
    bookService.getBookById(sku)
    .then(response => {
        console.log("API Response:", response.data);
        this.book = response.data;
    })
    .catch(error => {
        console.error("Error fetching book details:", error);
    });
}
}
</script>

<style scoped>
.book-container {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}

.book-details {
  flex: 1;
}

.book-image {
  flex: 1;
  /* Adjust width as necessary */
}

.book-actions {
  display: flex;
  justify-content: space-around;
  width: 100%;
}

button {
  /* Button styling */
}
</style>