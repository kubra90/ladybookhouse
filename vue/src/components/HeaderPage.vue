<template>
  <header class="header-container">
    <div class="header-top">
      <!-- First Box: Account and Cart -->
      <div class="top-right-container">
        <div class="account-cart">
          <div class="dropdown" v-if="isAuthenticated">
            <button
              class="btn btn-secondary btn-sm dropdown-toggle"
              type="button"
              data-bs-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              My Account 
            </button>
            <span>|</span>
            <ul class="dropdown-menu">
              <li><button class="dropdown-item" type="button"><router-link to="/account">My account</router-link></button></li>
    <li><button class="dropdown-item" type="button"> <router-link to="/checkOrders">Orders</router-link></button></li>
    <li><button class="dropdown-item" type="button">  <router-link to="/saved_books">Bookshelf </router-link></button></li>
            </ul>
          </div>

          <router-link v-else to="/login">Your account |</router-link>
          <router-link v-show="isAuthenticated" to="/logout"
            >Logout</router-link
          >
          <!-- <i class="fa fa-shopping-cart"></i> -->
          <router-link
            v-bind:to="{
              name: 'cart',
            }"
          >
            <i class="fa fa-shopping-cart"></i>
            <span class="cart-count">({{ basketCount }} items)</span>
          </router-link>
        </div>

      

        <div class="mb-3 custom-search-form">
          <!-- Bootstrap 5 no longer uses .form-group; .mb-3 is for margin -->
          <div class="input-group">
            <input
              v-model="searchText"
              type="text"
              class="form-control"
              placeholder="Author, title, or keyword"
              aria-label="Search"
            />
            <button
              class="btn btn-outline-secondary"
              type="submit"
              @click.prevent="searchAllBookDetails"
            >
              <i class="fas fa-search"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- Third Box: App Name -->
      <div class="app-name"><p>Lady Bookhouse</p></div>

      <!-- Fourth Box: Navigation Links -->
      <nav class="header-nav">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <!-- <a href="/search">Search</a> -->
        <router-link v-bind:to="{ name: 'advanced-search' }"
          >Search</router-link
        >
        <a href="/browse">Browse</a>
        <router-link v-bind:to="{ name: 'new-arrivals-view' }"
          >New Arrivals</router-link
        >
        <a href="/rare-books">Rare Books</a>
        <a href="/about">About</a>
        <router-link v-bind:to="{ name: 'contact' }">Contact</router-link>
        <!-- instagram icon -->
        <!-- <i class="fa fa-instagram"></i> -->
        <a
          href="https://www.instagram.com/ladybookhouse/"
          class="account-instagram"
          target="_blank"
          rel="noopener noreferrer"
        >
          <i class="fab fa-instagram"></i>
        </a>
      </nav>
    </div>
  </header>
</template>

  
  <script>
import { mapState, mapGetters } from "vuex";
export default {
  name: "header-page",
  data() {
    return {
      isClicked: false,
      searchText: "",
    };
  },
  methods: {
    toggleClick() {
      this.isClicked = !this.isClicked;
    },

    searchAllBookDetails() {
      const filteredDetails = this.books.filter((book) => {
        const filteredMatch = this.searchText
          ? book.author.toLowerCase().includes(this.searchText.toLowerCase()) ||
            book.title.toLowerCase().includes(this.searchText.toLowerCase()) ||
            book.isbn === this.searchText ||
            book.publisher
              .toLowerCase()
              .includes(this.searchText.toLowerCase()) ||
            book.category.toLowerCase().includes(this.searchText.toLowerCase())
          : true;

        return filteredMatch;
      });

      console.log(filteredDetails);
      for (let book of filteredDetails) {
        console.log(book.title);
        console.log(book.author);
      }
    },
  },

  created() {
    this.$store.dispatch("fetchBooks");
  },

  // Your script here
  computed: {
    ...mapState(["basketCount", "user", "books"]),
    ...mapGetters(["isAuthenticated"]),
  },
};
</script>

<style scoped>
/* Adjusted Styles */

.btn-outline-secondary{
  border: none;
  color: orange;
  background-color:brown;
  height:2rem;
}

.header-container {
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  width: 100%;
}

