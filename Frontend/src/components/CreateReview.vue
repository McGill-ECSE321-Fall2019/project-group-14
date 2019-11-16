<template>
  <div class="col-lg-8 col-md-8" id="component">
    <h4>Reviewing</h4>
    <div class="mt-10">
      <div class="rating">
        <span>
          <input type="radio" name="rating" id="str5" value="5" />
          <label for="str5"></label>
        </span>
        <span>
          <input type="radio" name="rating" id="str4" value="4" />
          <label for="str4"></label>
        </span>
        <span>
          <input type="radio" name="rating" id="str3" value="3" />
          <label for="str3"></label>
        </span>
        <span>
          <input type="radio" name="rating" id="str2" value="2" />
          <label for="str2"></label>
        </span>
        <span>
          <input type="radio" name="rating" id="str1" value="1" />
          <label for="str1"></label>
        </span>
      </div>
      <textarea
        class="single-textarea"
        placeholder="Leave a comment (optional)"
        onfocus="this.placeholder = ''"
        onblur="this.placeholder = 'Leave a comment (optional)'"
        required v-model="comment"
      ></textarea>
    </div>
    <div>
      <a class="btn_1" href="#" @click="createReview(userRating, comment, from, to)">Leave review {{ name }} </a>
    </div>
  </div>
</template>

<script src='./createreview.js'>


</script>

<style scoped>
.btn_1 {
  margin-top: 50px;
  margin-bottom: 50px;
}

#component {
  margin-top: 200px;

  margin-left: auto;
  margin-right: auto;
}

.h4 {
    margin-bottom: 50px;
    text-align: left
}

.rating {
  float: left;
  position: relative;
  margin-top: 30px;
  margin-bottom: 30px;
}
.rating span {
  float: right;
  position: relative;
  margin-right: 20px;
}
.rating span input {
  position: absolute;
  top: 0px;
  left: 0px;
  opacity: 0;
}
.rating span label {
  display: inline-block;
  width: 30px;
  height: 30px;
  text-align: center;
  color: #fff;
  background: #ccc;
  font-size: 30px;
  margin-right: 2px;
  line-height: 30px;
  border-radius: 50%;
  -webkit-border-radius: 50%;
}
.rating span:hover ~ span label,
.rating span:hover label,
.rating span.checked label,
.rating span.checked ~ span label {
  background: #f90;
  color: #fff;
}
</style>
