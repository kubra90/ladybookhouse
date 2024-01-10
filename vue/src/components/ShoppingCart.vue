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
                <p><strong>{{ book.title }}</strong></p>
                <p>{{ book.author }}</p>
          </div>
          <div class="book-remove">Remove item</div>
          <div class="book-price">${{ book.price }}</div>
          </div>
       </div>
       <div class="price-tab">
        <p class="subtotal-price">Subtotal <span>${{totalPrice}}</span> </p>
        <p class="total-price">Order Total <span class="order-total"> ${{totalPrice}}</span></p>
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
        ...mapState(['cartBooks']),
        totalPrice(){
            return this.cartBooks.reduce((total, book) => total + book.price, 0);
        }
    },
    methods: {
        goToHomePage() {
            this.$router.push({name: "home" });
        },
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
    /* align-items: center; */
    margin-bottom: 20px; /* Space between book items */
    border-bottom: 0.2px solid black; 
    margin-left:9%;
    padding-bottom:20px;
    /* justify-content:space-between; */
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
    margin-top:2%;
}

.book-image {
  height: auto; /* Maintain aspect ratio */
  border: 1px solid #ddd; /* Add a border */
  border-radius: 8px; /* Rounded corners */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Add a subtle shadow for depth */
  width: 120px; /* Increase width for smaller screens */
  height: 170px; /* Maintain aspect ratio */
  margin-right:1%;
  
}

.book-price {
  margin-right:15%;
  display: flex;
    align-items: flex-start; /* Center align vertically */
    flex-grow: 0; /* Prevent it from growing */
    white-space: nowrap; /* Prevent wrapping of text */
  
}

.book-info {
    flex-grow:1;
    margin-right: 10%;
}

.book-remove {
    display: flex;
    /* Center align vertically */
    align-items: flex-start; 
    justify-content: flex-end; /* Align to the end */
    margin-right: 15%; /* Fixed gap to the next element */
    flex-grow: 0; /* Prevent it from growing */
    white-space: nowrap; /* Prevent wrapping of text */
}

/* .order-total {
    color: chocolate;
    margin-left:13px;
} */

/* .total-price span{
    color: chocolate;
    margin-left:20px;
}

.subtotal-price span{
    margin-left:20px;
} */

/* .price-tab {
    border-bottom: 0.2px solid black; 
    margin-left:9%;
} */

/* .subtotal-price {
    margin-left: 76%;
} */

/* .total-price{
    margin-left:74.8%;
} */

.price-tab {
    display: flex;
    flex-direction: column;
    align-items: flex-end; /* Align items to the right */
    border-bottom: 0.2px solid black; 
    margin-left: 9%;
    margin-right: 13%; /* Add right margin for symmetry */
    padding: 10px 0; /* Add some padding */
}

.subtotal-price, .total-price {
    margin-bottom: 0px; /* Reduce space between subtotal and total */
}
.total-price span, .subtotal-price span {
    
    margin-left: 20px;
}
.total-price span{
    color:chocolate;
}

</style>

