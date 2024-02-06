<template>
  <div id="books">
   
   <router-link :to="{
     name: 'detail',
     params: {
       sku: book.sku
    }}">
     <img v-bind:src="book.image"/>
     <VClamp :line-clamp="1" class="book-title">{{ book.title }}</VClamp>
   </router-link>
 
   <p class="book-author"><b>{{ book.author }}</b></p>       
   
   <div class="book-detail">
     <p>{{ book.publisher }}, {{ book.media }}</p>
    </div>
         
    <div class="book-price">
     <p><b>Price:</b> ${{ formattedPrice }}</p>
    </div>    
     
     <router-link :to="{
       name: 'detail',
       params: {
         sku: book.sku
           }
     }">
       <p class="read-more">READ MORE</p>
     </router-link> 
         
  </div>          
</template>        
    
<script>
 
import VClamp from "vue-clamp"

export default {
  name:"book-card",
  components: {
    VClamp
  },
  props: ["book"],
  computed: {
    formattedPrice: function () {
      return Number(this.book.price).toFixed(2)
    }
  }
}

</script>

 <style scoped>


#books img {
  width: 70%; 
  height: 70%; 
  object-fit: cover;
}

.book-title {
  white-space: nowrap; 
  overflow: hidden; 
  text-overflow: ellipsis; 
  width: 160px; 
  text-transform: uppercase
}


.book-author {
  margin: 0; 
}

.book-detail p, .book-price p {
  margin: 0;
}

@media (max-width: 768px) {
  #books img {
   width: 80%; 
   height: auto; 
  }

  .book-title {
    width: 120px; 
    font-size: 0.9em; 
  }
}

@media (max-width: 480px) {
  #books img {
    width: 100%; 
  }

  .book-title {
    width: auto; 
    font-size: 0.8em; 
  }
}

</style> 
