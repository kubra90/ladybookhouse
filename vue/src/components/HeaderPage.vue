<template>
	<header class="header-container header-custom">
		<div class="container">
			<div class="row align-items-center justify-content-between">
				<!-- Website Name and Navigation Links -->
				<div class="col-md-auto">
					<h1 class="mt-4 mb-0">Lady Bookhouse</h1>
				</div>

				<!-- Account/Cart Links and Search Bar -->
				<div class="col-md-auto">
					<div class="d-flex align-items-center">
						<!-- Account/Cart Links -->
						<div class="me-3">
							<router-link v-if="isAuthenticated" to="/account" class="btn btn-sm btn-secondary">My
								Account</router-link>
							<router-link v-else to="/login" class="btn btn-sm btn-secondary">Your Account
							</router-link>
							<router-link v-bind:to="{ name: 'cart' }" class="btn btn-sm btn-secondary">
								<i class="fas fa-shopping-cart me-1"></i>
								<span>{{ basketCount }} items</span>
							</router-link>
							<!-- <router-link v-if="basketCount = 0" v-bind:to="{ name: 'cart' }" class="btn btn-sm btn-secondary">
								<i class="fas fa-shopping-cart me-1"></i>
								<span>{{ basketCount }} items</span>
							</router-link> -->
							<router-link v-if="isAuthenticated" to="/logout" class="btn btn-sm btn-secondary">Logout</router-link>
						</div>

						<!-- Search Bar -->
						<div class="col-md-auto">
							<form class="d-flex position-relative">
								<input v-model="searchText" type="text" class="form-control me-2 search-box shadow-none m-2 p-1"
									placeholder="Search" aria-label="Search" />
								<button class="btn btn-outline-secondary position-absolute end-0 m-2" type="submit" @click.prevent="searchAllBookDetails">
									<i class="fas fa-search"></i>
								</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Responsive Navbar for Navigation Links -->
		<nav class="navbar navbar-expand-md navbar-light nav-custom mt-4">
			<div class="container">
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item">
							<router-link v-bind:to="{ name: 'home' }" class="nav-link">Home</router-link>
						</li>
						<li class="nav-item">
							<router-link v-bind:to="{ name: 'advanced-search' }" class="nav-link">Search</router-link>
						</li>
						<li class="nav-item">
							<!-- <a href="/browse" class="nav-link">Browse</a> -->
							<router-link v-bind:to="{name: 'browse'}" class="nav-link">Browse</router-link>
						</li>
						<li class="nav-item">
							<router-link v-bind:to="{ name: 'new-arrivals-view' }" class="nav-link">New Arrivals</router-link>
						</li>
						<li class="nav-item">
							<a href="/rare-books" class="nav-link">Rare Books</a>
						</li>
						<li class="nav-item">
							<router-link v-bind:to="{ name: 'about' }" class="nav-link">About</router-link>
						</li>
						<li class="nav-item">
							<router-link v-bind:to="{ name: 'contact' }" class="nav-link">Contact</router-link>
						</li>
						<!-- Instagram Icon -->
						<div class="insta-icon">
							<a href="https://www.instagram.com/ladybookhouse/" class="nav-link" target="_blank"
								rel="noopener noreferrer">
								<i class="fab fa-instagram"></i>
							</a>
						</div>
					</ul>
				</div>
			</div>
		</nav>
	</header>
</template>

<script>
import { mapState, mapGetters } from "vuex"

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

	created() {
		this.$store.dispatch("fetchBooks")
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
	font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
		"Lucida Sans", Arial, sans-serif;
}

h1 {
	font-family: "PT Sans", sans-serif;
	font-weight: 600;
	color: #fa8072;
}

.btn-outline-secondary {
	border: none;
	color: orange;
	background-color: brown;
	height: 2.2rem;
}

.btn-secondary {
	background: none;
	color: #6b3630;
	border: none;
}

.search-box,
.search-box:active {
	border: 2px solid #6b3630;
}

.search-icon {
	color: #6b3630;
}

.nav-custom {
	background-color: #fa8072;
}

.nav-link {
	color: #6b3630;
	font-weight: 600;
	font-size: medium;
}

.insta-icon {
	display: grid;
	place-content: center;
	margin-left: 33em;
	background-color: white;
	border-radius: 50%;
	width: 2em;
	height: 2em;
}
</style>
