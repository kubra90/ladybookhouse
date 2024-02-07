<template>
  <div class="login-page">
    <header-page />
    <login-page class="login" />
    <footer-page class="footer" />
  </div>
</template>
<script>
import LoginPage from "../components/LoginPage.vue";
import HeaderPage from "../components/HeaderPage.vue";
import FooterPage from "../components/FooterPage.vue";

export default {
  name: "login",
  components: {
    LoginPage,
    HeaderPage,
    FooterPage,
  },
};
</script>

<style scoped>
.login {
  flex: 1;

}

.login-page {
  display: flex;
  flex-direction: column;
  height: 100%;
  margin: 0;
}

.footer {
  display: flex;
  bottom: 0;
}
</style>
