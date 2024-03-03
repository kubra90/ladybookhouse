import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import Book from '../views/Book.vue'
import store from '../store/index'
import Detail from '../views/Detail.vue'
import About from '../views/About.vue'
import NewArrivalsView from '../views/NewArrivalsView.vue'
import FeaturedBookView from '../views/FeaturedBookView'
import Cart from '../views/Cart.vue'
import Checkout from '../views/Checkout.vue'
import MyAccount from '../views/MyAccount.vue'
import Contact from '../views/Contact.vue'
import TermsOfSale from '../views/Terms.vue'
import SavedBooks from '../views/SavedBooks.vue'
import Browse from '../views/Browse.vue'
import CategoryDetail from '../views/CategoryDetail.vue'
import Order from '../views/Order.vue'
import AdvancedSearch from '../views/AdvancedSearch.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    // this home page is the main page
    {
      path: "/",
      name: "home",
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/books",
      name: "book",
      component: Book,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/books/:sku",
      name: "detail",
      component: Detail,
      props: true
    },
    {
      path: "/about",
      name: "about",
      component: About,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/account",
      name: "account",
      component: MyAccount,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/new-arrivals",
      name: "new-arrivals-view",
      component: NewArrivalsView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/featured-books",
      name: "featured-book-view",
      component: FeaturedBookView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/shopping-cart",
      name: "cart",
      component: Cart,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/checkout-page",
      name: "checkout",
      component: Checkout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/checkOrders",
      name: "order",
      component: Order,
      meta: {
        requiresAuth: false
      }
    },

    {
      path: "/contact",
      name: "contact",
      component: Contact,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/terms",
      name: "terms-of-sale",
      component: TermsOfSale,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/saved_books",
      name: "saved-books",
      component: SavedBooks,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/browse",
      name: "browse",
      component: Browse,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/:category",
      name: "category-detail",
      component: CategoryDetail,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/advSearch",
      name: "advanced-search",
      component: AdvancedSearch,
      meta: {
        requiresAuth: false
      }
    },

  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
  
    next();
  }
});

export default router;
