<template>
  <div>
    <section v-if="checkBookCart" class="bg-light py-5">
      <div class="container">
        <div class="row">
          <div class="col-xl-8 col-lg-8 mb-4">
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
                        v-model="orderInfo.phoneNumber"
                        value=""
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
            <div v-else-if="!isAuthenticated" class="card shadow-0 border mb-4">
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
                        v-model="orderInfo.firstName"
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
                        v-model="orderInfo.lastName"
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
                        v-model="orderInfo.phoneNumber"
                        value=""
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
              <div class="p-4">
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
                        />
                        <label class="form-check-label" for="flexRadioDefault1">
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
                        />
                        <label class="form-check-label" for="flexRadioDefault2">
                          UPS Express <br />
                          <small class="text-muted">4-7 business days </small>
                        </label>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-4 mb-3">
                  </div>
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
                        v-mode="orderInfo.address"
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
                    <select class="form-select" v-model="orderInfo.country">
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
                    <select class="form-select" v-model="orderInfo.state">
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
                        v-model="orderInfo.city"
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
                        v-model="orderInfo.zipCode"
                        class="form-control"
                        required
                      />
                    </div>
                  </div>
                </div>

                <div class="form-check mb-3">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    value=""
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

                <div class="float-end">
                  <button
                    class="btn btn-light border"
                    @click="continueToPayment"
                  >
                    Continue Checkout
                  </button>
                  <!-- <button class="btn btn-success shadow-0 border">
                    Continue Checkout
                  </button> -->
                </div>
              </div>
            </div>
            <!-- Payment Info -->
            <div class="card shadow-0 border mt-4">
              <div class="p-4">
                <h5 class="card-title mb-3">Payment Information</h5>
                <div v-if="showPaymentInfo">
                  <p>detailed payment information</p>
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
                <p class="mb-2">Total price:</p>
                <p class="mb-2">$195.90</p>
              </div>
              <div class="d-flex justify-content-between">
                <p class="mb-2">Discount:</p>
                <p class="mb-2 text-danger">- $60.00</p>
              </div>
              <div class="d-flex justify-content-between">
                <p class="mb-2">Shipping cost:</p>
                <p class="mb-2">+ $14.00</p>
              </div>
              <hr />
              <div class="d-flex justify-content-between">
                <p class="mb-2">Total price:</p>
                <p class="mb-2 fw-bold">$149.90</p>
              </div>

              <div class="input-group mt-3 mb-4">
                <input
                  type="text"
                  class="form-control border"
                  name=""
                  placeholder="Promo code"
                />
                <button class="btn btn-light text-primary border">Apply</button>
              </div>

              <hr />
              <h6 class="text-dark my-4">Items in cart</h6>

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
                  <a href="#" class="nav-link">
                    {{ book.title }} <br />
                    {{ book.author }}
                  </a>
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
export default {
  data() {
    return {
      orderInfo: {
        firstName: "",
        lastName: "",
        email: "",
        country: "",
        city: "",
        state: "",
        zipCode: "",
        address: "",
        phoneNumber: "",
        bookNo: "",
        message: "",
      },
      showPaymentInfo: false, //new property to control visibility
    };
  },
  computed: {
    ...mapGetters(["isAuthenticated"]),
    ...mapState(["cartBooks", "user", "order"]),
    ...mapActions(["removeBook"]),
    checkBookCart() {
      let checkBook = false;

      if (this.cartBooks.length > 0) {
        checkBook = true;
      }
      return checkBook;
    },
  },
  methods: {
    continueToPayment() {
        // access the form via this.$refs and check if it's valid
        const form = this.$refs.checkoutForm;
        if(form.checkValidity() === true){
      this.showPaymentInfo = true;
        }else{
            form.classList.add('was-validated')
        }
    },
  },
};
</script>
<style scoped>
</style>