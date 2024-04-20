

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
                <div>Order Total: <strong>${{formatPrice(order.totalPrice)}}</strong> </div>
            </div>
          </div>
          <div class="card-body">
            <ul class="list-group">
              <li v-for="bookNo in order.inventoryCode" :key="bookNo" class="list-group-item">
                Book Number: {{ bookNo }}
                
                {{ fetchBookById(bookNo) }}
                {{ bookNo.title }}
                <button @click="toggleBookDetails(bookNo)" class="btn btn-sm btn-primary">
                  {{ showBookDetails[bookNo] ? 'Hide Details' : 'Show Details' }}
                </button>
                 <div v-if="showBookDetails[bookNo]" class="dropdown-content">
                  <p> Title: {{ getBookDetails(bookNo).title || 'Loading...' }}</p>
                  <p>Loading book details...</p>
                </div> 
              </li>
            </ul>
            <p class="card-text">
              <!-- <small class="text-muted">Estimated Delivery: {{ order.estimatedDelivery }}</small> -->
            </p>
            <a href="#" class="card-link">Request Return/Refund</a>
            <a href="#" class="card-link">Contact Bookseller</a>
          </div>
          <ul class="list-group list-group-flush">
            <!-- <li class="list-group-item">Shipping Speed: {{ order.shippingSpeed }}</li>
              <li class="list-group-item">Tracking Number: {{ order.trackingNumber }}</li> -->
          </ul>
          <div class="card-footer text-muted">
            <!-- Order Total: ${{ order.total }} -->
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
   
//     toggleBookDetails(bookNo) {
//       this.showBookDetails = true;
//     this.fetchBookById(bookNo);
//     console.log(this.fetchBookById(bookNo));
// }

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
    this.fetchOrders();
    console.log(this.fetchOrders())
  },
//   async created() {
//   await this.fetchOrders();
//   this.orders.forEach(order => {
//     order.inventoryCode.forEach(bookNo => {
//       if (!this.getBookDetails[bookNo]) {  // Check if details are already loaded
//         this.fetchBookById(bookNo);
//       }
//     });
//   });
// }

};
</script>

<style scoped>
</style>