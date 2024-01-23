<template>
    <div>
        here is the saved books
        <p>Token : {{ this.$store.state.token }} {{ this.$store.state.localStorage }}</p>
    </div>
</template>
<script>
export default {
    name: "saved-books-page",
    components: {
        
    }
}
</script>
<style scoped>

</style>