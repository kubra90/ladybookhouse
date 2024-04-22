<template>
  <div class="container my-4">
    <div class="row" v-for="order in orders" :key="order.order_id">
      <div class="col">
        <div class="card mb-3">
          <div class="card-header d-flex justify-content-between">
            <div>
              <div>
                <strong>Sales Order #{{ order.orderId }}</strong>
              </div>
              <div>Ordered on {{ order.orderDateTime | formatDate }}</div>
            </div>
            <div>
                <div>Order Total: <strong>${{ formatPrice(order.totalPrice) }}</strong> </div>
            </div>
          </div>
          <!-- Moved list-group outside card-body -->
          <ul class="list-group list-group-flush">
            <li v-for="bookNo in order.inventoryCode" :key="bookNo" class="list-group-item">
              <div><strong>{{ getBookDetails(bookNo).title || 'Loading...' }}</strong></div>
              <div>{{ getBookDetails(bookNo).author || 'Loading...' }}</div>
              <button @click="toggleBookDetails(bookNo)" class="btn btn-link mt-4 mb-2">
                {{ showBookDetails[bookNo] ? 'Hide Book Description' : 'View Book Description' }}
              </button>
              <div v-if="showBookDetails[bookNo]" class="dropdown-content mb-2">
                <div><strong>ISBN: </strong>{{ getBookDetails(bookNo).isbn || 'Loading...' }}</div>
                <div><strong>Binding: </strong>{{ getBookDetails(bookNo).media || 'Loading...' }}</div>
                <div><strong>Condition: </strong>{{ getBookDetails(bookNo).conditionAsText || 'Loading...' }}</div>
                <div><strong>Publisher: </strong>{{ getBookDetails(bookNo).publisher || 'Loading...' }}</div>
                <div><strong>Publication Date: </strong>{{ getBookDetails(bookNo).isbn || 'Loading...' }}</div>
                <div>{{ getBookDetails(bookNo).notes || 'Loading...' }}</div>
              </div>
            </li>
          </ul>
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
      showBookDetails:{}
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
            return this.bookDetails[bookNo] || { title: 'Loading...' };
        };
    },
    
  },
  methods: {
    ...mapActions(["fetchOrders", "fetchBookById"]), // Moved mapActions to methods
    formatPrice(value){
      const formattedValue = Number(value).toFixed(2);
      return formattedValue;
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
}


},
  created() {

    this.fetchOrders().then(() => {
    this.orders.forEach(order => {
      order.inventoryCode.forEach(bookNo => {
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
</style>