import axios from 'axios';

async function getAddress(headers){
    return axios.get('/your-address', {headers})
}

export {getAddress};