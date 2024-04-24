<template>
    <div class="search-main">
      <HeaderPage />
      <SearchResult class="search-result-page"/>
      <FooterPage class="footer"/>
    </div>
  </template>
  <script>
  import HeaderPage from "../components/HeaderPage.vue"
  import FooterPage from "../components/FooterPage.vue"
  import SearchResult from "@/components/SearchResult.vue"
  
  export default {
    name: 'SearchResultView',
    components: {
      HeaderPage, FooterPage, SearchResult
    }
  }
  </script>

<style scoped>
.search-main {
    display:flex;
    flex-direction: column;
    height:100%;
    margin:0;
}

.search-result-page{
    flex:1;
}

.footer {
    display:flex;
    bottom:0;
}
</style>