<template>
  <div class="container">
    <div class="row align-items-center m-4 title">
      <div class="col-6">
        <h3><strong>New Arrivals</strong></h3>
      </div>
      <div class="col-6 text-end button">
        <router-link
          :to="{ name: 'new-arrivals-view' }"
          class="btn btn-primary btn-sm btn-custom"
          >See New Arrivals</router-link
        >
      </div>
    </div>
    <div class="book-cards">
      <ul class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-3">
        <book-card
          v-for="book in paginatedList"
          :book="book"
          :key="book.isbn"
        />
      </ul>
    </div>
  </div>
</template>

<script>
import BookCard from "@/components/BookCard.vue"
import { mapState, mapGetters, mapActions } from "vuex"

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

  methods: {
    ...mapActions(["fetchBooks"])
  },

  created() {
    this.fetchBooks()
  }
}
</script>

<style scoped>
h3 {
  color: #e2907a;
}

.button {
  padding-right: 4rem;
}

.btn-custom {
  background: #e2907a;
  color: #fff;
  text-align: center;
  font-weight: 700;
  cursor: pointer;
  border-style: none;
  border-radius: 0.6rem;
  box-shadow: 0 3px #999;
}
.btn-custom:hover {
  background-color: #e27253;
}

.btn-custom:active {
  background-color: #d16e53;
  box-shadow: 0 2px #666;
  transform: translateY(4px);
}
</style>
