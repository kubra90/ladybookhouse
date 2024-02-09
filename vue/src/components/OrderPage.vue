<template>
    <div >
<div v-for="order in this.orders" :order="order" :key="order.id">
   <ul>{{ order.email}}</ul>
   <ul>{{ order.bookNo }}</ul>
</div>
    </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'

export default {
    name: 'order-page',
    data() {
        return {
        user: {
            email: "",
            password: "",
        }
    }
    },
    computed: {
        ...mapState(['orders'])
    },
    
    methods: {
        ...mapActions(["fetchOrders"]),
        checkOrder(){
            console.log(this.orders);
        }
      }
    }
</script>

<style scoped>

</style>