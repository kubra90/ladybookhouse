<template>
  <div class="container">
    <div class="row mt-3 px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5 px-xxl-0 mx-xxl-0">
      <div class="col-md-6 col-6">
        <h3>Featured Books</h3>
      </div>
      <div class="col-md-6 col-6 d-flex justify-content-md-end align-items-center">
        <router-link :to="{ name: 'featured-book-view' }">
          <div class="button-container">
            <button class="btn btn-sm btn-primary">See All Featured Books</button>
          </div>
        </router-link>
      </div>
    </div>
    <ul class="row px-md-0 mx-md-0 px-sm-0 mx-sm-0 px-lg-5 mx-lg-5 px-xxl-0 mx-xxl-0">
      <book-card v-for="book in paginatedList" :book="book" :key="book.isbn" />
    </ul>
  </div>
</template>

<script>
import BookCard from "../components/BookCard.vue"
import { mapState, mapGetters } from "vuex"

export default {
  name: "featured-books",

  data() {
    return {
      isBrowseCateg: false,
    }
  },

  components: {
    BookCard,
  },

  computed: {
    paginatedList() {
      return this.isBrowseCateg
        ? this.shuffledBooks(this.featuredItems).slice(0, 4)
        : this.shuffledBooks(this.featuredItems).slice(0, 16)
    },
    ...mapState(['books']),
    ...mapGetters(['featuredItems'])
  },


  methods: {
    // Fisher-Yates Sorting Algorithm to shuffle items of an array
    shuffledBooks(array) {
      for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
      }
      return array
    }
  },

  created() {
<<<<<<< HEAD
    // this.$store.dispatch("fetchBooks");
||||||| adf4f83
    this.$store.dispatch("fetchBooks");
=======
>>>>>>> main
    this.isBrowseCateg = this.$route.name === "browse"
  },

  watch: {
    $route(to) {
      this.isBrowseCateg = to.name === "browse"
    }
  }
}
</script>

<style scoped>
h3 {
  color: #e2907a;
  font-weight: 700;
}

.button-container {
  padding-right: 4rem;
}

button {
  height: 2.3rem;
  width: 100%;
  background: #e2907a;
  color: #fff;
  text-align: center;
  font-weight: 700;
  cursor: pointer;
  border-style: none;
  border-radius: 0.6rem;
  box-shadow: 0 3px #999;
}

button:hover {
  background-color: #e08167;
}

button:active {
  background-color: #e08167;
  box-shadow: 0 2px #666;
  transform: translateY(4px);
}

ul {
  margin-top: 30px;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  list-style-type: none;
}

li {
  text-align: center;
}

/* Responsive adjustments */
@media (max-width: 768px) {
  ul {
    grid-template-columns: repeat(2, 1fr);
    gap: 20px;
  }
}

@media (max-width: 480px) {
  ul {
    grid-template-columns: 1fr;
  }
}
</style>
