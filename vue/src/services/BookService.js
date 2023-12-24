import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000'
})

async function getBooks() {
    const books = await http.get('/books')
    return books;
  }
async function getBookById(sku) {
    const book = await http.get(`/books/${sku}`)
    return book;
  }
  
export { getBooks, getBookById }

