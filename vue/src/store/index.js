import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import { getBooks, getBookById, getNewArrivals, getFeaturedItems } from '../services/BookService'
Vue.use(Vuex)
/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
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
    cartBooks: []
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
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
    GET_NEXT_BOOKS(state, values){
      state.startingVal = values.startingVal;
      state.endingVal = values.endingVal;
    },
    SET_BOOK_COUNT(state, data) {
      state.basketCount = data
    },
    //add the book to cart
    ADD_TO_CART(state, book){
      state.cartBooks.push(book);
      state.basketCount++;
    }
  },
  actions: {
    // addToCart({commit}, payload) {
    //     commit('SET_BOOK_COUNT', payload)
      
    //   },
    addToCart({commit}, book){
      commit('ADD_TO_CART', book);
    },
    async fetchBooks({commit}) {
        const response = await getBooks()
        commit('SET_BOOKS', response.data)
      },
    async fetchBookById({commit}, sku) {
        const response = await getBookById(sku)
        commit('SET_BOOK', response.data)
      },
    async fetchNewArrivals({commit}) {
        const response = await getNewArrivals()
        commit('SET_NEW_ARRIVALS', response.data)
      },
    async fetchFeaturedItems({commit}) {
        const response = await getFeaturedItems()
        commit('SET_FEATURED_ITEMS', response.data)
      },      
    }
  }
)
