import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000'
})
export default {

  getBooks() {
    return http.get('/books')
  },

  getBookById(sku) {
    return http.get(`/books/${sku}`)
  }
}
