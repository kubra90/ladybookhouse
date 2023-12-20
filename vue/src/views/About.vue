<template>
    <div>
        <header-page/>
      <about-lady/>
      <footer-page/>
    </div>

</template>

<script>
import AboutLady from "../components/AboutLady.vue"
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"
export default {
    name: "about",
    components: {
        AboutLady, HeaderPage, FooterPage
    }
}
</script>

<style scoped>

</style>