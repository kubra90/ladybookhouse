<template>
  <div class="container book-container d-flex flex-column flex-lg-row align-items-center justify-content-center">
    <div class="book-details mb-4 mb-lg-0">
      <p>
        <strong style="font-size: medium">{{ book.title }}</strong>
      </p>
      <p>{{ book.author }}</p>
      <p>{{ book.publisher }}. {{ book.media }}, {{ book.conditionAsText }}</p>
      <p>ISBN: {{ book.isbn }}</p>
      <p>{{ book.notes }}. {{ book.sku }}</p>
      <p>
        <strong>Price: ${{ formatPrice(book.price) }} </strong>
      </p>
      <p>{{ book.usedBook }}</p>
      <div class="container book-actions d-flex">
        <button @click="addToBasket" class="btn btn-primary btn-sm add-cart">
          <strong>Add To Cart</strong>
        </button>
        <button
          v-if="isAuthenticated"
          @click="addSavedBook"
          class="btn btn-primary btn-sm save-book"
        >
          <strong>Add to Bookshelf</strong>
        </button>
        <router-link v-else to="/login" class="btn btn-primary save-book"
          ><strong>Add to Bookshelf</strong></router-link
        >
      </div>
      <div v-if="showBookshelfPopup" class="overlay-bookshelf" @click="hidePopup"></div>
      <div v-if="showBookshelfPopup" class="bookshelf-popup">
        <div class="d-flex justify-content-between align-items-center">
          <p class="mb-3">This book added to your Bookshelf</p>
          <button
            type="button"
            class="btn-close mb-3"
            aria-label="Close"
            @click="hidePopup"
          ></button>
        </div>
        <button
          class="btn btn-primary btn-xs btn-bookshelf"
          style="text-align: center"
          @click="goToBookshelf"
        >
          <strong> Go to Bookshelf</strong>
        </button>
      </div>
    </div>

    <div class="new-image d-flex justify-content-center">
      <div class="book-image">
        <img v-bind:src="book.image" />
      </div>
    </div>

    <div v-if="showAddedToCart" class="overlay" @click="hidePopup"></div>
<div v-if="showAddedToCart" class="added-to-cart-popup p-4">
  <div class="d-flex justify-content-between align-items-center">
    <div v-if="showErrorMessage" class="alert alert-warning" role="alert" style="height:auto;">
      You've already added this book to the cart.
    </div>
    <div v-else class="fw-bold fs-5">Added to Cart</div>
    <button type="button" class="btn-close" aria-label="Close" @click="hidePopup"></button>
  </div>
  <div class="d-flex align-items-center mt-3">
    <img :src="book.image" class="img-fluid me-3" style="width: 100px; height: auto;" />
    <div>
      <h5 class="mb-0 book-title">{{ book.title }}</h5>
      <p class="mb-1">{{ book.author }}</p>
      <p class="fw-bold">Price: ${{ formatPrice(book.price) }}</p>
    </div>
  </div>
  <div class="mt-3 text-end">
    <button @click="hidePopup" class="btn btn-outline-primary btn-sm me-2" style="color:#6b3630; border-color:#6b3630;">CONTINUE SHOPPING</button>
    <button @click="goToCart" class="btn btn-primary btn-sm" style="color:#6b3630; background-color:antiquewhite;border:none;">GO TO CART</button>
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
      loginRequiredMessage: false,
    };
  },
  computed: {
    ...mapState(["book", "user", "savedBooks", "cartBooks", "savedBook"]),
    ...mapGetters(["isAuthenticated"]),
    // consider computed method to preserve book across navigation
    isBookInCart() {
      return this.cartBooks.some((book) => book.isbn === this.book.isbn);
    },
  },
  methods: {
    ...mapActions(["addToCart", "fetchBookById", "addBookToBookshelf"]),
    formatPrice(value) {
      const formattedPrice = Number(value).toFixed(2);
      return formattedPrice;
    },

    addSavedBook(sku) {
      if (this.isAuthenticated) {
        sku = this.book.sku;
        console.log(sku);
        this.addBookToBookshelf(sku);
        this.showBookshelfPopup = true;
      } else {
        this.$router.push({
          name: "login",
          query: { loginRequired: true },
        });
      }
    },

    addToBasket() {
      this.showAddedToCart = true;
      if (this.book.qty > 0 && !this.isBookInCart) {
        this.addToCart(this.book);
        this.numOfBooks++;
        this.book.qty--;
        this.showErrorMessage = false;
        // this new method;
        this.showModal();
      } else {
        this.showErrorMessage = true;
      }
    },
    hidePopup() {
      this.showAddedToCart = false;
      this.showBookshelfPopup = false;
    },
    goToCart() {
      this.$router.push({ name: "cart" });
    },
    goToBookshelf() {
      if (this.isAuthenticated) {
        this.$router.push({ name: "saved-books" });
      }
    },
  },
  created() {
    this.fetchBookById(this.$route.params.sku);
  },
};
</script>

<style scoped>

/* Overlay Style */
.overlay-bookshelf {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent black */
  z-index: 999; /* Ensure it's below the popup but above other content */
}
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1050;
}

/* Added to Cart Popup Style */
.added-to-cart-popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 1051;
  background: white;
  border-radius: 0.3rem;
  width: auto;
  max-width: 500px;
  padding: 1rem;
}

/* Popup Image Style */
.popup-book-image {
  width: 100px;
  height: auto;
}

/* Popup Margin and Padding Adjustments */
.me-3 {
  margin-right: 1rem; /* Bootstrap 5 uses margin utilities like me-3. You can adjust the value or keep it for consistency */
}

.mt-3 {
  margin-top: 1rem;
}

