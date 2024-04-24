import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import { getBooks, getBookById } from '../services/BookService'
import { register, login } from '../services/AuthService'
import { getOrders, placeOrder } from '../services/OrderService'
import { addBookshelf, deleteBook, getBookshelf } from '../services/BookshelfService'
import { getAddress, getAddressByAddressId} from '../services/AddressService'
// import BookDetail from '../components/BookDetail.vue'
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
    shippingAddresses: [], 
    address: {},
    addresses: [],
    // Object to store multiple book details keyed by sku
    bookDetails: {},
    // deliveryOption: "USPS",
    // paymentMethod: "paypal",

    checkout: {
      paymentMethod: '',
      returningUserEditingShipping: false,
    },
    categories: [
      { value: 'Literature', text: 'Literature' },
      { value: 'Science', text: 'Science' },
      { value: 'Social Science', text: 'Social Science' },
      { value: 'Art', text: 'Art' },
      { value: 'Bestseller', text: 'Bestseller' },
      { value: 'Technology', text: 'Technology' },
      { value: 'Language', text: 'Language' },
      { value: 'Religion&Philosophy', text: 'Religion&Philosophy' },
      { value: 'History&Memory', text: 'History&Memory' },
      { value: 'Design', text: 'Design' },
      { value: 'Kids', text: 'Kids' },
      { value: 'Other', text: 'Other' }
    ],
    tempOrderInfo: {
      email: "",
      inventoryCode: [],
      isSaveAddress: false,
      isInfoMail: false,
      message: "",
      shippingAddress: {
        firstname: '',
        lastname: '',
        addressLine: '',
        city: '',
        stateInfo: '',
        zipcode: '',
        country: '',
        phoneNumber: '',
        addressType : "shipping"
      },
      billingAddress: {
        firstname: '',
        lastname: '',
        addressLine: '',
        city: '',
        stateInfo: '',
        zipcode: '',
        country: '',
        phoneNumber: '',
        addressType: "billing"
      },
      deliveryOption: 'USPS',
      useSameAddressForBilling: false
    }
  },
  getters: {
    getCategories: state => state.categories,
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

        if(state.tempOrderInfo.deliveryOption === "UPS"){
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
      // this.$store.commit('RESET_TEMP_ORDER_INFO')
      state.cartBooks= [];
      state.basketCount = 0

    },

    

    UPDATE_SHIPPING_FIRST_NAME(state, firstname){
      if (!state.tempOrderInfo.shippingAddress) {
        Vue.set(state.tempOrderInfo, 'shippingAddress', { firstname: '' });
      }
      state.tempOrderInfo.shippingAddress.firstname = firstname
    },
    UPDATE_SHIPPING_LAST_NAME(state, lastname){
      if (!state.tempOrderInfo.shippingAddress) {
        Vue.set(state.tempOrderInfo, 'shippingAddress', { lastname: '' });
      }
      state.tempOrderInfo.shippingAddress.lastname = lastname
    },
    UPDATE_SHIPPING_PHONE_NUMBER(state, phoneNumber){
      if (!state.tempOrderInfo.shippingAddress) {
        Vue.set(state.tempOrderInfo, 'shippingAddress', { phoneNumber: '' });
      }
      state.tempOrderInfo.shippingAddress.phoneNumber = phoneNumber
    },
    UPDATE_SHIPPING_ADDRESS_LINE(state, addressLine){
      if (!state.tempOrderInfo.shippingAddress) {
        Vue.set(state.tempOrderInfo, 'shippingAddress', { addressLine: '' });
      }
      state.tempOrderInfo.shippingAddress.addressLine = addressLine
    },
    UPDATE_SHIPPING_COUNTRY(state, country){
      if (!state.tempOrderInfo.shippingAddress) {
        Vue.set(state.tempOrderInfo, 'shippingAddress', { country: '' });
      }
      state.tempOrderInfo.shippingAddress.country = country
    },
    UPDATE_SHIPPING_CITY(state, city){
      if (!state.tempOrderInfo.shippingAddress) {
        Vue.set(state.tempOrderInfo, 'shippingAddress', { city: '' });
      }
      state.tempOrderInfo.shippingAddress.city = city
    },  
    UPDATE_SHIPPING_STATE(state, stateInfo){
      if (!state.tempOrderInfo.shippingAddress) {
        Vue.set(state.tempOrderInfo, 'shippingAddress', { stateInfo: '' });
      }
      state.tempOrderInfo.shippingAddress.stateInfo = stateInfo
    },  
    UPDATE_SHIPPING_ZIP_CODE(state, zipcode){
      if (!state.tempOrderInfo.shippingAddress) {
        Vue.set(state.tempOrderInfo, 'shippingAddress', { zipcode: '' });
      }
      state.tempOrderInfo.shippingAddress.zipcode = zipcode
    },

    // mutations for billing info
    
    UPDATE_BILLING_FIRST_NAME(state, firstname){
      if (!state.tempOrderInfo.billingAddress) {
        Vue.set(state.tempOrderInfo, 'billingAddress', { firstname: '' });
      }
      state.tempOrderInfo.billingAddress.firstname = firstname
    },
    UPDATE_BILLING_LAST_NAME(state, lastname){
      if (!state.tempOrderInfo.billingAddress) {
        Vue.set(state.tempOrderInfo, 'billingAddress', { lastname: '' });
      }
      state.tempOrderInfo.billingAddress.lastname = lastname
    },
    UPDATE_BILLING_PHONE_NUMBER(state, phoneNumber){
      if (!state.tempOrderInfo.billingAddress) {
        Vue.set(state.tempOrderInfo, 'billingAddress', { phoneNumber: '' });
      }
      state.tempOrderInfo.billingAddress.phoneNumber = phoneNumber
    },
    UPDATE_BILLING_ADDRESS_LINE(state, addressLine){
      if (!state.tempOrderInfo.billingAddress) {
        Vue.set(state.tempOrderInfo, 'billingAddress', { addressLine: '' });
      }
      state.tempOrderInfo.billingAddress.addressLine = addressLine
    },
    UPDATE_BILLING_COUNTRY(state, country){
      if (!state.tempOrderInfo.billingAddress) {
        Vue.set(state.tempOrderInfo, 'billingAddress', { country: '' });
      }
      state.tempOrderInfo.billingAddress.country = country
    },
    UPDATE_BILLING_CITY(state, city){
      if (!state.tempOrderInfo.billingAddress) {
        Vue.set(state.tempOrderInfo, 'billingAddress', { city: '' });
      }
      state.tempOrderInfo.billingAddress.city = city
    },  
    UPDATE_BILLING_STATE_INFO(state, stateInfo){
      if (!state.tempOrderInfo.billingAddress) {
        Vue.set(state.tempOrderInfo, 'billingAddress', { stateInfo: '' });
      }
      state.tempOrderInfo.billingAddress.stateInfo = stateInfo
    },  
    UPDATE_BILLING_ZIP_CODE(state, zipcode){
      if (!state.tempOrderInfo.billingAddress) {
        Vue.set(state.tempOrderInfo, 'billingAddress', { zipcode: '' });
      }
      state.tempOrderInfo.billingAddress.zipcode = zipcode
    },
    // till here

    RESET_TEMP_ORDER_INFO(state){
      state.tempOrderInfo = {
        shippingAddress: {
          firstname: '',
          lastname: '',
          addressLine: '',
          city: '',
          stateInfo: '',
          zipcode: '',
          country: '',
          phoneNumber: '',
          addressType: 'shipping'
        },
        inventoryCode: [],
        email: '',
        isSaveAddress: false,
        isInfoMail: false,
        message: "",
        billingAddress: {
          firstname: '',
          lastname: '',
          addressLine: '',
          city: '',
          stateInfo: '',
          zipcode: '',
          country: '',
          phoneNumber: '',
          addressType: "billing"
        },
        deliveryOption : ''


        }
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
    SET_DELIVERY_OPTION(state, data){
      console.log(data);
     state.tempOrderInfo.deliveryOption = data;
    },
    SET_BOOK_DETAILS(state, {sku, details}){
      Vue.set(state.bookDetails, sku, details)
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
    SET_ADDRESS(state, data){
      state.address = data
    },
    // look at this below code to check how you can store one object that includes different addresses
    SET_SHIPPING_ADDRESS(state, { id, address }) {
      console.log("Mutation setting address for ID:", id, "Data:", address);
      Vue.set(state.shippingAddresses, id, address);
    },
    SET_ADDRESSES(state, data){
     state.addresses =data
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
    // REMOVE_ADDRESS_FROM_ADDRESSES(state, index){
    //   state.addresses.splice(index, 1)
    // },
    // SET_DELIVERY_OPTION(state, option){
    //   console.log(option);
    //   state.deliveryOption = option
    // },
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
      console.log(response.data)
  },
  async createOrder({commit}, order){
    const response = await placeOrder(order)
    commit('SET_ORDER', response.data)
   },
    async getUserAddresses({commit}){
     const response = await getAddress()
     commit('SET_ADDRESSES', response.data)
   },
  //  async getShippingAddressById({commit}, id){
  //     const response = await getAddressByAddressId(id);
  //     commit('SET_SHIPPING_ADDRESS', response.data)
  //  },
  async getShippingAddressById({ commit }, id) {
    try {
      const response = await getAddressByAddressId(id);
      console.log("Action received address data:", response);
      commit('SET_SHIPPING_ADDRESS', { id, address: response.data });
      return response.data
    } catch (error) {
      console.error("Failed to fetch shipping address by ID:", error);
    }
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

    // async deleteAddressFromAddressBook({commit, state}, addressId){
    //   const response = await deleteAddress(addressId)
    //   if(response && response.status === 200){
    //     const index = state.addresses.findIndex(address => address.address_id === addressId);
    //     if(index !== -1){
    //        commit('REMOVE_ADDRESS_FROM_ADDRESSES', index);
    //     }
    //   }
    // },

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
    // async fetchBookById({ commit }, sku) {
    //   const response = await getBookById(sku)
    //   commit('SET_BOOK', response.data)
    // },
    async fetchBookById({ commit }, sku) {
      try{
      const response = await getBookById(sku)
      if(response &&response.data){
      commit('SET_BOOK', response.data)
      commit('SET_BOOK_DETAILS', {sku, details: response.data})
      console.log(this.state.bookDetails);
      }else {
        throw new Error('no data received')
      }
      }catch(error){
        console.error('error fetching book by sku', error);
      }
    },

    // async fetchFeaturedItems({ commit }) {
    //   const response = await getFeaturedItems()
    //   commit('SET_FEATURED_ITEMS', response.data)
    // },
  }
}
)
