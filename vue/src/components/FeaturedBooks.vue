<template>
  <div id="featured-books">
    <div class="title-link">
      <h3><strong>Featured Books</strong></h3>
      <router-link :to="{ name: 'featured-book-view' }" class="link"
        ><button class="button">See All Featured Books</button></router-link
      >
    </div>
    <ul>
      <book-card v-for="book in paginatedList" :book="book" :key="book.isbn" />
    </ul>
  </div>
</template>

<script>
import BookCard from "../components/BookCard.vue"
import {mapState, mapGetters} from "vuex"

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
    this.$store.dispatch("fetchBooks");
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
.title-link h3 {
  color: #e2907a;
  padding-left: 45px;
}

.title-link {
  display: flex;
  justify-content: space-between;
}

.link {
  position: relative;
  display: block;
  text-transform: uppercase;
  flex-direction: row;
  margin-top: 20px;
  margin-bottom: 10px;
  padding-right: 90px;
}

.button {
  margin: auto;
  height: 4rem;
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
.button:hover {
  background-color: #e08167;
}

.button:active {
  background-color: #e08167;
  box-shadow: 0 2px #666;
  transform: translateY(4px);
}

#featured-books ul {
  margin-top: 50px;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  /* gap: 10px; */
  list-style-type: none;
  padding-left: 45px;
}

#featured-books li {
  text-align: center;
}

#featured-books {
  padding: 45px 90px;
}

/* Responsive adjustments */
@media (max-width: 768px) {
  #featured-books ul {
    grid-template-columns: repeat(2, 1fr);
    gap: 20px;
  }

  #featured-books {
    padding: 20px;
  }
}

@media (max-width: 480px) {
  #featured-books ul {
    grid-template-columns: 1fr; /* 1 book per row for mobiles  */
  }
}
</style>
