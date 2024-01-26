<template>
    <div>
    <div><header-page /></div>
    <div><categories /></div>
    <div><footer-page /></div>
    </div>
</template>

<script>

import HeaderPage from "../components/HeaderPage.vue"
import Categories from "../components/Categories.vue"
import FooterPage from "../components/FooterPage.vue"

export default {
    name: "browse",
    components: {
        HeaderPage, FooterPage, Categories
    }
}
</script>