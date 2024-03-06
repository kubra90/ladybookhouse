<template>
<div>
  <HeaderPage/> 
  <BookList/>
  <FooterPage/>
</div>    
</template>

<script>
import HeaderPage from '@/components/HeaderPage.vue';
import FooterPage from '@/components/FooterPage.vue';

export default {
  name: "book",
  components: {HeaderPage, FooterPage}
}
</script>
