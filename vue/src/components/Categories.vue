<template>
 <div>
  <ul>
    <!-- Create router links for each category -->
   <li v-for="category in categoryArr" :key="category">{{ category }}
    <!-- Show each book by category under this section dynamically -->
    <div v-for="book in filterBooksByCat('History&Memory')" :key="book.isbn">{{ book.author }} {{ book.category }}</div>
   </li>
  </ul>
 </div> 
</template>

<script>

import {mapState, mapActions, mapGetters} from "vuex"

export default {
  name: "categories",

  data() {
    return {
        categoryArr: ["History&Memory", "Science", 
        "Other", "Bestseller", "Religion&Philosophy", 
        "Literature", "Social Science", "Cookbook", "Kids", 
        "Design", "Technology", "Art", "Language"
        ]
    }  
},

  computed: {
     ...mapGetters(["filterBooksByCat"]),
    ...mapState(["books"])
},
  methods: {
      ...mapActions(["fetchBooks"])
    },

  created() {
        this.fetchBooks()
      }

}

</script>
