
<template>
  <div class="container">
  <nav class="navbar navbar-expand-lg navbar-light bg-none">
  <div class="container">
    <a class="navbar-brand" style="font-size:25px; color:brown;" href="#">Shopping Cart</a>
    <div v-if="checkBook">
      <button class="btn btn-outline-secondary me-2" @click="goToHomePage">Continue Shopping</button>
      <router-link v-bind:to="{name: 'checkout'}">
        <button class="btn btn-primary" style="color:oldlace; background-color:#fa8072; border:none;"><strong>Checkout</strong></button>
      </router-link>
    </div>
  </div>
</nav>
<div v-if="!checkBook" class="container d-flex mt-5 align-items-center justify-content-center">
  <div class="col-md-4 mb-6" >
    <div  v-if="!isAuthenticated">The shopping basket is empty. 
    <router-link v-bind:to="{name: 'login'}">Sign on</router-link> to your account to see saved items from a previous visit. Or 
    <router-link v-bind:to="{name: 'advanced-search'}"> search </router-link>for books to fill it up.</div>
     <div v-else-if="isAuthenticated">Go to <router-link v-bind:to="{name: 'home'}">home</router-link> page to continue shopping or <router-link v-bind:to="{name: 'advanced-search'}"> search </router-link>for books to fill it up.</div>
   </div>
