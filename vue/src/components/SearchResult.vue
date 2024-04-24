<template>
  <div class="container">
    <div
      class="row px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5 px-xxl-0 mx-xxl-0"
    >
      <!-- Filter Sidebar -->
      <div class="col-md-3">
        <div class="mb-3">
          <label for="searchBox" class="form-label"
            >Search within these results:</label
          >
          <input type="text" id="searchBox" class="form-control" />
        </div>
        <div class="mb-3">
          <strong>Select Category</strong>
          <ul class="list-group">
            <li v-for="category in categories" :key="category.value"
               class="list-group-item list-group-item-action no-border no-hover me-2"
               @click="setCategoryAndSearch(category.value)">
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
            <input type="radio" id="all-binding" name="mainBinding" value="all" v-model="binding" checked/>
            <label for="all-binding">All bindings</label>
          </div>
          <div>
            <div class="ms-3">
              <input type="radio" id="hardcover" name="subBinding" value="hardcover" v-model="subBinding" />
              <label for="hardcover">Hardcover</label>
            </div>
            <div class="ms-3">
              <input type="radio" id="softcover" name="subBinding" value="softcover" v-model="subBinding" />
              <label for="softcover">Softcover</label>
            </div>
          </div>
          </div>
        <div class="mb-3">
          <strong>Select Price</strong>
          <!-- Radio buttons for price options -->
          <div>
            <input type="radio" id="any-price" name="price" value="allPrice" checked/>
            <label for="all-price">Any Price</label>
          </div>
          <div>
          
              <input type="radio" id="lessPrice" name="price" value="under50" />
              <label for="underFifty">Under $50</label>
          </div>
            <div>
              <input type="radio" id="over50" name="price" value="over50"  />
              <label for="overFifty">$50 - $150</label>
            </div>
            <div>
              <input type="radio" id="over150" name="price" value="over150"  />
              <label for="overHundred">$150 - $1000</label>
            </div>
  
        </div>
      </div>

      <!-- Main Content Area -->
      <div class="col-md-9"></div>
    </div>
  </div>
</template>
  
 <script>
import { mapState, mapGetters } from "vuex";
export default {
  name: "search-result",
  data() {
    return {
      filteredBooks: [],
      binding: "all",
      subBinding: null,
      selectedCategory: ''
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
    '$route'(to, from) {
    if (to.query !== from.query) {
      this.performSearch(to.query);
    }
  }
  
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
    ...mapState(["book", "books"]),
    ...mapGetters(['getCategories']),
    categories(){
      return this.getCategories
    }
  },
  methods: {
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
  },

  updateMainBinding(selectedBinding) {
    if (selectedBinding === "hardcover" || selectedBinding === "softcover") {
      this.binding = null;
    }
  },
  setCategoryAndSearch(category) {
    
    console.log("Method called with category:", category);
    this.$router.replace({ name: 'search-result-view', query: { ...this.$route.query, category } });

  }
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
}
</style>


