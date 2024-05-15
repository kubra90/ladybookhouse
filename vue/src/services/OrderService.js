import axios from 'axios';

async function getOrders(headers) {
    console.log({headers})
    return axios.get('/orders', { headers });
}

async function placeOrder(order){
    return axios.post('/checkout', order)
}

// payment
async function executePayment(paymentId, payerId){
    return axios.post('/execute-payment', {paymentId, payerId})
}

export {getOrders, placeOrder, executePayment}