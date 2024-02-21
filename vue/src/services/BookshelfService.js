import axios from 'axios';

async function getBookshelf(headers){
    return axios.get('/your-books', {headers});
}

// add to the bookshelf
async function addBookshelf(savedBook){
    return axios.post('/bookshelf', savedBook)
}
export {getBookshelf, addBookshelf}