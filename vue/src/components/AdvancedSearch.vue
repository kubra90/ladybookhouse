<template>
  <div class="main-container">
    <div class="container">
    <h1>Advanced Search</h1>
    <form class="form-horizontal">
        <!-- Categories -->
        <div class="form-group">
            <label for="categorySelect" class="col-sm-1 control-label">Search in:</label>
            <div class="col-sm-4">
                <select v-model="searchForm.category" class="form-control" id="categorySelect">
                    <option value="">All Categories</option>
                    <option v-for="category in categories" :key="category.value">
                    {{ category.text }}
                    </option>
                    <!-- Populate categories here -->
                </select>
            </div>
        </div>
        
        <!-- Author -->
        <div class="form-group">
            <label for="authorInput" class="col-sm-1 control-label">Author:</label>
            <div class="col-sm-4">
                <input v-model="searchForm.author" type="text"  class="form-control" id="authorInput" placeholder="Author Name">
            </div>
        </div>

        <!-- Title -->
        <div class="form-group">
            <label for="titleInput" class="col-sm-1 control-label">Title:</label>
            <div class="col-sm-4">
                <input v-model="searchForm.title" type="text" class="form-control" id="titleInput" placeholder="Book Title">
            </div>
        </div>

        <!-- Keywords Option -->
        <div class="form-group">
            <label for="keywordInput" class="col-sm-1 control-label">Keywords:</label>
            <div class="col-sm-4">
                <input v-model="searchForm.keywords" type="text" class="form-control" id="keywordOption" placeholder="Keywords">
            </div>
        </div>

         <!-- Price Option -->
         <div class="form-group">
            <label for="priceInput" class="col-sm-1 control-label">Price:</label>
            <div class="col-sm-2">
                <div class="input-group">
                <span class="input-group-addon">$</span>
                <input v-model="searchForm.minPrice" type="text" class="form-control" id="priceOption" placeholder="Min Range">
            </div>
        </div>
            <div class="col-sm-2">
                <div class="input-group">
                <span class="input-group-addon">$</span>
                <input v-model="searchForm.maxPrice" type="text" class="form-control" id="priceOption" placeholder="Max Range">
            </div>
            </div>
        </div>

        <!-- Search Button -->
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </div>
    </form>
</div>

  </div>
</template>

<script>
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
    }
}
</script>

<style scoped>
.main-container{
    margin-left:12rem;
}
</style>