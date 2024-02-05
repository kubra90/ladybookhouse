<template>
 <div>
  <header-page />
  <ul>
   <router-link :to="{ path:`/${category}` }" v-for="category in categories" :key="category" ><p>{{ category }}</p></router-link>
  </ul>
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
     categories: ["History&Memory", "Science", 
     "Other", "Bestseller", "Religion&Philosophy", 
     "Literature", "Social Science", "Cookbook", "Kids", 
     "Design", "Technology", "Art", "Language"
    ]
  }  
},
  components: {
    HeaderPage, FooterPage
  }
    
}
</script>