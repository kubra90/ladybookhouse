<template>
  <div class="about-us-container">
    <h4 class="title-lb">Welcome Lady Bookhouse</h4>
    <p>
      A sanctuary for book lovers of all ages. Our vast collection caters to a multitude of tastes, 
      from young readers and teenagers to adults and older readers, each book carefully chosen 
      to inspire, educate, and entertain.</p>
    <p>
    <p>
      Our selections cover an extensive array of genres, from social sciences and psychology 
      to natural sciences, literature, art, design, and cooking, not forgetting our delightful 
      range of children's books. We also take pride in offering best-selling books from renowned 
      authors around the globe, providing our customers with the chance to delve into popular 
      narratives loved by millions.
    </p>
    <p>
      Our commitment to you extends beyond the breadth of our book collection. We highly 
      value customer satisfaction, which is why we are dedicated to fast shipping and 
      maintaining the quality of our books.
    </p>
    <p class="subtitle"><strong>Explore Our Online Stores</strong></p>
    <div class="store-links">
      <div class="store">
        <a href="https://www.abebooks.com/servlet/StoreFrontDisplay?cid=86527136" target="_blank">
          <img class="img-icon-first" src="/assets/icons/Abebooks-Icon-Logo-Vector.svg-1-1.png" alt="Store 1 Icon">
        </a>
      </div>
      <div class="store">
        <a href="https://www.ebay.com/str/ladybookhouse" target="_blank">
          <img class="img-icon-second" src="/assets/icons/free-ebay-credit-debit-card-bank-transaction-32281.webp" alt="Store 2 Icon">
        </a>
      </div>
      <div class="store">
        <a href="https://www.biblio.com/bookstore/lady-bookhouse-belmont" target="_blank">
          <img class="img-icon-third" src="/assets/icons/Biblio-seal-logo.svg.png" alt="">
          <span></span>
        </a>
      </div>
      <div class="store">
        <a href="https://www.alibris.com/stores/lady0520" target="_blank">
          <img class="img-icon-forth" src="/assets/icons/alibris.jpeg" alt="">
          <span></span>
        </a>
      </div>
    </div>
  </div>
</template>

<style scoped>
.about-us-container {
  padding-top: 30px;
  padding-left: 130px;
  display:flex;
  flex-direction: column;
  padding-right:180px;
}

.img-icon-first, .img-icon-second, .img-icon-third, .img-icon-forth {
  width: 4.5em; 
  height: 3.8em; 
}

.subtitle {
  margin-bottom: 2em;
}

.store-links {
  display: flex;
  flex-direction: row;
  gap:1.6rem;
}
</style>