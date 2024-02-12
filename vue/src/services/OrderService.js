import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000'
})

async function getOrders() {
    const orders = await http.get('/orders');
    return orders;
}

export {getOrders}