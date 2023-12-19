<template>
    <header class="footer-container">
      <div class="footer-top">
        <!-- First Box: Account and Cart -->
        <div class="contact-info">
          <p class="address"><b>Lady Bookhouse</b></p>
          <p>375 Acorn Park Dr.</p>
          <p>Belmont, MA, 02478</p>
          <p><a href="mailto:ladybookhouse@gmail.com">ladybookhouse@gmail.com</a></p>
        </div>
  
        <!-- Second Box: Search Bar -->
        <div class="footer-items">
            <router-link v-bind:to="{name: 'book'}">Search our Inventory</router-link>
            <!-- new arrivals router link -->
            <router-link v-bind:to="{name: 'book'}">New Arrivals</router-link>
            <!-- featured items --> 
            <router-link v-bind:to="{name: 'book'}">Featured Items</router-link>

        </div>
  
        <!-- Third Column: About Us -->
        <div class="footer-policies">
            <router-link v-bind:to="{name: 'about'}">About us</router-link>
            <!-- create contact us link -->
            <router-link v-bind:to="{name: 'contact'}">Contact us</router-link>
            <router-link v-bind:to="{name: 'policies'}">Policies</router-link>
        </div>
  
        <!-- Fourth Box: account info-->
        <nav class="footer-account">
            <!-- go to shopping cart  -->
          <router-link v-bind:to="{ name: 'cart' }">Shopping Cart</router-link>
          <!-- my account -->
          <router-link v-bind:to="{name: 'login'}">My account</router-link>
          <!-- create account -->
          <router-link v-bind:to="{name: 'register'}">Create Account</router-link>
        </nav>
      </div>
    </header>
  </template>
  
    
    <script>
    export default {
      name: "footer-page"
      // Your script here
    };
    </script>
  <style scoped>
  
  
  .footer-container {
    box-sizing:border-box;
    height: 300px;
    display: flex;
    flex-direction: column;
    background-color: oldlace;
    padding: 30px;
    /* padding-right: 0px; */
    width: 100%;
    height: 150px;
    top:0;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  }
  
  .footer-top {
    display: flex;
    flex-direction: row; 
    align-items: center;
    /* margin-right:0px; */
    /* padding: 10px; */
  }
  .contact-info, .footer-items, .footer-policies, .footer-account {
    width: 90%; /* Full width for each row */
    display: flex;
    flex-direction: column;
    /* justify-content: center; Center items horizontally */
   /* Padding for spacing between rows */
  }

  .contact-info {
    padding-left:10px;
  }

  /* .address {
    padding: 20px;
  } */


  .footer-account {
    justify-content: right;
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
  

  
  </style>
  
  