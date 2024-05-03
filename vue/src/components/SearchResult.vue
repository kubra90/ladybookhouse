<template>
  <div class="container">
    <div
      class="row px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5 px-xxl-0 mx-xxl-0 my-4"
    >
      <!-- Filter Sidebar -->
      <div class="col-md-3" style="border-right: 1px solid #ccc">
        <!-- <div class="mb-3 mt-2">
          <label for="searchBox" class="form-label"
            >Search within these results:</label
          >
          <input type="text" id="searchBox" class="form-control shadow-none" />
        </div> -->
        <div class="mb-3 mt-2">
  <label for="searchBox" class="form-label">Search within these results:</label>
  <div class="input-group">
    <input type="text" id="searchBox" class="form-control shadow-none" placeholder="Type here..." v-model="searchDetail">
    <button class="btn btn-secondary" type="button" id="goButton" style="background-color: dark gray;"
    @click="updateQueryParams">Go</button>
  </div>
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
            <input type="radio" id="new" name="condition" value="NEW" v-model="condition"/>
            <label for="NEW">New  <span>({{ getNewBookNumbersByCategory }} item) </span>
           
            </label>
          </div>
          <div>
            <input
              type="radio"
              id="used"
              name="condition"
              value="USED"
              checked
              v-model="condition"
            />
            <label for="USED">Used</label>
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
                value="paperback"
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
              value="anyPrice"
              v-model="price"
              checked
            />
            <label for="all-price">Any Price</label>
          </div>
          <div>
            <input type="radio" id="lessPrice" name="price" value="under50" v-model="price" />
            <label for="underFifty">Under $50</label>
          </div>
          <div>
            <input type="radio" id="over50" name="price" value="over50" v-model="price"/>
            <label for="overFifty">$50 - $150</label>
          </div>
          <div>
            <input type="radio" id="over150" name="price" value="over150" v-model="price" />
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
            <select class="form-select shadow-none" id="sortby" v-model="sortByOption">
              <option value="author">Author</option>
              <option value="title">Title</option>
              <option value="highestPrice">Highest Price</option>
              <option value="lowestPrice">Lowest Price</option>
              <option value="mostRecent">Most Recent</option>
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
                <li class="page-item">
                  <a
                    class="page-link"
                    v-if="currentPage > 1"
                    @click="goToPage(currentPage - 1)"
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
          <button @click="addToBasket(book)" class="btn btn-secondary cart-btn">
          Add To Cart</button>
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
      condition: 'USED',
      currentPage: 1,
      booksPerPage: 25,
      sortByOption : "author",
      searchDetail: '',
      filteredBooks: [],
      binding: "all",
      subBinding: null,
      selectedCategory: "",
      numOfBooks:0,
      newBook:0,
      priceRange: {
        anyPrice: {min : null, max: null},
        under50 : {min: 0, max: 50},
        over50: {min: 50, max: 150},
        over150: {min:150, max: 1000}
      },
  
      price: 'anyPrice'
    
    };
  },
  watch: {
    
    price() {
    if (this.getPriceRange) { // Only perform search if the price range actually matches the query, otherwise just update the query
      this.performSearch();
    } else {
      this.updateQueryParams(); // This method could update the URL without fetching data
    }
  },
    
    binding(newValue) {
    if (newValue === 'all') {
      this.subBinding = null; // Reset specific bindings when 'All bindings' is selected
      this.performSearch(this.$route.query)
    }
  },
  subBinding(newValue) {
    if (newValue) {
      this.binding = null; // Uncheck "All bindings" when a specific binding is selected
      this.performSearch(this.$route.query)
    }
  },

    // '$route'(to, from) {
    //   if(to.name === 'advanced-search-view'){
    //     console.log("this query to advanced search", to.query)
    //     this.$router.push({name: 'advanced-search-view'})
    //   }else if (to.query !== from.query) {
    //     console.log("this query to", to.query)
    //     this.performSearch(to.query);
       
    //   }
    // },
    '$route'(to, from) {
    // Check if the route has changed to 'advanced-search-view'
    if (to.name === 'advanced-search-view' && from.name !== 'advanced-search-view') {
      console.log("Navigated to advanced search with query", to.query);
      // Optionally reset data here if needed
      this.resetData();
    }

    // Perform search only if the actual query parameters have changed
    if (this.queryParamsChanged(to.query, from.query)) {
      console.log("Query changed, performing search with new query:", to.query);
      this.performSearch(to.query);
    }
  },

    booksPerPage(newValue, oldValue) {
      if (newValue != oldValue) {
        this.booksPerPage = newValue;
      }
    },
    sortByOption(newValue, oldValue){
      if(newValue != oldValue){
        this.performSearch(this.$route.query);
      }
    },
    condition(newValue, oldValue){
      if(newValue != oldValue){
        console.log("new VAlue", newValue, "oldValue:", oldValue)
        this.performSearch(this.$route.query)
      }
    }
  },



  created() {
    const queryParams = this.$route.query;
    console.log(queryParams);
    //   // Perform your search logic using these query parameters
    return this.performSearch(queryParams);
  },

  computed: {
    ...mapState(["book", "books", "cartBooks"]),
    ...mapGetters(["getCategories"]),

    getPriceRange(){
  const range = this.priceRange[this.price];
  const queryParams = this.$route.query;
  return Number(queryParams.minPrice) === range.min && Number(queryParams.maxPrice) === range.max;
},
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
    },
    getNewBookNumbersByCategory() {
    // Filter books that are both 'new' and belong to the specified category
    const newBooksInCategory = this.books.filter(book => book.usedBook === 'NEW' && book.category === this.currentCategory);
    return newBooksInCategory.length;
  },
  
  },
  methods: {
    resetData() {
    // Reset simple string variables
    this.searchDetail = '';
    this.selectedCategory = '';
    
    // Reset numeric variables
    this.currentPage = 1;
    
    // Reset complex objects or forms
    this.searchForm = {
      author: '',
      title: '',
      keywords: '',
      minPrice: '',
      maxPrice: ''
    };
    
    // Reset arrays, useful if you are managing a list or a set of selections
    this.filteredBooks = [];
    
    // Reset booleans to their default states
    this.isLoading = false;
    
    // Reset selected options in dropdowns or similar components
    this.condition = 'USED';  // Assuming 'USED' is a default state
    this.price = 'anyPrice';  // Default selection for price
    this.binding = 'all';     // Default selection for binding
    
    // Clear any specific flags or messages

  },
    queryParamsChanged(newQuery, oldQuery) {
    return newQuery !== oldQuery;
  },
    needsUpdate(newQuery, oldQuery) {
        // Compare specific query parameters that trigger updates
        return newQuery.search !== oldQuery.search ||
               newQuery.filter !== oldQuery.filter;
    },
    ...mapActions(['addToCart']),
    performSearch(queryParams) {
      console.log(this.books);
      const priceFilter = this.priceRange[this.price];
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
                .includes(queryParams.keywords.toLowerCase()) ||
              book.title.toLowerCase().includes(queryParams.keywords.toLowerCase()) ||
              book.author.toLowerCase().includes(queryParams.keywords.toLowerCase()) 

            : true;
           const minPriceMatch = queryParams.minPrice
           ? book.price >= Number(queryParams.minPrice)
            : priceFilter.min === null || book.price>= priceFilter.min;
          const maxPriceMatch = queryParams.maxPrice
            ? book.price <= Number(queryParams.maxPrice)
            : priceFilter.max === null || book.price <= priceFilter.max;

          // const priceMatch = (priceFilter.min === null || book.price >= priceFilter.min) &&
          //                (priceFilter.max === null || book.price <= priceFilter.max);
          
          const conditionMatch = book.usedBook === this.condition;

          const mediaMatch = this.binding === 'all' ? true : this.subBinding ? book.media === this.subBinding : true;
        
          return (
            categoryMatch &&
            authorMatch &&
            titleMatch &&
            keywordsMatch &&
            minPriceMatch &&
            maxPriceMatch &&
            conditionMatch &&
            mediaMatch
          
          );
        });
      }
      // sorting function
      this.filteredBooks = this.sortBooks(this.filteredBooks, this.sortByOption);
      // this below is for debugging purposes.
      console.log(this.filteredBooks);
      for (let book of this.filteredBooks) {
        console.log(book.title);
      }
    },
    updateQueryParams() {
    // Logic to update query parameters
    console.log("Updating query parameters without search");
    this.$router.push({
      name: 'search-result-view',
      query: {
        ...this.$route.query,
        keywords: this.searchDetail,
        minPrice: this.priceRange[this.price].min,
        maxPrice: this.priceRange[this.price].max
      }
    });
  },

  sortBooks(books, sortByOption){
    switch(sortByOption){
      case "author":
        return books.sort((a,b) => a.author.localeCompare(b.author));
      case "title":
        return books.sort((a,b) => a.title.localeCompare(b.title));
      case "highestPrice":
        return books.sort((a,b)=> b.price - a.price);
      case "lowestPrice":
        return books.sort((a,b) => a.price - b.price);
      case "mostRecent":
        return books.sort((a,b) => new Date(b.listed_date) - new Date(a.listed_date))
  
      
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

  
    setCategoryAndSearch(category) {
      console.log("Method called with category:", category);
      this.$router.push({
        name: "search-result-view",
        query: { ...this.$route.query, category },
      });
      this.currentPage = 1;
      this.condition = "USED";
      this.price = 'anyPrice';
      this.sortByOption = 'author'
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
  background-color: transparent !important; 
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
  border-color: #c4742d; 
}

.form-label {
 
  color: #5d5a5a; /* Dark grey color for the text */
  font-weight: bold;
}

.pagination .page-link {
  border: none; 
  background: none;
  color: black; 
  margin-top: 0.2em;
  margin-bottom: 0;
  padding-top: 0;
  padding-bottom: 0;
  margin-right: 0;
  padding-right: 0;
  font-size: 12px;
}

.pagination .page-item.active .page-link {
  background-color: none; 
  color: orange;
}

.pagination .page-link:hover {
  background-color: none;
}

/* .cart-btn {
  background-color: #fa8072;
} */


.cart-btn{
background-color: #c74c2a; 
  color: white; 
  /* padding: 10px 20px; */
  font-size: 18px; 
  border-radius: 5px; 
  border: none; 
  box-shadow: 2px 2px 10px rgba(0,0,0,0.1); 
  cursor: pointer; 
  align-items:center;
  justify-content:center;
  width:100%;
  font-family: "PT Sans", sans-serif;
  font-size:16px
}

</style>


