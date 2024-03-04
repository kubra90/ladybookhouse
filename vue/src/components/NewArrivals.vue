<template>
  <div id="new-arrivals">
    <div class="title-link">
      <h3><strong>New Arrivals</strong></h3>
      <router-link :to="{ name: 'new-arrivals-view' }" class="link"><button class="button">See All New Arrivals</button></router-link>
    </div>
    <ul>
     <book-card v-for="book in paginatedList" :book="book" :key="book.isbn" />
    </ul>
  </div>
</template>

<script>

import BookCard from "@/components/BookCard.vue"
import {mapState, mapGetters, mapActions} from "vuex"

export default {
  name: "new-arrivals",

  components: {
    BookCard
  },
  
  computed: {
    paginatedList() {
      return this.newBookArrivals.slice(0, 4)
    },
    // ...mapState(["newArrivals"]),
     ...mapState(['books']),
     ...mapGetters(['newBookArrivals'])
  },
  
  methods: {
    // ...mapActions(["fetchNewArrivals"])
    ...mapActions(['fetchBooks'])

  },
  
  created() {
    // this.fetchNewArrivals()
    this.fetchBooks()
  }
}

</script>

<style scoped>
.title-link h3 {
  padding-left: 45px;
  color: #e2907a;
}

.title-link {
  display: flex;
  justify-content: space-between;
}

.link {
  position: relative;
  display: block;
  text-transform: uppercase;
  flex-direction:row;
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
.button:hover {background-color: #e08167}

.button:active {
  background-color: #e08167;
  box-shadow: 0 2px #666;
  transform: translateY(4px);
}

#new-arrivals ul {
  margin-top:50px;
  display: grid;
  grid-template-columns: repeat(4, 1fr); /* 4 books per row */
  gap: 10px;
  list-style-type: none;
  padding-left: 45px;
 }

#new-arrivals li {
  text-align: center;
}

#new-arrivals {
  padding: 45px 90px;
}

/* Responsive adjustments */
@media (max-width: 768px) {
  #new-arrivals ul {
    grid-template-columns: repeat(2, 1fr); 
    gap: 20px;
  }

  #new-arrivals {
    padding: 20px;
  }
}

@media (max-width: 480px) {
  #new-arrivals ul {
    grid-template-columns: 1fr; /* 1 book per row for mobiles */
  }
}

</style>











