import axios from 'axios';

async function getOrders(headers) {
    console.log({headers})
    return axios.get('/orders', { headers });
}

async function placeOrder(order){
    return axios.post('/checkout', order)
}

export {getOrders, placeOrder}