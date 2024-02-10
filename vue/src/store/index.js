import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import { getBooks, getBookById, getNewArrivals, getFeaturedItems } from '../services/BookService'
import { register, login } from '../services/AuthService'
import { getOrders } from '../services/OrderService'
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
    userSavedBooks: {},
    // orders
    orders: [],
    order: {}
  },
  getters: {
    isAuthenticated: state => state.user.email,
    getSavedBooks: state => (email) => {
      return state.userSavedBooks[email] || []
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
  
    // add to bookshelf
    ADD_TO_BOOKSHELF(state, { user, book }) {
      // initialize a savedBooks array for the user if it doesn't exist
      if (!state.userSavedBooks[user]) {
        Vue.set(state.userSavedBooks, user, [])
      }
      //check if the books already exist in the savedBooks array
      const existingBook = state.userSavedBooks[user].find(item => item.isbn === book.isbn);
      if (!existingBook) {
        state.userSavedBooks[user].push(book);
      }
    }
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
      return response
    },
    addToCart({ commit }, book) {
      commit('ADD_TO_CART', book);
    },
    // add the book to the bookshelf
    addToBookshelf({ commit, state }, book) {
      if (state.user && state.user.email) {
        commit('ADD_TO_BOOKSHELF', { user: state.user.email, book })
      } else {
        // need to check this one!!!
        console.error('you need to login or create an account!')
      }

    },
    // async fetchOrders({ commit }, email) {
    //   const response = await getOrders(email)
    //   commit('SET_ORDERS', response.data)
    // },
    // async fetchOrders({ commit, state}) {
    //    // Debugging line
    //   try {
    //     commit('SET_AUTH_TOKEN', state.token)
    //     const response = await getOrders();
    //     console.log("Orders fetched:", response.data); 
    //     commit('SET_ORDERS', response.data);
    //   } catch (error) {
    //     console.error('Failed to fetch orders:', error);
    //   }
    // },
    async fetchOrders({commit}) {
      const token = localStorage.getItem('token');
      const headers = token ? { Authorization: `Bearer ${token}` } : {};
      const response = await getOrders( { headers });
      commit('SET_ORDERS', response.data)
  },
  
    
    
    removeBook({ commit, state }, index) {
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
    async fetchNewArrivals({ commit }) {
      const response = await getNewArrivals()
      commit('SET_NEW_ARRIVALS', response.data)
    },
    async fetchFeaturedItems({ commit }) {
      const response = await getFeaturedItems()
      commit('SET_FEATURED_ITEMS', response.data)
    },
  }
}
)
