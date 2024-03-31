<template>
  <div class="container">
    <div class="row mt-5">
      <div class="col-md-6">
        <h3>New Arrivals</h3>
      </div>
      <div class="col-md-6 d-flex justify-content-md-end align-items-center">
        <router-link :to="{ name: 'featured-book-view' }">
          <div class="button-container">
            <button class="btn btn-sm btn-primary">See All New Arrivals</button>
          </div>
        </router-link>
      </div>
    </div>
    <ul class="row p-2">
      <book-card v-for="book in paginatedList" :book="book" :key="book.isbn" />
    </ul>
  </div>
</template>

<script>
import BookCard from "@/components/BookCard.vue"
import { mapState, mapGetters } from "vuex"

export default {
  name: "new-arrivals",

  components: {
    BookCard
  },

  computed: {
    paginatedList() {
      return this.newBookArrivals.slice(0, 4)
    },
    ...mapState(["books"]),
    ...mapGetters(["newBookArrivals"])
  },

  // methods: {
  //   ...mapActions(["fetchBooks"])
  // },

  // created() {
  //   this.fetchBooks()
  // }
}
</script>

<style scoped>
h3 {
  color: #e2907a;
  font-weight: 700;
}

.button-container {
  padding-right: 6rem;
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
