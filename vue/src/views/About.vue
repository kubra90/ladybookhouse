<template>
  <div class="about-container">
    <HeaderPage/>
    <about-lady class="about-lady-body"/>
    <FooterPage class="footer"/>
  </div>
</template>

<script>
import AboutLady from "@/components/AboutLady.vue"
import HeaderPage from "@/components/HeaderPage.vue"
import FooterPage from "@/components/FooterPage.vue"

export default {
  name: "about",
  components: {
    AboutLady, HeaderPage, FooterPage
  }
}
</script>

<style scoped>
/* .footer {
  position:absolute;
  bottom:0;
} */

 .about-lady-body {
  flex:1;
} 

.about-container{
  display:flex;
  flex-direction:column;
  height:100%;
}

/* .footer {
  display:flex;
  bottom:0;
}  */
</style>

