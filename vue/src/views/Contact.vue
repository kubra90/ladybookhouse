<template>
    <div>
        <header-page></header-page>
        <contact-page></contact-page>
        <footer-page></footer-page>


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

</style>