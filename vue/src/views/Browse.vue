<template>
  <div>
    <header-page />
    <div class="container">
      <div class="row">
        <div class="col-md-12 mb-3 m-3">
          <h3 class="links-title mb-3">Browse Categories</h3>
        </div>
      </div>
      <div class="row">
        <router-link
          :to="{ path: `/${category}` }"
          v-for="category in categories"
          :key="category"
          class="col-md-3 mb-3 m-3 text-decoration-none"
        >
          <div class="link">{{ category }}</div>
        </router-link>
      </div>
    </div>
    <hr />
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