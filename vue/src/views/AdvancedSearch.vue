<template>
    <div>
      <HeaderPage/> 
      <AdvancedSearch/>
      <FooterPage/>
    </div>    
    </template>
    
    <script>
    import HeaderPage from '@/components/HeaderPage.vue';
    import FooterPage from '@/components/FooterPage.vue';
    import AdvancedSearch from '@/components/AdvancedSearch.vue';
    
    export default {
      name: "advanced-search",
      components: {HeaderPage, FooterPage, AdvancedSearch}
    }
    </script>