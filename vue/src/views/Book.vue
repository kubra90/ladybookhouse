<template>
<div>
    <div><header-page/></div>    
    <!-- <header-page/> -->
    <div><book-list/></div>
    <!-- <footer-page/> -->
    <div><footer-page /></div> 
</div>    
</template>


<script>
import BookList from '../components/BookList.vue';
import HeaderPage from '../components/HeaderPage.vue';
import FooterPage from '../components/FooterPage.vue';
export default {
    name: "book",
    components: {BookList, HeaderPage, FooterPage}
}
</script>

<style scoped>

</style>