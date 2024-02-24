import axios from 'axios';


// const http = axios.create({
//     baseURL: 'http://localhost:9000'
// })

async function getBookshelf(headers){
    console.log({ headers })
    return axios.get('/your-books', { headers });
}

// async function addBookshelf(sku, headers){
//     console.log('Headers:', { headers })
//     return axios.post(`/bookshelf/${sku}`, {},{ headers })
// }

async function addBookshelf(sku, headers){
    return axios.post(`/bookshelf?sku=${sku}`, { headers })
}
export {getBookshelf, addBookshelf}