.mb-0, .mb-1 {
  margin-bottom: 0;
  margin-bottom: 0.25rem;
}

/* Text End Alignment */
.text-end {
  text-align: right;
}

.book-container {
  /* box-sizing: border-box;
  display: flex; */
  /* flex-direction: row; */
  /* flex-direction: column; */
  font-family: "PT Sans", sans-serif;
  /* padding: 5.5rem 10rem 2rem 13rem;
  min-height: calc(10vh - 10px - 2px); */
}

.book-details {
  flex: 2; /* Allocate more space to the details */
  display: flex;
  flex-direction: column;
  padding-right: 6.25rem; 
}

.book-image {
  flex: 1; /* Adjust the flex ratio as needed */
  display: flex;
  justify-content: center; /* Center the image horizontally */
  align-items: center; /* Center the image vertically */
  /* padding:0.625rem;  */
  padding: 0;

  /* new styles */
  position: relative;
}

.book-image img {
  height: auto; /* Maintain aspect ratio */
  border: 1px solid #ddd; /* Add a border */
  border-radius: 1rem; /* Rounded corners */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Add a subtle shadow for depth */
  width: 18.5rem; /* Increase width for smaller screens */
  height: 25.75rem; /* Maintain aspect ratio */
  transition-duration: 1.25s;
}

.book-image img:hover {
  cursor: pointer;
  transform: scale(1.5);
}
.icon-background {
  width: 4.5rem;
  height: 3rem;
  position: absolute;
  top: 38%;
  right: 12%;
  /* left:83%; */
  /* z-index is to position the icon above the book */
  z-index: 1;
  /* border: 10px; */
  display: flex;
  justify-content: center;
  align-items: center;
  color: #6b3630;
}

.enlarger-icon {
  width: 100%;
  height: auto;
  cursor: pointer;
}

.new-image {
  flex: 1; /* Adjust the flex ratio as needed */
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
  /* position: relative;
  display: inline-block; */
  /* background: $button; */
  /* color: $white; */
  /* color: #6b3630;
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
  font-family: "PT Sans", sans-serif; */
}



.bookshelf-popup {
  position: fixed;
  top: 50%;
  left: 50%;
  padding: 10px 20px;
  color: #6b3630;
  text-align: left;
  z-index: 1000;
  width: 25%;
  /* max-width: 25%; */
  transform: translate(-50%, -50%);
  overflow: hidden;
  max-height: none;
  display: flex;
  flex-direction: column;
  background: white;
  font-size: 14px;
}

.bookshelf-popup .btn {
  font-size: 1rem; /* Adjust font size as necessary */
  padding: 0.5rem 1rem; /* Adjust padding to control the size */
  width: auto; /* Set width to auto or specify a width */
  max-width: 40%; /* Ensure it doesn't exceed its container's width */
  font-size: 12px;
  /* Add any other styling as necessary */
}

.btn-primary.btn-bookshelf {
  color: #6b3630;
  background-color: oldlace;
  border: none;
  outline: none;
  height: auto;
  font-size: 12px;
}

.btn-primary.save-book,
.btn-primary.add-cart,
.btn-primary.shopping,
.btn-primary.cart {
  color: #6b3630;
  background-color: oldlace;
  font-size: 14px;
  height: 120%;
  width: 30%;
  border: none;
  outline: none;
  height: auto;
}
.btn-close {
  /* position: absolute; */
  /* top: 1rem; */
  /* right: 0.6rem; */
  border: none;
  background-color: none;
  color: black;
  font-size: 0.8em;
  cursor: pointer;
}

/* Optional: Add a hover effect for the close button */
.close-popup:hover {
  color: #555; /* Darken the color on hover */
}



.popup-content {
  display: flex;
  flex-direction: row;
  flex: 3;
  overflow: auto;
}

.popup-navbar {
  display: flex;
  flex-direction: row;
  /* align-items: right; */
  justify-content: right;
  max-height: 30px;
  flex: 0;
}

.go-to-cart-bar {
  background-color: antiquewhite;
}

.added-book-error {
  box-sizing: border-box;
  border-style: solid;
  background-color: rgb(226, 144, 144);
  border-color: rgb(226, 144, 144);
  margin-right: 0.7rem;
}

@media (max-height: 600px) {
  .icon-background {
    bottom: 3%; /* Adjust for shorter screens */
  }
}

@media (min-width: 768px) {
  /* Adjust for medium screens and up */
  .book-container {
    flex-direction: row; /* Switch to horizontal layout on larger screens */
    padding: 3.5rem 7rem 1rem 4rem; /* Adjust padding for larger screens, can be fine-tuned */
  }
}
@media (min-width: 368px) {
  /* Adjust for small screens and up */
  .book-container {
    flex-direction: row; /* Switch to horizontal layout on small screens */
    padding: 2.5rem 0rem 2rem 1rem; /* Adjust padding for small screens, can be fine-tuned */
  }
  .bookshelf-popup {
    padding: 1rem 1rem 1rem 2rem;
    /* max-width: 80%; */
    width: 50%;
  }

  .btn-primary.save-book {
    width: 55%;
  }

  .bookshelf-popup.btn {
    font-size: 1rem; /* Adjust font size as necessary */
    padding: 0rem 0rem; /* Adjust padding to control the size */
    font-size: 12px;
    width: 50%;
  }
}

@media (min-width: 992px) {
  /* Adjust for large screens */
  .book-container {
    /* Original padding for large screens */
    /* padding: 5.5rem 10rem 2rem 13rem;  */
  }
  .bookshelf-popup {
    padding: 1rem 2rem 1rem 2rem;
    width: 30%;
  }
}
</style>