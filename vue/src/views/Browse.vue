<template>
  <div>
    <header-page />
    <div class="container">
      <div class=" my-4 px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5 px-xxl-0 mx-xxl-0">
      <div class="row ">
        <div class="col-md-12 mb-3 m-3">
          <h3 class="links-title mb-3">Browse Categories</h3>
        </div>
      </div>
      <div class="row">
        <router-link :to="{name: 'search-result-view', query: {category: category}}"
          v-for="category in categories"
          :key="category"
          class="col-md-3 mb-2 m-3 text-decoration-none"
        >
          <div class="link">{{ category }}</div>
        </router-link>
      </div>
      </div>
      <hr class="justify-content-center" style="width:80%; margin:0 auto"/>
    </div>
   
    <featured-books />
    <footer-page />
  </div>
</template>

<script>
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"
import FeaturedBooks from "../components/FeaturedBooks.vue"

export default {
  name: "browse",

  data() {
    return {
      categories: [
        "History&Memory",
        "Science",
        "Other",
        "Bestseller",
        "Religion&Philosophy",
        "Literature",
        "Social Science",
        "Cookbook",
        "Kids",
        "Design",
        "Technology",
        "Art",
        "Language"
      ]
    }
  },
  components: {
    HeaderPage,
    FooterPage,
    FeaturedBooks
  }
}
</script>

<style scoped>
.links-title {
  color: #e2907a;
  font-weight: 700;
}

.link {
  color: #dc8a73;
}

.link:hover {
  color: #b33e1e;
}
</style>