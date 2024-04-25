<template>
  <header class="header-container header-custom">
    <div class="container">
      <div
        class="row align-items-start justify-content-between px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5 px-xxl-0 mx-xxl-0"
      >
        <!-- Website Name and Navigation Links -->
        <div class="col-md-auto py-5 col-sm-12">
          <h1 class="mt-5 mb-0">Lady Bookhouse</h1>
        </div>

        <!-- Account/Cart Links and Search Bar -->
        <div class="col-md-3 custom-account">
	
          <div class="row">
            <div class="col-10 ps-2">
              <!-- Account/Cart Links -->
              <div class="d-flex align-items-center justify-content-end mt-2">
                <!-- Added mb-3 to create space between this section and the search bar -->
                <div class="dropdown" v-if="isAuthenticated">
                  <button
                    class="btn btn-secondary dropdown-toggle"
                    type="button"
                    data-bs-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                    style="font-size: 14px"
                  >
                    My Account
                  </button>
                  <ul class="dropdown-menu custom-menu">
                    <li>
                      <router-link class="menu-link" to="/account">
                        My Account
                      </router-link>
                    </li>
                    <li>
                      <router-link class="menu-link" to="/checkOrders">
                        My purchases
                      </router-link>
                    </li>
                    <li>
                      <router-link class="menu-link" to="/saved_books">
                        My bookshelf
                      </router-link>
                    </li>
                  </ul>
                </div>
                <router-link v-else to="/login" class="btn btn-sm btn-secondary"
                  >Your Account |
                </router-link>
                <router-link
                  v-bind:to="{ name: 'cart' }"
                  class="btn btn-sm btn-secondary"
                >
                  <i class="fas fa-shopping-cart me-1"></i>
                  <span>{{ basketCount }} items</span>
                </router-link>
                <router-link
                  v-if="isAuthenticated"
                  to="/logout"
                  class="btn btn-sm btn-secondary"
                  >Logout</router-link
                >
              </div>
            </div>
            <!-- Search Bar -->
            <div class="col-10">
              <!-- Changed to col-12 to make it full width and below account/cart links -->
              <form class="d-flex position-relative">
                <input
                  v-model="searchText"
                  type="text"
                  class="form-control me-2 search-box shadow-none m-1 p-1"
                  placeholder="Author, title, or keyword"
                  aria-label="Search"
                />
                <button
                  class="btn btn-outline-secondary position-absolute end-0 m-1 py-1"
                  type="submit"
                  @click.prevent="searchAllBookDetails"
                >
                  <i class="fas fa-search"></i>
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Responsive Navbar for Navigation Links -->
    <nav class="navbar navbar-expand-md navbar-light nav-custom">
      <div class="container">
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div
          class="collapse navbar-collapse px-md-5 mx-md-5 px-sm-0 mx-sm-0 px-xxl-0 mx-xxl-0"
          id="navbarNav"
        >
		
          <ul class="navbar-nav col-md-10">
            <li class="nav-item">
              <router-link v-bind:to="{ name: 'home' }" class="nav-link"
                >Home</router-link
              >
            </li>
            <li class="nav-item">
              <router-link
                v-bind:to="{ name: 'advanced-search' }"
                class="nav-link"
                >Search</router-link
              >
            </li>
            <li class="nav-item">
              <!-- <a href="/browse" class="nav-link">Browse</a> -->
              <router-link v-bind:to="{ name: 'browse' }" class="nav-link"
                >Browse</router-link
              >
            </li>
            <li class="nav-item">
              <router-link
                v-bind:to="{ name: 'new-arrivals-view' }"
                class="nav-link"
                >New Arrivals</router-link
              >
            </li>
            <li class="nav-item">
              <a href="/rare-books" class="nav-link">Rare Books</a>
            </li>
            <li class="nav-item">
              <router-link v-bind:to="{ name: 'about' }" class="nav-link"
                >About</router-link
              >
            </li>
            <li class="nav-item">
              <router-link v-bind:to="{ name: 'contact' }" class="nav-link"
                >Contact</router-link
              >
            </li>
          </ul>
          <!-- Instagram Icon -->
          <div class="col-md-2 link-instagram">
            <a
              href="https://www.instagram.com/ladybookhouse/"
              class="nav-link insta-icon "
              target="_blank"
              rel="noopener noreferrer"
            >
              <i class="fab fa-instagram"></i>
            </a>
          </div>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>
import { mapState, mapGetters } from "vuex";

export default {
	name: "header-page",
	data() {
		return {
			isClicked: false,
			searchText: ""
		}
	},
	methods: {
		toggleClick() {
			this.isClicked = !this.isClicked
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
					: true

				return filteredMatch
			})

			console.log(filteredDetails)
			for (let book of filteredDetails) {
				console.log(book.title)
				console.log(book.author)
			}
		}
	},
	computed: {
		...mapState(["basketCount", "user", "books"]),
		...mapGetters(["isAuthenticated"])
	}
}
</script>

<style scoped>
.header-custom {
  background-color: #fdf5e6;
  /* font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
		"Lucida Sans", Arial, sans-serif; */
  font-family: "PT Sans", sans-serif;
}

h1 {
  font-family: "PT Sans", sans-serif;
  font-weight: 600;
  color: #fa8072;
}

.dropdown-toggle:active,
.open .dropdown-toggle {
  background: #fff !important;
  color: #6b3630;
}

.custom-menu {
  height: fit-content;
}

.menu-link {
  color: #6b3630;
  padding: 0.8rem;
  text-decoration: none;
}

.menu-link:hover {
  color: #f36858;
}

.btn-outline-secondary {
  border: none;
  color: #6b3630;
  /* background-color: brown; */
  height: 2rem;
  margin-bottom: 2rem;
}



.btn-secondary {
  background: none;
  color: #6b3630;
  border: none;
  margin: 0rem;
}

.search-box{
  /* border: 2px solid #6b3630; */
  border: none;
  background-color: #fa8072;
  font-size: medium;
  font-family: "PT Sans", sans-serif;
  font-size:13px;
  
 
}

.search-icon {
  color: #6b3630;
  background: none;
}

.nav-custom {
  background-color: #fa8072;
}

.link-instagram {
	padding-left:7rem;
}

.nav-link {
  color: #6b3630;
  font-weight: 600;
  font-size: medium;
}

.custom-account {
	position:relative;
	
	
}

.insta-icon {
  /* display: grid;*/
  /* margin-left: 33em;  */
  place-content:center;

  background-color: white;
  border-radius: 50%;
  width: 2em;
  height: 2em;
  /* margin-right:16px; */
  align-items: center; 
  justify-content: center; 
  display:flex;
}
</style>
