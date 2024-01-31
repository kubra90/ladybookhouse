<template>
  <div>
    <Header-page/>
    <main class="account-content">
      <div class="flex-container">
        <h1>Your account</h1>
        <div class="welcome">
          Welcome, {{ user.username }}. 
          <router-link to="/logout">Logout</router-link>
        </div>
      </div>
      <div class="personal-info">
        <span class="personal-info-header">Personal Information</span>
        <hr>
        <div class="personal-info-body">
          <span>Name: {{ user.username }} </span><br>
        </div>
        <div class="orders">
          <strong>Orders</strong>
          <hr>
          <span>You currently have no recent orders</span>
        </div>  
      </div>
    </main>
    <Footer-page/>
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
    ...mapState(['user'])
  }
}
</script>

<style scoped>

* {
  box-sizing: border-box;
  padding: 0px;
}
.personal-info {
  margin: 1em;
}
.personal-info-header {
  font-weight: 700;
}
hr {
  color: #c1c1c1;
  margin-top: 0.50em;
}
.orders {
  margin: 2em 0;
}
@media (min-width: 768px) {
  main.account-content {
  display: block;
  margin-left: 130px;
  padding: 1em;
}
.flex-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
}
</style>