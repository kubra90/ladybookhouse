<template>
  <div class="book-container">
    <div class="book-details">
      <p>
        <strong>{{ book.author }}</strong>
      </p>
      <p>{{ book.title }} {{ book.isbn }}</p>
      <p>{{ book.publisher }}. {{ book.media }}, {{ book.conditionAsText }}</p>
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

        <button class="save-book"><strong>Save the book</strong></button>
      </div>
    </div>
    <div class="book-image">
      <img v-bind:src="book.image" />
    </div>
    <div v-if="showAddedToCart" class="overlay" @click="hidePopup"></div>
    <div v-if="showAddedToCart" class="added-to-cart-popup">
      Added to Cart
      <button class="close-popup" @click="hidePopup"><strong>x</strong></button>
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
          <button class="go-to-cart-bar">GO TO CART</button>
      </div>
    </div>
  </div>
</template>
  
<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "book-detail",
  data() {
    return {
      numOfBooks: 0,
      showAddedToCart: false,
    };
  },
  computed: {
    ...mapState(["book"]),
  },
  methods: {
    ...mapActions(["addToCart", "fetchBookById"]),
    formatPrice(value) {
      const formattedPrice = Number(value).toFixed(2);
      return formattedPrice;
    },
    addToBasket() {
      // this.numOfBooks++;
      // this.addToCart(this.numOfBooks);
      //show the popup
      // this.showAddedToCart = true;
      if(this.book.qty> 0) {
        this.addToCart(this.book);
        this.numOfBooks++;
        // show the popup
      this.showAddedToCart = true;
      this.book.qty--;
      }else {
        console.error("you have already added this book to the cart!");
      }
    },
    hidePopup() {
      this.showAddedToCart = false;
    },
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
  max-width: 300px;
  max-height: 400px;
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
  background-color: #e4e4e4;
  color: #6b3630;
  border-radius: 5px;
  z-index: 1000;
  text-align: left; /* Optional: for text alignment inside the popup */
  width: 35%;
  height: 280px;
  font-size: 20px;
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
  top: 10px;
  right: 10px;
  border: none;
  background: none;
  color: gray; /* Or any color that matches your design */
  font-size: 0.7em; /* Adjust size as needed */
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
  width: 120px;
}

.popup-content {
  display: flex;
  flex-direction: row;
}

.popup-navbar {
  display: flex;
  flex-direction :row;
  /* align-items: right; */
  justify-content: right;
}

.go-to-cart-bar {
  background-color: antiquewhite;
}
</style>