<template>
  <div>
    <header-page />
    <h3 class="links-title">Browse Categories</h3>
    <div class="links">
      <router-link
        :to="{ path: `/${category}` }"
        v-for="category in categories"
        :key="category"
      >
        <div class="link">{{ category }}</div>
      </router-link>
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
        "Language",
      ],
    }
  },
  components: {
    HeaderPage,
    FooterPage,
    FeaturedBooks,
  }
}
</script>

<style scoped>
.links {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  max-width: 100%;
  padding-inline-start: 12rem;
}

.links-title {
  color: #e2907a;
  padding-inline-start: 13rem;
  font-weight: 700;
  margin-top: 3rem;
}

.link {
  display: inline-block;
  margin: 1rem;
  padding: 0.2rem;
  text-align: center;
  color: #dc8a73;
}

.link:hover {
  color: #b33e1e;
}
</style>
