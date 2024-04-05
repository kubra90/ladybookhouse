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
        <div class="col-12 col-md-8">
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
                        v-bind:to="{ name: 'checkout', query: {focus: 'shipping'} }"
                        class="btn btn-secondary btn-sm"
                        style="margin-left: 0.4rem; background-color: gray"
                        >Change</router-link
                      >
                   
                    </div>
                    <p class="card-text">
                      <strong>{{ displayName }}</strong
                      ><br />
                      {{ tempOrderInfo.shippingAddress.addressLine }}<br />
                      {{ tempOrderInfo.shippingAddress.city }}<br />
                      {{ tempOrderInfo.shippingAddress.state }} {{ tempOrderInfo.shippingAddress.zipcode
                      }}<br />
                      {{ tempOrderInfo.shippingAddress.country }}<br />
                      Phone: {{ tempOrderInfo.shippingAddress.phoneNumber }}<br />
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
                      v-bind:to="{name: 'checkout', query: {focus: 'payment'}}"
                      class="btn btn-secondary btn-sm"
                        style="margin-left: 0.4rem; background-color: gray"
                      >
                        Change
                      </router-link>
                    </div>
                    <p class="card-text">Method: {{paymentMethod}}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- Order Total and Shipping Price Information -->
        <div class="col-md-4 col-12">
          <div class="card shadow-sm">
            <div class="card-body">
              <h5 class="card-title">Order Summary</h5>
              <hr />
              <div class="mb-3">
                <div class="card-text">
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <span>Items({{ basketCount }}):</span>
                    <span> ${{ formatPrice(subTotalPrice) }}</span>
                  </div>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <span>Shipping:</span>
                    <span> ${{ formatPrice(totalShippingCost) }}</span>
                  </div>
                  <hr>
                  <div
                    class="d-flex justify-content-between align-items-center" style="color:#c7002c"
                  >
                    <span>Order Total:</span>
                    <span> ${{ formatPrice(totalPrice) }}</span>
                  </div>
                  <hr>
                  <button class="btn btn-primary w-100" style="color:white;background-color:#c7002c;width:auto;border:none;" @click="concludeOrder">Place your order</button>
                </div>
              </div>
              <!-- Additional details like shipping cost can be added here -->
            </div>
          </div>
        </div>
      </div>

      <!-- Book Details and Shipping Options -->
      <div class="row mt-4">
<!-- Book Details with Price Adjustment and Shipping Dropdown -->
<div class="col-12 col-md-8">
  <div class="card shadow-sm">
    <div class="card-body">
      <h5 class="card-title">Books in Cart</h5>
      <div v-for="(book, index) in cartBooks" :key="index" class="mb-4">
        <div class="d-flex align-items-center">
          <div class="me-3 position-relative">
            <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill badge-secondary">
              {{ book.quantity }}
            </span>
            <img :src="book.image" alt="book image" class="img-sm rounded border" style="height: 96px; width: 66px"/>
          </div>
          <div class="flex-grow-1">
            <router-link :to="{ name: 'detail', params: { sku: book.sku }}" class="nav-link">
              <strong>{{ book.title }}</strong> <br />
              {{ book.author }}
            </router-link>
          </div>
          <div>
            <span class="price text-muted" style="color:chocolate">$ {{ book.price }}</span>
          </div>
        </div>
        <!-- Shipping Options Dropdown -->
      </div>
    </div>
  </div>
</div>

      </div>
    </div>
  </div>
</template>
  
  
  
  <script>
import { mapGetters, mapState, mapActions } from "vuex";
export default {
  name: "OrderSummary",
  // Assuming props are passed or Vuex store is used
  computed: {
    ...mapState(["order", "cartBooks", "selectedPaymentMethod", "basketCount", "tempOrderInfo","order"]),
    ...mapGetters(["totalPrice", "isAuthenticated", "totalShippingCost", "subTotalPrice"]),
    tempOrderInfo() {
      return this.$store.state.tempOrderInfo;
    },
    selectedDelivery() {
      return this.$store.state.deliveryOption;
    },
    chooseOptionInDelivery() {
      if (this.$store.state.deliveryOption === "USPS") {
        return "UPS";
      } else {
        return "USPS";
      }
    },
    paymentMethod() {
      return this.$store.state.checkout.paymentMethod;
    },
    displayName() {
      const firstName =
        this.tempOrderInfo.shippingAddress.firstname || this.$store.state.user.firstName;
      const lastName =
        this.tempOrderInfo.shippingAddress.lastname || this.$store.state.user.lastName;
      return `${firstName} ${lastName}`;
    },
    displayEmail() {
      const email = this.tempOrderInfo.email || this.$store.state.user.email;
      return `${email}`;
    },
  },
  methods: {
    ...mapActions(['createOrder']),
    formatPrice(value) {
      const formattedPrice = Number(value).toFixed(2);
      return formattedPrice;
    },
   
//    async concludeOrder() {
//   if (this.cartBooks != null && this.cartBooks.length > 0) {
//     // Assuming tempOrderInfo is already up-to-date with user inputs
//     // and just needs inventory codes from cartBooks
//     this.tempOrderInfo.inventoryCode = this.cartBooks.map(book => book.sku);

//     try {
//       // Use tempOrderInfo for creating the order
//       await this.createOrder(this.tempOrderInfo);

//       // Navigate to the order success page
//       this.$router.push({ name: "orderSuccess" });
//     } catch (error) {
//       // Log or handle the error appropriately
//       console.error("Order submission failed", error);
//       // Optionally, display a user-friendly error message
//       this.$refs.alertToast.addToast({
//         title: "Order Submission Failed",
//         message: "There was a problem submitting your order. Please try again.",
//       });
//     }
//   }
// }
    concludeOrder() {
      if (this.cartBooks != null && this.cartBooks.length > 0) {
        this.tempOrderInfo.inventoryCode = [];
        for (const book of this.cartBooks) {
          this.tempOrderInfo.inventoryCode.push(book.sku);
        }

        try {
          this.createOrder(this.tempOrderInfo);
          console.log(this.tempOrderInfo.saveAddress);
          this.$router.push({ name: "orderSuccess" });
        } catch (error) {
          console.error("Order submission failed", error);
        }
      }
    },
  }

}
</script>
  
  <style scoped>
</style>
  
