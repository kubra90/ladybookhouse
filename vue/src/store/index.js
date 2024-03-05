import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import { getBooks, getBookById, getFeaturedItems } from '../services/BookService'
import { register, login } from '../services/AuthService'
import { getOrders } from '../services/OrderService'
import { addBookshelf, deleteBook, getBookshelf } from '../services/BookshelfService'
Vue.use(Vuex)
/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    startingVal: 0,
    endingVal: 10,
    books: [],
    book: {},
    newArrivals: [],
    featuredItems: [],
    basketCount: 0,
    cartBooks: [],
    // bookshelf
    savedBooks: [],
    // one saved book
    savedBook: {},
    // orders
    orders: [],
    order: {}
  },
  getters: {
    isAuthenticated: state => state.user.email,
    newBookArrivals(state){
       const threeMonthsAgo = new Date();
       threeMonthsAgo.setMonth(threeMonthsAgo.getMonth() - 3);
       return state.books.filter(book=> {
         const bookDate = new Date(book.listed_date)
         return bookDate >= threeMonthsAgo;
       })
       .sort((a, b) => {
        const dateA = new Date(a.listed_date);
        const dateB =new Date(b.listed_date);

        return dateB - dateA;
       })
    },
    featuredItems(state){
      return state.books.filter(book=> {
       return book.sku.includes('SS') || book.sku.includes('NS') ||
       book.sku.includes('SSL') || book.sku.includes('D') ||
       book.sku.includes('AM') || book.sku.includes('CS')
      })
    },
    filteredBooksByCateg: state => (bookCateg) => {
      return state.books.filter(book => book.category === bookCateg)
    }
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, data) {
      state.user = data;
      localStorage.setItem('user', JSON.stringify(data));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_BOOKS(state, data) {
      state.books = data
    },
    SET_BOOK(state, data) {
      state.book = data
    },
    SET_NEW_ARRIVALS(state, data) {
      state.newArrivals = data
    },
    SET_FEATURED_ITEMS(state, data) {
      state.featuredItems = data
    },
    GET_NEXT_BOOKS(state, values) {
      state.startingVal = values.startingVal;
      state.endingVal = values.endingVal;
    },
    SET_BOOK_COUNT(state, data) {
      state.basketCount = data
    },
    //add the book to cart
    ADD_TO_CART(state, book) {
      state.cartBooks.push(book);
      state.basketCount++;
    },
    UPDATE_CART(state, newCart) {
      state.basketCount--;
      state.cartBooks = newCart;
    },
    SET_ORDERS(state, data) {
      state.orders = data
    },

    // new code for modifying bookshelf logic
    SET_BOOKSHELF(state, data){
      state.savedBooks = data
    },

    SET_SAVED_BOOK(state, data){
      state.savedBook = data
    },
    REMOVE_BOOK_FROM_BOOKSHELF(state, index){
      state.savedBooks.splice(index, 1)
    },
  },
  actions: {
    async registerUser({ commit }, user) {
      const response = await register(user)
      commit('SET_USER', response.data.user)
      commit('LOGOUT')
      return response
    },
    async loginUser({ commit }, user) {
      const response = await login(user)
      commit('SET_USER', response.data.user)
      commit('SET_AUTH_TOKEN', response.data.token);
      console.log(response.data.token);
      return response
    },
    addToCart({ commit }, book) {
      commit('ADD_TO_CART', book);
    },
    async fetchOrders({commit}) {
      const response = await getOrders();
      commit('SET_ORDERS', response.data)
  },

    async fetchBookshelf({commit}) {
      const response = await getBookshelf();
     
      commit('SET_BOOKSHELF', response.data)
    },

    async deleteBookFromBookshelf({commit, state}, sku){
        const response = await deleteBook(sku)
        if(response && response.status === 200){
          const index = state.savedBooks.findIndex(book => book.sku === sku);
          if(index !== -1){
            commit('REMOVE_BOOK_FROM_BOOKSHELF', index)
          }
        }
    },

  async addBookToBookshelf({commit}, sku) {
     const response = await addBookshelf(sku)
     commit('SET_SAVED_BOOK', response.data)
  },
    removeBook({ commit,state }, index) {
      let updatedCart = [...state.cartBooks];
      updatedCart.splice(index, 1);
      commit('UPDATE_CART', updatedCart)
    },
    async fetchBooks({ commit }) {
      const response = await getBooks()
      commit('SET_BOOKS', response.data)
    },
    async fetchBookById({ commit }, sku) {
      const response = await getBookById(sku)
      commit('SET_BOOK', response.data)
    },
    // async fetchNewArrivals({ commit }) {
    //   const response = await getNewArrivals()
    //   commit('SET_NEW_ARRIVALS', response.data)
    // },
    async fetchFeaturedItems({ commit }) {
      const response = await getFeaturedItems()
      commit('SET_FEATURED_ITEMS', response.data)
    },
  }
}
)
