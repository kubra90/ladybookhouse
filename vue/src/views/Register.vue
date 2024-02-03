<template>
  <main>
    <header-page/>
    <div id="register">
      <form @submit.prevent="handleRegister">
        <h1>Create Account</h1>
        <div role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <div class="form-input-group">
          <label for="username">First Name</label>
          <input type="text" id="username" v-model="user.firstName" size="35" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="username">Last Name</label>
          <input type="text" id="username" v-model="user.lastName" size="35" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="username">Email</label>
          <input type="text" id="username" v-model="user.email" size="35" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="user.password" size="35" required autocomplete="on" />
        </div>
        <div class="form-input-group">
          <label for="confirmPassword">Confirm Password</label>
          <input type="password" id="confirmPassword" v-model="user.confirmPassword" size="35" required autocomplete="on" />
        </div>
        <button type="submit">Create Account</button>
        <p><router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
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
  name: 'register',
  components: {
    HeaderPage,
    FooterPage
  },
  data() {
    return {
      // user: {
      //   username: '',
      //   password: '',
      //   confirmPassword: '',
      //   role: 'user',
      // },
      user: {
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        confirmPassword: '',
        role: 'user'
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    ...mapActions(["registerUser"]),
    handleRegister() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
          this.registerUser(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
@media only screen and (min-width: 768px) {
#register {
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
label[for=confirmPassword] {
  margin-left: -4em;
}
button {
  cursor: pointer;
  border: 0;
  border-radius: 4px;
  font-weight: 600;
  width: 120px;
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
