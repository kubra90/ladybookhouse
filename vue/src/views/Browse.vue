<template>
  <div>
    <header-page />
    <div class="links">
      <router-link
        :to="{ path: `/${category}` }"
        v-for="category in categories"
        :key="category"
      >
        <div class="link">{{ category }}</div>
      </router-link>
    </div>
    <footer-page />
  </div>
</template>

<script>
import HeaderPage from "../components/HeaderPage.vue"
import FooterPage from "../components/FooterPage.vue"

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
      ]
    }
  },
  components: {
    HeaderPage,
    FooterPage,
  },
}
</script>

<style scoped>
.links {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  max-width: 100%;
  padding-inline-start: 12rem;
}
.link {
  display: inline-block;
  margin: 1rem;
  padding: 0.2rem;
  text-align: center;
}
</style>
