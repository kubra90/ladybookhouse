<template>
  <div class="order-summary">
    <div class="container py-4">
      <div class="row g-4">
        <p
          class="h5 fw-normal"
          style="
            font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS',
              sans-serif;
          "
        >
          Review your Order
        </p>
        <!-- Container for Payment and Shipping Information -->
        <div class="col-8">
          <div class="card shadow-sm">
            <div class="card-body">
              <!-- <h5 class="card-title">Payment & Shipping Information</h5> -->
              <div class="row">
                <!-- Shipping Information -->
                <div class="col-md-6">
                  <div class="mb-3">
                    <div
                      class="d-flex justify-content-start align-items-center"
                    >
                      <span
                        class="fw-normal"
                        style="text-decoration: underline; font-weight: 300"
                        >Shipping Information</span
                      >
                      <router-link
                        v-bind:to="{ name: 'checkout' }"
                        class="btn btn-secondary btn-sm"
                        style="margin-left: 0.4rem; background-color: gray"
                        >Change</router-link
                      >
                    </div>
                    <p class="card-text">
                      <strong>{{ displayName }}</strong
                      ><br />
                      {{ tempOrderInfo.addressLine }}<br />
                      {{ tempOrderInfo.city }}<br />
                      {{ tempOrderInfo.state }} {{ tempOrderInfo.zipCode
                      }}<br />
                      {{ tempOrderInfo.country }}<br />
                      Phone: {{ tempOrderInfo.phoneNumber }}<br />
                      Email: {{ displayEmail }}
                    </p>
                  </div>
                </div>
                <!-- Payment Information -->
                <div class="col-md-6">
                  <div class="mb-3">
                    <div
                      class="d-flex justify-content-start align-items-center"
                    >
                      <span class="fw-normal" style="text-decoration: underline"
                        >Payment Information</span
                      >
                      <router-link
                        v-bind:to="{ name: 'checkout' }"
                        class="btn btn-secondary btn-sm"
                        style="margin-left: 0.4rem; background-color: gray"
                        >Change</router-link
                      >
                    </div>
                    <p class="card-text">Method: {{ selectedPaymentMethod }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- Order Total and Shipping Price Information -->
        <div class="col-4">
          <div class="card shadow-sm">
            <div class="card-body">
              <h5 class="card-title">Order Summary</h5>
              <hr />
              <div class="mb-3">
              
                <div class="card-text">
                    <div class="d-flex justify-content-between align-items-center">
                  <span>Items({{ basketCount }}):</span>
                  <span> ${{ formatPrice(totalPrice) }}</span>
                  </div>
                  <div class="d-flex justify-content-between align-items-center">
                  <span>Shipping:</span>
                  <span> ${{ formatPrice(totalShippingCost) }}</span>
                  </div>
                </div>
             
            </div>
              <!-- Additional details like shipping cost can be added here -->
            </div>
          </div>
        </div>
      </div>

      <!-- Book Details and Shipping Options -->
      <div class="row mt-4">
        <div class="col-8">
          <div class="card shadow-sm">
            <div class="card-body">
              <h5 class="card-title">Books in Cart</h5>
              <div v-for="(book, index) in cartBooks" :key="index" class="mb-3">
                <div>{{ book.title }} by {{ book.author }}</div>
                <div>Price: ${{ book.price }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  
  
  
  <script>
import { mapGetters, mapState } from "vuex";
export default {
  name: "OrderSummary",
  // Assuming props are passed or Vuex store is used
  computed: {
    ...mapState(["order", "cartBooks", "selectedPaymentMethod", "basketCount"]),
    ...mapGetters(["totalPrice", "isAuthenticated", "totalShippingCost"]),
    tempOrderInfo() {
      return this.$store.state.tempOrderInfo;
    },
    selectedPaymentMethod() {
      return this.$store.state.selectedPaymentMethod;
    },
    displayName() {
      const firstName =
        this.tempOrderInfo.firstName || this.$store.state.user.firstName;
      const lastName =
        this.tempOrderInfo.lastName || this.$store.state.user.lastName;
      return `${firstName} ${lastName}`;
    },
    displayEmail() {
      const email = this.tempOrderInfo.email || this.$store.state.user.email;
      return `${email}`;
    },
  },
  methods: {
    formatPrice(value) {
      const formattedPrice = Number(value).toFixed(2);
      return formattedPrice;
    },
  },
};
</script>
  
  <style scoped>
</style>
  
