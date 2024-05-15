<template>
    <div>
        <h1>Processing your Payment</h1>
    </div>
</template>

<script>
import {executePayment} from '@/services/OrderService';
export default {
    name:"PaymentSuccessPage",
    mounted() {
        this.finalizePayment();
    },
    methods: {
        finalizePayment() {
            const urlParams = new URLSearchParams(window.location.search);
            const paymentId = urlParams.get('paymentId');
            const payerId =urlParams.get('PayerId');
            
            executePayment(paymentId, payerId)
            .then(response => {
                //    handle success
                console.log("Payment executed successfully:", response.data);
                this.$router.push({name: 'home'})
                }).catch(error => {
                    console.log("error executing payment:",  error);
                    this.$router.push({name: 'error'});
                })
            }
        }
    }

</script>