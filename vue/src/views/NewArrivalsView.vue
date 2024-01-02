<template>
    <div>
        <div><headerPage/></div>    
        <div><newArrivalList/></div>
        <div><footerPage /></div> 
    </div>    
    </template>

<script>

import headerPage from "../components/HeaderPage.vue"
import footerPage from "../components/FooterPage.vue"
import newArrivalList from "../components/NewArrivalList.vue"

export default {
    
    name: "NewArrivalsView",

    components: {
        headerPage, footerPage, newArrivalList
    }
}

</script>

<style>
</style>