<template>
  <div class="container">
    <div
      class="row px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5 px-xxl-0 mx-xxl-0 my-4"
    >
      <!-- Filter Sidebar -->
      <div class="col-md-3" style="border-right: 1px solid #ccc">
        <div class="mb-3 mt-2">
          <label for="searchBox" class="form-label"
            >Search within these results:</label
          >
          <input type="text" id="searchBox" class="form-control shadow-none" />
        </div>
        <hr />

        <div class="mb-3">
          <div class="mb-2"><b>Select Category</b></div>
          <ul class="list-group">
            <li
              v-for="category in categories"
              :key="category.value"
              class="list-group-item list-group-item-action no-border no-hover me-2"
              :class="{
                'highlight-category': isCategorySelected(category.value),
              }"
              @click="setCategoryAndSearch(category.value)"
            >
              {{ category.text }}
            </li>
          </ul>
        </div>
        <div class="mb-3">
          <b>Select Condition</b>
          <div>
            <input type="radio" id="new" name="condition" value="new" />
            <label for="new">New</label>
          </div>
          <div>
            <input
              type="radio"
              id="used"
              name="condition"
              value="used"
              checked
            />
            <label for="used">Used</label>
          </div>
        </div>
        <div class="mb-3">
          <strong>Select Binding</strong>
          <div>
            <input
              type="radio"
              id="all-binding"
              name="mainBinding"
              value="all"
              v-model="binding"
              checked
            />
            <label for="all-binding">All bindings</label>
          </div>
          <div>
            <div class="ms-3">
              <input
                type="radio"
                id="hardcover"
                name="subBinding"
                value="hardcover"
                v-model="subBinding"
              />
              <label for="hardcover">Hardcover</label>
            </div>
            <div class="ms-3">
              <input
                type="radio"
                id="softcover"
                name="subBinding"
                value="softcover"
                v-model="subBinding"
              />
              <label for="softcover">Softcover</label>
            </div>
          </div>
        </div>
        <div class="mb-3">
          <strong>Select Price</strong>
          <!-- Radio buttons for price options -->
          <div>
            <input
              type="radio"
              id="any-price"
              name="price"
              value="allPrice"
              checked
            />
            <label for="all-price">Any Price</label>
          </div>
          <div>
            <input type="radio" id="lessPrice" name="price" value="under50" />
            <label for="underFifty">Under $50</label>
          </div>
          <div>
            <input type="radio" id="over50" name="price" value="over50" />
            <label for="overFifty">$50 - $150</label>
          </div>
          <div>
            <input type="radio" id="over150" name="price" value="over150" />
            <label for="overHundred">$150 - $1000</label>
          </div>
        </div>
      </div>

      <!-- Main Content Area -->
      <div class="col-md-9 mt-2">
        <!-- category name -->
        <div class="fs-2 fst-normal fw-lighter">
          {{ this.currentCategory }}
        </div>

        <hr class="my-0" />
        <!-- sort by and pagination -->
        <div class="d-flex justify-content-between align-items-center">
          <!-- Sort By Dropdown -->
          <div class="me-0 pe-0 mt-2">Sort by</div>
          <div class="ms-0 ps-0">
            <label for="sortby" class="form-label"></label>
            <select class="form-select shadow-none" id="sortby">
              <option>Author</option>
              <option>Title</option>
              <option>Highest Price</option>
              <option>Lowest Price</option>
              <option>Most Recent</option>
              <!-- Add more sorting options here -->
            </select>
          </div>

          <!-- Number of Items Per Page Dropdown -->
          <div>
            <label for="itemsPerPage" class="form-label"></label>
            <select
              class="form-select shadow-none"
              v-model="booksPerPage"
              id="itemsPerPage"
            >
              <option value="25">25 per page</option>
              <option value="50">50 per page</option>
            </select>
          </div>

          <!-- View Toggle Buttons (optional) -->
          <div class="mt-4">
            <!-- Replace with icons or images as needed -->
            <button class="btn" style="background-color: #fdf5e6">
              <i class="bi-grid-3x3-gap"></i>
            </button>
            <button class="btn" style="background-color: #fdf5e6">
              <i class="bi-list-ul"></i>
            </button>
          </div>
        </div>

        <!-- Pagination Component -->
        <nav aria-label="Page navigation example">
          <div class="d-flex justify-content-between mt-4">
            <!-- left part about results -->
            <div class="">
              Results {{ bookStart }} - {{ bookEnd }} (of
              {{ filteredBooks.length }})
              <span> {{ currentCategory }}</span>
              <!-- add small x icon  -->
            </div>
            <div class="pagination justify-content-end">
              <p>Page {{ currentPage }} of {{ totalPages }}</p>
              <ul class="pagination">
                <li class="page-item disabled">
                  <a
                    class="page-link"
                   
                    tabindex="-1"
                    aria-disabled="true"
                    >&lt;</a
                  >
                </li>
                <!-- less 10 -->
                <li class="page-item">
                  <a
                    class="page-link"
                    v-if="currentPage > 10"
                    @click="currentPage -= 10"
                    >&lt;&lt;</a
                  >
                </li>
                <!-- Dynamically generated page numbers here -->
                <li
                  class="page-item"
                  v-for="n in paginationNumbers"
                  :key="n"
                  :class="{ active: n === currentPage }"
                >
                  <a class="page-link" @click="goToPage(n)">{{ n }}</a>
                </li>
                <!-- if there is items more than 10 -->
                <li class="page-item">
                  <a
                    class="page-link"
                    v-if="
                      currentPage < totalPages && currentPage + 10 <= totalPages
                    "
                    @click="currentPage += 10"
                    >&gt;&gt;</a
                  >
                </li>
                <!-- till here -->
                <li class="page-item">
                  <a
                    class="page-link"
            
                    @click="goToPage(currentPage + 1)"
                    >&gt;</a
                  >
                </li>
              </ul>
            </div>
          
            
          </div>
        </nav>
        <hr class="my-0" />
          <!-- main body after pagination numbers -->
        <!-- <div class="container d-flex">
          <div class="row">
         <div class="d-flex justify-content-between" v-for="book in this.filteredBooks" :key="book.isbn">
          <div class="">

           {{ book.title }}
          </div>
          <div class="ms-auto">
           <button class="btn">Add to Cart</button>
         </div>
         </div>
        </div> -->
        <div class="container">
  <div class="row">
    <div class="col-12 my-3" v-for="book in visibleBooks" :key="book.isbn">
      <div class="d-flex justify-content-between align-items-start ">
        <!-- Book Image -->
        <div class="flex-shrink-0">
          <router-link :to="{name: 'detail', params: {sku: book.sku}}">
          <img :src="book.image" alt="Book Cover" class="img-fluid rounded-start" style="max-width: 120px; height: auto;">
        </router-link>
        </div>

        <!-- Book Title and Author -->
        <div class="flex-grow-1 mx-3" style="border-right: 1px solid #ccc;">
          <router-link :to="{name: 'detail', params: {sku: book.sku}}" style="text-decoration: none; color:black">
          <b><small>{{ book.author }}</small></b>
          <p>{{ book.title }}</p>
        </router-link>
           <p>{{ book.media }}. {{ book.publisher }}. ISBN: {{ book.isbn }} {{ book.conditionAsText }}. 
           <router-link :to="{name: 'detail', params: {sku: book.sku}}" style="color: orange; text-decoration:none">
            <span >More ></span>
          </router-link>
          </p>
        </div>

        <!-- Price and Add to Cart Button -->
        <div class="flex-shrink-0 ms-3">
          <!-- <button class="btn btn-primary">Add to Cart</button>
          <p class="mb-0">{{ book.price | currency }}</p> -->
          <button @click="addToBasket(book)" class="btn btn-primary  cart-btn">
          <strong>Add To Cart</strong></button>
           <div class="mt-2"><b>Price:</b>${{ book.price }}</div>
           <router-link :to="{name: 'detail', params: {sku: book.sku}}" style="text-decoration: none;color:#5d5a5a">
           <div class="mt-2">Item Details</div>
          </router-link>

          <!-- ask a question -->
          <div style="color:#5d5a5a">Ask a Question</div>
        </div>
      </div>
      <hr>
    </div>
  </div>
