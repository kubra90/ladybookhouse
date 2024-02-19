<template>
    <div >
        <p>order page</p>
        {{ user.email }}
        
        {{ orders }}
        <!-- {{ this.fetchOrders }} -->
<div v-for="order in orders" :key="order.orderId">
   <ul>
    <li>{{ order.email}}</li>
    <!-- <li>{{ user.email }}</li> -->
    <li>{{ order.inventoryCode }}</li>
   </ul>
</div>
    </div>
</template>

<script>
import {mapActions,  mapState } from 'vuex'

export default {
    name: 'order-page',
    data() {
        return {
    }
    },
    computed: {
        ...mapState(['orders', 'user'])
    },
    methods: {
      
    ...mapActions(['fetchOrders']), // Moved mapActions to methods
    },
    created() {
this.fetchOrders();

    }
    }

</script>

<style scoped>

</style>