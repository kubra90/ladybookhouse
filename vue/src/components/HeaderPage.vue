<template>
  <header class="header-container">
    <div class="header-top">
      <!-- First Box: Account and Cart -->
      <div class="account-cart">
        <a href="/account">My Account</a>
        <a href="/cart">My Cart</a>
      </div>

      <!-- Second Box: Search Bar -->
      <div class="search-bar">
        <input type="text" placeholder="Search...">
      </div>

      <!-- Third Box: App Name -->
      <div class="app-name"><p>Lady Bookhouse</p></div>

      <!-- Fourth Box: Navigation Links -->
      <nav class="header-nav">
        <a href="/browse">Browse</a>
        <a href="/about">About</a>
        <a href="/contact">Contact</a>
      </nav>
    </div>
  </header>
</template>

  
  <script>
  export default {
    name: "header-page"
    // Your script here
  };
  </script>
<style scoped>
.header-container {
  display: flex;
  flex-direction: column;
  background-color: blanchedalmond;
}

.header-top {
  display: flex;
  flex-direction: column; /* Change to column layout */
  align-items: center;
  padding: 10px;
}

.account-cart, .search-bar, .app-name, .header-nav {
  /* width: 100%; Full width for each row */
  display: flex;
  /* justify-content: center; Center items horizontally */
 /* Padding for spacing between rows */
}

.account-cart, .search-bar{
  justify-content: right;
  padding:5px 0;
}



.app-name {
   /* Adjust the font size as needed */
  font-weight: bold;
  flex-grow: 1; /* Allows the app name to grow and take available space */
  text-align: center; /* Center the text if desired */
  padding: 40px 40px 40px 40px; /* Add some vertical padding */
  margin: 30px;
  width: 100%; /* Ensures it spans the full width of the row */
  line-height: 1.2;
  font-family: 'Roboto', sans-serif;
  font-weight: 700; /* Try a bolder weight */
}

.header-nav {
  justify-content: left;
  padding:0;
  margin: 0px;
  background-color:lightgoldenrodyellow;
}


.app-name {
  font-size: large;
  font-weight: bold;
}

.account-cart a, .header-nav a {
  text-decoration: none;
  padding: 5px;
  /* Additional styling for links */
}

</style>