</div>

         
        </div>
      </div>
    </div>
</template>
  
 <script>
import { mapState, mapGetters, mapActions } from "vuex";
export default {
  name: "search-result",
  data() {
    return {
      currentPage: 1,
      booksPerPage: 25,
      filteredBooks: [],
      binding: "all",
      subBinding: null,
      selectedCategory: "",
      numOfBooks:0
      // This should be dynamic based on the category selected
      // ... Other data properties ...
    };
  },
  watch: {
    subBinding(newValue) {
      if (newValue === "hardcover" || newValue === "softcover") {
        this.binding = null; // This unchecks "All bindings"
      }
    },
    $route(to, from) {
      if (to.query !== from.query) {
        this.performSearch(to.query);
      }
    },

    booksPerPage(newValue, oldValue) {
      if (newValue != oldValue) {
        this.booksPerPage = newValue;
      }
    },
  },
  created() {
    const queryParams = this.$route.query;
    console.log(queryParams);
    //   // Perform your search logic using these query parameters
    return this.performSearch(queryParams);
    // return this.performSearch(this.$route.query);
  },
  // mounted() {
  //   console.log("Component mounted!");
  // },
  computed: {
    ...mapState(["book", "books", "cartBooks"]),
    ...mapGetters(["getCategories"]),
    categories() {
      return this.getCategories;
    },
    isBookInCart() {
     return this.cartBooks.some((book) => book.isbn === this.book.isbn)
    },
    currentCategory() {
      return this.$route.query.category;
    },
    totalPages() {
      return Math.ceil(this.filteredBooks.length / this.booksPerPage);
    },
    bookStart() {
      return (this.currentPage - 1) * this.booksPerPage + 1;
    },
    bookEnd() {
      let end = this.currentPage * this.booksPerPage;
      return end > this.filteredBooks.length ? this.filteredBooks.length : end;
    },
    paginationNumbers() {
      let startPage = Math.floor((this.currentPage - 1) / 10) * 10 + 1;
      let endPage = Math.min(startPage + 9, this.totalPages);
      return Array.from(
        { length: endPage - startPage + 1 },
        (v, k) => k + startPage
      );
    },
    visibleBooks(){
      let startIndex = (this.currentPage - 1) * this.booksPerPage;
      return this.filteredBooks.slice(startIndex, startIndex + this.booksPerPage);
    }
  },
  methods: {
    ...mapActions(['addToCart']),
    performSearch(queryParams) {
      console.log(this.books);
      if (this.books && this.books.length > 0) {
        console.log("after getting books");
        this.filteredBooks = this.books.filter((book) => {
          const categoryMatch = queryParams.category
            ? book.category === queryParams.category
            : true;
          const authorMatch = queryParams.author
            ? book.author
                .toLowerCase()
                .includes(queryParams.author.toLowerCase())
            : true;
          const titleMatch = queryParams.title
            ? book.title.toLowerCase().includes(queryParams.title.toLowerCase())
            : true;
          const keywordsMatch = queryParams.keywords
            ? book.isbn === queryParams.keywords ||
              book.publisher
                .toLowerCase()
                .includes(queryParams.keywords.toLowerCase()) ||
              book.media
                .toLowerCase()
                .includes(queryParams.keywords.toLowerCase())
            : true;
          const minPriceMatch = queryParams.minPrice
            ? book.price >= Number(queryParams.minPrice)
            : true;
          const maxPriceMatch = queryParams.maxPrice
            ? book.price <= Number(queryParams.maxPrice)
            : true;
          return (
            categoryMatch &&
            authorMatch &&
            titleMatch &&
            keywordsMatch &&
            minPriceMatch &&
            maxPriceMatch
          );
        });
      }
      console.log(this.filteredBooks);
      for (let book of this.filteredBooks) {
        console.log(book.title);
      }
    },

    goToPage(page) {
      this.currentPage = page;
    },

    //add to cart
    addToBasket(book) {
      //this.showAddedToCart = true;
      if (book.qty > 0 && !this.isBookInCart) {
        this.addToCart(book);
        this.numOfBooks++;
        book.qty--;
        // this.showErrorMessage = false;
        // this new method;
        // this.showModal();
      } 
      // else {
      //   this.showErrorMessage = true;
      // }
    },

    updateMainBinding(selectedBinding) {
      if (selectedBinding === "hardcover" || selectedBinding === "softcover") {
        this.binding = null;
      }
    },
    setCategoryAndSearch(category) {
      console.log("Method called with category:", category);
      this.$router.replace({
        name: "search-result-view",
        query: { ...this.$route.query, category },
      });
      this.currentPage = 1;
    },
    isCategorySelected(categoryValue) {
      return this.currentCategory === categoryValue;
    },
  },
};
</script>

