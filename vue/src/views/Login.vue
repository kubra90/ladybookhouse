<template>
  <main>
    <header-page/>
    <div id="login">
      <form @submit.prevent="handleLogin">
        <h1>Login</h1>
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="form-input-group">
          <label for="username">Email</label>
          <input type="text" id="username" v-model="user.email" size="35" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="user.password" size="35" required autocomplete="on" />
        </div>
        <button type="submit">Login</button>
        <p>
        <router-link class="router" :to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
      </form>
    </div>
    <footer-page class="footer"/>
  </main>  
</template>

<script>
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"
import { mapActions } from "vuex"

export default {
  name: "login",
  components: { 
    HeaderPage, 
    FooterPage 
  },
  data() {
    return {
      user: {
<<<<<<< HEAD
        username: "",
        password: ""
=======
        email: "",
        password: "",
>>>>>>> main
      },
      invalidCredentials: false
    };
  },
  methods: {
    ...mapActions(["loginUser"]),
    handleLogin() {
      this.loginUser(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
@media only screen and (min-width: 768px) {
#login {
  padding: 0.75em 2.5em 1.25em 6.25em;
  margin: 1.5em 1em;
  position: absolute;
  top: 50%;
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
}
form h1 {
  font-family: 'PT Sans', sans-serif;
  font-size: 30px;
  font-style: normal;
  line-height: 1.25;
}
.form-input-group, h1, button, .router {
  margin: 1rem;
}
label {
  margin-right: 0.75rem;
}
button {
  cursor: pointer;
  border: 0;
  border-radius: 4px;
  font-weight: 600;
  width: 60px;
  height: 30px;
  box-shadow: 0 0 20px rgba(104, 85, 224, 0.2);
  transition: 0.4s;
  color: #337ab7;
  background-color: rgba(255, 255, 255, 1);
  border: 1px solid rgba(104, 85, 224, 1);
}
button:hover {
  color: white;
  box-shadow: 0 0 5px #337ab7;
  background-color: #337ab7;
}
.footer {
  position:absolute;
  bottom: 0;
}
}
</style>