import axios from 'axios';

async function getAddress(headers){
    return axios.get('/your-address', {headers})
}

// async function deleteAddress(addressId, headers){
//     return axios.delete(`removeAddress?addressId=${addressId}`, {headers})
// }

export {getAddress};