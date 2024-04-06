<template>
  <div>
    <alert-toast ref="alertToast"></alert-toast>
    <section v-if="checkBookCart" class="bg-light py-5">
      <div class="container">
        <div class="row">
          <div class="col-xl-8 col-lg-8 mb-4">
            <!-- begin form -->
           
            <form ref="checkoutForm" @submit.prevent="continueToPayment">
              <div v-if="!isAuthenticated" class="card mb-4 border shadow-0">
                <div class="p-4 d-flex justify-content-between">
                  <div class="">
                    <h5>Have an account?</h5>
                    <p class="mb-0 text-wrap">
                      If you already have an account, please sign in for faster
                      checkout or you can register here
                    </p>
                  </div>
                  <div
                    class="d-flex align-items-center justify-content-center flex-column flex-md-row"
                  >
                    <router-link
                      v-bind:to="{ name: 'register' }"
                      class="btn btn-outline-primary me-0 me-md-2 mb-2 mb-md-0 w-100"
                      >Register</router-link
                    >
                    <router-link
                      v-bind:to="{ name: 'login' }"
                      class="btn btn-primary shadow-0 text-nowrap w-100"
                      >Sign in</router-link
                    >
                  </div>
                </div>
              </div>

              <!-- Checkout for user-->
              <div v-if="isAuthenticated" class="card shadow-0 border mb-4">
                <div class="p-4">
                  <h5 class="card-title mb-3">Checkout</h5>
                  <div class="row">
                    <div class="col-6 mb-3">
                      <p class="mb-0">First name</p>
                      <div class="form-outline">
                        <input
                          type="text"
                          id="typeText"
                          v-model="user.firstName"
                          placeholder="Type here"
                          class="form-control"
                          required
                        />
                      </div>
                    </div>

                    <div class="col-6">
                      <p class="mb-0">Last name</p>
                      <div class="form-outline">
                        <input
                          type="text"
                          id="typeText"
                          v-model="user.lastName"
                          placeholder="Type here"
                          class="form-control"
                          required
                        />
                      </div>
                    </div>

                    <div class="col-6 mb-3">
                      <p class="mb-0">Phone</p>
                      <div class="form-outline">
                        <input
                          type="tel"
                          id="typePhone"
                          v-model="orderInfo.shippingAddress.phoneNumber"
                          class="form-control"
                        />
                      </div>
                    </div>

                    <div class="col-6 mb-3">
                      <p class="mb-0">Email</p>
                      <div class="form-outline">
                        <input
                          type="email"
                          id="typeEmail"
                          v-model="user.email"
                          placeholder="example@gmail.com"
                          class="form-control"
                          required
                        />
                      </div>
                    </div>
                  </div>

                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      value=""
                      id="flexCheckDefault"
                    />
                    <label class="form-check-label" for="flexCheckDefault"
                      >Keep me up to date on new arrivals and discount</label
                    >
                  </div>
                </div>
              </div>

              <!-- checkout for guest -->
              <div
                v-else-if="!isAuthenticated"
                class="card shadow-0 border mb-4"
              >
                <div class="p-4">
                  <h5 class="card-title mb-3">Guest checkout</h5>
                  <div class="row">
                    <div class="col-6 mb-3">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        First Name <span class="text-danger">*</span>
                      </label>
                      <div class="form-outline">
                        <input
                          type="text"
                          id="typeText"
                          v-model="orderInfo.shippingAddress.firstname"
                          placeholder="Type here"
                          class="form-control"
                          required
                        />
                      </div>
                    </div>

                    <div class="col-6">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        Last Name <span class="text-danger">*</span>
                      </label>
                      <div class="form-outline">
                        <input
                          type="text"
                          id="typeText"
                          v-model="orderInfo.shippingAddress.lastname"
                          placeholder="Type here"
                          class="form-control"
                          required
                        />
                      </div>
                    </div>

                    <div class="col-6 mb-3">
                      <p class="mb-0">Phone</p>
                      <div class="form-outline">
                        <input
                          type="tel"
                          id="typePhone"
                          v-model="orderInfo.shippingAddress.phoneNumber"
                          class="form-control"
                        />
                      </div>
                    </div>

                    <div class="col-6 mb-3">
                      <!-- <p class="mb-0">Email</p> -->
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        Email address <span class="text-danger">*</span>
                      </label>
                      <div class="form-outline">
                        <input
                          type="email"
                          id="typeEmail"
                          v-model="orderInfo.email"
                          placeholder="example@gmail.com"
                          class="form-control"
                          required
                        />
                      </div>
                    </div>
                  </div>

                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      value=""
                      id="flexCheckDefault"
                    />
                    <label class="form-check-label" for="flexCheckDefault"
                      >Keep me up to date on new arrivals and discount</label
                    >
                  </div>
                </div>
              </div>
              <!-- shipping info -->
              <!-- <hr class="my-4" /> -->
              <div class="card shadow-0 border">
                <div class="p-4" ref="shippingSection">
                  <h5 class="card-title mb-3">Shipping info</h5>

                  <div class="row mb-3">
                    <div class="col-lg-4 mb-3">
                      <!-- Default checked radio -->
                      <div class="form-check h-100 border rounded-3">
                        <div class="p-3">
                          <input
                            class="form-check-input"
                            type="radio"
                            name="flexRadioDefault"
                            id="flexRadioDefault1"
                            checked
                            value="USPS"
                            v-model="selectedDelivery"
                          />
                          <label
                            class="form-check-label"
                            for="flexRadioDefault1"
                          >
                            USPS Media <br />
                            <small class="text-muted">6-11 business days</small>
                          </label>
                        </div>
                      </div>
                    </div>
                    <div class="col-lg-4 mb-3">
                      <!-- Default radio -->
                      <div class="form-check h-100 border rounded-3">
                        <div class="p-3">
                          <input
                            class="form-check-input"
                            type="radio"
                            name="flexRadioDefault"
                            id="flexRadioDefault2"
                            value="UPS"
                            v-model="selectedDelivery"
                          />
                          <label
                            class="form-check-label"
                            for="flexRadioDefault2"
                          >
                            UPS Express <br />
                            <small class="text-muted"
                              >4-7 business days. May apply extra charges in
                              shopping cost
                            </small>
                          </label>
                        </div>
                      </div>
                    </div>
                    <div class="col-lg-4 mb-3"></div>
                  </div>

                  <div class="row">
                    <div class="col-sm-8 mb-3">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        Address <span class="text-danger">*</span>
                      </label>
                      <div class="form-outline">
                        <input
                          type="text"
                          id="typeText"
                          v-model="orderInfo.shippingAddress.addressLine"
                          placeholder="Type here"
                          class="form-control"
                          required
                        />
                      </div>
                    </div>
                    <div class="col-sm-4 mb-3">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        Country <span class="text-danger">*</span>
                      </label>
                      <select
                        class="form-select"
                        v-model="orderInfo.shippingAddress.country"
                        required
                      >
                        <option value="USA">United States</option>
                      </select>
                    </div>
                    <div class="col-sm-4 col-6 mb-3">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        State <span class="text-danger">*</span>
                      </label>
                      <select
                        class="form-select"
                        v-model="orderInfo.shippingAddress.state"
                        required
                      >
                        <option value="AK">Alaska</option>
                        <option value="AL">Alabama</option>
                        <option value="AR">Arkansas</option>
                        <option value="AZ">Arizona</option>
                        <option value="CA">California</option>
                        <option value="CO">Colorado</option>
                        <option value="CT">Connecticut</option>
                        <option value="DC">District of Columbia</option>
                        <option value="DE">Delaware</option>
                        <option value="FL">Florida</option>
                        <option value="GA">Georgia</option>
                        <option value="HI">Hawaii</option>
                        <option value="IA">Iowa</option>
                        <option value="ID">Idaho</option>
                        <option value="IL">Illinois</option>
                        <option value="IN">Indiana</option>
                        <option value="KS">Kansas</option>
                        <option value="KY">Kentucky</option>
                        <option value="LA">Louisiana</option>
                        <option value="MA">Massachusetts</option>
                        <option value="MD">Maryland</option>
                        <option value="ME">Maine</option>
                        <option value="MI">Michigan</option>
                        <option value="MN">Minnesota</option>
                        <option value="MO">Missouri</option>
                        <option value="MS">Mississippi</option>
                        <option value="MT">Montana</option>
                        <option value="NC">North Carolina</option>
                        <option value="ND">North Dakota</option>
                        <option value="NE">Nebraska</option>
                        <option value="NH">New Hampshire</option>
                        <option value="NJ">New Jersey</option>
                        <option value="NM">New Mexico</option>
                        <option value="NV">Nevada</option>
                        <option value="NY">New York</option>
                        <option value="OH">Ohio</option>
                        <option value="OK">Oklahoma</option>
                        <option value="OR">Oregon</option>
                        <option value="PA">Pennsylvania</option>
                        <option value="PR">Puerto Rico</option>
                        <option value="RI">Rhode Island</option>
                        <option value="SC">South Carolina</option>
                        <option value="SD">South Dakota</option>
                        <option value="TN">Tennessee</option>
                        <option value="TX">Texas</option>
                        <option value="UT">Utah</option>
                        <option value="VA">Virginia</option>
                        <option value="VT">Vermont</option>
                        <option value="WA">Washington</option>
                        <option value="WI">Wisconsin</option>
                        <option value="WV">West Virginia</option>
                        <option value="WY">Wyoming</option>
                      </select>
                    </div>

                    <!-- <div class="col-sm-4 mb-3">
                    <p class="mb-0"></p>
                    <div class="form-outline">
                      <input
                        type="text"
                        id="typeText"
                        placeholder="Type here"
                        class="form-control"
                      />
                    </div>
                  </div> -->

                    <div class="col-sm-4 col-6 mb-3">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        City <span class="text-danger">*</span>
                      </label>
                      <div class="form-outline">
                        <input
                          type="text"
                          id="typeText"
                          v-model="orderInfo.shippingAddress.city"
                          class="form-control"
                          required
                        />
                      </div>
                    </div>

                    <div class="col-sm-4 col-6 mb-3">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        Zip <span class="text-danger">*</span>
                      </label>
                      <div class="form-outline">
                        <input
                          type="text"
                          id="typeText"
                          v-model="orderInfo.shippingAddress.zipcode"
                          class="form-control"
                          required
                          pattern="\d{5}(-\d{4})?"
                          title="Zip code can only contain numbers and hyphens."
                        />
                      </div>
                    </div>
                  </div>

                  <div class="form-check mb-3" v-if="isAuthenticated">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      v-model="orderInfo.saveAddress"
                      id="flexCheckDefault1"
                    />
                    <label class="form-check-label" for="flexCheckDefault1"
                      >Save this address</label
                    >
                  </div>

                  <div class="mb-3">
                    <p class="mb-0">Message to seller</p>
                    <div class="form-outline">
                      <textarea
                        class="form-control"
                        id="textAreaExample1"
                        v-model="orderInfo.message"
                        rows="2"
                      ></textarea>
                    </div>
                  </div>

                  <!-- <div class="float-end">
                    <button class="btn btn-light border">
                      Continue Checkout
                    </button>
                  </div> -->
                </div>
              </div>

              <!-- billing info -->
              <div class="card shadow-0 border mt-4">
                <div class="p-4" ref="billingSection">
                  <h5 class="card-title mb-3">Billing info</h5>

                  <!-- <div class="row mb-3">
                    <div class="col-lg-4 mb-3"></div>
                  </div>  -->

                  <!-- use the same address with the shipping one -->
                  <div class="form-check mb-3">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      v-model="useSameAddress"
                      id="useSameAddressCheckbox"
                      @change="setBillingAddress"
                    />
                    <label class="form-check-label" for="flexCheckDefault1"
                      >Use the shipping address as the billing address</label
                    >
                  </div>

                  <div class="row">

                    <!-- here for firstname and lastname -->

                    <div class="col-6 mb-3">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        First Name <span class="text-danger">*</span>
                      </label>
                      <div class="form-outline">
                        <input
                          type="text"
                          id="typeText"
                          v-model="orderInfo.billingAddress.firstname"
                          placeholder="Type here"
                          class="form-control"
                          required
                        />
                      </div>
                    </div>

                    <div class="col-6">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        Last Name <span class="text-danger">*</span>
                      </label>
                      <div class="form-outline">
                        <input
                          type="text"
                          id="typeText"
                          v-model="orderInfo.billingAddress.lastname"
                          placeholder="Type here"
                          class="form-control"
                          required
                        />
                      </div>
                    </div>
                    <!-- till here -->
                    <div class="col-sm-8 mb-3">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        Address <span class="text-danger">*</span>
                      </label>
                      <div class="form-outline">
                        <input
                          type="text"
                          id="typeText"
                          v-model="orderInfo.billingAddress.addressLine"
                          placeholder="Type here"
                          class="form-control"
                          required
                        />
                      </div>
                    </div>
                    <div class="col-sm-4 mb-3">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        Country <span class="text-danger">*</span>
                      </label>
                      <select
                        class="form-select"
                        v-model="orderInfo.billingAddress.country"
                        required
                      >
                        <option value="USA">United States</option>
                      </select>
                    </div>
                    <div class="col-sm-4 col-6 mb-3">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        State <span class="text-danger">*</span>
                      </label>
                      <select
                        class="form-select"
                        v-model="orderInfo.billingAddress.state"
                        required
                      >
                        <option value="AK">Alaska</option>
                        <option value="AL">Alabama</option>
                        <option value="AR">Arkansas</option>
                        <option value="AZ">Arizona</option>
                        <option value="CA">California</option>
                        <option value="CO">Colorado</option>
                        <option value="CT">Connecticut</option>
                        <option value="DC">District of Columbia</option>
                        <option value="DE">Delaware</option>
                        <option value="FL">Florida</option>
                        <option value="GA">Georgia</option>
                        <option value="HI">Hawaii</option>
                        <option value="IA">Iowa</option>
                        <option value="ID">Idaho</option>
                        <option value="IL">Illinois</option>
                        <option value="IN">Indiana</option>
                        <option value="KS">Kansas</option>
                        <option value="KY">Kentucky</option>
                        <option value="LA">Louisiana</option>
                        <option value="MA">Massachusetts</option>
                        <option value="MD">Maryland</option>
                        <option value="ME">Maine</option>
                        <option value="MI">Michigan</option>
                        <option value="MN">Minnesota</option>
                        <option value="MO">Missouri</option>
                        <option value="MS">Mississippi</option>
                        <option value="MT">Montana</option>
                        <option value="NC">North Carolina</option>
                        <option value="ND">North Dakota</option>
                        <option value="NE">Nebraska</option>
                        <option value="NH">New Hampshire</option>
                        <option value="NJ">New Jersey</option>
                        <option value="NM">New Mexico</option>
                        <option value="NV">Nevada</option>
                        <option value="NY">New York</option>
                        <option value="OH">Ohio</option>
                        <option value="OK">Oklahoma</option>
                        <option value="OR">Oregon</option>
                        <option value="PA">Pennsylvania</option>
                        <option value="PR">Puerto Rico</option>
                        <option value="RI">Rhode Island</option>
                        <option value="SC">South Carolina</option>
                        <option value="SD">South Dakota</option>
                        <option value="TN">Tennessee</option>
                        <option value="TX">Texas</option>
                        <option value="UT">Utah</option>
                        <option value="VA">Virginia</option>
                        <option value="VT">Vermont</option>
                        <option value="WA">Washington</option>
                        <option value="WI">Wisconsin</option>
                        <option value="WV">West Virginia</option>
                        <option value="WY">Wyoming</option>
                      </select>
                    </div>

                    <div class="col-sm-4 col-6 mb-3">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        City <span class="text-danger">*</span>
                      </label>
                      <div class="form-outline">
                        <input
                          type="text"
                          id="typeText"
                          v-model="orderInfo.billingAddress.city"
                          class="form-control"
                          required
                        />
                      </div>
                    </div>

                    <div class="col-sm-4 col-6 mb-3">
                      <label
                        for="exampleFormControlInput1"
                        class="form-label mb-0"
                      >
                        Zip <span class="text-danger">*</span>
                      </label>
                      <div class="form-outline">
                        <input
                          type="text"
                          id="typeText"
                          v-model="orderInfo.billingAddress.zipcode"
                          class="form-control"
                          required
                          pattern="\d{5}(-\d{4})?"
                          title="Zip code can only contain numbers and hyphens."
                        />
                      </div>
                    </div>
                  </div>

                  <div class="form-check mb-3" v-if="isAuthenticated">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      v-model="orderInfo.saveAddress"
                      id="flexCheckDefault1"
                    />
                    <label class="form-check-label" for="flexCheckDefault1"
                      >Save this address</label
                    >
                  </div>

                  <div class="float-end">
                    <button class="btn btn-light border">
                      Continue Checkout
                    </button>
                  </div>
                </div>
              </div>
            </form>


          
            
            <!-- payment information -->
            <div class="card shadow-0 border mt-3" >
              <div class="p-4" ref="paymentSection">
                <h5 class="card-title mb-1">Payment Information</h5>
                <div v-if="showPaymentInfo" class="mt-4">
                  <div class="container">
                    <div class="row mb-3">
                      <!-- PayPal Card -->
                      <div class="col-12">
                        <div
                          class="card shadow-0 border bg-light"
                          @click="togglePaymentMethod('paypal')"
                        >
                          <div class="card-body">
                            <h6 class="card-title">PayPal</h6>
                          </div>
                        </div>
                        <!-- PayPal Details -->
                        <div
                          v-if="selectedPaymentMethod === 'paypal'"
                          class="mt-2"
                          ref="paypalDetails"
                        >
                          <div class="card-body">
                            <p>
                              PayPal payments will be processed by the seller
                              after checkout. The seller will contact you to
                              arrange payment within 2 business days after the
                              order is placed.
                            </p>
                            <button
                              class="btn btn-secondary"
                              @click="saveAndContinue"
                            >
                              Save and Continue
                            </button>
                            <!-- <button @click="concludeOrder">Checkout</button> -->
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <!-- Credit Card -->
                      <div class="col-12">
                        <div
                          class="card shadow-0 border bg-light"
                          @click="togglePaymentMethod('card')"
                        >
                          <div class="card-body">
                            <h6 class="card-title">Credit Card</h6>
                          </div>
                        </div>
                        <!-- Credit Card Details -->
                        <div
                          v-if="selectedPaymentMethod === 'card'"
                          class="mt-2"
                          ref="cardDetails"
                        >
                          <div class="card-body">
                            <p>
                              Your credit card order details will be displayed
                              here.
                            </p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- calculating cost part of the checkout page -->
          <div
            class="col-xl-4 col-lg-4 d-flex justify-content-center justify-content-lg-end"
          >
            <div class="ms-lg-4 mt-4 mt-lg-0" style="max-width: 320px">
              <h6 class="mb-3">Summary</h6>
              <div class="d-flex justify-content-between">
                <p class="mb-2">Price:</p>
                <p class="mb-2">${{ formatPrice(subTotalPrice) }}</p>
              </div>
              <!-- <div class="d-flex justify-content-between">
                <p class="mb-2">Discount:</p>
                <p class="mb-2 text-danger">- $60.00</p>
              </div> -->
              <div class="d-flex justify-content-between">
                <p class="mb-2">Shipping: </p>
                <p class="mb-2">${{ formatPrice(totalShippingCost) }}</p>
              </div>
              <hr />
              <div class="d-flex justify-content-between">
                <p class="mb-2">Total price:</p>
                <p class="mb-2 fw-bold">${{ formatPrice(totalPrice) }}</p>
              </div>

              <!-- <div class="input-group mt-3 mb-4">
                <input
                  type="text"
                  class="form-control border"
                  name=""
                  placeholder="Promo code"
                />
                <button class="btn btn-light text-primary border">Apply</button>
              </div> -->

              <hr />
              <h6 class="text-dark my-4">
                <router-link v-bind:to="{ name: 'cart' }" class="cart-item"
                  >Items in cart</router-link
                >
              </h6>

              <div
                v-for="(book, index) in cartBooks"
                :key="index"
                class="d-flex align-items-center mb-4"
              >
                <div class="me-3 position-relative">
                  <span
                    class="position-absolute top-0 start-100 translate-middle badge rounded-pill badge-secondary"
                  >
                    1
                  </span>
                  <img
                    :src="book.image"
                    alt="book image"
                    style="height: 96px; width: 96x"
                    class="img-sm rounded border"
                  />
                </div>
                <div class="">
                  <!-- <a href="#" class="nav-link">
                    {{ book.title }} <br />
                    {{ book.author }}
                  </a> -->
                  <router-link
                    :to="{
                      name: 'detail',
                      params: {
                        sku: book.sku,
                      },
                    }"
                    class="nav-link"
                  >
                    {{ book.title }} <br />
                    {{ book.author }}
                  </router-link>
                  <div class="price text-muted">Total: {{ book.price }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import { mapGetters, mapState, mapActions } from "vuex";
import AlertToast from "../components/AlertToast.vue";
export default {
  name: "checkout-page",
  components: {
    AlertToast
  },
  data() {
    return {
      useSameAddress: false,
    //   paymentMethod: "",
      showPaymentInfo: false, //new property to control visibility
       selectedPaymentMethod: "", // Track which payment method is selected
      selectedDelivery: "USPS", //default value
    };
  },
  watch: {
    selectedDelivery(newValue) {
      this.$store.commit("SET_DELIVERY_OPTION", newValue);
    },
    '$route.query.focus'(newValue){
         if(newValue === 'payment'){
            this.showPaymentInfo = true;
            this.scrollToPaymentSection();
         }else if(newValue === 'shipping'){
            this.showPaymentInfo = true;
            this.scrollToShippingSection();
         }
         },
    },
  mounted() {
    if(this.$route.query.focus === 'payment'){
        this.showPaymentInfo = true;
        this.$nextTick(()=> {
            this.scrollToPaymentSection();
        })
    } if(this.$route.query.focus === 'shipping'){
        this.showPaymentInfo = true;
        this.$nextTick(()=> {
            this.scrollToShippingSection();
        })
    }
    if(this.paymentMethod){
        this.showPaymentInfo =true;
    }
  },
  computed: {
   
    ...mapGetters([
      "isAuthenticated",
      "totalPrice",
      "subTotalPrice",
      "totalShippingCost",
    ]),
    ...mapState(["cartBooks", "user", "order"]),
    ...mapActions(["removeBook"]),
    paymentMethod() {
    return this.$store.state.checkout.paymentMethod;
  },
  returningUserEditingShipping() {
    return this.$store.state.checkout.returningUserEditingShipping;
  },
    orderInfo: {
      get() {
        return this.$store.state.tempOrderInfo;
      },
      set(value) {
        this.$store.commit("SET_TEMP_ORDER_INFO", value);
      },
    },
    
    checkBookCart() {
      let checkBook = false;

      if (this.cartBooks.length > 0) {
        checkBook = true;
      }
      return checkBook;
    },
  },
  methods: {
    ...mapActions(["createOrder"]),


    checkUserAuthentication(){
      if (this.isAuthenticated) {
        this.orderInfo.shippingAddress.firstname = this.user.firstName;
        this.orderInfo.shippingAddress.lastname = this.user.lastName;
        this.orderInfo.email = this.user.email;
    }
    },

    setBillingAddress() {
    if (this.useSameAddress) {
      // Copy shipping address to billing address
    //   if (this.isAuthenticated) {
    //     this.orderInfo.shippingAddress.firstname = this.user.firstName;
    //     this.orderInfo.shippingAddress.lastname = this.user.lastName;
    //     this.orderInfo.email = this.user.email;
    // }

    this.checkUserAuthentication();

      this.orderInfo.billingAddress = JSON.parse(JSON.stringify(this.orderInfo.shippingAddress));
    }else {
      // Optional: Reset billing address fields if necessary
      this.orderInfo.billingAddress = {
        firstname: '',
        lastname: '',
        phoneNumber: '',
        email: '',
        addressLine: '',
        country: '',
        state: '',
        city: '',
        zipCode: '',
      };
    }
  },

continueToPayment() {
  console.log(this.orderInfo);
    // First, check if the form is valid.
    const form = this.$refs.checkoutForm;
    if (!form.checkValidity()) {
        this.$refs.alertToast.addToast({
            title: "Form Incomplete",
            message: "Please fill out all required fields",
        });
        form.classList.add("was-validated");
        return;
    }

    // Next, update orderInfo based on authentication status.
    // if (this.isAuthenticated) {
    //     this.orderInfo.shippingAddress.firstname = this.user.firstName;
    //     this.orderInfo.shippingAddress.lastname = this.user.lastName;
    //     this.orderInfo.email = this.user.email;
    // }

    this.checkUserAuthentication();

    // Save the order information to the store regardless of the payment method selection
    this.$store.dispatch("updateTempOrderInfo", this.orderInfo);

    // Then, check if a payment method has been selected.
    if (this.paymentMethod === "") {
        this.showPaymentInfo = true; // Prompt the user to select a payment method if not already selected.
        return; // Stop execution if no payment method is selected.
    } else {
        // If a payment method is selected, update it in the store
        // this.$store.dispatch('updateSelectedPaymentMethod', this.selectedPaymentMethod);

        // Redirect to order summary if coming from checkout process
        if (this.$route.query.focus === 'shipping' || this.$route.query.focus === 'payment') {
            this.showPaymentInfo = true;
            console.log("Redirecting to order summary");
            this.$router.push({ name: 'orderSummary' });
        } else {
            // Show payment info section if not redirected from checkout summary
            console.log("Redirecting to order summary");
            this.$router.push({name: 'orderSummary'})
        }
    }
},

    saveAndContinue() {
    if (this.selectedPaymentMethod) {
        console.log(this.selectedPaymentMethod);
      // Assuming 'updateSelectedPaymentMethod' is an action that correctly updates the Vuex state
      this.$store.dispatch('updateSelectedPaymentMethod', this.selectedPaymentMethod);
      console.log(this.paymentMethod + "this is this.paymentMethod")
      // Assuming orderInfo is correctly populated and needs to be updated as well
      this.$store.dispatch("updateTempOrderInfo", this.orderInfo);
      
      // Navigate to order summary or next step
      this.$router.push({ name: "orderSummary", query: { fromCheckout: true } });
    } else {
      // Handle case where no payment method is selected
      alert("Please select a payment method.");
    }
  },
    togglePaymentMethod(method) {
      if (this.selectedPaymentMethod === method) {
        this.selectedPaymentMethod = ""; // Clicking the same method again will hide its details
      } else {
        this.selectedPaymentMethod = method;
      }

      // Optional: Smooth dropdown effect for PayPal details
      if (method === "paypal" && this.selectedPaymentMethod === method) {
        this.$nextTick(() => {
          const details = this.$refs.paypalDetails;
          if (details) {
            details.style.display = "block";
            details.style.height = "0";
            details.style.overflow = "hidden";
            details.style.transition = "height 0.5s ease";

            // Adjust the height as per the content dynamically or set to a specific value
            details.style.height = `${details.scrollHeight}px`;
          }
        });
      }
      // Credit card details
      if (method === "card" && this.selectedPaymentMethod === method) {
        this.$nextTick(() => {
          const details = this.$refs.cardDetails;
          if (details) {
            details.style.display = "block";
            details.style.height = "0";
            details.style.overflow = "hidden";
            details.style.transition = "height 0.5s ease";

            // Adjust the height as per the content dynamically or set to a specific value
            details.style.height = `${details.scrollHeight}px`;
          }
        });
      }
    },
  

    scrollToPaymentSection(){
     const paymentSection = this.$refs.paymentSection;
     if(paymentSection){
        paymentSection.scrollIntoView({behavior: 'smooth'});
     }else {
        console.log("payment section ref not found")
     }
    },
    scrollToShippingSection(){
     if(this.$refs.shippingSection){
        this.$refs.shippingSection.scrollIntoView({behavior: 'smooth'});
     }
    },
    formatPrice(value) {
      const formattedPrice = Number(value).toFixed(2);
      return formattedPrice;
    },
  },
};
</script>
<style scoped>
.cart-item {
  color: black;
  font-size: 13px;
  text-decoration: none;
}
/* Override styles for valid inputs to remove green background and border */
::v-deep .was-validated .form-control:valid,
::v-deep .form-control.is-valid,
::v-deep .was-validated .form-check-input:valid ~ .form-check-label,
::v-deep .form-check-input.is-valid ~ .form-check-label {
  background-image: none !important;
  border-color: #ced4da;
  box-shadow: none !important;
  color: #495057;
}

/* Remove the green check icon for valid select inputs */
::v-deep .was-validated .form-select:valid,
::v-deep .form-select.is-valid {
  background-image: none !important;
}

/* Override styles for valid feedback to prevent it from displaying in green */
::v-deep .valid-feedback,
::v-deep .was-validated .form-control:valid ~ .valid-feedback,
::v-deep .was-validated .form-check-input:valid ~ .valid-feedback {
  display: none;
}
</style>

