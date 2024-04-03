<template>
  <div class="container">
    <div
      class="row px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5 px-xxl-0 mx-xxl-0"
    >
      <div class="col-12 ps-0">
        <div class="navigation-links" style="font-size: 12px">
          <router-link
            v-bind:to="{ name: 'cart' }"
            class="btn btn-link"
            style="font-size: 12px; padding-top: 3px"
            >Shopping Cart</router-link
          >
          <span class="greater-sign">&gt;</span>
          <span style="padding-left: 1px">Address Book</span>
        </div>
      </div>
    </div>
    <!-- 
      <div class="row px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5 px-xxl-0 mx-xxl-0"> -->

    <div class="row">
      <div class="col">
        <div class="card h-100">
          <div class="card-header">
            <h5 class="card-title">
              {{ addresses[arrLength].firstname }}
              {{ addresses[arrLength].lastname }}
            </h5>
          </div>
          <div class="card-body">
            <p class="card-text">{{ addresses[arrLength].addressLine }}</p>
            <p class="card-text">
              {{ addresses[arrLength].city }}, {{ addresses[arrLength].state }}
              {{ addresses[arrLength].zipcode }}
            </p>
            <p class="card-text">{{ addresses[arrLength].country }}</p>
            <p class="card-text">{{ addresses[arrLength].phone }}</p>
            <p class="card-text">{{ addresses[arrLength].email }}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="row py-4">
      <div class="col">
        <div class="card">
          <div class="card-header">
            <h5 class="card-title">Other Addresses</h5>
          </div>
          <div class="card-body p-0">
            <ul class="list-group list-group-flush">
              <!-- Loop over the addresses -->
              <li
                class="list-group-item"
                v-for="(address, index) in addresses" 
                :key="index" 
              >
                <div class="d-flex align-items-center" v-if="index !== arrLength">
                  <!-- Rounded Checkbox -->
                  <div class="form-check form-check-inline me-2">
                    <input
                      class="form-check-input"
                      type="radio"
                      :id="'address-' + index"
                      :value="index"
                      v-model="selectedAddress"
                      name="addressSelection"
                    />
                    <label
                      class="form-check-label"
                      :for="'address-' + index"
                    ></label>
                  </div>
                  <!-- Address Info -->
                  <div class="flex-grow-1 d-flex align-items-center">
                    <div class="me-3">
                      <strong
                        >{{ address.firstname }} {{ address.lastname }}</strong
                      >
                    </div>
                    <div class="me-3">
                      {{ address.addressLine }}
                    </div>
                    <div class="me-3">
                      {{ address.city }}, {{ address.state }}
                      {{ address.zipcode }}
                    </div>
                    <div class="me-3">
                      {{ address.country }}
                    </div>
                    <div class="me-3">
                      {{ address.phone }}
                    </div>
                    <div>
                      {{ address.email }}
                    </div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
          <div class="card-footer">
            <button class="btn btn-secondary" @click="performActionOnSelected">
              Delete Address
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "address-book",
  data() {
    return {
      selectedAddress: null,
    };
  },
  computed: {
    ...mapState(["user", "addresses"]),
    arrLength() {
      return this.addresses.length - 1;
    },
    indexNum(){
        return this.arrLength - 1;
    }
    // ...mapActions(["getUserAddresses"])
  },
  created() {
    this.$store.dispatch("getUserAddresses");
  },

  methods: {
    performActionOnSelected(){
       if(this.selectedAddress){
        return;
    }
}
  }
};
</script>

<style scoped>

.container {
    padding-right:4rem;
}
</style>
