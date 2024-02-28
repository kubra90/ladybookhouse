<template>
  <div class="saved-books-container">
    <div class="saved-book-nav">
      <router-link v-bind:to="{ name: 'account' }">My Account</router-link>
      <span class="greater-sign">&gt;</span>
      <p>My Bookshelf</p>
    </div>
    <!-- {{ user.email }} -->
    <p class="title"><strong>My Bookshelf</strong></p>

    <div class="book-card-container">
      <div
        v-for="savedBook in savedBooks"
        :key="savedBook.sku"
        class="book-card"
      >
        <!-- <img v-bind:src="savedBook.image" alt="book image"> -->
        <router-link
          :to="{
            name: 'detail',
            params: {
              sku: savedBook.sku,
            },
          }"
        >
          <img v-bind:src="savedBook.image" alt="book image" />
        </router-link>
        <p>
          <strong>{{ savedBook.title }}</strong>
        </p>
        <p class="author">{{ savedBook.author }}</p>

        <div class="book-card-footer">
          <!-- <div class="cart">
            <i class="fa fa-shopping-cart" @click="addToBasket(savedBook)"></i>
  
          </div> -->
          <div class="fa fa-shopping-cart" @click="addToBasket(savedBook)">
             <span class="tooltip-text">Add to shopping cart</span>
          </div>
          <button class="footer-btn" @click="removeBook(savedBook.sku)">
            <i class="fa fa-heart"></i>
          </button>
          <span class="price">${{ formattedPrice(savedBook.price) }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState, mapGetters } from "vuex";
export default {
  name: "saved-books-page",
  data() {
    return {
      showAddedToCart: false,
    };
  },
  components: {},
  computed: {
    ...mapState(["savedBooks", "user", "savedBook", "cartBooks", "book"]),
    ...mapGetters(["isAuthenticated"]),
  },

  methods: {
    ...mapActions(["fetchBookshelf", "deleteBookFromBookshelf", "addToCart"]),
    formattedPrice(price) {
      return Number(price).toFixed(2);
    },
    removeBook(sku) {
      if (this.isAuthenticated) {
        console.log(sku);
        this.deleteBookFromBookshelf(sku);
        this.fetchBookshelf();
      }
    },
    addToBasket(book) {
      if (!this.isBookInBasket(book)) {
        this.addToCart(book);
        console.log(this.book);
      }
    },
    isBookInBasket(savedBook) {
      return this.cartBooks.some((book) => book.isbn === savedBook.isbn);
    },
  },
  created() {
    this.fetchBookshelf();
    // this.deleteBookFromBookshelf();
  },
};
</script>
<style scoped>
.saved-book-nav {
  display: flex;
  flex-direction: row;
  font-size: 11.5px;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

.saved-books-container {
  display: flex;
  flex-direction: column;
  padding-top: 2rem;
  padding-left: 13rem;
  padding-right: 9rem;
  /* margin-top:2rem; */
}

.greater-sign {
  font-size: 10px;
  padding: 0.2rem;
  padding-left: 0.6rem;
}

.fa-shopping-cart {
  background-color: white;
  color: orange;
  cursor: pointer;
  margin-left: 1rem;
}

.fa-heart {
  margin-right: 13rem;
}

/* new css */
.fa-shopping-cart {
  position: relative;
  display: inline-block;
  /* border-bottom: 1px dotted black; */
}

.fa-shopping-cart .tooltip-text {
  visibility: hidden;
  width: 120px;
  background-color: #555;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 5px 0;
  position: absolute;
  z-index: 1;
  bottom: 125%;
  left: 50%;
  margin-left: -60px;
  opacity: 0;
  transition: opacity 0.3s;
}

.fa-shopping-cart .tooltip-text::after {
  /* content: ""; */
  position: absolute;
  top: 100%;
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: #555 transparent transparent transparent;
}

.fa-shopping-cart:hover .tooltip-text {
  visibility: visible;
  opacity: 1;
}







img {
  height: auto; /* Maintain aspect ratio */
  width: 100%;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Add a subtle shadow for depth */
  width: 23.5rem; /* Increase width for smaller screens */
  height: 33.75rem; /* Maintain aspect ratio */
}

.footer-btn {
  font-size: 12px;
  color: orange;
  background-color: white;
  border: hidden;
  display: flex;
  padding-top: 0.3rem;

  /* flex:1;
 text-align:start;
 padding-right:1rem;
  margin-bottom:2rem; */
}

.title {
  font-size: 18px;
  padding-top: 2rem;
  color: brown;
}

.price {
  /* display:flex; */
  text-align: end;
  justify-content: end;
  margin-right: 1rem;
}

.book-card-footer {
  flex-direction: row;
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex: 1;
  /* padding-right:1rem; */
  margin-bottom: 2rem;
}

.author {
  display: flex;
  flex: 5; /*to fix price at the bottom of the card, think about more appropriate way*/
}

.book-card {
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  border-style: ridge;
  width: 24rem;
  height: auto; /*consider setting a min-height */
  /* padding:1rem; */
}

.book-card-container {
  /* flex-direction:row; */
  flex-wrap: wrap; /*this allows the child elements to wrap within the container   */
  display: flex;
  box-sizing: border-box;
  border-style: hidden;
  width: 100%;
  max-width: 140rem;
  height: auto;
  gap: 0.5rem;
}

@media (min-width: 768px) {
  .book-card-container {
    width: 100%;
  }
}

@media (min-width: 1024px) {
  .book-card-container {
    width: 100%;
  }
}

@media (min-width: 1200px) {
  .book-card-container {
    width: 100%; /* You can adjust this or keep it at 100% */
    max-width: 180rem; /* adjust this to fit your design needs */
  }
}
</style>