</div>
<template v-if="checkBook">
<div class="container mt-4">
  <div><hr></div>
  <div v-for="(book, index) in cartBooks" :key="index" class="card mb-3 border-0">
    <div class="row g-0">
      <div class="col-sm-4 col-md-3 col-lg-2 col-6 col-xs-12">
        <router-link :to="{ name: 'detail', params: { sku: book.sku }}">
          <img :src="book.image" alt="Book Image" class="img-fluid rounded-start book-image"/>
        </router-link>
      </div>
      <div class="col-md-9 col-lg-10 col-sm-8">
        <div class="card-body">
          <h5 class="card-title">{{ book.title }}</h5>
          <p class="card-text">{{ book.author }}</p>
          <div class="d-flex justify-content-between align-items-center">
           <button class="btn btn-link remove-item" style="padding-left:0rem;" @click="confirmRemoveBook(index)">Remove Item</button>
           <p class="card-text ms-auto text-end" style="padding-right:0rem;">${{ formatPrice(book.price) }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div><hr></div>
</div>
</template>
<div v-if="checkBook" class="container my-4">
  <div class="d-flex justify-content-end">
    <div class="text-end">
      <p class="mb-1">Subtotal: <span>${{ formatPrice(subTotalPrice) }}</span></p>
      <p class="mb-1">Shipping: <span>${{formatPrice(totalShippingCost)}}</span></p>
      <p class="mb-0">Order Total: <span style="color:chocolate;">${{ formatPrice(totalPrice) }}</span></p>
      <router-link v-bind:to="{name: 'checkout'}">
        <button class="btn btn-primary mt-2" style="color:oldlace; background-color:#fa8072; border:none;"><strong>Checkout</strong></button>
      </router-link>
    </div>
  </div>
</div>
</div>
</template>

<script>
import { mapState, mapActions, mapGetters } from 'vuex';
export default {
  name: "shopping-cart",
  computed: {
    
    ...mapState(['cartBooks']),
    ...mapGetters(['isAuthenticated', 'totalShippingCost', 'totalPrice', 'subTotalPrice']),
    checkBook(){
      let checkBookCart = false;
      if(this.cartBooks.length > 0){
        checkBookCart = true;
        }
        return checkBookCart;
    },
    // totalPrice(){
    //   return this.cartBooks.reduce((total, book) => total + book.price, 0) + this.totalShippingCost;
    // },
    // subTotalPrice(){
    //   return this.cartBooks.reduce((total, book) => total + book.price, 0);
    // },
    
  },
  methods: {
    goToHomePage() {
      this.$router.push({name: "home" });
    },
    formatPrice(value) {
      const formattedPrice = Number(value).toFixed(2);
      return formattedPrice;
    },
    confirmRemoveBook(index) {
      if (confirm("Are you sure to remove this book from the cart?")) {
        this.removeBookFromCart(index);
      }
    },
    removeBookFromCart(index){
      this.removeBook(index)
    },
    ...mapActions(['removeBook'])
    
  }
  
}

</script>

<style scoped>

.btn-link.remove-item {
  font-size:12px;
  color:black;
  /* margin-right: 30px; */
}


.book-item {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px; 
  border-bottom: 0.2px solid black; 
  margin-left:9.5%;
  padding-bottom:20px;
  margin-right: 13%;
}

.cart-header {
  border-bottom: 0.2px solid black; 
  padding-bottom: 5px;
  margin-right:13%;
  margin-left:9.5%;
  margin-top:2%;
  display: flex;
  flex-direction: row;
}

.continue-shopping {
  justify-content: center;
  display:flex;
  margin-left:60%;
  align-items:center;
  flex-direction: row;
  flex:1;
  position: relative;
  display: inline-block;
  color: #6b3630;
  background-color: oldlace;
  border: none;
  border-radius: 0;
  padding: 1.25rem 2.5rem;
  font-size: 1rem;
  text-transform: uppercase;
  cursor: pointer;
  transform: translateZ(0);
  transition: color 0.3s ease;
  letter-spacing: 0.0625rem;
}

.checkout, .checkout-btn {
  justify-content: center;
  display:flex;
  flex-direction:row;
  margin-left:10px;
  align-items: center;
  position: relative;
  display: inline-block;
  color: #6b3630;
  background-color: oldlace;
  border: none;
  border-radius: 0;
  padding: 1.25rem 2.5rem;
  font-size: 1rem;
  text-transform: uppercase;
  cursor: pointer;
  transform: translateZ(0);
  transition: color 0.3s ease;
  letter-spacing: 0.0625rem;
}

.book-detail {
  display:flex;
  flex-direction: row;
  margin-top:2%;
}

.book-price {
  display: flex;
  align-items: flex-start; 
  flex-grow: 0; 
  white-space: nowrap; 
}

.book-info {
  flex-grow:1;
  margin-right: 15%;
  margin-left:2%;
}

.book-remove {
  display: flex;
  align-items: flex-start; 
  justify-content: flex-end; 
  margin-right: 10%; 
  flex-grow: 0; 
  white-space: nowrap; 
  text-decoration: underline;
  cursor: pointer;
  font-size: 11px;;
}

.price-tab {
  display: flex;
  flex-direction: column;
  align-items: flex-end; 
  border-bottom: 0.2px solid black; 
  margin-left: 9%;
  margin-right: 13%; 
  padding: 0px 0; 
}

.subtotal-price, .total-price, .shipping-price {
  margin-bottom: 0px; 
}
.total-price span, .subtotal-price span{
  margin-left: 20px;
}

.shipping-price span{
  margin-left:27px;
}
.total-price span{
  color:chocolate;
}

.checkout-btn{
  margin-left:79%;
  position: relative;
  display: inline-block;
  color: white;
  background-color: chocolate;
  border:none;
  border-radius: 0;
  padding: 1.25rem 2.5rem;
  font-size: 1rem;
  text-transform: uppercase;
  cursor: pointer;
  transform: translateZ(0);
  transition: color 0.3s ease;
  letter-spacing: 0.0625rem;
  margin-top:0.8rem;
  /* margin-bottom: 2rem; */

}

@media (max-width: 576px) {
  /* Adjust for small screens and up */
  .card-title{
    padding: 0rem;
    font-size:17px;
  }

  .btn-primary.save-book {
    width: 43%;
  }

  .bookshelf-popup.btn {
    font-size: 1rem; /* Adjust font size as necessary */
    padding: 0rem 0rem; /* Adjust padding to control the size */
    font-size: 12px;
    width: 50%;
  }
}


</style>

