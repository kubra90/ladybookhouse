<template>
    <div>
      <h1>{{ book.title}} {{ book.sku }}</h1>
      <!-- <img v-bind:src=" book.image"/> -->
    </div>
</template>

<script>
import bookService from '../services/BookService.js';

export default {
    name: "book-detail",
    props: {
        sku: String
    },
    data() {
        return {
            book: {},
            // price: "",
            // image: ""
        }
    },
    created() {
        const {sku} = this.$route.params;
    console.log("Received SKU:", this.sku);
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

<!-- <style scoped>
div {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 20px;
    max-width: 600px;
    margin: auto;
}

h4 {
    margin: 10px 0;
    color: #333;
    font-size: 1.5em;
}

img {
    max-width: 100%;
    height: auto;
    border: 1px solid #ddd;
    box-shadow: 2px 2px 5px rgba(0,0,0,0.2);
    margin-bottom: 20px;
}

/* Adjust as needed for specific mobile or desktop views */
@media (max-width: 600px) {
    div {
        max-width: 100%;
    }
}
</style> -->