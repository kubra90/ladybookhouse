<!-- <template>
  <div class="saved-books-container">
    <div class="saved-book-nav">
      <router-link v-bind:to="{ name: 'account' }">My Account</router-link>
      <span class="greater-sign">&gt;</span>
      <p>My Bookshelf</p>
    </div>
    <p class="title"><strong>My Bookshelf</strong></p>

    <div class="book-card-container">
      <div
        v-for="savedBook in savedBooks"
        :key="savedBook.sku"
        class="book-card"
      >
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
      
          <div class="fa fa-shopping-cart" :class="{'in-cart': isBookInBasket(savedBook)}" @click="addToBasket(savedBook)">
             <span class="tooltip-text" v-if="!isBookInBasket(savedBook)">Add to shopping cart</span>
          </div>
          <button class="footer-btn" @click="removeBook(savedBook.sku)">
            <div class="fa fa-heart">
              <span class="remove-tooltip-text">Remove book</span>
            </div>
          </button>
          <span class="price">${{ formattedPrice(savedBook.price) }}</span>
        </div>
      </div>
    </div>
  </div>
</template> -->

<template>
  <div class="container my-1" style="padding-left: 80px;">
    <div class="row">
      <div class="col-12">
    <div class="navigation-links" style="font-size:12px;">
      <router-link v-bind:to="{ name: 'account' }" class="btn btn-link" style="font-size:12px; padding-top: 3px;">My Account</router-link>
      <span class="greater-sign">&gt;</span>
      <span style="padding-left: 1px;">My bookshelf</span>
    </div>
    </div>
    </div>


    <h3 class="mb-3 mt-3">My Bookshelf</h3>

    <div class="row">
      <div v-for="savedBook in savedBooks" :key="savedBook.sku" class="col-sm-6 col-md-3 mb-4 col-xs-12" >
        <div class="card h-100">
          <router-link :to="{ name: 'detail', params: { sku: savedBook.sku } }">
            <img :src="savedBook.image" class="card-img-top" alt="book image">
          </router-link>
          <div class="card-body">
            <h6 class="card-title">{{ savedBook.title }}</h6>
            <p class="card-text">{{ savedBook.author }}</p>
          </div>
          <div class="card-footer d-flex justify-content-between align-items-center">
           <div>
          <button class="btn btn-light" :class="{'in-cart': isBookInBasket(savedBook)}" data-bs-toggle="tooltip" data-bs-placement="top" title="Add to shopping cart"
          @click="addToBasket(savedBook)">
  <i class="fas fa-shopping-cart"></i>
</button>
          <button class="btn btn-light" data-bs-toggle="tooltip" data-bs-placement="top" title="Remove Book" @click="removeBook(savedBook.sku)">
            <div class="fa fa-heart" style="color:orange">
              <!-- <span class="remove-tooltip-text"></span> -->
            </div>
          </button>
          </div>
            <span class="text me-2"><strong>${{ formattedPrice(savedBook.price) }}</strong></span>
          </div>
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

  mounted() {
    this.$nextTick(async () => {
      // Dynamically import Bootstrap JS to ensure it's available
      const bootstrap = await import('bootstrap');

      // Then initialize tooltips
      const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
      tooltipTriggerList.forEach(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
    });
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
<!-- <style scoped>
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

.in-cart {
  color:#228B22;
  
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
.fa-shopping-cart, .fa-heart {
  position: relative;
  display: inline-block;
}

.fa-shopping-cart .tooltip-text, .fa-heart .remove-tooltip-text {
  visibility: hidden;
  width: 140px;
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

.fa-shopping-cart .tooltip-text::after, .fa-heart .remove-tooltip-text::after {
  /* content: ""; */
  position: absolute;
  top: 100%;
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: #555 transparent transparent transparent;
}

.fa-shopping-cart:hover .tooltip-text, .fa-heart:hover .remove-tooltip-text {
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
</style> -->

 <style scoped>
.in-cart {
  color:#228B22;
  
} 

.btn-light.fa-heart:hover,
.btn-light.fa-heart:focus{
    background: none;
    border:none;
    outline:none
}

.btn-light {
  background: none;   
  border: none;       
}

</style>
