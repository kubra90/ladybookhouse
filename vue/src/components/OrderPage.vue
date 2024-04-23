<template>
  <div class="container my-4">
    <div class="row px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5 px-xxl-0 mx-xxl-0" v-for="order in orders" :key="order.order_id">
      <div class="col">
        <div class="card mb-3">
          <div class="card-header d-flex justify-content-between">
            <div>
              <div>
                <b>Sales Order #{{ order.orderId }}</b>
              </div>
              <div>Ordered on {{ order.orderDateTime | formatDate }}</div>
            </div>
            <div>
              <div>
                Order Total:
                <strong>${{ formatPrice(order.totalPrice) }}</strong>
              </div>
            </div>
          </div>
          <div class="card-body p-0">
            <ul class="list-group list-group-flush">
              <li
                v-for="bookNo in order.inventoryCode"
                :key="bookNo"
                class="list-group-item"
              >
                <div class="d-flex justify-content-between">
                  <div class="flex-grow-1">
                    <div>
                      <b>{{ getBookDetails(bookNo).title || "Loading..." }}</b>
                    </div>
                    <div>
                      {{ getBookDetails(bookNo).author || "Loading..." }}
                    </div>
                  </div>
                  <div class="align-items-end ms-4" style="text-align: end">
                    <div class="item-status text-nowrap">Item status: <b> shipped</b></div>
                    <div class="text-wrap align-items-end" >The book has been shipped to you</div>
                  </div>
                </div>
                <button
                  @click="toggleBookDetails(bookNo)"
                  class="btn btn-link mt-2"
                >
                  {{
                    showBookDetails[bookNo]
                      ? "Hide Book Description"
                      : "View Book Description"
                  }}
                </button>
                <div
                  v-if="showBookDetails[bookNo]"
                  class="dropdown-content mt-2 mb-3"
                >
                  <div>
                    <b>ISBN:</b>
                    {{ getBookDetails(bookNo).isbn || "Loading..." }}
                  </div>
                  <div>
                    <b>Binding:</b>
                    {{ getBookDetails(bookNo).media || "Loading..." }}
                  </div>
                  <div>
                    <b>Condition:</b>
                    {{ getBookDetails(bookNo).conditionAsText || "Loading..." }}
                  </div>
                  <div>
                    <b>Publisher:</b>
                    {{ getBookDetails(bookNo).publisher || "Loading..." }}
                  </div>
                  <div>
                    <b>Publication Date:</b>
                    {{ getBookDetails(bookNo).publicationDate || "Loading..." }}
                  </div>
                  <div>{{ getBookDetails(bookNo).notes || "Loading..." }}</div>
                </div>
                </li>
                <!-- shipping and payment info -->
                <li class="list-group-item mb-3">
                <div class="d-flex justify-content-between">
                  <div class="flex-grow-1">
                    <div>
                      <b>Estimated Delivery</b>
                    </div>
                    <div>
                      on/before estimated_delivery
                    </div>
                  </div>
                  <div class="flex-grow-1">
                    <div>
                      <div>Shipping speed: <span> {{getDeliveryDays(order.deliveryOption)}}</span></div>
                    </div>
                    <div>
                      <div class="text-nowrap">Delivery: <span>{{ order.deliveryOption }}</span></div>
                    </div>
                    <div>
                      <div>Tracking number: <span>pending</span></div>
                    </div>
                  </div>
                  <div class="align-items-end ms-4 text-nowrap" style="text-align: end">
                    <div class="">Subtotal: ${{ formatPrice(order.subTotalPrice)}}</div>
                    <div class="">Shipping: ${{ formatPrice(order.shippingCost)}}</div>
                    <div class="">Total: <b> $ {{ formatPrice(order.totalPrice) }}</b></div>
                  </div>
                </div>
                <!-- view payment ans shipping details -->
                <button
                  class="btn btn-link mt-2"
                  @click="toggleShippingDetails"
                >
                  {{
                    showShippingDetails
                      ? "Payment & Shipping Details"
                      : "Payment & Shipping Details"
                  }}
                </button>
                <div
                  v-if="showShippingDetails"
                  class="dropdown-content mt-2 mb-3"
                >
                <div class="d-flex justify-content-between">
                  <div>
                 <div>Payment Method: <span> PayPal</span></div>
                 <div>Payment Date <span>Date</span></div>
                  </div>
                  <div>
                    Shipping Address

                  </div>
                </div>
                </div>
                </li>
            </ul>
          </div>
          <div class="card-footer text-muted">
            <a href="#" class="card-link">Request Return/Refund</a>
            <a href="#" class="card-link">Contact Bookseller</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


  

<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "order-page",
  data() {
    return {
      showBookDetails: {},
      showShippingDetails: false,
    };
  },
  filters: {
    formatDate(value) {
      if (value) {
        const date = new Date(value);
        return date.toLocaleDateString("en-US", {
          year: "numeric",
          month: "long",
          day: "numeric",
        });
      }
      return "";
    },
  },
  computed: {
    ...mapState(["orders", "user", "book", "bookDetails"]),
    // getBookDetails() {
    //   console.log("accessing bookdetails from the store:", this.$store.state.bookDetails)
    //   return this.$store.state.bookDetails;
    // }
    getBookDetails() {
      return (bookNo) => {
        return this.bookDetails[bookNo] || { title: "Loading..." };
      };
    },

   
  },
  methods: {
    ...mapActions(["fetchOrders", "fetchBookById"]), // Moved mapActions to methods
    formatPrice(value) {
      const formattedValue = Number(value).toFixed(2);
      return formattedValue;
    },
    getDeliveryDays(value){
      if(value === "UPS"){
        return "4-7 business days";
      }else{
        return "6-10 business days";
      }
    },

    toggleBookDetails(bookNo) {
      // Initialize if not present
      if (this.showBookDetails[bookNo] === undefined) {
        this.$set(this.showBookDetails, bookNo, false); // Initialize as false initially
      }

      // Toggle the visibility
      this.showBookDetails[bookNo] = !this.showBookDetails[bookNo];

      // Fetch book details if not already loaded and if becoming visible
      if (this.showBookDetails[bookNo] && !this.getBookDetails[bookNo]) {
        this.fetchBookById(bookNo);
      }
    },
    toggleShippingDetails(){
      this.showShippingDetails = !this.showShippingDetails;
    }
  },
  created() {
    this.fetchOrders().then(() => {
      this.orders.forEach((order) => {
        order.inventoryCode.forEach((bookNo) => {
          this.fetchBookById(bookNo); // Fetch details
        });
      });
    });
  },
};
</script>

<style scoped>
.btn-link {
  font-size: small;
  /* notes about !important */
  margin-left: 0 !important;
  padding-left: 0 !important;
}

/* .item-status {
  text-align:end;
} */

@media (max-width: 576px) {
  /* Adjust for small screens and up */
  .item-status {
    padding-right: 0 !important;
    margin-right: 0 !important;
    margin-left: 0.7em;
  }
}
</style>