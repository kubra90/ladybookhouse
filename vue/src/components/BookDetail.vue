<template>

  <div class="book-container">
    <div class="book-details">
      <p>
        <strong style="font-size:medium">{{ book.title}}</strong>
      </p>
      <p>{{ book.author }}</p>
      <p>{{ book.publisher }}. {{ book.media }}, {{ book.conditionAsText }}</p>
      <p> ISBN: {{ book.isbn }}</p>
      <p>{{ book.notes }}. {{ book.sku }}</p>
      <p>
        <strong>Price: ${{ formatPrice(book.price) }} </strong>
      </p>
      <p>{{ book.usedBook }}</p>
      <!-- Other details here -->
      <div class="book-actions">
        <button @click="addToBasket" class="add-to-cart">
          <strong>Add To Cart</strong>
        </button>
        <button v-if="isAuthenticated" @click="addBookshelf" class="save-book">
          <strong>Add to Bookshelf</strong>
        </button>
       
        <router-link v-else to="/login" class="save-book">
          <strong>Add to Bookshelf</strong>
        </router-link>
         <!-- pop up page show the message the book added into the cart -->
         <div v-if="showBookshelfPopup" class="overlay" @click="hidePopup"></div>
         <div v-if="showBookshelfPopup" class="bookshelf-popup">
             <div><p>This book added to your Bookshelf</p></div>
             <button class="close-popup" @click="hidePopup"><strong>x</strong></button>
            <!-- <button @click="showBookshelfPopup = false">x</button> -->
            <!-- link go to the bookshelf -->
          
            <button class="bookshelf-btn" @click="goToBookshelf">Go to Bookshelf</button>
          
         </div>
        </div>
    </div>

    <div class="new-image">
      <div class="book-image">
        <img v-bind:src="book.image" />
      </div>
    </div>
    
    <div v-if="showAddedToCart" class="overlay" @click="hidePopup"></div>
    <div v-if="showAddedToCart" class="added-to-cart-popup">
      <!-- Added to Cart -->
      <button class="close-popup" @click="hidePopup"><strong>x</strong></button>
      <div v-if="showErrorMessage" class="added-book-error"><h5>You're already added this book to the cart</h5></div>
      <!-- this is error message for the added page -->
      <div v-else><h3>Added to Cart</h3> </div>
      <div class="popup-content">
        <!-- check the book is already in the basket or not! -->
        
        <img :src="book.image" class="popup-book-image" />
        <div class="popup-detail">
          <h4 class="popup-book-title">{{ book.title }}</h4>
          <h5 class="popup-book-author">{{ book.author }} </h5>
          <h5 class="popup-book-price">
            <strong>Price: ${{ formatPrice(book.price) }}</strong>
          </h5>
        </div>
       
      </div>
      <div class="popup-navbar">
        <button class="continue-shopping-bar" @click="hidePopup">CONTINUE SHOPPING</button>
        <button class="go-to-cart-bar" @click="goToCart">GO TO CART</button>
      </div>
    </div>
  </div>
</template>
  
<script>
import { mapActions, mapState, mapGetters } from "vuex";

export default {
  name: "book-detail",
  data() {
    return {
      numOfBooks: 0,
      showAddedToCart: false,
      showErrorMessage: false,
      showBookshelfPopup: false,
      loginRequiredMessage: false
    };
  },
  computed: {
    ...mapState(["book", "user", "savedBooks", "cartBooks"]),
    ...mapGetters(['isAuthenticated']),
    // consider computed method to preserve book across navigation
    isBookInCart(){
      return this.cartBooks.some(book=> book.isbn === this.book.isbn);
    }
  },
  methods: {
    ...mapActions(["addToCart", "fetchBookById", "addToBookshelf"]),
    formatPrice(value) {
      const formattedPrice = Number(value).toFixed(2);
      return formattedPrice;
    },
    addBookshelf(){
      if(this.isAuthenticated){
        this.addToBookshelf(this.book);
        this.showBookshelfPopup= true;
        console.log(this.savedBooks.book);
      }else {
        this.$router.push(
          {name: 'login',
          query: {loginRequired: true}
        });
      }
    },
    addToBasket() {
      this.showAddedToCart = true;
      if((this.book.qty> 0) && (!this.isBookInCart)){
        this.addToCart(this.book);
        this.numOfBooks++;
      this.book.qty--;
      this.showErrorMessage= false;
      }else {
        this.showErrorMessage = true;
      }
    },
    hidePopup() {
      this.showAddedToCart = false;
      this.showBookshelfPopup = false;
    },
    goToCart() {
      this.$router.push({name: "cart"});
    },
    goToBookshelf() {
      if(this.isAuthenticated){
      this.$router.push({name: "saved-books"});
      }
    }
  },
  created() {
    this.fetchBookById(this.$route.params.sku);
  },

};
</script>

