<template>
  <div class="main-container">
    <div class="container">
    <h2>Advanced Search</h2>
    <form class="form-horizontal">
        <!-- Categories -->
        <div class="mb-4 row"> <!-- Categories -->
          <label for="categorySelect" class="col-form-label col-sm-1">Search in:</label>
          <div class="col-sm-4">
            <select v-model="searchForm.category" class="form-select" id="categorySelect">
              <option value="">All Categories</option>
              <option v-for="category in categories" :key="category.value" :value="category.value">
                {{ category.text }}
              </option>
            </select>
          </div>
        </div>
        
        <div class="mb-4 row"> <!-- Author -->
          <label for="authorInput" class="col-form-label col-sm-1">Author:</label>
          <div class="col-sm-4">
            <input v-model="searchForm.author" type="text" class="form-control" id="authorInput" placeholder="Author Name">
          </div>
        </div>

        <div class="mb-4 row"> <!-- Title -->
          <label for="titleInput" class="col-form-label col-sm-1">Title:</label>
          <div class="col-sm-4">
            <input v-model="searchForm.title" type="text" class="form-control" id="titleInput" placeholder="Book Title">
          </div>
        </div>

        <div class="mb-4 row"> <!-- Keywords Option -->
          <label for="keywordInput" class="col-form-label col-sm-1">Keywords:</label>
          <div class="col-sm-4">
            <input v-model="searchForm.keywords" type="text" class="form-control" id="keywordInput" placeholder="Keywords">
          </div>
        </div>

        <div class="mb-3 row"> <!-- Price Option -->
          <label for="minPriceInput" class="col-form-label col-sm-1">Price:</label>
          <div class="col-sm-2">
            <div class="input-group">
              <span class="input-group-text">$</span>
              <input v-model="searchForm.minPrice" type="text" class="form-control" id="minPriceInput" placeholder="Min Range">
            </div>
          </div>
          <div class="col-sm-2">
            <div class="input-group">
              <span class="input-group-text">$</span>
              <input v-model="searchForm.maxPrice" type="text" class="form-control" id="maxPriceInput" placeholder="Max Range">
            </div>
          </div>
        </div>

        <div class="mb-5 row"> <!-- Search Button -->
          <div class="offset-sm-1 col-sm-10">
            <button type="submit" class="btn btn-primary" @click.prevent="searchBookDetails">Search</button>
          </div>
        </div>
     
    </form>
</div>

  </div>
</template>

<script>
import { mapState} from 'vuex'
export default {
    data() {
        return {
            searchForm: {
                category: '',
                author: '',
                title: '',
                keywords: '',
                minPrice: '',
                maxPrice: ''
            },
            categories: [
                {value: 'Literature', text: 'Literature'},
                {value: 'Science', text: 'Science'},
                {value: 'Social Science', text: 'Social Science'},
                {value: 'Art', text: 'Art'},
                {value: 'Bestseller', text: 'Bestseller'},
                {value: 'Technology', text: 'Technology'},
                {value: 'Language', text: 'Language'},
                {value: 'Religion&Philosophy', text: 'Religion&Philosophy'},
                {value: 'History&Memory', text: 'History&Memory'},
                {value: 'Design', text: 'Design'},
                {value: 'Kids', text: 'Kids'},
                {value: 'Other', text: 'Other'}
            ]
        }
    },

    computed: {
        ...mapState(['books', 'book']),
    },
    methods: {
        searchBookDetails(){
        
          const filteredBooks = this.books.filter(book => {

            // category filter
            const categoryMatch = this.searchForm.category ? book.category === this.searchForm.category : true;
            console.log(categoryMatch);
            // author filter
            const authorMatch = this.searchForm.author ? book.author.toLowerCase().includes(this.searchForm.author.toLowerCase()) : true;
            console.log(authorMatch);
            // Title filter
            const titleMatch = this.searchForm.title ? book.title.toLowerCase().includes(this.searchForm.title.toLowerCase()): true;

            // keywords filter
            const keywordsMatch = this.searchForm.keywords ? book.isbn === this.searchForm.keywords || book.publisher.toLowerCase().includes(this.searchForm.keywords.toLowerCase()) 
            || book.media.toLowerCase().includes(this.searchForm.keywords.toLowerCase()) : true;

            const minPriceMatch = this.searchForm.minPrice ? book.price >= Number(this.searchForm.minPrice) : true;
            const maxPriceMatch = this.searchForm.maxPrice ? book.price <= Number(this.searchForm.maxPrice): true;

            return categoryMatch && authorMatch && titleMatch && keywordsMatch && minPriceMatch && maxPriceMatch;
          });
          console.log(filteredBooks);
          for(let book of filteredBooks){
            console.log(book.title);
          }
        }
    }
}
</script>

<style scoped>
.main-container{
}

h2 {
  color: #e2907a;
  font-weight: 500;
  margin-top: 6rem;
  font-family: "PT Sans", sans-serif;
}

.form-horizontal {
    /* display:flex;
    flex-direction: column; */
/* max-width: 100%; */
  color: #dc8a73;
}


</style>