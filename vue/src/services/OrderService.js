import axios from 'axios';

async function getOrders(headers) {
    return axios.get('/orders', { headers });
}

export {getOrders}