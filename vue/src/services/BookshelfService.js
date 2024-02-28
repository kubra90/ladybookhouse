import axios from 'axios';



async function getBookshelf(headers){
    console.log({ headers })
    return axios.get('/your-books', { headers });
}


async function addBookshelf(sku, headers){
    return axios.post(`/bookshelf?sku=${sku}`, { headers })
}

async function deleteBook(sku){
    return axios.delete(`/removeBook?sku=${sku}`)
}
export {getBookshelf, addBookshelf, deleteBook}