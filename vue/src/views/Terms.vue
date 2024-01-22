<template>
    <div>
       <header-page></header-page>
       <terms-of-sale></terms-of-sale>
       <footer-page></footer-page>
    </div>
</template>
<script>
import HeaderPage from '../components/HeaderPage.vue'
import FooterPage from '../components/FooterPage.vue'
import TermsOfSale from '../components/TermsOfSale.vue'
export default {
    name: "terms",
    components: {
        HeaderPage, FooterPage, TermsOfSale
    }

}
</script>
<style scoped>

</style>