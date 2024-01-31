<template>
  <div>
    <p class="contact-header">Contact Us</p>
    <div class="contact-address">
    <p><strong>Lady Bookhouse</strong></p>
    <p>375 Acorn Park Dr. 1310</p>
    <p>Belmont, MA 02478</p>
      </div>
    <p class="mail-address"><strong>Mail: </strong><a class="mail-address-link" href="mailto:ladybookhouse@gmail.com">ladybookhouse@gmail.com</a></p>
  </div>
</template>
<script>
</script>

<style scoped>
.contact-header {
  margin-top: 4%;
  margin-left: 9%;
  margin-bottom:4%;
  font-size:39px;
  color:brown;
  font-family: "PT Sans", sans-serif;
}

.contact-address {
  margin-left:9%;
  margin-top:2%;
  margin-bottom: 3%;
  display: flex;
  flex-direction: column;
}

.contact-address p {
  margin: 0; /* Removes default margin */
  padding: 0; /* Optional: if you want to remove default padding as well */
}

.mail-address{
  margin-left: 9%;
  margin-bottom:4%;
}

.mail-address-link{
  color:brown;
}
</style>