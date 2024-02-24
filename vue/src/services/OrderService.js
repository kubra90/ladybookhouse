import axios from 'axios';

async function getOrders(headers) {
    console.log({headers})
    return axios.get('/orders', { headers });
}

export {getOrders}