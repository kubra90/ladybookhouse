<template>
    <div class="cart-container">
        <div class="cart-header">
            <h4>Shopping Cart</h4>
            <button class="continue-shopping" @click="goToHomePage">CONTINUE SHOPPING</button>
            <!-- this goes the checkout page! -->
            <button class="checkout">CHECKOUT</button>
        </div>
        
       <div v-for="(book, index) in cartBooks" :key="index" class="book-item">
          <div class="book-detail">
         <img :src="book.image" alt="Book Image" class="book-image"/>
         <div class="book-info">
                <h3>{{ book.title }}</h3>
                <p>Author: {{ book.author }}</p>
          </div>
          <div class="book-remove">Remove item</div>
          <div class="book-price">{{ book.price }}</div>
          </div>
       </div>
    </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
    name: "shopping-cart",
    data() {
       return {
          
       } 
    },
    computed: {
        ...mapState(['cartBooks'])
    },
    methods: {
        goToHomePage() {
            this.$router.push({name: "home" });
        }
    }
   
}

</script>

<style scoped>
.cart-container {
    display: flex;
    flex-direction: column;
}

.book-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 20px; /* Space between book items */
}

.cart-header {
/* this is for the white line under the text */
border-bottom: 0.7px solid black; 
padding-bottom: 5px;
/* padding-left: 100px; */
margin-left:9%;
margin-top:2%;
display: flex;
flex-direction: row;
}

.continue-shopping {
    justify-content: flex-end;
    display:flex;
    margin-left:55%;
}

.checkout {
    justify-content: flex-end;
    display:flex;
    flex-direction:row;
    margin-left:10px;
}

.book-detail {
    display:flex;
    flex-direction: row;
}

.book-image {
  /* Control the max width of the image */
  /* Control the max height of the image */
  /* Maintain aspect ratio */
  height: auto; /* Maintain aspect ratio */
  border: 1px solid #ddd; /* Add a border */
  border-radius: 8px; /* Rounded corners */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Add a subtle shadow for depth */
  width: 200px; /* Increase width for smaller screens */
  height: 300px; /* Maintain aspect ratio */
  max-width: 300px;
  max-height: 400px;
}

</style>