<template>
    <div class="cart-container">
        <div class="cart-header">
            <h4>Shopping Cart</h4>
            <button class="continue-shopping" @click="goToHomePage"><strong>CONTINUE SHOPPING</strong> </button>
            <!-- this goes the checkout page! -->
            <router-link v-bind:to="{name: 'checkout'}">
            <button class="checkout"><strong>CHECKOUT</strong></button>
        </router-link>
        </div>
        
       <div v-for="(book, index) in cartBooks" :key="index" class="book-item">
          <div class="book-detail">
            <router-link v-bind:to="{
          name: 'detail',
          params: {
            sku: book.sku
          }}">
         <img :src="book.image" alt="Book Image" class="book-image"/>
         </router-link>
         <div class="book-info">
                <p><strong>{{ book.title }}</strong></p>
                <p>{{ book.author }}</p>
          </div>
          <div class="book-remove" @click="confirmRemoveBook(index)">Remove Item</div>
          <div class="book-price">${{ formatPrice(book.price) }}</div>
          </div>
       </div>
       <div class="price-tab">
        <p class="subtotal-price">Subtotal <span>${{formatPrice(subTotalPrice)}}</span> </p>
        <p class="shipping-price">Shipping <span>${{formatPrice(totalShippingCost) }}</span> </p>
        <p class="total-price">Order Total <span> ${{ formatPrice(totalPrice)}}</span></p>
       </div>
       <div>
        <router-link v-bind:to="{name: 'checkout'}">
        <button class="checkout-btn"><strong>CHECKOUT</strong></button>
    </router-link>
       </div>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
export default {
    name: "shopping-cart",
    data() {
       return {
        
       } 
    },
    computed: {
        ...mapState(['cartBooks']),
        totalPrice(){
         return this.cartBooks.reduce((total, book) => total + book.price, 0) + this.totalShippingCost;
         
        },
        subTotalPrice(){
            return this.cartBooks.reduce((total, book) => total + book.price, 0);
        },
        totalShippingCost(){
            let totalWeight=0;
            let shippingCost=0;
            for(const book of this.cartBooks){
                  totalWeight+=book.weight;
            }
            if(totalWeight>0 && totalWeight<17){
                shippingCost = 4.15;
            }else if(totalWeight>16 && totalWeight<33){
                shippingCost = 4.90;
            }else if(totalWeight>32 && totalWeight<=48){
                shippingCost =5.65;
            }else if(totalWeight>48 && totalWeight<=64){
                shippingCost =6.40;
            }else if(totalWeight>64 && totalWeight<=80){
                shippingCost =7.15;
            }else if(totalWeight>80 && totalWeight <=96){
                shippingCost = 7.90;
            }else if(totalWeight>96 && totalWeight<=112){
                shippingCost = 8.65;
            }else{
                shippingCost =9.99;
            }
            //implement more shopping cost here.
            return shippingCost;
        }
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
    ...mapActions(['removeBook']),
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
    margin-left:9.5%;
    padding-bottom:20px;
    margin-right: 13%;
    /* justify-content:space-between; */
}

.cart-header {
/* this is for the white line under the text */
border-bottom: 0.2px solid black; 
padding-bottom: 5px;
margin-right:13%;
/* padding-left: 100px; */
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

    /* properties from book detail */
    position: relative;
  display: inline-block;
  /* background: $button; */
  /* color: $white; */
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
  /* font-family: "PT Sans", sans-serif; */
}

.checkout, .checkout-btn {
    justify-content: center;
    display:flex;
    flex-direction:row;
    margin-left:10px;
    align-items: center;

    /* properties from book detail */
    position: relative;
  display: inline-block;
  /* background: $button; */
  /* color: $white; */
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
  /* font-family: "PT Sans", sans-serif; */
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
  /* margin-right:2%; */
  display: flex;
    align-items: flex-start; /* Center align vertically */
    flex-grow: 0; /* Prevent it from growing */
    white-space: nowrap; /* Prevent wrapping of text */
  
}

.book-info {
    flex-grow:1;
    margin-right: 15%;
    margin-left:2%;
}

.book-remove {
    display: flex;
    /* Center align vertically */
    align-items: flex-start; 
    justify-content: flex-end; /* Align to the end */
    margin-right: 10%; /* Fixed gap to the next element */
    flex-grow: 0; /* Prevent it from growing */
    white-space: nowrap; /* Prevent wrapping of text */
    text-decoration: underline;
    cursor: pointer;
    font-size: 11px;;
}


.price-tab {
    display: flex;
    flex-direction: column;
    align-items: flex-end; /* Align items to the right */
    border-bottom: 0.2px solid black; 
    margin-left: 9%;
    margin-right: 13%; /* Add right margin for symmetry */
    padding: 0px 0; /* Add some padding */
}

.subtotal-price, .total-price, .shipping-price {
    margin-bottom: 0px; /* Reduce space between subtotal and total */
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
  /* background: $button; */
  /* color: $white; */
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
  margin-bottom: 2rem;;
  /* font-family: "PT Sans", sans-serif; */
  
}

</style>