<style scoped>
.no-border {
  border: none !important; /* Override Bootstrap styles */
  box-shadow: none !important; /* Remove any box-shadow if present */
  background-color: none;
}

.no-hover:hover {
  background-color: transparent !important; /* Ensures no color change on hover */
  text-decoration: underline;
  cursor: pointer;
}

/* New styles for reducing spacing */
.list-group-item {
  padding-top: 0rem; /* Reduce the top and bottom padding */
  padding-bottom: 0rem;
  margin-bottom: 0px; /* Reduce the space between items */
  margin: 0;
  padding: 0;
}

.highlight-category {
  color: orange;
}
#searchBox,
.form-select,
.btn {
  height: 30px; /* Smaller height */
  border-radius: 0; /* No border-radius */
  border: 1px solid #ccc; /* Subtle border color */
  outline: none; /* Removes the default focus highlight */
  font-size: 12px;
  font-family: "PT Sans", sans-serif;
}

* {
  font-family: "PT Sans", sans-serif;
}

#searchBox:focus,
.form-select:focus {
  border-color: #c4742d; /* Darker border when focused for better visibility */
}

.form-label {
  /* Optional: Makes the label text bold */
  /* font-weight: bold;   */
  color: #5d5a5a; /* Dark grey color for the text */
  font-weight: bold;
}

