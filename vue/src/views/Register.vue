<template>
  <main>
    <header-page />
    <div class="container p-4">
      <div class="row justify-content-center-md-center">
        <div class="col col-md-4">
          <div class="card">
            <div class="card-body">
              <h3 class="card-title mb-2">Create Account</h3>
              <form @submit.prevent="handleRegister">
                <div role="alert" v-if="registrationErrors">
                  {{ registrationErrorMsg }}
                </div>
                <div class="mb-1">
                  <label for="username" class="col-form-label"
                    >First Name</label
                  >
                  <input
                    type="text"
                    id="username"
                    v-model="user.firstName"
                    size="35"
                    required
                    autofocus
                    class="form-control"
                  />
                </div>
                <div class="mb-1">
                  <label for="username" class="col-form-label">Last Name</label>
                  <input
                    type="text"
                    id="username"
                    v-model="user.lastName"
                    size="35"
                    required
                    autofocus
                    class="form-control"
                  />
                </div>
                <div class="mb-1">
                  <label for="username" class="col-form-label">Email</label>
                  <input
                    type="text"
                    id="username"
                    v-model="user.email"
                    size="35"
                    required
                    autofocus
                    class="form-control"
                  />
                </div>
                <div class="mb-1">
                  <label for="password" class="col-form-label">Password</label>
                  <input
                    type="password"
                    id="password"
                    v-model="user.password"
                    size="35"
                    required
                    autocomplete="on"
                    class="form-control"
                  />
                </div>
                <div class="mb-1">
                  <label for="confirmPassword" class="col-form-label"
                    >Confirm Password</label
                  >
                  <input
                    type="password"
                    id="confirmPassword"
                    v-model="user.confirmPassword"
                    size="35"
                    required
                    autocomplete="on"
                    class="form-control"
                  />
                </div>
                <button type="submit" class="btn btn-custom mt-2">
                  Create Account
                </button>
                <p class="card-title mt-4">
                  <router-link :to="{ name: 'login' }"
                    >Already have an account? Log in</router-link
                  >
                </p>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <footer-page class="footer" />
  </main>
</template>

<script>
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"
import { mapActions } from "vuex"

export default {
  name: "register",
  components: {
    HeaderPage,
    FooterPage
  },
  data() {
    return {
      user: {
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        confirmPassword: "",
        role: "user"
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user."
    }
  },
  methods: {
    ...mapActions(["registerUser"]),
    handleRegister() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true
        this.registrationErrorMsg = "Password & Confirm Password do not match."
      } else {
        this.registerUser(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" }
              })
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors"
            }
          })
      }
    },
    clearErrors() {
      this.registrationErrors = false
      this.registrationErrorMsg = "There were problems registering this user."
    }
  }
}
</script>

<style scoped>
.btn-custom {
  cursor: pointer;
  border: 0;
  border-radius: 4px;
  font-weight: 600;
  box-shadow: 0 0 20px rgba(104, 85, 224, 0.2);
  transition: 0.4s;
  color: #337ab7;
  background-color: rgba(255, 255, 255, 1);
  border: 1px solid rgba(104, 85, 224, 1);
}
.btn-custom:hover {
  color: white;
  box-shadow: 0 0 5px #337ab7;
  background-color: #337ab7;
}
</style>
