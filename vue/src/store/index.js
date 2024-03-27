import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import { getBooks, getBookById, getFeaturedItems } from '../services/BookService'
import { register, login } from '../services/AuthService'
import { getOrders, placeOrder } from '../services/OrderService'
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
    order: {},
    deliveryOption: "USPS",
    // paymentMethod: "paypal",

    checkout: {
      paymentMethod: '',
      returningUserEditingShipping: false,
    },
    tempOrderInfo: {}
  },
  getters: {
    isAuthenticated: state => state.user.email,
    totalShippingCost: (state) => {
        let totalWeight=0;
        let shippingCost=0;
       state.cartBooks.forEach( book=> {
        totalWeight+=book.weight;
       })
        if(totalWeight>0 && totalWeight<17){
          shippingCost = 4.15;
        }else if(totalWeight>16 && totalWeight<33){
          shippingCost = 4.90;
        }else if(totalWeight>32 && totalWeight<=48){
          shippingCost =5.65;
        }else if(totalWeight>48 && totalWeight<=64){
          shippingCost =6.40;
        }else if(totalWeight>64 && totalWeight<=80){
          shippingCost =7.15;
        }else if(totalWeight>80 && totalWeight <=96){
          shippingCost = 7.90;
        }else if(totalWeight>96 && totalWeight<=112){
          shippingCost = 8.65;
        }else{
          shippingCost =9.99;
        }

        if(state.deliveryOption === "UPS"){
          shippingCost+=7.99;
        }
        console.log(shippingCost);
        return shippingCost;
      },
      subTotalPrice: (state)=>{
        return state.cartBooks.reduce((total, book) => total + book.price, 0);
      },
      totalPrice: (state, getters) => {
        // Assuming subTotalPrice returns the total price of books without shipping
        const subTotalPrice = getters.subTotalPrice;
        // Assuming totalShippingCost returns the shipping cost based on the total weight
        const shippingCost = getters.totalShippingCost;
        // Calculate the final total price by adding the subtotal price and the shipping cost
        const total = subTotalPrice + shippingCost;
        return total;
    },
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
      // additional step to clear tempOrderInfo and cartBooks
      state.tempOrderInfo = {};
      state.cartBooks= [];
      state.basketCount = 0

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
    SET_ORDER(state, data){
      state.order = data
    },
    SET_TEMP_ORDER_INFO(state, order){
      state.tempOrderInfo = order
    },
    SET_BOOKSHELF(state, data){
      state.savedBooks = data
    },

    SET_SAVED_BOOK(state, data){
      state.savedBook = data
    },
    REMOVE_BOOK_FROM_BOOKSHELF(state, index){
      state.savedBooks.splice(index, 1)
    },
    SET_DELIVERY_OPTION(state, option){
      console.log(option);
      state.deliveryOption = option
    },
    SET_PAYMENT_METHOD(state, data){
      state.checkout.paymentMethod = data
    },
    SET_RETURNING_USER_EDITING_SHIPPING(state, value) {
      state.checkout.returningUserEditingShipping = value;
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
      console.log(response.data.token);
      return response
    },
    addToCart({ commit }, book) {
      commit('ADD_TO_CART', book);
    },
    updateTempOrderInfo({commit}, order){
     commit('SET_TEMP_ORDER_INFO', order)
    },
    // updateSelectedPaymentMethod({commit}, paymentMethod){
    // commit('SET_PAYMENT_OPTION', paymentMethod);
    // },
    updateSelectedPaymentMethod({ commit }, method) {
      commit('SET_PAYMENT_METHOD', method);
    },
    updateReturningUserEditingShipping({ commit }, value) {
      commit('SET_RETURNING_USER_EDITING_SHIPPING', value);
    },
   
    async fetchOrders({commit}) {
      const response = await getOrders();
      commit('SET_ORDERS', response.data)
  },
  async createOrder({commit}, order){
    const response = await placeOrder(order)
    commit('SET_ORDER', response.data)
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

    async fetchFeaturedItems({ commit }) {
      const response = await getFeaturedItems()
      commit('SET_FEATURED_ITEMS', response.data)
    },
  }
}
)
