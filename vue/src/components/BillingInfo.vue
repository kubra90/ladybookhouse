<template>
                  <!-- <hr class="my-4" /> -->
                  <div class="card shadow-0 border">
                <div class="p-4" ref="shippingSection">
                  <h5 class="card-title mb-3">Shipping info</h5>

                  <div class="row mb-3">
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
                          v-model="orderInfo.addressLine"
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
                        v-model="orderInfo.country"
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
                        v-model="orderInfo.state"
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

                  <div class="float-end">
                    <button class="btn btn-light border">
                      Continue Checkout
                    </button>
                    <!-- <button class="btn btn-success shadow-0 border">
                    Continue Checkout
                  </button> -->
                  </div>
                </div>
              </div>
</template>

<script>
export default {
    name: 'billing-info',
    computed: {
        orderInfo: {
      get() {
        return this.$store.state.tempOrderInfo;
      },
      set(value) {
        this.$store.commit("SET_TEMP_ORDER_INFO", value);
      },
    },
    }
}
</script>