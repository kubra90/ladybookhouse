New book card


<template>
  <div class="book-card">
    <router-link :to="{ name: 'detail', params: { sku: book.sku }}" style="text-decoration: none;">
    
      <img v-bind:src="book.image" class="img-fluid"/>
    
   
      <b><VClamp :line-clamp="1" class="book-title">{{ book.title }}</VClamp></b>
    
    </router-link>
    <p class="book-author"><b>{{ book.author }}</b></p>
    <div class="book-detail">
      <p>{{ book.publisher }}, {{ book.media }}</p>
    </div>
    <div class="book-price">
      <p><b>Price:</b> ${{ formattedPrice }}</p>
    </div>
    <router-link :to="{ name: 'detail', params: { sku: book.sku }}" style="text-decoration: none;">
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


.book-card {
  display: flex;
  flex-direction: column;
  justify-content:center;
  min-height: 350px; /* Ensures uniform card height */
}

.book-card img {
  width:80%;

  height: auto;
  object-fit: cover;
  max-height:350px;
  max-width:300px;
}

.book-card .book-title,
.book-card .book-author,
.book-card .book-price {
  margin-bottom: 0;
  margin-top:0;
  padding-top:0;
  padding-bottom:0;
}

.book-price {
  padding:0;
  margin:0;
}

.book-card .book-author,
.book-card .book-title {
  margin: 0; 
  /* new css for wrapping the author */
  overflow: hidden; 
  text-overflow: ellipsis; 
  width:200px; 
  white-space:nowrap;

}

.book-card  .book-title, .read-more {
  color:#87271a;
  text-decoration: none;
  text-transform: uppercase;
}

/* new css */

@media (max-width: 468px) {

.book-card .book-title,
.book-card .book-author {
  width:150px;
}

  }


</style>
