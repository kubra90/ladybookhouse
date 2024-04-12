

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
                <div>Order Total: </div>
            </div>
          </div>
          <div class="card-body">
            <!-- <h5 class="card-title">{{ order.bookTitle }}</h5>
              <p class="card-text">Item Status: {{ order.status }}</p> -->
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
    return {};
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
    ...mapState(["orders", "user"]),
  },
  methods: {
    ...mapActions(["fetchOrders"]), // Moved mapActions to methods
  },
  created() {
    this.fetchOrders();
    console.log(this.fetchOrders());
  },
};
</script>

<style scoped>
</style>