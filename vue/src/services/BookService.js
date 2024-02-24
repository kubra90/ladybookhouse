import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000'
})

async function getBooks() {
    const books = await http.get('/books')
    console.log('API Base URL:', process.env.VUE_APP_REMOTE_API); // Debugging line

    return books;
  }
async function getBookById(sku) {
    const book = await http.get(`/books/${sku}`)
    return book;
  }
async function getNewArrivals() {
    const newArrivals = await http.get('/new-arrivals')
    return newArrivals;
  }
async function getFeaturedItems() {
    const featuredItems = await http.get('/featured-items')
    return featuredItems;
  }
  
export { getBooks, getBookById, getNewArrivals, getFeaturedItems }

