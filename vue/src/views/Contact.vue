<template>
  <div class="contact-page">
    <header-page/>
    <ContactPage class="contact"/>
    <footer-page class="footer"/>
  </div>
</template>
<script>
 import HeaderPage from '../components/HeaderPage.vue'
 import FooterPage from '../components/FooterPage.vue'
 import ContactPage from '../components/ContactPage.vue'

 export default {
  name : 'contact',
  components: {
    HeaderPage, FooterPage, ContactPage 
  }
  }
</script>

<style scoped>
.contact{
  flex:2;
}

.contact-page{
  display:flex;
  flex-direction: column;
  height:100%;
  margin:0;
}

.footer{
  display:flex;
  bottom:0;
  /* position:absolute;
  bottom:0; */
  
}

</style>  
