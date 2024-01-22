<template>
    <div>
        <header-page></header-page>
        <saved-books-page></saved-books-page>
        <footer-page></footer-page>
    </div>
</template>
<script>
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"
import SavedBooksPage from "../components/SavedBooksPage.vue"
export default {
    name: 'saved-books',
    components: {
        HeaderPage, FooterPage, SavedBooksPage
    }
}

</script>
<style scoped>

</style>