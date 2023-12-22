<template>
    <div class="book-container">
      <div class="book-details">
        <p><strong>{{ book.author }}</strong> </p>
        <p>{{ book.title }} {{ book.isbn }}</p>
        <p>{{ book.publisher }}. {{ book.media }}, {{ book.conditionAsText }}</p>
        <p>{{ book.notes }}. {{ book.sku }}</p>
        <p><strong>Price:</strong> ${{ book.price }}</p>
        <p> {{ book.usedBook }}</p>
        <!-- Other details here -->
        <div class="book-actions">
            <!-- <button @click="saveBook">Save the Book</button> -->
            <button @click="addToBasket" class="add-to-cart"><strong>Add To Cart</strong></button>
            <button class="save-book"><strong>Save the book</strong></button>
            <button class="remove-book"><strong>Remove the book</strong></button>
         </div>
      </div>
      <div class="book-image">
        <img v-bind:src="book.image" />
      </div>
     
    </div>
  </template>
  

<script>
import bookService from '../services/BookService.js';
import { mapActions } from 'vuex'

export default {
    name: "book-detail",
    data() {
        return {
            numOfBooks: 0,
            book: {}
        }
    },
    methods: {
      ...mapActions(['addToCart']),
      addToBasket() {
                this.numOfBooks++
                this.addToCart(this.numOfBooks);
            }
      // addToCart() { 
      //       let bookToSave = {
      //           id :this.id,
      //           name: this.name,
      //           url : this.$route.params.url
      //       }
        //     bookService.addToCart(bookToSave)
        //     .then(() => {
        //         alert(`${this.name} has been saved to Favorites`);
        //         //this push the route name pokemon
        //         this.$router.push({ name : 'pokemon'});
        //         // this.$store.commit("GET_NEXT_PREVIOUS_POKEMON");
        //     })
        // }
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
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  font-family:'PT Sans',sans-serif;
  padding: 40px 130px;
}

.book-details {
  flex: 2; /* Allocate more space to the details */
  display: flex;
  flex-direction: column;
  padding-right: 20px; /* Add some spacing between the details and the image */
}

.book-image {
  flex: 1; /* Adjust the flex ratio as needed */
  display: flex;
  justify-content: center; /* Center the image horizontally */
  align-items: center; /* Center the image vertically */
  padding: 10px; /* Add padding around the image */
 
}

.book-image img {
   /* Control the max width of the image */
  /* Control the max height of the image */
  /* Maintain aspect ratio */
  height: auto; /* Maintain aspect ratio */
  border: 1px solid #ddd; /* Add a border */
  border-radius: 8px; /* Rounded corners */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Add a subtle shadow for depth */
  width: 200px; /* Increase width for smaller screens */
    height: 300px; /* Maintain aspect ratio */
    max-width:300px;
    max-height:400px;
}

.book-actions {
    display: flex;
    flex-direction: row;
    gap:20px;
}


.book-actions .add-to-cart, .book-actions .save-book, .remove-book {
  position: relative;
  display: inline-block;
  /* background: $button; */
  /* color: $white; */
  color: #6B3630;
  background-color:oldlace;
  border: none;
  border-radius: 0;
  padding: 1.25rem 2.5rem;
  font-size: 1rem;
  text-transform: uppercase;
  cursor: pointer;
  transform: translateZ(0);
  transition: color 0.3s ease;
  letter-spacing: 0.0625rem;
  font-family:'PT Sans',sans-serif;
  
}


</style>