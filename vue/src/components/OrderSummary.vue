<template>
  <div class="order-summary">
    <div class="container py-4">
      <div class="row g-4" v-if="!showConfirmationModal">
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
                        v-bind:to="{
                          name: 'checkout',
                          query: { focus: 'shipping' },
                        }"
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
                      {{ tempOrderInfo.shippingAddress.stateInfo }}
                      {{ tempOrderInfo.shippingAddress.zipcode }}<br />
                      {{ tempOrderInfo.shippingAddress.country }}<br />
                      Phone: {{ tempOrderInfo.shippingAddress.phoneNumber
                      }}<br />
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
                        v-bind:to="{
                          name: 'checkout',
                          query: { focus: 'payment' },
                        }"
                        class="btn btn-secondary btn-sm"
                        style="margin-left: 0.4rem; background-color: gray"
                      >
                        Change
                      </router-link>
                    </div>
                    <p class="card-text">Method: {{ paymentMethod }}</p>
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
                  <hr />
                  <div
                    class="d-flex justify-content-between align-items-center"
                    style="color: #c7002c"
                  >
                    <span>Order Total:</span>
                    <span> ${{ formatPrice(totalPrice) }}</span>
                  </div>
                  <hr />
                  <!-- Button trigger modal -->
                  <button
                    class="btn btn-primary w-100"
                    data-bs-toggle="modal"
                    data-bs-target="#exampleModal"
                    style="
                      color: white;
                      background-color: #c7002c;
                      width: auto;
                      border: none;
                    "
                    @click="concludeOrder"
                  >
                    Place your order
                  </button>

                  <!-- till here -->
                </div>
              </div>
              <!-- Additional details like shipping cost can be added here -->
            </div>
          </div>
        </div>
      </div>

      <!-- modal for the order confirmation -->
      <!-- modal  -->
      <div
        v-if="showConfirmationModal"
        class="modal fade"
        id="exampleModal"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header my-4">
              <h5 class="modal-title" id="exampleModalLabel">
                Lady Bookhouse
              </h5>
              <!-- <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
                @click="hidePopup"
              ></button> -->
            
            </div>
            <div class="modal-body d-flex flex-column justify-content-center my-4">
              <div>{{displayName}}, thank you for your order. </div>
          
              <div class="my-2 mx-1 px-1">We've received your order. We'll contact you as soon as your order is shipped</div>
            </div>
            <div class="modal-footer">
              <!-- <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> -->
              <button
                type="button"
                class="btn btn-secondary"
                @click="goToHomePage"
              >
                Go to Home
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Book Details and Shipping Options -->
      <div class="row mt-4" v-if="!showConfirmationModal">
        <!-- Book Details with Price Adjustment and Shipping Dropdown -->
        <div class="col-12 col-md-8">
          <div class="card shadow-sm">
            <div class="card-body">
              <h5 class="card-title">Books in Cart</h5>
              <div v-for="(book, index) in cartBooks" :key="index" class="mb-4">
                <div class="d-flex align-items-center">
                  <div class="me-3 position-relative">
                    <span
                      class="position-absolute top-0 start-100 translate-middle badge rounded-pill badge-secondary"
                    >
                      {{ book.quantity }}
                    </span>
                    <img
                      :src="book.image"
                      alt="book image"
                      class="img-sm rounded border"
                      style="height: 96px; width: 66px"
                    />
                  </div>
                  <div class="flex-grow-1">
                    <router-link
                      :to="{ name: 'detail', params: { sku: book.sku } }"
                      class="nav-link"
                    >
                      <strong>{{ book.title }}</strong> <br />
                      {{ book.author }}
                    </router-link>
                  </div>
                  <div>
                    <span class="price text-muted" style="color: chocolate"
                      >$ {{ book.price }}</span
                    >
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
  data() {
    return {
      showConfirmationModal: false,
    };
  },
  // Assuming props are passed or Vuex store is used
  computed: {
    ...mapState([
      "order",
      "cartBooks",
      "selectedPaymentMethod",
      "basketCount",
      "tempOrderInfo"
    ]),
    ...mapGetters([
      "totalPrice",
      "isAuthenticated",
      "totalShippingCost",
      "subTotalPrice",
    ]),
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
        this.tempOrderInfo.shippingAddress.firstname ||
        this.$store.state.user.firstName;
      const lastName =
        this.tempOrderInfo.shippingAddress.lastname ||
        this.$store.state.user.lastName;
      return `${firstName} ${lastName}`;
    },
    displayEmail() {
      const email = this.tempOrderInfo.email || this.$store.state.user.email;
      return `${email}`;
    },
  },
  methods: {
    ...mapActions(["createOrder"]),

    formatPrice(value) {
      const formattedPrice = Number(value).toFixed(2);
      return formattedPrice;
    },
    // toggleModal(show) {
    //   this.showConfirmationModal = show;
    //   this.showHeaderAndFooter = !show;
    //   if (show) {
    //     this.$nextTick(() => {
    //       document.getElementById("exampleModal").style.display = "block";
    //       document.getElementById("exampleModal").classList.add("show");
    //       // the below change the color to salmon after
    //       if (this.$route.name === "orderSummary") {
    //         document.body.style.backgroundColor = "salmon";
    //       }else {
    //         document.body.style.backgroundColor = "";
    //       }
    //     });
    //   } else {
    //     document.getElementById("exampleModal").style.display = "none";
    //     document.getElementById("exampleModal").classList.remove("show");
    //   }
    // },
    toggleModal(show) {
      this.showConfirmationModal = show;
      this.$store.commit('SHOW_HEADER_AND_FOOTER', false);
      this.$nextTick(() => {
        const modal = document.getElementById("exampleModal");
        if (modal) {
          modal.style.display = show ? "block" : "none";
          modal.classList.toggle("show", show);
          if (this.$route.name === "orderSummary" && show) {
            document.body.style.backgroundColor = "OldLace";
          } 
        }
      });
    },
    goToHomePage() {
      this.$store.commit('SHOW_HEADER_AND_FOOTER', true);
      this.$router.push({ name: "home" });
      document.body.style.backgroundColor = "white";
    },
    hidePopup() {
      this.$store.commit('SHOW_HEADER_AND_FOOTER', true);
      this.showConfirmationModal = false;
      this.$router.push({name: "home"})
      document.body.style.backgroundColor = "white";
    },
    concludeOrder() {
      if (this.cartBooks.length > 0) {
        this.tempOrderInfo.inventoryCode = this.cartBooks.map(
          (book) => book.sku
        );
        this.createOrder(this.tempOrderInfo)
          .then(() => {
         
            console.log(this.order);
            this.toggleModal(true);
            this.$store.commit("RESET_TEMP_ORDER_INFO");
          })
          .catch((error) => {
            console.error("Order submission failed", error);
            this.toggleModal(false);
            this.$store.state.showHeaderAndFooter=true;
          });
      }
    },
  },
};
</script>
  
  <style scoped>

  /* Modal content styling */
