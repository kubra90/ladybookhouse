<template>
    <div>
        <div><headerPage/></div>    
        <div><featuredBookList/></div>
        <div><footerPage /></div> 
    </div>    
    </template>

<script>

import headerPage from "../components/HeaderPage.vue"
import footerPage from "../components/FooterPage.vue"
import featuredBookList from "../components/FeaturedBookList.vue"

export default {
    
    name: "featuredBookView",

    components: {
        headerPage, footerPage, featuredBookList
    }
}

</script>

<style>
</style>