<template>
  <div class="container mt-5 mb-5 p-4">
    <div class="row justify-content-center-md-center">
      <div class="col col-md-4">
        <div class="card">
          <div class="card-body">
            <h3 class="card-title mb-4">Login</h3>
            <form @submit.prevent="handleLogin">
              <div role="alert" v-if="invalidCredentials">
                Invalid email and password!
              </div>
              <div role="alert" v-if="this.$route.query.registration">
                Thank you for registering, please sign in.
              </div>
              <div class="mb-3">
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
              <div class="mb-3">
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
              <button type="submit" class="btn btn-custom">Login</button>
              <p class="card-title mt-4">
                <router-link :to="{ name: 'register' }"
                  >Need an account? Sign up</router-link
                >
              </p>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex"

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        email: "",
        password: "",
      },
      invalidCredentials: false
    }
  },
  methods: {
    ...mapActions(["loginUser"]),
    handleLogin() {
      this.loginUser(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$router.push("/")
          }
        })
        .catch((error) => {
          const response = error.response
          if (response.status === 401) {
            this.invalidCredentials = true
          }
        })
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

.error-box {
  color: red;
  margin: 10px 0;
}
</style>
