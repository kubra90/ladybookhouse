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
            <router-link v-bind:to="{name: 'terms-of-sale'}">Terms of Sale</router-link>
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
    height: 200px;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  }
  
  .footer-top {
    display: flex;
    flex-direction: row; 
    align-items: center;
    justify-content:space-around;
    /* margin-right:0px; */
    /* padding: 10px; */
  }
  .contact-info, .footer-items, .footer-policies, .footer-account {
    width: 90%; /* Full width for each row */
    display: flex;
    flex-direction: column;  
    flex-grow: 1;
    margin:0;
    padding:0;
    /* justify-content: center; Center items horizontally */
   /* Padding for spacing between rows */
  }

  .contact-info  {
    padding-left:100px;
  }

  .footer-items {
    padding-left: 20px;
  }

<<<<<<< HEAD
  .contact-info p {
    margin:0;
  }
  .address {
    padding-top: 15px;
  }
=======
      <nav class="footer-account">
        <router-link v-bind:to="{ name: 'cart' }">Shopping Cart</router-link>
        <router-link v-bind:to="{name: 'login'}">My account</router-link>
        <router-link v-bind:to="{name: 'register'}">Create Account</router-link>
        <a href="https://www.instagram.com/ladybookhouse/" class="account-instagram" target="_blank" rel="noopener noreferrer">
        <i class="fa fa-instagram"></i>
         </a>
      </nav>
    </div>
  </footer>
</template>
>>>>>>> main

  .footer-account {
    padding-right: 6em;
  }

<<<<<<< HEAD
=======
<style scoped>
.footer-container {
  box-sizing:border-box;
  display: flex;
  flex-direction: column;
  background-color: oldlace;
  padding: 30px 0;
  width: 100%;
  height: 200px;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  position: absolute;
  transform: translateY(100%);
}

.footer-top {
  display: flex;
  flex-direction: row; 
  align-items: center;
  justify-content:space-around;
}
.contact-info, .footer-items, .footer-policies, .footer-account {
  width: 90%;
  display: flex;
  flex-direction: column;  
  flex-grow: 1;
  margin:0;
  padding:0;
}

.fa-instagram {
    font-size: 1em;
    color: #6B3630;
    /* margin-left: 33em;
    margin-right: 3em; */

    /* Circle background */
    /* background: white;
    border-radius: 50%; 
    width: 1.6em; 
    height: 1.6em; 
    padding: 0.5em; 
     margin-top:0.18em; */
    /* display: flex;
    align-items: center; */
    /* justify-content: center; 
    Horizontally center the content */
}

.contact-info  {
  padding-left:100px;
}

.footer-items {
  padding-left: 20px;
}

.contact-info p {
  margin:0;
}
.address {
  padding-top: 15px;
}

.footer-account {
  padding-right: 6em;
}
>>>>>>> main

/* Responsive adjustments for smaller screens */
@media (max-width: 768px) {
  .footer-container {
    padding: 20px; /* Reduce padding */
  }

  .footer-top {
    flex-direction: column; /* Stack items vertically */
    align-items: flex-start; /* Align items to the start */
  }

  .contact-info, .footer-items, .footer-policies, .footer-account {
    width: 100%; /* Full width for each section */
    padding-left: 0; /* Remove additional padding */
    padding-right: 0;
    margin-top: 10px; /* Add some space between sections */
  }

  .footer-account {
    padding-right: 0; /* Reset padding */
  }
}

@media (max-width: 480px) {
  .footer-container {
    padding: 15px; /* Further reduce padding */
  }

  .contact-info p, .footer-items a, .footer-policies a, .footer-account a {
    font-size: 0.9em; /* Reduce font size for readability */
  }

  /* Additional adjustments for mobile view */
}


  </style>
  
  