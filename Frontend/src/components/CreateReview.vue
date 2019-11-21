<template>
  <div class="action">
    <div class="col-lg-8 col-md-8" id="component">
      <h3>Rate your experience with {{name}}</h3>
      <div class="mt-10">
        <div class="stars" data-rating="3">
          <span class="star">&nbsp;</span>
          <span class="star">&nbsp;</span>
          <span class="star">&nbsp;</span>
          <span class="star">&nbsp;</span>
          <span class="star">&nbsp;</span>
        </div>
        <input
          type="text"
          class="single-textarea"
          placeholder="Leave a comment (optional)"
          onfocus="this.placeholder = ''"
          onblur="this.placeholder = 'Leave a comment (optional)'"
          v-model="comment"
        >
        <button
          style="margin-top: 50px;
  margin-bottom: 50px;"
          class="genric-btn primary circle"
          @click="createReview(comment, getCookie('userId'), id)"
        >Leave review</button>
      </div>
    </div>
  </div>
</template>

<script src='./js/createreview.js' >
</script>

<style scoped>
#component {
  margin-top: 200px;

  margin-left: auto;
  margin-right: auto;
}

.h3 {
  margin-bottom: 50px;
  text-align: left;
}applications

.star {
  color: #f90;
  font-size: 3rem;
  padding: 0 1rem; /* space out the stars */
  margin-bottom: 100px;
}
.star::before {
  content: "\2606"; /* star outline */
  cursor: pointer;
}
.star.rated::before {
  /* the style for a selected star */
  content: "\2605"; /* filled star */
}

.stars {
  counter-reset: rateme 0;
  font-size: 2rem;
  font-weight: 400;
  color: #0c2e60;
}
.star.rated {
  counter-increment: rateme 1;
}
.stars::after {
  content: counter(rateme) "/5";
}
</style>
