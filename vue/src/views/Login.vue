<template>
<div class="login-page">
  <header-page></header-page>
  <login-page class="login"></login-page>
  <footer-page class="footer"></footer-page>
</div> 
</template>

<script>
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"
import LoginPage from "../components/LoginPage.vue"

export default {
  name: "login",
  components: { 
    HeaderPage, 
    FooterPage,
    LoginPage
  }
}
  
</script>
 <style scoped>
.login {
  flex:1;
}

.login-page{
  display:flex;
  flex-direction: column;
  height:100%;
  margin:0;
}

.footer{
  display:flex;
  
}

</style>  
