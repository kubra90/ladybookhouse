<template>
  <div class="main-container">
    <div class="container">
    <h1>Advanced Search</h1>
    <form class="form-horizontal">
        <!-- Categories -->
        <div class="form-group">
            <label for="categorySelect" class="col-sm-1 control-label">Search in:</label>
            <div class="col-sm-4">
                <select class="form-control" id="categorySelect">
                    <option value="">All Categories</option>
                    <!-- Populate categories here -->
                </select>
            </div>
        </div>
        
        <!-- Author -->
        <div class="form-group">
            <label for="authorInput" class="col-sm-1 control-label">Author:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="authorInput" placeholder="Author Name">
            </div>
        </div>

        <!-- Title -->
        <div class="form-group">
            <label for="titleInput" class="col-sm-1 control-label">Title:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="titleInput" placeholder="Book Title">
            </div>
        </div>

        <!-- Price Option -->
        <div class="form-group">
            <label for="priceOption" class="col-sm-1 control-label">Keywords:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="priceOption" placeholder="Price Range">
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
</script>

<style scoped>
.main-container{
    margin-left:12rem;
}
</style>