.modal-content {
    background-color: #f8f8f8; /* Light grey background */
    /* border-radius: 8px;  */
    border: none; /* No border */
    box-shadow: 0 5px 15px rgba(0,0,0,0.1); /* Subtle shadow */
    
}

/* Modal header styling */
.modal-header {
    background-color: #fff; /* White background for the header */
    width: 100%; /* Full width to align title properly */
    display: flex;
    justify-content: center; /* Center the title horizontally */
    padding-bottom: 10px;
   
}
.modal-title {
    font-size: 1.00rem; /* Larger font size */
    color: #333; /* Dark grey font color */
    text-decoration: none;
    text-align: center;
}

/* Modal body styling */
.modal-body {
    padding: 20px; /* Ample padding for spacing */
    display: flex; /* Ensures flexbox layout */
    flex-direction: column; /* Stacks children vertically */
    align-items: center; /* Centers children horizontally */
    justify-content: center; /* Centers children vertically */
    text-align: center;
}


/* Modal footer styling */
.modal-footer {
    /* border-top: 1px solid #dee2e6; 
    background-color: #fff;  */
    border-bottom-left-radius: 8px; /* Rounded bottom left corner */
    border-bottom-right-radius: 8px; /* Rounded bottom right corner */
}

/* Button styling */
.btn-primary {
    background-color: #007bff; /* Primary button color */
    border-color: #007bff; /* Border color same as background */
}

.btn-secondary {
    background-color: #6c757d; /* Secondary button color */
    border-color: #6c757d; /* Border color same as background */
}

</style>
  
