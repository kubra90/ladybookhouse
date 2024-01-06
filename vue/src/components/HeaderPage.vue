<template>
  <header class="header-container">
    <div class="header-top">
      <!-- First Box: Account and Cart -->
      <div class="account-cart">
        <a href="/account">My Account  |</a>
        <i class="fa fa-shopping-cart"></i>
        <!-- <span class="cart-count">{{ basketCount }}</span> -->
        <a href="/cart">Cart <span class="cart-count">({{ basketCount }} items)</span></a>
      </div>

      <!-- Second Box: Search Bar -->
      <div class="search-bar">
        <input type="text" placeholder="Search...">
      </div>

      <!-- Third Box: App Name -->
      <div class="app-name"><p>Lady Bookhouse</p></div>

      <!-- Fourth Box: Navigation Links -->
      <nav class="header-nav">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <a href="/browse">Browse</a>
        <a href="/category">Category</a>
        <a href="/about">About</a>
        <a href="/contact">Contact</a>
      </nav>
    </div>
  </header>
</template>

  
  <script>
  import { mapState } from 'vuex'
  export default {
    name: "header-page",
    // Your script here
    computed: {
      ...mapState(['basketCount'])
    }
  }
  </script>
<style scoped>


.header-container {
  box-sizing:border-box;
  display: flex;
  flex-direction: column;
  background-color: oldlace;
  padding-right: 0px;
  width: 100%;
  top:0;
  margin-top:0px;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.header-top {
  display: flex;
  flex-direction: column; /* Change to column layout */
  align-items: flex-start;
  /* margin-right:0px; */
  /* padding: 10px; */
}
.account-cart, .search-bar, .app-name, .header-nav {
  width: 90%; /* Full width for each row */
  display: flex;
  /* justify-content: center; Center items horizontally */
 /* Padding for spacing between rows */
}

.account-cart, .search-bar{
  justify-content: right;
  padding-top:7px;
  padding-right:70px;
}

.fa-shopping-cart{
  margin-top: 8px;
  font-size: 16px;
}


.app-name {
   /* Adjust the font size as needed */
  /* font-weight: bold; */
  flex-grow: 1; /* Allows the app name to grow and take available space */
  /* Center the text if desired */
  padding-top: 15px;
  padding-right:40px;
  padding-left:100px;
  padding-bottom:20px;
  width: 100%; /* Ensures it spans the full width of the row */
  line-height: 1;
  /* font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif; */
  font-family:'PT Sans',sans-serif;
  font-weight: 400; /* Try a bolder weight */
  font-size:60px;
  height:50px;
  margin: 30px;
  color: #FA8072;
  
}

.header-nav {
  width: calc(100% - 130px - 2px);
  box-sizing:border-box;
  justify-content: left;
  background-color:#FA8072;
  padding-bottom: 3px;
  /* padding-right:40px; */
  /* Match the width of .app-name, considering its padding */
  margin-left: 130px; /* Align with .app-name */
  margin-right:80px;
  display:flex;
  flex-direction:row;
  gap:20px;
}


.account-cart a, .header-nav a {
  text-decoration: none;
  padding: 3px;
  margin:3px;
  /* color:	#B22222; */
  color: #6B3630;
}


/* Responsive adjustments for tablets and smaller desktops */
@media (max-width: 768px) {
  .header-container {
    padding: 10px; /* Reduce padding */
  }

  .app-name {
    font-size: 40px; /* Reduce font size */
    padding-left: 20px; /* Adjust padding */
  }

  .header-nav {
    flex-wrap: wrap; /* Allow nav items to wrap */
    justify-content: center; /* Center the items */
    margin-left: 0; /* Reset margin */
    margin-right: 0;
  }

  .account-cart, .search-bar {
    padding-right: 20px; /* Adjust padding */
    justify-content: space-between; /* Adjust alignment */
  }
}

/* Responsive adjustments for mobile devices */
@media (max-width: 480px) {
  .header-top {
    flex-direction: column; /* Stack all items vertically */
    align-items: center; /* Center align items */
  }

  .app-name {
    font-size: 30px; /* Further reduce font size */
    padding: 10px 0; /* Adjust padding */
  }

  .header-nav {
    flex-wrap: wrap; /* Ensure nav items can wrap on small screens */
    justify-content: center; /* Center the items */
    gap: 10px; /* Adjust gap between items */
  }

  .account-cart, .search-bar {
    padding: 0; /* Remove padding */
    width: 100%; /* Full width */
  }

  /* Additional mobile adjustments */
}





</style>