.header-top {
  width: 100%;
  display: flex;
  flex-direction: column;
  /* align-items: center; */
}

.top-right-container {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items:end;
  gap: 5px; /* Adjust gap between account/cart and search form */
}

.custom-search-form {
  width: 16%;
  display: flex;
  justify-content: flex-end;
  margin-right:6rem;

 
}

.btn-secondary{
  background:none;
  color:#6b3630;;
  border:none;
  
}

.form-control {
  font-size:12px;
}

/* new 2222 */

/* .main {
  width: 50%;
  margin: 50px auto;
} */

.header-container {
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  background-color: #fdf5e6;
  padding-right: 0px;
  width: 100%;

  /* width: 100vw;
  height: 15vw; */
  top: 0;
  margin-top: 0px;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
}

.header-top {
  display: flex;
  flex-direction: column; /* Change to column layout */
  /* align-items: flex-start; */
  /* margin-right:0px; */
  /* padding: 10px; */
}
.account-cart,
.app-name,
.fa-instagram,
.header-nav {
  width: 90%; /* Full width for each row */
  display: flex;
  flex-direction:row;
}

.account-cart,
.search-bar {
  justify-content: right;
  padding-top: 7px;
  padding-right: 70px;
}

/* search icon updated responsive css */
/* .search-box {
  max-width: 200px; /* Maximum width */
/* width: 100%; /* Make it flexible */
/* border: 2px solid #6b3630;
  cursor: pointer; */
/* 
.search-bar {
  position: relative;
  display: flex;
  align-items: center;
} */

.fa-instagram {
  font-size: 1em;
  color: #6b3630;
  margin-left: 33em;
  margin-right: 3em;

  /* Circle background */
  background: white;
  border-radius: 50%;
  width: 1.6em;
  height: 1.6em;
  /* padding: 0.5em;  */
  margin-top: 0.18em;
  align-items: center;
  justify-content: center; /* Horizontally center the content */
}

.search-bar .search-box.clicked {
  /* border-color: #6B3630; */
  border: 2px solid #6b3630;
}

.search-icon {
  position: absolute;
  right: 8rem;
  color: #6b3630;
  margin-left: auto;
}
/* responsive design for the smaller screen */
/* @media screen and (max-width: 600px) { */
@media screen and (max-width: 768px) {
  .search-box {
    max-width: 100%; /* Allow it to expand */
    width: 40%; /* Take more width on smaller screens */
  }

  .search-icon {
    /* Adjust icon size or position if needed */
    margin-left: auto;
  }
}

/* until here the search bar css */

.fa-shopping-cart {
  margin-top: 2px;
  font-size: 16px;
}

.cart-count {
  margin-left: 3px;
  font-size: 12px;
}

.app-name {
  /* Adjust the font size as needed */
  /* font-weight: bold; */
  flex-grow: 1; /* Allows the app name to grow and take available space */
  /* Center the text if desired */
  padding-top: 15px;
  padding-right: 40px;
  padding-left: 130px;
  padding-bottom: 20px;
  width: 100%; /* Ensures it spans the full width of the row */
  line-height: 1;
  /* font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif; */
  font-family: "PT Sans", sans-serif;
  font-weight: 400; /* Try a bolder weight */
  font-size: 60px;
  height: 50px;
  margin-bottom: 30px;
  color: #fa8072;
}

.header-nav {
  width: calc(100% - 110px - 20px);

  box-sizing: border-box;
  justify-content: left;
  background-color: #fa8072;
  padding-bottom: 3px;
  /* padding-right:40px; */
  /* Match the width of .app-name, considering its padding */
  margin-left: 130px; /* Align with .app-name */
  display: flex;
  flex-direction: row;
  gap: 20px;
}

.account-cart a,
.header-nav a {
  text-decoration: none;
  padding: 3px;
  margin: 3px;
  /* color:	#B22222; */
  color: #6b3630;
}

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

  .account-cart {
    padding-right: 20px; /* Adjust padding */
    justify-content: space-between; /* Adjust alignment */
  }
}

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

  .account-cart {
    padding: 0; /* Remove padding */
    width: 100%; /* Full width */
  }
}
</style>