.pagination .page-link {
  border: none; /* Removes the border */
  background: none; /* Removes the background color */
  color: black; /* Bootstrap blue for link color, adjust as necessary */
  margin-top: 0.2em;
  margin-bottom: 0;
  padding-top: 0;
  padding-bottom: 0;
  margin-right: 0;
  padding-right: 0;
  font-size: 12px;
}

.pagination .page-item.active .page-link {
  background-color: none; /* Bootstrap blue for active page, adjust if needed */
  color: orange;
}

.pagination .page-link:hover {
  background-color: none; /* Light grey background on hover, optional */
}

/* .cart-btn {
  background-color: #fa8072;
} */


.cart-btn{
background-color: #c74c2a; /* Salmon color, keep it or change as needed */
  color: white; /* Ensures the text color is white for better readability */
  /* padding: 10px 20px; */
  font-size: 18px; /* Larger font size makes the button text more prominent */
  border-radius: 5px; /* Rounded corners, adjust the radius as you like */
  border: none; /* Remove border for a cleaner look */
  box-shadow: 2px 2px 10px rgba(0,0,0,0.1); /* Optional: adds a subtle shadow for a 3D effect */
  cursor: pointer; /* Reaffirm that this is a clickable button */
  align-items:center;
  justify-content:center;
  width:100%;
  font-family: "PT Sans", sans-serif;
  font-size:16px
}

</style>


