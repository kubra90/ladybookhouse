import axios from 'axios';



async function getBookshelf(headers){
    console.log({ headers })
    return axios.get('/your-books', { headers });
}


async function addBookshelf(sku, headers){
    return axios.post(`/bookshelf?sku=${sku}`, { headers })
}

async function deleteBook(sku, headers){
    return axios.delete(`/removeBook?sku=${sku}`, { headers })
}
export {getBookshelf, addBookshelf, deleteBook}