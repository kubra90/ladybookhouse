<template>
  <main>
    <header-page/>
    <div id="login">
      <form @submit.prevent="login">
        <h1>Login</h1>
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="user.password" required autocomplete="on" />
        </div>
        <button type="submit">Login</button>
        <p>
        <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
      </form>
    </div>
    <footer-page class="footer"/>
  </main>  
</template>

<script>
import authService from "../services/AuthService";
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"

export default {
  name: "login",
  components: { 
    HeaderPage, 
    FooterPage 
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
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
#login {
  margin: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  text-align: center;
}
form h1 {
  font-family: 'PT Sans', sans-serif;
  font-size: 30px;
  font-style: normal;
  line-height: 1.25;
  text-align: center;
}
.form-input-group {
  margin: 1rem;
  padding: 0.75rem;
}
label {
  margin-right: 0.75rem;
}
button {
  cursor: pointer;
  border: 0;
  border-radius: 4px;
  font-weight: 600;
  width: 75px;
  padding: 5px 0;
  margin-bottom: 5px;
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
</style>