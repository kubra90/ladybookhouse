<template>
  <footer class="footer mt-auto footer-custom" v-show="showComponent">
    <div class="container">
      <div class="row px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5 px-xxl-0 mx-xxl-0">
        <div class="col-md-3 mb-3 mt-4">
          <p><b>Lady Bookhouse</b></p>
          <p>375 Acorn Park Dr.</p>
          <p>Belmont, MA, 02478</p>
          <p><a href="mailto:ladybookhouse@gmail.com">ladybookhouse@gmail.com</a></p>
        </div>

        <div class="col-md-3 mb-3 mt-4">
          <ul class="list-unstyled">
            <li class="mb-2"><router-link class="link" v-bind:to="{ name: 'advanced-search-view' }">Search our
                Inventory</router-link>
            </li>
            <li class="mb-2"><router-link class="link" v-bind:to="{ name: 'new-arrivals-view' }">New
                Arrivals</router-link></li>
            <li class="mb-2"><router-link class="link" v-bind:to="{ name: 'featured-book-view' }">Featured
                Items</router-link></li>
          </ul>
        </div>

        <div class="col-md-3 mb-3 mt-4">
          <ul class="list-unstyled">
            <li class="mb-2"><router-link class="link" v-bind:to="{ name: 'about' }">About us</router-link></li>
            <li class="mb-2"><router-link class="link" v-bind:to="{ name: 'contact' }">Contact us</router-link></li>
            <li class="mb-2"><router-link class="link" v-bind:to="{ name: 'terms-of-sale' }">Terms of Sale</router-link>
            </li>
          </ul>
        </div>

        <div class="col-md-3 mb-3 mt-4">
          <ul class="list-unstyled">
            <li class="mb-2"><router-link class="link" v-bind:to="{ name: 'cart' }">Shopping Cart</router-link></li>
            <li class="mb-2"><router-link class="link" v-bind:to="{ name: 'login' }">My account</router-link></li>
            <li class="mb-2"><router-link class="link" v-bind:to="{ name: 'register' }">Create Account</router-link>
            </li>
            <li class="mb-2"><a href="https://www.instagram.com/ladybookhouse/" class="account-instagram"
                target="_blank" rel="noopener noreferrer">
                <i class="fab fa-instagram"></i>
              </a></li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "footer-page",
  computed: {
    showComponent() {
      return this.$store.state.showHeaderAndFooter
    }
  }
}
</script>

<style scoped>
.footer-custom {
  background-color: oldlace;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

.link,
p,
p>a {
  color: #935048;
}

.link:hover {
  color: #6B3630;
}

.fa-instagram {
  font-size: 1em;
  color: #6B3630;
}
</style>