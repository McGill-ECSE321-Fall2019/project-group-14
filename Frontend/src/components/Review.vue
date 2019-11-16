<template>
  <div class="col-lg-8 col-md-8" id="component">
    <div class="col-md-4 mt-sm-30" id = "list">
      <h3 class="mb-20">Unreviewed Sessions</h3>
            <h6 class="mb-20">You have 24 hours to review a session with a student after it ended.</h6>
      <div class>
        <ul class="unordered-list">
          <li>Fta Keys</li>
          <li>For Women Only Your Computer Usage</li>
          <li>Dealing With Technical Support 10 Useful Tips</li>
          <li>Make Myspace Your Best Designed Space</li>
          <li>Cleaning And Organizing Your Computer</li>
        </ul>
      </div>
    </div>
    <h3 class="mb-30" id="reviews">Your reviews</h3>
    <a class="btn_1" href="#">Apply!</a>
  </div>
</template>

<script src="./review.js">
</script>

<style scoped>
h3 {
  margin-top: 200px;
  text-align: left;
}
h6 {
  display: inline-block;
  text-align: left;
}
#component {
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 10px;
}

#reviews {
  margin-top: 100px;

}
ul {
      margin-top: 50px;
  display: inline-block;
  text-align: left;
}

.btn_1 {
  margin-top: 50px;
  margin-bottom: 50px;
}
</style>