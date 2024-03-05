<template>
    <div class="advanced-container">
      <HeaderPage/> 
      <AdvancedSearch class="search"/>
      <FooterPage class="footer"/>
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

    <style scoped>
      .advanced-container {
        display:flex;
        flex-direction:column;
        height:100%;
        align-items:center;
    
      }

      .search {
        flex:1;
        padding-inline-start: 6rem;
      }

    </style>