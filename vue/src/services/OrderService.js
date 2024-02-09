import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000'
})

async function getOrders(email) {
    const orders = await http.get('/orders', email);
    return orders;
}

export {getOrders}