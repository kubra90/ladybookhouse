<template>
  <div class="account-container">
    <Header-page />
    <div class="container mt-4 mb-4 ">
      <div class="row px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5">
        <div class="col-md-6 ">
          <h2>Your account</h2>
        </div>
        <div class="col-md-6">
          <div class="welcome">
            Welcome, {{ user.firstName }} {{ user.lastName }}.
            <router-link class="link" to="/logout">Logout</router-link>
          </div>
        </div>
      </div>
      <div class="row g-3 mt-3 px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5">
        <div class="col-md-6">
          <div class="container p-0">
            <h6 class="mb-0">Personal Information</h6>
            <hr class="mt-1 mb-2">
            <p class="mb-0">Name: {{ user.firstName }} {{ user.lastName }}</p>
            <p class="mb-0">Email: {{ user.email }}</p>
            <p>Password: ••••••••</p>
          </div>
        </div>
        <div class="col-md-6">
          <div class="container p-0">
            <h6 class="mb-0">Address</h6>
            <hr class="mt-1 mb-2">
            <p class="mb-0"> <strong>{{ addresses[arrLength].firstname }} {{ addresses[arrLength].lastname }}</strong></p>
          <p class="mb-0"> {{ addresses[arrLength].addressLine }}</p>
          <p class="mb-0"> {{ addresses[arrLength].country}}</p>
          <p class="mb-0"> {{ addresses[arrLength].state }}</p>
            <p class="mb-0">{{ addresses[arrLength].city }}</p>
            <p class="mb-0"> {{ addresses[arrLength].zipcode}}</p>
            <p class="mb-0"> {{ addresses[arrLength].phoneNumber}}</p>
            <!-- Address information here -->
            <router-link v-bind:to="{name: 'address-book'}" class="link">View other addresses</router-link>
          </div>
        </div>
      </div>
      <div class="row g-3 mt-3 px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5">
        <div class="col-md-6">
          <div class="container p-0">
            <h6 class="mb-0">My Purchases</h6>
            <hr class="mt-1 mb-2">
            <router-link v-bind:to="{name: 'order'}" class="link">View Order History</router-link>
          </div>
        </div>
        <div class="col-md-6">
          <div class="container p-0">
            <h6 class="mb-0">My Bookshelf</h6>
            <hr class="mt-1 mb-2">
            <router-link v-bind:to="{name: 'saved-books'}" class="link">View My Bookshelf</router-link>
          </div>
        </div>
      </div>
      <div class="row g-3 mt-3 px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5">
        <div class="col-md-6">
          <div class="container p-0">
            <h6 class="mb-0">New Arrival Notifications</h6>
            <hr class="mt-1 mb-2">
            <router-link to="/order-history" class="link">Change Notifications</router-link>
          </div>
        </div>
        <!-- <div class="col-md-6">
          <div class="container p-0">
            <h6 class="mb-0">My Bookshelf</h6>
            <hr class="mt-1 mb-2">
            <router-link to="/saved-books" class="link">View My Bookshelf</router-link>
          </div>
        </div> -->
      </div>
    </div>
    <Footer-page />
  </div>
</template>

<script>
import HeaderPage from "../components/HeaderPage.vue";
import FooterPage from "../components/FooterPage.vue";
import { mapState } from 'vuex';

export default {
  name: "my-account",
  components: {
    HeaderPage, FooterPage
  },
  computed: {
    ...mapState(["user", "addresses"]),
    arrLength(){
      return this.addresses.length - 1;
    }
    // ...mapActions(["getUserAddresses"])
  },
  created() {
    this.$store.dispatch('getUserAddresses')
  }
}
</script>

<style scoped>

.account-container {
  display:flex;
        flex-direction:column;
        height:100%;
}
* {
  font-family: "PT SANS", sans-serif;
}

h1, h5, h2, h6, hr{
  color: #6b3630;
}

hr {
  color: #6b3630;
  width:90%;
}

.link {
  color: #935048;
  font-weight: 600;
}

.link:hover {
  color: #6B3630;
}
</style>
