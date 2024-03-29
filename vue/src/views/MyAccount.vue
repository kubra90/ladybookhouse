<template>
  <div>
    <Header-page />
    <div class="container">
      <div class="row">
        <div class="col">
          <h1 class="mt-4">Your account</h1>
        </div>
        <div class="col text-end">
          <div class="welcome mt-4">
            Welcome, {{ user.firstName }} {{ user.lastName }}.
            <p class="pt-3"><router-link class="link " to="/logout">Logout</router-link></p>
          </div>
        </div>
      </div>
    </div>
    <div class="container mt-2">
      <div class="personal-info">
        <span class="personal-info-header">Personal Information</span>
        <hr>
        <div class="personal-info-body">
          <p>Name: {{ user.firstName }} {{ user.lastName }}</p>
          <p class="pt-2">Email: {{ user.email }}</p>
        </div>
        <hr>
        <p><router-link v-bind:to="{ name: 'order' }" class="link">View My Purchases</router-link></p>
        <p class="pt-2 pb-2"><router-link v-bind:to="{ name: 'saved-books' }" class="link">View My
            Bookshelf</router-link></p>
      </div>
    </div>
    <Footer-page />
  </div>
</template>


<script>

import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"
import { mapState } from 'vuex'

export default {
  name: "my-account",
  components: {
    HeaderPage, FooterPage
  },
  computed: {
    ...mapState(["user"])
  }
}
</script>

<style scoped>
* {
  font-family: "PT SANS", sans-serif;
}

h1 {
  color: #6b3630;
}

hr {
  color: #c1c1c1;
}

.link {
  color: #935048;
  font-weight: 600;
}

.link:hover {
  color: #6B3630;
}
</style>