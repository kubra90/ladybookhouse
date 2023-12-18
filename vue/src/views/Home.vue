<template>
  <div class="home">
    <!-- <h1>Home</h1> -->
    <!-- <p>You must be authenticated to see this</p> -->
    <div class="your-account">
      <!-- decide top right items -->
      <h4>Your Account</h4>  
    </div>
    <h1>Lady Bookhouse</h1>
    <div ><book/></div>
  </div>
  
</template>

<script>
import book from "../views/Book.vue"
export default {
  name: "home",
  components: {
    book
  }
};
</script>

<style scoped>
.your-account {
  color: orange;
  text-align: left;
  text-size-adjust: 30px;
  font-family: sans-serif;
  font-size: 7px;
}

h1 {
  color: orange
}


</style>
