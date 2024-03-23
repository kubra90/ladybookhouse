<template>
    <div class="order-summary">
      <div class="container py-4">
        <div class="row g-4">
          <!-- Shipping Information with Change Button -->
          <div class="col-md-6">
            <div class="card shadow-sm">
              <div class="card-body">
                <div class="d-flex justify-content-between align-items-center mb-3">
                  <h5 class="card-title mb-0">Shipping Information</h5>
                  <router-link v-bind:to="{ name: 'checkout' }" class="btn btn-outline-primary btn-sm">Change</router-link>
                </div>
                <p class="card-text">
                  <strong> {{ displayName}}</strong><br>
                  {{ tempOrderInfo.addressLine }} <br> 
                  {{ tempOrderInfo.city }} <br> 
                  {{ tempOrderInfo.state }} {{ tempOrderInfo.zipCode }} <br> 
                  {{ tempOrderInfo.country }}<br>
                  Phone: {{ tempOrderInfo.phoneNumber }}<br>
                  Email: {{ tempOrderInfo.email }}
                </p>
              </div>
            </div>
          </div>
  
          <!-- Payment Information with Change Button -->
          <div class="col-md-6">
            <div class="card shadow-sm">
              <div class="card-body">
                <div class="d-flex justify-content-between align-items-center mb-3">
                  <h5 class="card-title mb-0">Payment Method</h5>
                  <router-link :to="{ name: 'checkout' }" class="btn btn-outline-primary btn-sm">Change</router-link>
                </div>
                <p class="card-text">
                  Method: {{ selectedPaymentMethod }} paypal
                </p>
              </div>
            </div>
          </div>
        </div>
  
        <!-- Book Details and Price Summary -->
        <div class="row">
          <div class="col-12">
            <div class="card shadow-sm mb-4">
              <div class="card-body">
                <h5 class="card-title">Books in Cart</h5>
                <div v-for="(book, index) in cartBooks" :key="index" class="mb-2">
                  <div>{{ book.title }} by {{ book.author }}</div>
                  <div>Price: ${{ book.price }}</div>
                </div>
                <hr>
                <div class="d-flex justify-content-between">
                  <strong>Total Price:</strong>
                  <span>${{ totalPrice }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { mapGetters, mapState } from 'vuex';
  export default {
    name: 'OrderSummary',
    // Assuming props are passed or Vuex store is used
    computed: {
      ...mapState(['order', 'cartBooks']),
      ...mapGetters(['totalShippingCost', 'isAuthenticated']),
      tempOrderInfo(){
        return this.$store.state.tempOrderInfo;
      },
      selectedPaymentMethod() {
        return this.$store.state.selectedPaymentMethod;
      },
      displayName(){
        const firstName = this.tempOrderInfo.firstName || this.$store.state.user.firstName;
        const lastName = this.tempOrderInfo.lastName || this.$store.state.user.lastName;
        return `${firstName} ${lastName}`;
      }
    }
  }
  </script>
  
  <style scoped>
  /* Your custom styles here */
  </style>
  
