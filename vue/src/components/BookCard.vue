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
   white-space: nowrap; /* Keeps the text in a single line */
   overflow: hidden; /* Ensures that overflow text is hidden */
   text-overflow: ellipsis; /* Adds an ellipsis to indicate text overflow */
   width: 160px; /*Set a width that suits your layout */
   text-transform: uppercase
 }
 
 .book-author {
     margin: 0; /* Adjust top and bottom margin to reduce white space */
 }
 
 .book-detail p, .book-price p {
     margin: 0; /* Remove default margin to reduce white space */
 }
 
 
 /* Responsive adjustments */
 @media (max-width: 768px) {
   #books img {
     width: 80%; /* Increase width for smaller screens */
     height: auto; /* Maintain aspect ratio */
   }
 
   .book-title {
     width: 120px; /* Adjust width for smaller screens */
     font-size: 0.9em; /* Reduce font size */
   }
 }
 
 @media (max-width: 480px) {
 
   #books img {
     width: 100%; /* Full width on mobile */
   }
 
   .book-title {
     width: auto; /* Allow title to take full width */
     font-size: 0.8em; /* Further reduce font size */
   }
 }
   
 </style>