<style scoped>
.book-container {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  font-family: "PT Sans", sans-serif;
  padding-top: 5.5rem;
  padding-right: 10rem;
  padding-left:13rem;
  padding-bottom: 2rem;
}

.book-details {
  flex: 2; /* Allocate more space to the details */
  display: flex;
  flex-direction: column;
  padding-right: 6.25rem; /* Add some spacing between the details and the image */
}

.book-image {
  flex: 1; /* Adjust the flex ratio as needed */
  display: flex;
  justify-content: center; /* Center the image horizontally */
  align-items: center; /* Center the image vertically */
  /* padding:0.625rem;  */
  padding:0;

  /* new styles */
  position : relative;
}

.book-image img {
  /* Control the max width of the image */
  /* Control the max height of the image */
  /* Maintain aspect ratio */
  height: auto; /* Maintain aspect ratio */
  border: 1px solid #ddd; /* Add a border */
  border-radius: 1rem; /* Rounded corners */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Add a subtle shadow for depth */
  width: 18.5rem; /* Increase width for smaller screens */
  height: 25.75rem; /* Maintain aspect ratio */
  /* max-width: 300px; */
  /* max-height: 400px; */
  transition-duration: 1.25s;
}

.book-image img:hover {
  cursor: pointer;
  transform: scale(1.50);
}
.icon-background {
  width:4.5rem;
  height:3rem;
  position:absolute;
  top:38%;
  right:12%;
  /* left:83%; */
  /* z-index is to position the icon above the book */
  z-index:1; 
  /* border: 10px; */
  display:flex;
  justify-content:center;
  align-items:center;
  color:#6b3630;
}

.enlarger-icon {
  width: 100%;
  height: auto;
  cursor: pointer;
}

.new-image{
  flex:1;/* Adjust the flex ratio as needed */
  display: flex;
  flex-direction: column;
  /* justify-content: center; Center the image horizontally */
  /* align-items: center; Center the image vertically  */
  /* padding:0.625rem;  */
}

.book-actions {
  display: flex;
  flex-direction: row;
  gap: 20px;
}

.book-actions .add-to-cart,
.book-actions .save-book,
.remove-book {
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
  font-family: "PT Sans", sans-serif;
}

/* popup design */
.added-to-cart-popup {
  position: fixed;
  top: 50%; /* Center vertically */
  left: 50%; /* Center horizontally */
  transform: translate(-50%, -50%); /* Adjust for the element's own size */
  padding: 10px 20px;
  background-color: white;
  color: #6b3630;
  border-radius: 5px;
  z-index: 1000;
  text-align: left; /* Optional: for text alignment inside the popup */
  width: 35%;
  height: 320px;
  
  /* max-height: 350px; */
  font-size: 20px;
  /* new properties */
  display: flex;
  flex-direction: column;
  max-height: none; /* Remove max height or make it larger */
  overflow: hidden; /* Hide overflow or use 'auto' to allow scrolling */
}

.bookshelf-popup {
  position: fixed;
  top:50%;
  left:50%;
  padding:10px 20px;
  color:#6b3630;
  text-align:left;
  z-index: 1000;
  width:19%;
  transform: translate(-50%, -50%);
  overflow: hidden;
  max-height:none;
  display:flex;
  flex-direction:column;
  background-color: white;
  font-size:14px;
}

.bookshelf-btn {
  display:flex;
  justify-content: center;
  margin-left:5rem;
  margin-right:5rem;
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent black */
  z-index: 999; /* Ensure it's below the popup but above other content */
}

.close-popup {
  position: absolute;
  top: 1rem;
  right: 0.6rem;
  border: none;
  background: none;
  color: gray; /* Or any color that matches your design */
  font-size: 0.8em; /* Adjust size as needed */
  cursor: pointer;
}

/* Optional: Add a hover effect for the close button */
.close-popup:hover {
  color: #555; /* Darken the color on hover */
}

.popup-book-image {
  /* max-width: 70px; Adjust as per your design */
  height: auto;
  margin: 10px 0;
  width: 140px;
  max-height: 200px;
}

.popup-content {
  display: flex;
  flex-direction: row;
  flex: 3;
  overflow: auto;
}

.popup-navbar {
  display: flex;
  flex-direction :row;
  /* align-items: right; */
  justify-content: right;
  max-height:30px;
  flex:0;
}

.go-to-cart-bar {
  background-color: antiquewhite;
}

.added-book-error {
  box-sizing: border-box;
  border-style :solid;
  background-color: rgb(226, 144, 144);
  border-color: rgb(226, 144, 144);
  margin-right:0.7rem;
}

@media (max-height: 600px) {
  .icon-background {
    bottom: 3%; /* Adjust for shorter screens */
  }
}
</style>