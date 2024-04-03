<template>
    <div class="address-container">
    <HeaderPage/>
    <AddressBook class="address-body"/>
    <FooterPage/>
    </div>
</template>

<script>


import HeaderPage from '@/components/HeaderPage.vue';
import AddressBook from '@/components/AddressBook.vue';
import FooterPage from '@/components/FooterPage.vue';

export default {
    name: "address-book",
    components: {
       HeaderPage, AddressBook, FooterPage
    }
}
</script>


<style scoped>
/* .address-container {
  display:flex;
  flex-direction:column;
  height:100%;
 
} */

.address-body {
  flex:1;
 
}

</style>