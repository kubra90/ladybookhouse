<template>
  <div class="contact-page">
    <HeaderPage/>
    <ContactPage class="contact"/>
    <FooterPage class="footer"/>
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
  flex:1;
}

.contact-page{
  display:flex;
  flex-direction: column;
  height:100%;
  margin:0;
}

.footer{
  display:flex;
  
}

</style>  
