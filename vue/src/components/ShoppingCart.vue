<template>
    <div class="cart-container">
       <div v-for="(book, index) in cartBooks" :key="index" class="book-item">
         <img :src="book.image" alt="Book Image" class="book-image"/>
         <div class="book-info">
                <h3>{{ book.title }}</h3>
                <p>Author: {{ book.author }}</p>
                <p>Price: ${{ book.price }}</p>
          </div>
       </div>
    </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
    name: "shopping-cart",
    data() {
       return {
          
       } 
    },
    computed: {
        ...mapState(['cartBooks'])
    },
   
}

</script>

<style scoped>
.cart-container {
    display: flex;
    flex-direction: column;
}

.book-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 20px; /* Space between book items */
}

</style>